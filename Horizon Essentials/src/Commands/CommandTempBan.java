package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.EssentialsPlayer;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandTempBan extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTempBan))
			{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
			}
			if(args.length <= 1){
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] [�ð�(����)]"));
			}else{
				try{
					int a = Integer.parseInt(args[1]);
					if(a <= 0)
						super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");
					else{
						if(!DataManager.players.containsKey(Bukkit.getOfflinePlayer(args[0])))
							DataManager.players.put(Bukkit.getOfflinePlayer(args[0]), new EssentialsPlayer(args[0]));
							DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).tempBan = (double)System.currentTimeMillis() + (double)a * 1000;
							String bancause = "��c�������� ���ܵǾ����ϴ�.";
							if(args.length >= 3)
							{
								StringBuilder b = new StringBuilder(args[2]);
								for(int i = 3;i < args.length;i++)
									b.append(" " + args[i]);
								bancause = b.toString();
							}
							DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).banCause = bancause;
							DataUtil.OperatorMessage(super.m.getMessage("�ð���").replace("[�ð�]", DataUtil.IntegerToTime(a)).replace("[������]",sender.getName()).replace("[���]", args[0]).replace("/*nextline*/", "\n"));
							Bukkit.getConsoleSender().sendMessage(super.m.getMessage("�ð���").replace("[�ð�]", DataUtil.IntegerToTime(a)).replace("[������]",sender.getName()).replace("[���]", args[0]).replace("/*nextline*/", "\n"));
							if(Bukkit.getOfflinePlayer(args[0]).isOnline())
								((Player)Bukkit.getOfflinePlayer(args[0])).kickPlayer(super.m.getMessage("�ð� �� �޽���").replace("/*nextline*/", "\n").replace("[�ð�]", DataUtil.IntegerToTime(a)).replace("[����]", DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).banCause));

					}
				}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");}
			}
		return false;
	}
}