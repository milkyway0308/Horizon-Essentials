package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandSay extends CommandsRegistry implements CommandExecutor {
	public final String Developer = "�� Ŭ������ KiwiYou�Կ� ���� �ۼ��Ǿ����ϴ�.";
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //��Ŷ������ �翬�� ��� ����
        if (!PlayerUtil.hasPermission(sender, PermissionType.CommandSay))
        {
            super.m.sendMessage(sender, "��ɾ� ���� ����");
            return false;
        }
        if (args.length < 1)
        {
            super.m.sendMessage(sender, "��ɾ� ����",
                    new DoubleString("[����]", String.format("/%s [����]", label)));
            return false;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; ++i)
            if (sb.append(args[i]) != null && i < args.length - 1) sb.append(" ");
        String c = sb.toString();
        // &�� �׷� ġȯ�� �ݴϴ�
        // ���Խ� ���: &[0-9a-fl-o]
        c = c.replaceAll("&([0-9a-fl-o])", "��$1");
        Bukkit.broadcastMessage(super.m.getMessage("��������").replace("[����]", c));
        return true;
    }
}