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
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if (args.length <= 0) {
			super.m.sendMessage(sender, "��ɾ� ����", new DoubleString("[����]", "/" + label + " [�÷��̾�]"));
		} else {
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null)
				super.m.sendMessage(sender, "��� �÷��̾� ��������", new DoubleString("[���]", DataManager.getName(args[0])));
			else {
				super.m.sendMessage(sender, "��Ʈ ���� ���", new DoubleString("[���]", DataManager.getName(args[0])));
				super.m.sendMessage(p, "��Ʈ ����");
				DataManager.players.get(p).canChat = -1;
			}
		}
		return false;
	}
}