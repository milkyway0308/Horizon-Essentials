package Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandGamemode extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandsGameMode))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if(sender instanceof Player)
			{
				if(args.length <= 0)
				{
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [0/1/2]"));
				}else if(args.length == 1)
				{
					int gm = 0;
					try{
						gm = Integer.parseInt(args[0]);
					}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
					if(gm <= -1)
					{
						super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [0/1/2]"));
						return false;
					}
					switch(gm)
					{
					case 0:
						((Player)sender).setGameMode(GameMode.SURVIVAL);
						super.m.sendMessage(sender, "���Ӹ�� �����̹�");
						break;
					case 1:
						((Player)sender).setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(sender, "���Ӹ�� ũ������Ƽ��");
						break;
					case 2:
						((Player)sender).setGameMode(GameMode.ADVENTURE);
						super.m.sendMessage(sender, "���Ӹ�� ��庥��");
						break;
					default:
						GameMode c = GameMode.getByValue(gm);
						if(c == null)
						{
							super.m.sendMessage(sender, "���Ӹ�� ������");
							return false;
						}
						((Player)sender).setGameMode(c);
						super.m.sendMessage(sender, "���Ӹ�� ����");
					}
				}else{
					int gm = 0;
					try{
						gm = Integer.parseInt(args[0]);
					}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
					if(gm <= -1 )
					{
						super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [0/1/2] <�÷��̾�>"));
						return false;
					}
					Player p = Bukkit.getPlayer(args[1]);
					if(p == null)
					{
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[1]));
						return false;
					}
					
					switch(gm)
					{
					case 0:
						p.setGameMode(GameMode.SURVIVAL);
						super.m.sendMessage(p, "���Ӹ�� �����̹�");
						super.m.sendMessage(sender, "��� ���Ӹ�� �����̹�",new DoubleString("[���]",DataManager.getName(p.getName())));
						break;
					case 1:
						p.setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(p, "���Ӹ�� ũ������Ƽ��");
						super.m.sendMessage(sender, "��� ���Ӹ�� ũ������Ƽ��",new DoubleString("[���]",DataManager.getName(p.getName())));
						break;
					case 2:
						p.setGameMode(GameMode.ADVENTURE);
						super.m.sendMessage(p, "���Ӹ�� ��庥��");
						super.m.sendMessage(sender, "��� ���Ӹ�� ��庥��",new DoubleString("[���]",DataManager.getName(p.getName())));
						break;
					default:
						GameMode c = GameMode.getByValue(gm);
						if(c == null)
						{
							super.m.sendMessage(sender, "���Ӹ�� ������");
							return false;
						}
						p.setGameMode(c);
						super.m.sendMessage(p, "���Ӹ�� ����");
						super.m.sendMessage(sender, "��� ���Ӹ�� ����",new DoubleString("[���]",DataManager.getName(p.getName())));
					}
				}
			}else{
				if(args.length == 2)
				{
					{
						int gm = 0;
						try{
							gm = Integer.parseInt(args[0]);
						}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
						if(gm <= -1)
						{
							super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [0/1/2...] <�÷��̾�>"));
							return false;
						}
						Player p = Bukkit.getPlayer(args[1]);
						if(p == null)
						{
							super.m.sendMessage(sender, "��� �÷��̾� ��������");
							return false;
						}
						
						switch(gm)
						{
						case 0:
							p.setGameMode(GameMode.SURVIVAL);
							super.m.sendMessage(p, "���Ӹ�� �����̹�");
							super.m.sendMessage(sender, "��� �÷��̾� �����̹�",new DoubleString("[���]",DataManager.getName(p.getName())));
							break;
						case 1:
							p.setGameMode(GameMode.CREATIVE);
							super.m.sendMessage(p, "���Ӹ�� ũ������Ƽ��");
							super.m.sendMessage(sender, "��� �÷��̾� ũ������Ƽ��",new DoubleString("[���]",DataManager.getName(p.getName())));
							break;
						case 2:
							p.setGameMode(GameMode.SURVIVAL);
							super.m.sendMessage(p, "���Ӹ�� ��庥��");
							super.m.sendMessage(sender, "��� �÷��̾� ��庥��",new DoubleString("[���]",DataManager.getName(p.getName())));
							break;
						default:
							GameMode c = GameMode.getByValue(gm);
							if(c == null)
							{
								super.m.sendMessage(sender, "���Ӹ�� ������");
							}
							p.setGameMode(c);
							super.m.sendMessage(p, "���Ӹ�� ����");
							super.m.sendMessage(sender, "��� ���Ӹ�� ����",new DoubleString("[���]",DataManager.getName(p.getName())));
						}
					}
				}else{
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [0/1/2]"));
				}
			}
			return false;
	}
}