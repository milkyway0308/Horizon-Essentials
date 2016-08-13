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
				super.m.sendMessage(sender, "명령어 권한 부족");
				return false;
			}
			if(sender instanceof Player){
				if(args.length <= 0)
				{
					((Player)sender).setAllowFlight(!((Player)sender).getAllowFlight());
					if(((Player)sender).getAllowFlight())
						super.m.sendMessage(sender, "플라이 활성화");
					else
						super.m.sendMessage(sender, "플라이 비활성화");
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
						boolean fly = !p.getAllowFlight();
						if(args.length == 2)
							fly = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
						else if(args.length == 3){
							fly = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
							msg = Boolean.parseBoolean(args[2].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
						}
						p.setAllowFlight(fly);
						if(msg)
						{
							super.m.sendMessage(sender,"타인 플라이 활성화",new DoubleString("[대상]",args[0]));
							if(fly)
								super.m.sendMessage(p, "플라이 활성화");
							else
								super.m.sendMessage(p, "플라이 비활성화");
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
						return false;
					}
					Player p = Bukkit.getPlayer(args[0]);
					boolean msg = true;
					boolean fly = !p.getAllowFlight();
					if(args.length == 2)
						fly = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
					else if(args.length == 3){
						fly = Boolean.parseBoolean(args[1].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
						msg = Boolean.parseBoolean(args[2].replaceAll("enable|활성화", "true").replaceAll("disable|비활성화", "false"));
					}
					p.setAllowFlight(fly);
					if(msg)
					{
						
						if(fly)
						{
							super.m.sendMessage(p, "플라이 활성화");
							super.m.sendMessage(sender,"타인 플라이 활성화",new DoubleString("[대상]",args[0]));
						}
						else
							super.m.sendMessage(p, "플라이 비활성화");
					}
				}
			}
		return false;
	}
}