package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandTPDeny extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if(sender instanceof Player)
		{
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTpDeny))
			{
					super.m.sendMessage(sender, "명령어 권한 부족");
					return false;
			}
			if(DataManager.players.get((Player)sender).teleportRequest.size() <= 0){
				 super.m.sendMessage(sender, "텔레포트 요청 미존재");
				 return false;
			}
			if(args.length <= 0)
			{
				Player p = (DataManager.players.get((Player)sender).teleportRequest.get((DataManager.players.get((Player)sender).teleportRequest.size()-1)));
				
				super.m.sendMessage(sender, "텔레포트 거절",new DoubleString("[대상]",DataManager.getName(p.getName())));
				DataManager.players.get((Player)sender).denyRequest.add(p);
				DataManager.players.get((Player)sender).teleportRequest.remove(DataManager.players.get((Player)sender).teleportRequest.size()-1);
			}else if(args.length == 1){
				Player p = Bukkit.getPlayer(args[0]);
				if(p == null)
					super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
				else{
					if(DataManager.players.get((Player)sender).teleportRequest.contains(p)){
						DataManager.players.get((Player)sender).denyRequest.add(p);
						DataManager.players.get((Player)sender).teleportRequest.remove(p);
						super.m.sendMessage(sender, "텔레포트 거절",new DoubleString("[대상]",DataManager.getName(p.getName())));
					}
				}
			}else{
				super.m.sendMessage(sender, "명령어 사용법");
			}
		}else{
			super.m.sendMessage(sender, "플레이어 전용 커맨드");
		}
		return false;
	}
}