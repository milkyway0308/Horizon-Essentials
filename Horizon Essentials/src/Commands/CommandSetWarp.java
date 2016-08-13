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

public class CommandSetWarp extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandSetWarp))
			{
				if(sender instanceof Player)
				{
					if(args.length <= 0)
					{
						super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]", "/" + label + " [워프이름]"));
					}else{
						DataManager.warps.put(args[0], new LocationCrafter(((Player)sender).getLocation()));
						super.m.sendMessage(sender, "워프 설정",new DoubleString("[워프]",args[0]));
					}
				}
			}
	return false;
	}
}