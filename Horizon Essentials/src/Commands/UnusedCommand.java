package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnusedCommand  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		sender.sendMessage("��6[Horizon Essentials] ��c��Ȱ��ȭ�� ��ɾ��Դϴ�.");
		return false;
	}
}