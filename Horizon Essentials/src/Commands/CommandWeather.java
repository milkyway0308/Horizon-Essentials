package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandWeather  extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if (!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandMute)) {
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(args.length <= 0)
			super.m.sendMessage(sender, "��ɾ� ����");
		return false;
	}
}