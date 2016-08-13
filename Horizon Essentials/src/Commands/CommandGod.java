package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandGod extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandFly))
			{
				super.m.sendMessage(sender, "명령어 권한 부족");
				return false;
			}
			if(sender instanceof Player){
				if(args.length <= 0)
				{
					DataManager.players.get(((Player)sender)).isGodmod = !DataManager.players.get(((Player)sender)).isGodmod;
					if(DataManager.players.get(((Player)sender)).isGodmod)
						super.m.sendMessage(sender, "갓모드 활성화");
					else
						super.m.sendMessage(sender, "갓모드 비활성화");
				}else{
					if(Bukkit.getPlayer(args[0]) == null){
						super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
					}else{
						if(Bukkit.getPlayer(args[0]) == null){
							super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
							return false;
						}
						Player p = Bukkit.getPlayer(args[0]);
						boolean msg = true;
						boolean god = !DataManager.players.get(((Player)sender)).isGodmod;
						if(args.length == 2)
							god = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
						else if(args.length == 3){
							god = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
							msg = Boolean.parseBoolean(args[2].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
						}
						p.setAllowFlight(god);
						if(msg)
						{
							
							if(god)
							{
								super.m.sendMessage(sender,"타인 갓모드 활성화",new DoubleString("[대상]",args[0]));
								super.m.sendMessage(p, "플라이 활성화");
							}
								
							else{
								super.m.sendMessage(p, "플라이 비활성화");
								super.m.sendMessage(sender,"타인 갓모드 비활성화",new DoubleString("[대상]",args[0]));
							}
								
						}
					}
				}
			}else{
				if(args.length <= 0)
				{
					if(DataUtil.isAlpha(label)){
						super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어] <enable/disable> <메시지 출력 여부(true/false)>"));
					}else{
						super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어] <활성화/비활성화> <메시지 출력 여부(true/false)>"));
					}
				}
				else{
					if(Bukkit.getPlayer(args[0]) == null){
						super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
					}else{
						if(Bukkit.getPlayer(args[0]) == null){
							super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
							return false;
						}
						Player p = Bukkit.getPlayer(args[0]);
						boolean msg = true;
						boolean god = !DataManager.players.get(((Player)sender)).isGodmod;
						if(args.length == 2)
							god = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
						else if(args.length == 3){
							god = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
							msg = Boolean.parseBoolean(args[2].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
						}
						p.setAllowFlight(god);
						if(msg)
						{
							
							if(god)
							{
								super.m.sendMessage(sender,"타인 갓모드 활성화",new DoubleString("[대상]",args[0]));
								super.m.sendMessage(p, "플라이 활성화");
							}
								
							else{
								super.m.sendMessage(p, "플라이 비활성화");
								super.m.sendMessage(sender,"타인 갓모드 비활성화",new DoubleString("[대상]",args[0]));
							}
								
						}
					}
				}
			}
		return false;
	}
}