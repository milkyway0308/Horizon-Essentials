package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandSay extends CommandsRegistry implements CommandExecutor {
	public final String Developer = "이 클래스는 KiwiYou님에 의해 작성되었습니다.";
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //버킷에서도 당연히 사용 가능
        if (!PlayerUtil.hasPermission(sender, PermissionType.CommandSay))
        {
            super.m.sendMessage(sender, "명령어 권한 부족");
            return false;
        }
        if (args.length < 1)
        {
            super.m.sendMessage(sender, "명령어 사용법",
                    new DoubleString("[사용법]", String.format("/%s [내용]", label)));
            return false;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; ++i)
            if (sb.append(args[i]) != null && i < args.length - 1) sb.append(" ");
        String c = sb.toString();
        // &을 §로 치환해 줍니다
        // 정규식 사용: &[0-9a-fl-o]
        c = c.replaceAll("&([0-9a-fl-o])", "§$1");
        Bukkit.broadcastMessage(super.m.getMessage("서버공지").replace("[내용]", c));
        return true;
    }
}