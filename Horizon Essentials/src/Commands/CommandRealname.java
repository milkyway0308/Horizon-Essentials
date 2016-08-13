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
				super.m.sendMessage(sender,"��ɾ� ���� ����");
				return false;
			}
			if(args.length <= 0)
			{
				super.m.sendMessage(sender,"��ɾ� ����",new DoubleString("[����]","/" + label + " [�г���]"));
				return false;
			}
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < args.length; ++i)
	            if (sb.append(args[i]) != null && i < args.length - 1) sb.append(" ");
	        String c = sb.toString().replace("&", "��");
			if(DataManager.unrealname.containsKey(c)){
				super.m.sendMessage(sender, "�̸� ��ȸ",DataUtil.asArray(new DoubleString("[���]",c),new DoubleString("[�г���]",DataUtil.ArrayToString(DataManager.unrealname.get(c)))));
			}else{
				super.m.sendMessage(sender, "�̸� ������",DataUtil.asArray(new DoubleString("[���]",c),new DoubleString("[�г���]",c)));
			}
			
		}
		return false;
	}
}