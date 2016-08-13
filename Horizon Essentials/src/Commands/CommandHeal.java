package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandHeal extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player)
		{
			if(args.length <= 0)
			{
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandHeal))
				{
					((Player)sender).setHealth(((Player)sender).getMaxHealth());
					((Player)sender).setFoodLevel(20);
					PlayerUtil.removeBuff((Player)sender);
					((Player)sender).setFireTicks(0);
					super.m.sendMessage(sender, "ü�� ȸ��");
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}else if(args.length == 1){
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandHealOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("��� �÷��̾� ��������").replace("[���]", args[0]));
						return false;
					}
					p.setHealth(p.getMaxHealth());
					p.setFoodLevel(20);
					PlayerUtil.removeBuff(p);
					p.setFireTicks(0);
					super.m.sendMessage(p, "ü�� ȸ��");
					super.m.sendMessage(sender, "��� �÷��̾� ü�� ȸ��",new DoubleString("���",p.getName()));
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}else if(args.length == 2){
				boolean b = false;
					if(Utility.DataUtil.isBoolean(args[1]))
						b = Boolean.parseBoolean(args[1]);
					else
					{
						sender.sendMessage(super.m.getMessage("��ɾ� ����").replace("[����]", "/" + label + " [�÷��̾�] <�޽��� ��� ����(true/false)>"));
						return false;
					}
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandHealOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("��� �÷��̾� ��������").replace("[���]", args[0]));
						return false;
					}
					p.setHealth(p.getMaxHealth());
					p.setFireTicks(0);
					p.setFoodLevel(20);
					PlayerUtil.removeBuff(p);
					super.m.sendMessage(p, "ü�� ȸ��");
					if(b)
						super.m.sendMessage(sender, "��� �÷��̾� ü�� ȸ��",new DoubleString("[���]",p.getName()));
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}else if(args.length == 3){
				boolean b = false;
				boolean b2 = false;
				if(Utility.DataUtil.isBoolean(args[1]) && Utility.DataUtil.isBoolean(args[2]))
				{
					b = Boolean.parseBoolean(args[1]);
					b2 = Boolean.parseBoolean(args[2]);
				}
				else
				{
					sender.sendMessage(super.m.getMessage("��ɾ� ����").replace("[����]", "/" + label + " [�÷��̾�] <�޽��� ��� ����(true/false)> <��� �޽��� ��� ����(true/false>"));
					return false;
				}
				
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandHealOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("��� �÷��̾� ��������").replace("[���]", args[0]));
						return false;
					}
					p.setFireTicks(0);
					p.setHealth(p.getMaxHealth());
					PlayerUtil.removeBuff(p);
					p.setFoodLevel(20);
					if(b)
						super.m.sendMessage(sender, "��� �÷��̾� ü�� ȸ��",new DoubleString("[���]",p.getName()));
					if(b2)
						super.m.sendMessage(p, "ü�� ȸ��");
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}
		}else{
			
			
			if(args.length <= 0)
			{
				sender.sendMessage(super.m.getMessage("��ɾ� ����").replace("[����]", "/" + label + " [�÷��̾�]"));
			}else if(args.length == 1){
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("��� �÷��̾� ��������").replace("[���]", args[0]));
						return false;
					}
					p.setHealth(p.getMaxHealth());
					PlayerUtil.removeBuff(p);
					p.setFireTicks(0);
					p.setFoodLevel(20);
					super.m.sendMessage(p, "ü�� ȸ��");
					super.m.sendMessage(sender, "��� �÷��̾� ü�� ȸ��");
			}else if(args.length == 2){
				boolean b = false;
				if(Utility.DataUtil.isBoolean(args[1]))
					b = Boolean.parseBoolean(args[1]);
				else
				{
					sender.sendMessage(super.m.getMessage("��ɾ� ����").replace("[����]", "/" + label + " [�÷��̾�] <�޽��� ��� ����(true/false)>"));
					return false;
				}
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("��� �÷��̾� ��������").replace("[���]", args[0]));
						return false;
					}
					p.setHealth(p.getMaxHealth());
					PlayerUtil.removeBuff(p);
					p.setFoodLevel(20);
					p.setFireTicks(0);
					super.m.sendMessage(p, "ü�� ȸ��");
					if(b)
						super.m.sendMessage(sender, "��� �÷��̾� ü�� ȸ��",new DoubleString("[���]",p.getName()));
				
			}else if(args.length == 3){
				boolean b = false;
				boolean b2 = false;
				if(Utility.DataUtil.isBoolean(args[1]) && Utility.DataUtil.isBoolean(args[2]))
				{
					b = Boolean.parseBoolean(args[1]);
					b2 = Boolean.parseBoolean(args[2]);
				}
				else
				{
					sender.sendMessage(super.m.getMessage("��ɾ� ����").replace("[����]", "/" + label + " [�÷��̾�] <�޽��� ��� ����(true/false)> <��� �޽��� ��� ����(true/false>"));
					return false;
				}
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("��� �÷��̾� ��������").replace("[���]", args[0]));
						return false;
					}
					p.setHealth(p.getMaxHealth());
					p.setFoodLevel(20);
					p.setFireTicks(0);
					PlayerUtil.removeBuff(p);
					if(b)
						super.m.sendMessage(sender, "��� �÷��̾� ü�� ȸ��");
					if(b2)
						super.m.sendMessage(p, "ü�� ȸ��",new DoubleString("[���]",p.getName()));
			}
		}
		return false;
	}

}
