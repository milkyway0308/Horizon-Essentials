package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandRealname extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player)
		{
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandRealName)){
				super.m.sendMessage(sender,"명령어 권한 부족");
				return false;
			}
			if(args.length <= 0)
			{
				super.m.sendMessage(sender,"명령어 사용법",new DoubleString("[사용법]","/" + label + " [닉네임]"));
				return false;
			}
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < args.length; ++i)
	            if (sb.append(args[i]) != null && i < args.length - 1) sb.append(" ");
	        String c = sb.toString().replace("&", "§");
			if(DataManager.unrealname.containsKey(c)){
				super.m.sendMessage(sender, "이름 조회",DataUtil.asArray(new DoubleString("[대상]",c),new DoubleString("[닉네임]",DataUtil.ArrayToString(DataManager.unrealname.get(c)))));
			}else{
				super.m.sendMessage(sender, "이름 미존재",DataUtil.asArray(new DoubleString("[대상]",c),new DoubleString("[닉네임]",c)));
			}
			
		}
		return false;
	}
}