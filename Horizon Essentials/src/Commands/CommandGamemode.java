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
				super.m.sendMessage(sender, "명령어 권한 부족");
				return false;
			}
			if(sender instanceof Player)
			{
				if(args.length <= 0)
				{
					super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [0/1/2]"));
				}else if(args.length == 1)
				{
					int gm = 0;
					try{
						gm = Integer.parseInt(args[0]);
					}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");return false;}
					if(gm <= -1)
					{
						super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [0/1/2]"));
						return false;
					}
					switch(gm)
					{
					case 0:
						((Player)sender).setGameMode(GameMode.SURVIVAL);
						super.m.sendMessage(sender, "게임모드 서바이벌");
						break;
					case 1:
						((Player)sender).setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(sender, "게임모드 크리에이티브");
						break;
					case 2:
						((Player)sender).setGameMode(GameMode.ADVENTURE);
						super.m.sendMessage(sender, "게임모드 어드벤쳐");
						break;
					default:
						GameMode c = GameMode.getByValue(gm);
						if(c == null)
						{
							super.m.sendMessage(sender, "게임모드 미존재");
							return false;
						}
						((Player)sender).setGameMode(c);
						super.m.sendMessage(sender, "게임모드 변경");
					}
				}else{
					int gm = 0;
					try{
						gm = Integer.parseInt(args[0]);
					}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");return false;}
					if(gm <= -1 )
					{
						super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [0/1/2] <플레이어>"));
						return false;
					}
					Player p = Bukkit.getPlayer(args[1]);
					if(p == null)
					{
						super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[1]));
						return false;
					}
					
					switch(gm)
					{
					case 0:
						p.setGameMode(GameMode.SURVIVAL);
						super.m.sendMessage(p, "게임모드 서바이벌");
						super.m.sendMessage(sender, "대상 게임모드 서바이벌",new DoubleString("[대상]",DataManager.getName(p.getName())));
						break;
					case 1:
						p.setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(p, "게임모드 크리에이티브");
						super.m.sendMessage(sender, "대상 게임모드 크리에이티브",new DoubleString("[대상]",DataManager.getName(p.getName())));
						break;
					case 2:
						p.setGameMode(GameMode.ADVENTURE);
						super.m.sendMessage(p, "게임모드 어드벤쳐");
						super.m.sendMessage(sender, "대상 게임모드 어드벤쳐",new DoubleString("[대상]",DataManager.getName(p.getName())));
						break;
					default:
						GameMode c = GameMode.getByValue(gm);
						if(c == null)
						{
							super.m.sendMessage(sender, "게임모드 미존재");
							return false;
						}
						p.setGameMode(c);
						super.m.sendMessage(p, "게임모드 변경");
						super.m.sendMessage(sender, "대상 게임모드 변경",new DoubleString("[대상]",DataManager.getName(p.getName())));
					}
				}
			}else{
				if(args.length == 2)
				{
					{
						int gm = 0;
						try{
							gm = Integer.parseInt(args[0]);
						}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");return false;}
						if(gm <= -1)
						{
							super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [0/1/2...] <플레이어>"));
							return false;
						}
						Player p = Bukkit.getPlayer(args[1]);
						if(p == null)
						{
							super.m.sendMessage(sender, "대상 플레이어 오프라인");
							return false;
						}
						
						switch(gm)
						{
						case 0:
							p.setGameMode(GameMode.SURVIVAL);
							super.m.sendMessage(p, "게임모드 서바이벌");
							super.m.sendMessage(sender, "대상 플레이어 서바이벌",new DoubleString("[대상]",DataManager.getName(p.getName())));
							break;
						case 1:
							p.setGameMode(GameMode.CREATIVE);
							super.m.sendMessage(p, "게임모드 크리에이티브");
							super.m.sendMessage(sender, "대상 플레이어 크리에이티브",new DoubleString("[대상]",DataManager.getName(p.getName())));
							break;
						case 2:
							p.setGameMode(GameMode.SURVIVAL);
							super.m.sendMessage(p, "게임모드 어드벤쳐");
							super.m.sendMessage(sender, "대상 플레이어 어드벤쳐",new DoubleString("[대상]",DataManager.getName(p.getName())));
							break;
						default:
							GameMode c = GameMode.getByValue(gm);
							if(c == null)
							{
								super.m.sendMessage(sender, "게임모드 미존재");
							}
							p.setGameMode(c);
							super.m.sendMessage(p, "게임모드 변경");
							super.m.sendMessage(sender, "대상 게임모드 변경",new DoubleString("[대상]",DataManager.getName(p.getName())));
						}
					}
				}else{
					super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [0/1/2]"));
				}
			}
			return false;
	}
}