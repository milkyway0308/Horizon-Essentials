package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.LocationCrafter;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandSetJail  extends CommandsRegistry implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandSetJail)){
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 0)
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [감옥이름]"));
			else{
					super.m.sendMessage(sender,"감옥 설정",new DoubleString("[이름]",args[0]));
					DataManager.jail.put(args[0], new LocationCrafter(((Player)sender).getLocation()));
			}
		}else{
			super.m.sendMessage(sender, "플레이어 전용 커맨드");
		}
		return false;
	}
}