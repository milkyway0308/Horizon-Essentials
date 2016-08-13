package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandUnban extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandUnban)){
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(args.length <= 0)
		{
			super.m.sendMessage(sender,"명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어]"));
			return false;
		}
		Bukkit.getOfflinePlayer(args[0]).setBanned(false);
		if(DataManager.players.containsKey(Bukkit.getOfflinePlayer(args[0])))
			DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).tempBan = -1;
		super.m.sendMessage(sender, "밴 해제됨",new DoubleString("[플레이어]",args[0]));
		DataUtil.OperatorMessage(super.m.getMessage("밴 해제").replace("[플레이어]", args[0]).replace("[시전자]", sender.getName()));
		Bukkit.getConsoleSender().sendMessage(super.m.getMessage("밴 해제").replace("[플레이어]", args[0]).replace("[시전자]", sender.getName()));
		return false;
	}

}
