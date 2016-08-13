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

public class CommandViewJail extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandViewJail)){
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 0)
			{
				Player p = (Player)sender;
				if(DataManager.players.get(p).isJail <= -1)
					super.m.sendMessage(sender, "수감 안됨");
				else
					super.m.sendMessage(sender, "수감 시간",new DoubleString("[시간]",DataUtil.IntegerToTime((long)(DataManager.players.get(p).isJail - System.currentTimeMillis()) / 1000)));
			}else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
				else{
					if(!DataManager.players.containsKey(args[0]))
						super.m.sendMessage(sender, "타인 수감 안됨",new DoubleString("[대상]",args[0]));
					else{
						Player p = Bukkit.getPlayer(args[0]);
						if(DataManager.players.get(p).isJail <= -1)
							super.m.sendMessage(sender, "타인 수감 안됨",new DoubleString("[대상]",args[0]));
						else
							super.m.sendMessage(sender, "타인 수감 시간",DataUtil.asArray(new DoubleString("[시간]",DataUtil.IntegerToTime((long)(DataManager.players.get(p).isJail - System.currentTimeMillis()) / 1000)),new DoubleString("[대상]",args[0])));
					}
				}
			}
		}else{
			
		}
		return false;
	}

}
