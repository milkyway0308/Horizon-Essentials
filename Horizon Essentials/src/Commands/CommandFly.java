package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandFly extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandFly))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if(sender instanceof Player){
				if(args.length <= 0)
				{
					((Player)sender).setAllowFlight(!((Player)sender).getAllowFlight());
					if(((Player)sender).getAllowFlight())
						super.m.sendMessage(sender, "�ö��� Ȱ��ȭ");
					else
						super.m.sendMessage(sender, "�ö��� ��Ȱ��ȭ");
				}else{
					if(Bukkit.getPlayer(args[0]) == null){
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
					}else{
						if(Bukkit.getPlayer(args[0]) == null){
							super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
							return false;
						}
						Player p = Bukkit.getPlayer(args[0]);
						boolean msg = true;
						boolean fly = !p.getAllowFlight();
						if(args.length == 2)
							fly = Boolean.parseBoolean(args[1].replaceAll("enable|Ȱ��ȭ", "true").replaceAll("disable|��Ȱ��ȭ", "false"));
						else if(args.length == 3){
							fly = Boolean.parseBoolean(args[1].replaceAll("enable|Ȱ��ȭ", "true").replaceAll("disable|��Ȱ��ȭ", "false"));
							msg = Boolean.parseBoolean(args[2].replaceAll("enable|Ȱ��ȭ", "true").replaceAll("disable|��Ȱ��ȭ", "false"));
						}
						p.setAllowFlight(fly);
						if(msg)
						{
							super.m.sendMessage(sender,"Ÿ�� �ö��� Ȱ��ȭ",new DoubleString("[���]",args[0]));
							if(fly)
								super.m.sendMessage(p, "�ö��� Ȱ��ȭ");
							else
								super.m.sendMessage(p, "�ö��� ��Ȱ��ȭ");
						}
					}
				}
			}else{
				if(args.length <= 0)
				{
					if(DataUtil.isAlpha(label)){
						super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] <enable/disable> <�޽��� ��� ����(true/false)>"));
					}else{
						super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] <Ȱ��ȭ/��Ȱ��ȭ> <�޽��� ��� ����(true/false)>"));
					}
				}
				else{
					if(Bukkit.getPlayer(args[0]) == null){
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
						return false;
					}
					Player p = Bukkit.getPlayer(args[0]);
					boolean msg = true;
					boolean fly = !p.getAllowFlight();
					if(args.length == 2)
						fly = Boolean.parseBoolean(args[1].replaceAll("enable|Ȱ��ȭ", "true").replaceAll("disable|��Ȱ��ȭ", "false"));
					else if(args.length == 3){
						fly = Boolean.parseBoolean(args[1].replaceAll("enable|Ȱ��ȭ", "true").replaceAll("disable|��Ȱ��ȭ", "false"));
						msg = Boolean.parseBoolean(args[2].replaceAll("enable|Ȱ��ȭ", "true").replaceAll("disable|��Ȱ��ȭ", "false"));
					}
					p.setAllowFlight(fly);
					if(msg)
					{
						
						if(fly)
						{
							super.m.sendMessage(p, "�ö��� Ȱ��ȭ");
							super.m.sendMessage(sender,"Ÿ�� �ö��� Ȱ��ȭ",new DoubleString("[���]",args[0]));
						}
						else
							super.m.sendMessage(p, "�ö��� ��Ȱ��ȭ");
					}
				}
			}
		return false;
	}
}