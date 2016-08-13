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

public class CommandUnmute extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if (!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandMute)) {
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if (args.length <= 0) {
			super.m.sendMessage(sender, "명령어 사용법", new DoubleString("[사용법]", "/" + label + " [플레이어]"));
		} else {
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null)
				super.m.sendMessage(sender, "대상 플레이어 오프라인", new DoubleString("[대상]", DataManager.getName(args[0])));
			else {
				super.m.sendMessage(sender, "뮤트 해제 대상", new DoubleString("[대상]", DataManager.getName(args[0])));
				super.m.sendMessage(p, "뮤트 해제");
				DataManager.players.get(p).canChat = -1;
			}
		}
		return false;
	}
}