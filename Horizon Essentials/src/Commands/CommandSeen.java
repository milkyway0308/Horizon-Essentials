package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.DoubleString;
import DataUtil.EssentialsPlayer;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandSeen extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandSeen))
		{
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(args.length <= 0)
			super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�/������]"));
		else{
			if(args[0].split("\\.").length == 4)
			{
				if(DataManager.connectedIPs.containsKey(args[0])){
					super.m.sendMessage(sender, "������ ��ȸ",DataUtil.asArray(new DoubleString("[������]",args[0]),new DoubleString("[�÷��̾�]",DataUtil.ArrayToString(DataManager.connectedIPs.get(args[0])))));
				}else{
					super.m.sendMessage(sender, "������ ������",new DoubleString("[������]",args[0]));
				}
			}else{
				if(DataManager.players.containsKey(Bukkit.getOfflinePlayer(args[0])))
				{
					EssentialsPlayer p = DataManager.players.get(Bukkit.getOfflinePlayer(args[0]));
					if(p.last <= -1 || p.ip.equals("0.0.0.0"))
						super.m.sendMessage(sender, "�г��� ������",new DoubleString("[���]",args[0]));
					else{
						if(Bukkit.getOfflinePlayer(args[0]).isOnline()){
							super.m.sendMessage(sender, "�г��� ��ȸ",DataUtil.asArray(new DoubleString("[���]",args[0]),new DoubleString("[�ð�]",DataUtil.IntegerToTime((System.currentTimeMillis() - (long)p.last) / 1000)),new DoubleString("[������]",p.ip)));
						}else{
							super.m.sendMessage(sender, "�г��� ��ȸ ��������",DataUtil.asArray(new DoubleString("[���]",args[0]),new DoubleString("[�ð�]",DataUtil.IntegerToTime((System.currentTimeMillis() - (long)p.last) / 1000)),new DoubleString("[������]",p.ip)));
						}
					}
				}else{
					super.m.sendMessage(sender, "�г��� ������",new DoubleString("[���]",args[0]));
				}
			}
		}
		return false;
	}

}
