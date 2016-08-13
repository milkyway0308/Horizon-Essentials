package Commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandTeleport extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTeleport))
			{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
			}
			if(args.length <= 0)
			{
				if(sender instanceof Player)
				{
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [Ÿ�� �÷��̾�]"));
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [x] [y] [z]"));
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [x] [y] [z] [����]"));
				}
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] [x] [y] [z] [����]"));
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [��� �÷��̾�] [Ÿ�� �÷��̾�]"));
				return false;
			}else if(args.length == 1)
			{
				if(sender instanceof Player)
				{

					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",DataManager.getName(args[0])));
						return false;
					}
					((Player)sender).teleport(p);
					super.m.sendMessage(sender, "�ڷ���Ʈ",new DoubleString("[���]",DataManager.getName(p.getName())));
					}else{
						sender.sendMessage("��c�ܼ��� ���� �ڷ���Ʈ ��ɾ ����� �� �����ϴ�.");
					}
				return false;
			}else if(args.length == 2){
					Player p = Bukkit.getPlayer(args[0]);
					Player p2 = Bukkit.getPlayer(args[1]);
					if(p == null)
					{
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",DataManager.getName(args[0])));
						return false;
					}
					
					if(p2 == null)
					{
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",DataManager.getName(args[0])));
						return false;
					}
					p.teleport(p2);
					super.m.sendMessage(sender, "���� �ڷ���Ʈ",Arrays.asList(new DoubleString("[���1]",DataManager.getName(p.getName())),new DoubleString("[���2]",DataManager.getName(p2.getName()))));
					return false;	
				}else if(args.length == 3)
				{
					if(sender instanceof Player)
					{
					Player p = (Player)sender;
					double x = 0.0;
					double y = 0.0;
					double z = 0.0;
					World w = p.getWorld();
					try{
						if(args[0].startsWith("~"))
						{
							if(args[0].length() == 1)
								x = p.getLocation().getX();
							else
								x = p.getLocation().getX() + Double.parseDouble(args[0].substring(1, args[0].length()));
						}else
							x = Double.parseDouble(args[0]);
						if(args[1].startsWith("~"))
						{
							if(args[1].length() == 1)
								y = p.getLocation().getY();
							else
								y = p.getLocation().getY() + Double.parseDouble(args[1].substring(1, args[1].length()));
						}else{
							y = Double.parseDouble(args[1]);
						}
						if(args[2].startsWith("~"))
						{
							if(args[2].length() == 1)
								z = p.getLocation().getZ();
							else
								z = p.getLocation().getZ() + Double.parseDouble(args[2].substring(1, args[2].length()));
						}else{
							z = Double.parseDouble(args[2]);
						}
						
						p.teleport(new Location(w,x,y,z));
					}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
					}else{
						sender.sendMessage("��c�ܼ��� �ڽ� ���� ��ǥ �ڷ���Ʈ ��ɾ ����� �� �����ϴ�.");
					}
					return false;
				}else if(args.length == 4)
				{
					if(sender instanceof Player)
					{
					Player p = (Player)sender;
					double x = 0.0;
					double y = 0.0;
					double z = 0.0;
					World w = null;
					try{
						if(args[0].startsWith("~"))
						{
							if(args[0].length() == 1)
								x = p.getLocation().getX();
							else
								x = p.getLocation().getX() + Double.parseDouble(args[0].substring(1, args[0].length()));
						}else
							x = Double.parseDouble(args[0]);
						if(args[1].startsWith("~"))
						{
							if(args[1].length() == 1)
								y = p.getLocation().getY();
							else
								y = p.getLocation().getY() + Double.parseDouble(args[1].substring(1, args[1].length()));
						}else{
							y = Double.parseDouble(args[1]);
						}
						if(args[2].startsWith("~"))
						{
							if(args[2].length() == 1)
								z = p.getLocation().getZ();
							else
								z = p.getLocation().getZ() + Double.parseDouble(args[2].substring(1, args[2].length()));
						}else{
							z = Double.parseDouble(args[2]);
						}
							try{
								if(args[3].startsWith("~"))
									w = p.getWorld();
								else{
								w = Bukkit.getWorld(args[3]);
								if(w == null)
								{
									super.m.sendMessage(sender, "���� ������");
									return false;
								}
								}
							}catch(Exception e){super.m.sendMessage(sender, "���� ������");return false;}
						p.teleport(new Location(w,x,y,z));
					}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
					}else{
						sender.sendMessage("��c�ܼ��� �ڽ� ���� ��ǥ �ڷ���Ʈ ��ɾ ����� �� �����ϴ�.");
					}
					return false;
				}else if(args.length >= 5)
				{
					Player p = Bukkit.getPlayer(args[0]);
					double x = 0.0;
					double y = 0.0;
					double z = 0.0;
					World w = null;
					try{
						if(args[1].startsWith("~"))
						{
							if(args[1].length() == 1)
								x = p.getLocation().getX();
							else
								x = p.getLocation().getX() + Double.parseDouble(args[1].substring(1, args[1].length()));
						}else
							x = Double.parseDouble(args[1]);
						if(args[2].startsWith("~"))
						{
							if(args[2].length() == 1)
								y = p.getLocation().getY();
							else
								y = p.getLocation().getY() + Double.parseDouble(args[2].substring(1, args[2].length()));
						}else{
							y = Double.parseDouble(args[2]);
						}
						if(args[3].startsWith("~"))
						{
							if(args[3].length() == 1)
								z = p.getLocation().getZ();
							else
								z = p.getLocation().getZ() + Double.parseDouble(args[3].substring(1, args[3].length()));
						}else{
							z = Double.parseDouble(args[3]);
						}
							try{
								if(args[4].startsWith("~"))
									w = p.getWorld();
								else{
								w = Bukkit.getWorld(args[4]);
								if(w == null)
								{
									super.m.sendMessage(sender, "���� ������");
									return false;
								}
								}
							}catch(Exception e){super.m.sendMessage(sender, "���� ������");return false;}
						p.teleport(new Location(w,x,y,z));
					}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
					return false;
				}

			return false;
	}
}
