package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.Event.Horizon_MotdEvent;
import Utility.PlayerUtil;

public class CommandSetMotd  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandChangeMotd))
			{
				if(args.length <= 0)
				{
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [add/remove/list/reload]"));
				}else{
					switch(args[0])
					{
					case "add":
						if(args.length <= 1)
						{
							super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " add [motd ����]"));
							break;
						}
						StringBuilder b = new StringBuilder(args[1]);
						for(int i = 2;i < args.length;i++)
							b.append(" " + args[i]);
						Horizon_MotdEvent.motd.add(b.toString().replace("&", "��"));
						super.m.sendMessage(sender, "���� motd �߰�");
						break;
					case "remove":
						if(args.length <= 1)
						{
							super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " remove [motd ��ȣ]"));
							break;
						}
						int a = 0;
						try{
						a = Integer.parseInt(args[1]);
						if(a <= 0)
						{
							super.m.sendMessage(sender, "�Ű� ���� ����");
							return false;
						}
						}catch(Exception e){super.m.sendMessage(sender, "�߸��� �Ű� ����"); return false;}
						if(Horizon_MotdEvent.motd.size() < a)
						{
							sender.sendMessage("��c��ϵ� motd���� ������ motd�� ��ȣ�� �� �����ϴ�.");
							return false;
						}
						Horizon_MotdEvent.motd.remove(a-1);
						super.m.sendMessage(sender, "���� motd ����");
						break;
					case "list":
						sender.sendMessage("��7  Registered Motd List");
						for(int i = 0;i < Horizon_MotdEvent.motd.size();i++)
						{
							sender.sendMessage("��7[" + (i+1) + "] ��f " + Horizon_MotdEvent.motd.get(i));
						}
						break;
					case "reload":
						Horizon_Essentials.Event.Horizon_MotdEvent.Load();
						sender.sendMessage("��6MOTD�� �ٽ� �ҷ��Խ��ϴ�.");
						break;
					}
				}
			}else{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
			}
	return false;
	}
}