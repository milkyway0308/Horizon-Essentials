package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandTPAll  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTpall))
			{
					super.m.sendMessage(sender, "명령어 권한 부족");
					return false;
			}
			if(args.length <= 0){
				
				if(sender instanceof Player){
					super.m.sendMessage(sender, "전체 텔레포트");
					for(Player p : Bukkit.getOnlinePlayers())
						if(!p.getName().equals(sender.getName()))
							p.teleport(((Player)sender).getLocation());
				}else{
					super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [x] [y] [z] [월드]"));
				}
			}else if(args.length == 4){
				World w = Bukkit.getWorld(args[3]);
				if(w == null)
					super.m.sendMessage(sender, "월드 미존재");
				else{
					try{
						Location loc = new Location(w,Double.parseDouble(args[0]),Double.parseDouble(args[1]),Double.parseDouble(args[2]));
						super.m.sendMessage(sender, "전체 텔레포트 좌표",new DoubleString("[좌표]",w.getName() + "," + args[0] + "," + args[1] + "," + args[2]));
						for(Player p : Bukkit.getOnlinePlayers())
							p.teleport(loc);
					}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");}
				}
			}else{
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [x] [y] [z] [월드]"));
			}
			return false;
	}
}