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
						super.m.sendMessage(sender, "이코노미 조회",new DoubleString("[잔액]",s1 + EssentialsBase.economyMain + " " + s2 + EssentialsBase.economySub));
					}else{
						if(EssentialsBase.usePoint){
							super.m.sendMessage(sender, "이코노미 조회",new DoubleString("[잔액]",String.format("%.2f",EssentialsBase.getMoney((Player)sender)) + EssentialsBase.economyMain));
						}else{
							super.m.sendMessage(sender, "이코노미 조회",new DoubleString("[잔액]",String.format(".2f", EssentialsBase.getMoney((Player)sender)).split(".")[0] + EssentialsBase.economyMain));
						}
					}
				}else{
					sender.sendMessage("   §6Horizon Essentials - Economy");
					if(DataUtil.isAlpha(label)){
						sender.sendMessage("§7/" + label + " view [플레이어] §e- §f[플레이어]의 잔액을 조회합니다.");
						sender.sendMessage("§7/" + label + " add <플레이어> <금액> §e- §f<플레이어>의 잔액에 <금액>을 추가합니다.");
						sender.sendMessage("§7/" + label + " remove <플레이어> <금액> §e- §f<플레이어>의 잔액에서 <금액>을 차감합니다.");
						sender.sendMessage("§7/" + label + " set <플레이어> <금액> §e- §f<플레이어>의 잔액을 <금액>으로 설정합니다.");
						sender.sendMessage("§7/" + label + " reset <플레이어> §e- §f<플레이어>의 계좌를 리셋합니다.");
					}else{
						sender.sendMessage("§7/" + label + " 조회 [플레이어] §e- §f[플레이어]의 잔액을 조회합니다.");
						sender.sendMessage("§7/" + label + " 지급/추가 <플레이어> <금액> §e- §f<플레이어>의 잔액에 <금액>을 추가합니다.");
						sender.sendMessage("§7/" + label + " 차감/제거 <플레이어> <금액> §e- §f<플레이어>의 잔액에서 <금액>을 차감합니다.");
						sender.sendMessage("§7/" + label + " 설정 <플레이어> <금액> §e- §f<플레이어>의 잔액을 <금액>으로 설정합니다.");
						sender.sendMessage("§7/" + label + " 초기화 <플레이어> §e- §f<플레이어>의 계좌를 리셋합니다.");
					}
				}
			}else{

					if(args.length == 1){
						sender.sendMessage("   §6Horizon Essentials - Economy");
						if(DataUtil.isAlpha(label)){
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							sender.sendMessage("§7/" + label + " view [플레이어] §e- §f[플레이어]의 잔액을 조회합니다.");
							sender.sendMessage("§7/" + label + " add [플레이어] [금액] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 잔액에 [금액]을 추가합니다.");
							sender.sendMessage("§7/" + label + " remove [플레이어] [금액] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 잔액에서 [금액]을 차감합니다.");
							sender.sendMessage("§7/" + label + " set [플레이어] [금액] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 잔액을 [금액]으로 설정합니다.");
							sender.sendMessage("§7/" + label + " reset [플레이어] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 계좌를 리셋합니다.");
							}
							if(sender instanceof Player)
								sender.sendMessage("§7/" + label + "send [플레이어] [액수] §e- §f[플레이어]에게 돈을 보냅니다.");
						}else{
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							sender.sendMessage("§7/" + label + " 조회 [플레이어] §e- §f[플레이어]의 잔액을 조회합니다.");
							sender.sendMessage("§7/" + label + " 지급/추가 [플레이어] [금액] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 잔액에 [금액]을 추가합니다.");
							sender.sendMessage("§7/" + label + " 차감/제거 [플레이어] [금액] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 잔액에서 [금액]을 차감합니다.");
							sender.sendMessage("§7/" + label + " 설정 [플레이어] [금액] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 잔액을 [금액]으로 설정합니다.");
							sender.sendMessage("§7/" + label + " 초기화 [플레이어] <메시지 출력 여부(true/false)> §e- §f[플레이어]의 계좌를 리셋합니다.");
							}
							if(sender instanceof Player)
								sender.sendMessage("§7/" + label + " 보내기 [플레이어] [액수] §e- §f[플레이어]에게 돈을 보냅니다.");
						}
					}else{
						boolean msg = true;
						if(args.length >= 4)
							msg = Boolean.parseBoolean(args[3]);
						switch(args[0]){
						case "send":
						case "보내기":
						{
							if(sender instanceof Player){
								if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomySend)){
									if(args.length <= 2)
										super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " " + args[0] + "[플레이어] [금액]"));
									else{
										if(sender.getName().toLowerCase().equals(args[1].toLowerCase()))
										{
											super.m.sendMessage(sender, "이코노미 대상 오류");
											return false;
										}
										if(!DataUtil.isDouble(args[2]))
											super.m.sendMessage(sender, "잘못된 상수 매개 변수");
										else{
											OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
											Double d = Double.parseDouble(args[2]);
											if(d <= 0)
											{
												super.m.sendMessage(sender, "이코노미 음수 금액");
												return false;
											}
											if(!EssentialsBase.hasMoney((Player)sender, d)){
												super.m.sendMessage(sender, "이코노미 돈 부족");
												return false;
											}
											EssentialsBase.removeMoney((Player)sender, d);
											EssentialsBase.addMoney(p, d);
												super.m.sendMessage(sender, "이코노미 돈 보냄",DataUtil.asArray(new DoubleString("[대상]",args[1]) ,new DoubleString("[금액]",args[2] + EssentialsBase.economyMain)));
												if(p.isOnline()){
													super.m.sendMessage((Player)p, "이코노미 돈 받음",DataUtil.asArray(new DoubleString("[대상]",sender.getName()) ,new DoubleString("[금액]",String.valueOf(d) + EssentialsBase.economyMain)));
											}
										}
									}
								}else{
									super.m.sendMessage(sender, "명령어 권한 부족");
								}
							}else{
								super.m.sendMessage(sender, "플레이어 전용 커맨드");
							}
						}
						case "add":
						case "지급":
						case "추가":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							if(args.length <= 2)
								super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " " + args[0] + "[플레이어] [금액] <메시지 출력 여부(true/false)>"));
							else{
								if(!DataUtil.isDouble(args[2]))
									super.m.sendMessage(sender, "잘못된 상수 매개 변수");
								else{
									OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
									Double d = Double.parseDouble(args[2]);
									if(d <= 0)
									{
										super.m.sendMessage(sender, "이코노미 음수 금액 지급");
										return false;
									}
									EssentialsBase.addMoney(p, d);
									if(msg){
										super.m.sendMessage(sender, "이코노미 돈 주기",DataUtil.asArray(new DoubleString("[대상]",args[1]) ,new DoubleString("[금액]",args[2] + EssentialsBase.economyMain)));
										if(p.isOnline()){
											Player p2 = (Player)p;
											super.m.sendMessage(p2, "이코노미 돈 추가됨",new DoubleString("[금액]",String.valueOf(d) + EssentialsBase.economyMain));
										}
									}
								}
							}
							}else{
								super.m.sendMessage(sender, "명령어 권한 부족");
							}
							break;
						case "remove":
						case "차감":
						case "제거":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							if(args.length <= 2)
								super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " " + args[0] + "[플레이어] [금액] <메시지 출력 여부(true/false)>"));
							else{
								if(!DataUtil.isDouble(args[2]))
									super.m.sendMessage(sender, "잘못된 상수 매개 변수");
								else{
									OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
									Double d = Double.parseDouble(args[2]);
									if(d <= 0)
									{
										super.m.sendMessage(sender, "이코노미 음수 금액 차감");
										return false;
									}
									EssentialsBase.removeMoney(Bukkit.getOfflinePlayer(args[1]), d);
									if(msg){
										super.m.sendMessage(sender, "이코노미 돈 차감",DataUtil.asArray(new DoubleString("[대상]",args[1]) ,new DoubleString("[금액]",args[2] + EssentialsBase.economyMain)));
										if(p.isOnline()){
											Player p2 = (Player)p;
											super.m.sendMessage(p2, "이코노미 돈 차감됨",new DoubleString("[금액]",String.valueOf(d) + EssentialsBase.economyMain));
										}
									}
								}

							}
							}else{
								super.m.sendMessage(sender, "명령어 권한 부족");
							}
							break;
						case "set":
						case "설정":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							if(args.length <= 2)
								super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " " + args[0] + "[플레이어] [금액] <메시지 출력 여부(true/false)>"));
							else{
								if(!DataUtil.isDouble(args[2]))
									super.m.sendMessage(sender, "잘못된 상수 매개 변수");
								else{
									OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
									Double d = Double.parseDouble(args[2]);
									EssentialsBase.setMoney(p, d);
									if(msg){
										super.m.sendMessage(sender, "이코노미 돈 설정",DataUtil.asArray(new DoubleString("[대상]",args[1]) ,new DoubleString("[금액]",args[2] + EssentialsBase.economyMain)));
										if(p.isOnline()){
											Player p2 = (Player)p;
											super.m.sendMessage(p2, "이코노미 돈 설정됨",new DoubleString("[금액]",String.valueOf(d) + EssentialsBase.economyMain));
										}
									}
								}
							}
							}else{
								super.m.sendMessage(sender, "명령어 권한 부족");
							}
						case "view":
						case "조회":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							super.m.sendMessage(sender, "이코노미 타인 조회",DataUtil.asArray(new DoubleString("[잔액]",String.valueOf(EssentialsBase.getMoney(Bukkit.getOfflinePlayer(args[1]))) + EssentialsBase.economyMain),new DoubleString("[대상]",args[1])));
							if(EssentialsBase.useSub)
							{
								String s = String.format("%.2f",EssentialsBase.getMoney(Bukkit.getOfflinePlayer(args[1])));
								int index = s.indexOf(".");
								String s1 = s.substring(0, index);
								String s2 = String.valueOf(Integer.parseInt(s.substring(index+1,s.length())));
								super.m.sendMessage(sender, "이코노미 조회",DataUtil.asArray(new DoubleString("[대상]",args[1]),new DoubleString("[잔액]",s1 + EssentialsBase.economyMain + " " + s2 + EssentialsBase.economySub)));
							}else{
								if(EssentialsBase.usePoint){
									super.m.sendMessage(sender, "이코노미 조회",DataUtil.asArray(new DoubleString("[대상]",args[1]),new DoubleString("[잔액]",String.format("%.2f",EssentialsBase.getMoney((Player)sender)) + EssentialsBase.economyMain)));
								}else{
									super.m.sendMessage(sender, "이코노미 조회",DataUtil.asArray(new DoubleString("[대상]",args[1]),new DoubleString("[잔액]",String.format("%.0f", EssentialsBase.getMoney((Player)sender)) + EssentialsBase.economyMain)));
								}
							}
							}else{
								super.m.sendMessage(sender, "명령어 권한 부족");
							}
							break;
						case "reset":
						case "초기화":
							if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandEconomy))
							{
							OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
							EssentialsBase.setMoney(Bukkit.getOfflinePlayer(args[1]), EssentialsBase.defaultmoney);
							if(msg){
								super.m.sendMessage(sender, "이코노미 돈 초기화",DataUtil.asArray(new DoubleString("[대상]",args[1])));
								if(p.isOnline()){
									Player p2 = (Player)p;
									super.m.sendMessage(p2, "이코노미 돈 초기화됨");
								}
							}
							}else{
								super.m.sendMessage(sender, "명령어 권한 부족");
							}
							break;
						default:
							super.m.sendMessage(sender, "알수 없는 서브커맨드");
						}
					}

			}
			return false;
	}
	
}