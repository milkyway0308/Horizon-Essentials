package Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import HorizonEssentials.Economy.EssentialsBase;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandEconomy extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(args.length <= 0){
				if(sender instanceof Player){
					if(EssentialsBase.useSub)
					{
						String s = String.format("%.2f", EssentialsBase.getMoney((Player)sender));
						int index = s.indexOf(".");
						String s1 = s.substring(0, index);
						String s2 = String.valueOf(Integer.parseInt(s.substring(index+1,s.length())));
						super.m.sendMessage(sender, "���ڳ�� ��ȸ",new DoubleString("[�ܾ�]",s1 + EssentialsBase.economyMain + " " + s2 + EssentialsBase.economySub));
					}else{
						if(EssentialsBase.usePoint){
							super.m.sendMessage(sender, "���ڳ�� ��ȸ",new DoubleString("[�ܾ�]",String.format("%.2f",EssentialsBase.getMoney((Player)sender)) + EssentialsBase.economyMain));
						}else{
							super.m.sendMessage(sender, "���ڳ�� ��ȸ",new DoubleString("[�ܾ�]",String.format(".2f", EssentialsBase.getMoney((Player)sender)).split(".")[0] + EssentialsBase.economyMain));
						}
					}
				}else{
					sender.sendMessage("   ��6Horizon Essentials - Economy");
					if(DataUtil.isAlpha(label)){
						sender.sendMessage("��7/" + label + " view [�÷��̾�] ��e- ��f[�÷��̾�]�� �ܾ��� ��ȸ�մϴ�.");
						sender.sendMessage("��7/" + label + " add <�÷��̾�> <�ݾ�> ��e- ��f<�÷��̾�>�� �ܾ׿� <�ݾ�>�� �߰��մϴ�.");
						sender.sendMessage("��7/" + label + " remove <�÷��̾�> <�ݾ�> ��e- ��f<�÷��̾�>�� �ܾ׿��� <�ݾ�>�� �����մϴ�.");
						sender.sendMessage("��7/" + label + " set <�÷��̾�> <�ݾ�> ��e- ��f<�÷��̾�>�� �ܾ��� <�ݾ�>���� �����մϴ�.");
						sender.sendMessage("��7/" + label + " reset <�÷��̾�> ��e- ��f<�÷��̾�>�� ���¸� �����մϴ�.");
					}else{
						sender.sendMessage("��7/" + label + " ��ȸ [�÷��̾�] ��e- ��f[�÷��̾�]�� �ܾ��� ��ȸ�մϴ�.");
						sender.sendMessage("��7/" + label + " ����/�߰� <�÷��̾�> <�ݾ�> ��e- ��f<�÷��̾�>�� �ܾ׿� <�ݾ�>�� �߰��մϴ�.");
						sender.sendMessage("��7/" + label + " ����/���� <�÷��̾�> <�ݾ�> ��e- ��f<�÷��̾�>�� �ܾ׿��� <�ݾ�>�� �����մϴ�.");
						sender.sendMessage("��7/" + label + " ���� <�÷��̾�> <�ݾ�> ��e- ��f<�÷��̾�>�� �ܾ��� <�ݾ�>���� �����մϴ�.");
						sender.sendMessage("��7/" + label + " �ʱ�ȭ <�÷��̾�> ��e- ��f<�÷��̾�>�� ���¸� �����մϴ�.");
					}
				}
			}else{

					if(args.length == 1){
						sender.sendMessage("   ��6Horizon Essentials - Economy");
						if(DataUtil.isAlpha(label)){
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							sender.sendMessage("��7/" + label + " view [�÷��̾�] ��e- ��f[�÷��̾�]�� �ܾ��� ��ȸ�մϴ�.");
							sender.sendMessage("��7/" + label + " add [�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� �ܾ׿� [�ݾ�]�� �߰��մϴ�.");
							sender.sendMessage("��7/" + label + " remove [�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� �ܾ׿��� [�ݾ�]�� �����մϴ�.");
							sender.sendMessage("��7/" + label + " set [�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� �ܾ��� [�ݾ�]���� �����մϴ�.");
							sender.sendMessage("��7/" + label + " reset [�÷��̾�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� ���¸� �����մϴ�.");
							}
							if(sender instanceof Player)
								sender.sendMessage("��7/" + label + "send [�÷��̾�] [�׼�] ��e- ��f[�÷��̾�]���� ���� �����ϴ�.");
						}else{
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							sender.sendMessage("��7/" + label + " ��ȸ [�÷��̾�] ��e- ��f[�÷��̾�]�� �ܾ��� ��ȸ�մϴ�.");
							sender.sendMessage("��7/" + label + " ����/�߰� [�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� �ܾ׿� [�ݾ�]�� �߰��մϴ�.");
							sender.sendMessage("��7/" + label + " ����/���� [�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� �ܾ׿��� [�ݾ�]�� �����մϴ�.");
							sender.sendMessage("��7/" + label + " ���� [�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� �ܾ��� [�ݾ�]���� �����մϴ�.");
							sender.sendMessage("��7/" + label + " �ʱ�ȭ [�÷��̾�] <�޽��� ��� ����(true/false)> ��e- ��f[�÷��̾�]�� ���¸� �����մϴ�.");
							}
							if(sender instanceof Player)
								sender.sendMessage("��7/" + label + " ������ [�÷��̾�] [�׼�] ��e- ��f[�÷��̾�]���� ���� �����ϴ�.");
						}
					}else{
						boolean msg = true;
						if(args.length >= 4)
							msg = Boolean.parseBoolean(args[3]);
						switch(args[0]){
						case "send":
						case "������":
						{
							if(sender instanceof Player){
								if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomySend)){
									if(args.length <= 2)
										super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " " + args[0] + "[�÷��̾�] [�ݾ�]"));
									else{
										if(sender.getName().toLowerCase().equals(args[1].toLowerCase()))
										{
											super.m.sendMessage(sender, "���ڳ�� ��� ����");
											return false;
										}
										if(!DataUtil.isDouble(args[2]))
											super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");
										else{
											OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
											Double d = Double.parseDouble(args[2]);
											if(d <= 0)
											{
												super.m.sendMessage(sender, "���ڳ�� ���� �ݾ�");
												return false;
											}
											if(!EssentialsBase.hasMoney((Player)sender, d)){
												super.m.sendMessage(sender, "���ڳ�� �� ����");
												return false;
											}
											EssentialsBase.removeMoney((Player)sender, d);
											EssentialsBase.addMoney(p, d);
												super.m.sendMessage(sender, "���ڳ�� �� ����",DataUtil.asArray(new DoubleString("[���]",args[1]) ,new DoubleString("[�ݾ�]",args[2] + EssentialsBase.economyMain)));
												if(p.isOnline()){
													super.m.sendMessage((Player)p, "���ڳ�� �� ����",DataUtil.asArray(new DoubleString("[���]",sender.getName()) ,new DoubleString("[�ݾ�]",String.valueOf(d) + EssentialsBase.economyMain)));
											}
										}
									}
								}else{
									super.m.sendMessage(sender, "��ɾ� ���� ����");
								}
							}else{
								super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
							}
						}
						case "add":
						case "����":
						case "�߰�":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							if(args.length <= 2)
								super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " " + args[0] + "[�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)>"));
							else{
								if(!DataUtil.isDouble(args[2]))
									super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");
								else{
									OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
									Double d = Double.parseDouble(args[2]);
									if(d <= 0)
									{
										super.m.sendMessage(sender, "���ڳ�� ���� �ݾ� ����");
										return false;
									}
									EssentialsBase.addMoney(p, d);
									if(msg){
										super.m.sendMessage(sender, "���ڳ�� �� �ֱ�",DataUtil.asArray(new DoubleString("[���]",args[1]) ,new DoubleString("[�ݾ�]",args[2] + EssentialsBase.economyMain)));
										if(p.isOnline()){
											Player p2 = (Player)p;
											super.m.sendMessage(p2, "���ڳ�� �� �߰���",new DoubleString("[�ݾ�]",String.valueOf(d) + EssentialsBase.economyMain));
										}
									}
								}
							}
							}else{
								super.m.sendMessage(sender, "��ɾ� ���� ����");
							}
							break;
						case "remove":
						case "����":
						case "����":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							if(args.length <= 2)
								super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " " + args[0] + "[�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)>"));
							else{
								if(!DataUtil.isDouble(args[2]))
									super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");
								else{
									OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
									Double d = Double.parseDouble(args[2]);
									if(d <= 0)
									{
										super.m.sendMessage(sender, "���ڳ�� ���� �ݾ� ����");
										return false;
									}
									EssentialsBase.removeMoney(Bukkit.getOfflinePlayer(args[1]), d);
									if(msg){
										super.m.sendMessage(sender, "���ڳ�� �� ����",DataUtil.asArray(new DoubleString("[���]",args[1]) ,new DoubleString("[�ݾ�]",args[2] + EssentialsBase.economyMain)));
										if(p.isOnline()){
											Player p2 = (Player)p;
											super.m.sendMessage(p2, "���ڳ�� �� ������",new DoubleString("[�ݾ�]",String.valueOf(d) + EssentialsBase.economyMain));
										}
									}
								}

							}
							}else{
								super.m.sendMessage(sender, "��ɾ� ���� ����");
							}
							break;
						case "set":
						case "����":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							if(args.length <= 2)
								super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " " + args[0] + "[�÷��̾�] [�ݾ�] <�޽��� ��� ����(true/false)>"));
							else{
								if(!DataUtil.isDouble(args[2]))
									super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");
								else{
									OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
									Double d = Double.parseDouble(args[2]);
									EssentialsBase.setMoney(p, d);
									if(msg){
										super.m.sendMessage(sender, "���ڳ�� �� ����",DataUtil.asArray(new DoubleString("[���]",args[1]) ,new DoubleString("[�ݾ�]",args[2] + EssentialsBase.economyMain)));
										if(p.isOnline()){
											Player p2 = (Player)p;
											super.m.sendMessage(p2, "���ڳ�� �� ������",new DoubleString("[�ݾ�]",String.valueOf(d) + EssentialsBase.economyMain));
										}
									}
								}
							}
							}else{
								super.m.sendMessage(sender, "��ɾ� ���� ����");
							}
						case "view":
						case "��ȸ":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							super.m.sendMessage(sender, "���ڳ�� Ÿ�� ��ȸ",DataUtil.asArray(new DoubleString("[�ܾ�]",String.valueOf(EssentialsBase.getMoney(Bukkit.getOfflinePlayer(args[1]))) + EssentialsBase.economyMain),new DoubleString("[���]",args[1])));
							if(EssentialsBase.useSub)
							{
								String s = String.format("%.2f",EssentialsBase.getMoney(Bukkit.getOfflinePlayer(args[1])));
								int index = s.indexOf(".");
								String s1 = s.substring(0, index);
								String s2 = String.valueOf(Integer.parseInt(s.substring(index+1,s.length())));
								super.m.sendMessage(sender, "���ڳ�� ��ȸ",DataUtil.asArray(new DoubleString("[���]",args[1]),new DoubleString("[�ܾ�]",s1 + EssentialsBase.economyMain + " " + s2 + EssentialsBase.economySub)));
							}else{
								if(EssentialsBase.usePoint){
									super.m.sendMessage(sender, "���ڳ�� ��ȸ",DataUtil.asArray(new DoubleString("[���]",args[1]),new DoubleString("[�ܾ�]",String.format("%.2f",EssentialsBase.getMoney((Player)sender)) + EssentialsBase.economyMain)));
								}else{
									super.m.sendMessage(sender, "���ڳ�� ��ȸ",DataUtil.asArray(new DoubleString("[���]",args[1]),new DoubleString("[�ܾ�]",String.format("%.0f", EssentialsBase.getMoney((Player)sender)) + EssentialsBase.economyMain)));
								}
							}
							}else{
								super.m.sendMessage(sender, "��ɾ� ���� ����");
							}
							break;
						case "reset":
						case "�ʱ�ȭ":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
							EssentialsBase.setMoney(Bukkit.getOfflinePlayer(args[1]), EssentialsBase.defaultmoney);
							if(msg){
								super.m.sendMessage(sender, "���ڳ�� �� �ʱ�ȭ",DataUtil.asArray(new DoubleString("[���]",args[1])));
								if(p.isOnline()){
									Player p2 = (Player)p;
									super.m.sendMessage(p2, "���ڳ�� �� �ʱ�ȭ��");
								}
							}
							}else{
								super.m.sendMessage(sender, "��ɾ� ���� ����");
							}
							break;
						default:
							super.m.sendMessage(sender, "�˼� ���� ����Ŀ�ǵ�");
						}
					}

			}
			return false;
	}
	
}