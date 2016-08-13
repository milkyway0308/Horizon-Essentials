package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandTPDenyAll extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if(sender instanceof Player)
		{
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTpaccept))
			{
					super.m.sendMessage(sender, "명령어 권한 부족");
					return false;
			}
			if(DataManager.players.get((Player)sender).teleportRequest.size() <= 0){
				 super.m.sendMessage(sender, "텔레포트 요청 미존재");
				 return false;
			}
			for(Player p : DataManager.players.get((Player)sender).teleportRequest){
				DataManager.players.get((Player)sender).denyRequest.add(p);
			}
			super.m.sendMessage(sender, "텔레포트 전체 거절");
		}else{
			super.m.sendMessage(sender, "플레이어 전용 커맨드");
		}
		return false;
	}
}