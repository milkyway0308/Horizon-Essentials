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

public class CommandSetSpawn extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player)
		{
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandSetSpawn))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if(args.length <= 0)
			{
				super.m.sendMessage(sender, "���� ����");
				DataManager.spawn.put("default", new LocationCrafter(((Player)sender).getLocation()));
			}else if(args.length == 1){
				DataManager.spawn.put(args[0], new LocationCrafter(((Player)sender).getLocation()));
				super.m.sendMessage(sender, "�׷� ���� ����",new DoubleString("[�׷�]",args[0]));
			}
		}
		return false;
	}
}