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
						super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]", "/" + label + " [�����̸�]"));
					}else{
						DataManager.warps.put(args[0], new LocationCrafter(((Player)sender).getLocation()));
						super.m.sendMessage(sender, "���� ����",new DoubleString("[����]",args[0]));
					}
				}
			}
	return false;
	}
}