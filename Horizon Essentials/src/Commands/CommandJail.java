package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.LocationCrafter;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandJail extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandJail)){
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(args.length <= 2)
		{
			super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어] [감옥이름] [시간]"));
			return false;
		}
		if(Bukkit.getPlayer(args[0]) == null)
			super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
		else{
			Player p = Bukkit.getPlayer(args[0]);
			if(!DataManager.jail.containsKey(args[1]))
				super.m.sendMessage(sender, "감옥 미존재");
			else{
				int time = 0;
				try{
					time = Integer.parseInt(args[2]);
				}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");return false;}
				DataManager.players.get(p).jail = new LocationCrafter(p.getLocation());
				p.teleport(DataManager.jail.get(args[1]).getLocation());
				super.m.sendMessage(sender, "감옥 가둠",DataUtil.asArray(new DoubleString("[대상]",args[0]),new DoubleString("[감옥]",args[1]),new DoubleString("[시간]",DataUtil.IntegerToTime(time))));
				super.m.sendMessage(p,"감옥 가둬짐",new DoubleString("[시간]",DataUtil.IntegerToTime(time)));
				DataManager.players.get(p).isJail = System.currentTimeMillis() + (double)time * (double)1000;
				

			}
		}
		return false;
	}

}
