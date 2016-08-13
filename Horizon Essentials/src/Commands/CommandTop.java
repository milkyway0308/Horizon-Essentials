package Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandTop extends CommandsRegistry implements CommandExecutor {
	public final String Developer = "이 클래스는 KiwiYou님에 의해 작성되었습니다.";
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if (arg0 instanceof Player) {
			if (!PlayerUtil.hasPermission(arg0, PermissionType.CommandTop)) {
				super.m.sendMessage(arg0, "명령어 권한 부족");
				return false;
			}
			Player p = (Player) arg0;
			for (int y = 255; y >= p.getLocation().getBlockY(); --y) {
				Location testLoc = new Location(p.getWorld(), p.getLocation().getX(), y, p.getLocation().getZ());
				if (testLoc.getBlock().getType().isSolid()) {
					testLoc = testLoc.add(0, 1, 0);
					p.teleport(testLoc, TeleportCause.COMMAND);
					return true;
				}
			}
			super.m.sendMessage(arg0, "위쪽 블록 찾지 못함");
			return false;
		} else {
			super.m.sendMessage(arg0, "플레이어 전용 커맨드");
			return false; // 제가 생각한것보다 완전히 다르게 하셨어 ㄷㄷ..

		}
	}
/*wtf*/
}
