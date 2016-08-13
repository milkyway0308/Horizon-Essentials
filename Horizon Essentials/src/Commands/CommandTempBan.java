package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.EssentialsPlayer;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandTempBan extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTempBan))
			{
					super.m.sendMessage(sender, "명령어 권한 부족");
					return false;
			}
			if(args.length <= 1){
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어] [시간(숫자)]"));
			}else{
				try{
					int a = Integer.parseInt(args[1]);
					if(a <= 0)
						super.m.sendMessage(sender, "잘못된 상수 매개 변수");
					else{
						if(!DataManager.players.containsKey(Bukkit.getOfflinePlayer(args[0])))
							DataManager.players.put(Bukkit.getOfflinePlayer(args[0]), new EssentialsPlayer(args[0]));
							DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).tempBan = (double)System.currentTimeMillis() + (double)a * 1000;
							String bancause = "§c서버에서 차단되었습니다.";
							if(args.length >= 3)
							{
								StringBuilder b = new StringBuilder(args[2]);
								for(int i = 3;i < args.length;i++)
									b.append(" " + args[i]);
								bancause = b.toString();
							}
							DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).banCause = bancause;
							DataUtil.OperatorMessage(super.m.getMessage("시간밴").replace("[시간]", DataUtil.IntegerToTime(a)).replace("[시전자]",sender.getName()).replace("[대상]", args[0]).replace("/*nextline*/", "\n"));
							Bukkit.getConsoleSender().sendMessage(super.m.getMessage("시간밴").replace("[시간]", DataUtil.IntegerToTime(a)).replace("[시전자]",sender.getName()).replace("[대상]", args[0]).replace("/*nextline*/", "\n"));
							if(Bukkit.getOfflinePlayer(args[0]).isOnline())
								((Player)Bukkit.getOfflinePlayer(args[0])).kickPlayer(super.m.getMessage("시간 밴 메시지").replace("/*nextline*/", "\n").replace("[시간]", DataUtil.IntegerToTime(a)).replace("[사유]", DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).banCause));

					}
				}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");}
			}
		return false;
	}
}