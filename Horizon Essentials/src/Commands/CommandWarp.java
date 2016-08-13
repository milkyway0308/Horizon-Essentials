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

public class CommandWarp extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		System.out.println(args.length);
		if(sender instanceof Player){
			if(args.length <= 0)
			{
				
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]" ,"/" + label + " [�����̸�]"));
			}
			else if(args.length == 1)
			{
				if(PlayerUtil.hasPermission(sender, PermissionType.CommandWarp) || sender.hasPermission(PermissionType.CommandWarp.getValue() + "." + args[0]))
				{
				if(!DataManager.warps.containsKey(args[0]))
					super.m.sendMessage(sender, "���� ������");
				else{
					super.m.sendMessage(sender, "����",new DoubleString("[����]",args[0]));
					 ((Player)sender).teleport(DataManager.warps.get(args[0]).getLocation());
				}
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}else if(args.length >= 2){
				if(PlayerUtil.hasPermission(sender, PermissionType.CommandOtherWarp) || sender.hasPermission(PermissionType.CommandOtherWarp.getValue() + "." + args[0]))
				{
				Player p = Bukkit.getPlayer(args[1]);
				if(p == null)
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[1]));
				else{
					if(!DataManager.warps.containsKey(args[0]))
						super.m.sendMessage(sender, "���� ������");
					else{
					boolean msg = true;
					if(args.length == 3)
						msg = Boolean.parseBoolean(args[2]);
					if(msg)
						super.m.sendMessage(p, "����",new DoubleString("[����]",args[0]));
					p.teleport(DataManager.warps.get(args[0]).getLocation());
					}
					
				}
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}
		}else{
			if(args.length <= 1)
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�����̸�] [�÷��̾�]"));
			else{
				Player p = Bukkit.getPlayer(args[1]);
				if(p == null)
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[1]));
				else{
					if(!DataManager.warps.containsKey(args[0]))
						super.m.sendMessage(sender, "���� ������");
					else{
					boolean msg = true;
					if(args.length == 3)
						msg = Boolean.parseBoolean(args[2]);
					if(msg)
						super.m.sendMessage(p, "����",new DoubleString("[����]",args[0]));
					p.teleport(DataManager.warps.get(args[0]).getLocation());
					}
					
				}
			}
		}
			
	return false;
	}
}