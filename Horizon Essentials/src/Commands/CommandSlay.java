package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandSlay extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandSlay))
			{
				if(sender instanceof Player)
				{
					if(args.length <= 0)
					{
						super.m.sendMessage(sender, "����",new DoubleString("[���]",DataManager.getName(sender.getName())));
						((Player)sender).setHealth(0);
					}else{
						if(Bukkit.getPlayer(args[0]) == null)
							super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",DataManager.getName(args[0])));
						else{
							Bukkit.getPlayer(args[0]).setHealth(0);
							super.m.sendMessage(sender, "���",new DoubleString("[���]",DataManager.getName(args[0])));
							super.m.sendMessage(Bukkit.getPlayer(args[0]), "����",new DoubleString("[���]",DataManager.getName(args[0])));
						}
					}
				}else{
					if(args.length <= 0)
						super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�]"));
					else{
						if(Bukkit.getPlayer(args[0]) == null)
							super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",DataManager.getName(args[0])));
						else{
							Bukkit.getPlayer(args[0]).setHealth(0);
							super.m.sendMessage(sender, "���",new DoubleString("[���]",DataManager.getName(args[0])));
							super.m.sendMessage(Bukkit.getPlayer(args[0]), "����",new DoubleString("[���]",DataManager.getName(args[0])));
						}
					}
				}
					
			}else{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
			}
			return false;
	}
}