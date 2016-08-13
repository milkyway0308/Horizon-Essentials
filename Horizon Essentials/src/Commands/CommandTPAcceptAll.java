package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandTPAcceptAll extends CommandsRegistry implements CommandExecutor{
	

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
			DataManager.players.get((Player)sender).teleportRequest.clear();
			super.m.sendMessage(sender, "텔레포트 전체 수락");
		}else{
			super.m.sendMessage(sender, "플레이어 전용 커맨드");
		}
		return false;
	}
}