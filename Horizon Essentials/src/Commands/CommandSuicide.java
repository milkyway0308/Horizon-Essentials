package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandSuicide extends CommandsRegistry implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandSuicide))
		{
			if(!(sender instanceof Player))
				super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
			else{
				((Player)sender).setHealth(0);
				super.m.sendMessage(sender, "�ڻ�");
			}
		}
		return false;
}
}
