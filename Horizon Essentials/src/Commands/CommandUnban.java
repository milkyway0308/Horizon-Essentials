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
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(args.length <= 0)
		{
			super.m.sendMessage(sender,"��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�]"));
			return false;
		}
		Bukkit.getOfflinePlayer(args[0]).setBanned(false);
		if(DataManager.players.containsKey(Bukkit.getOfflinePlayer(args[0])))
			DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).tempBan = -1;
		super.m.sendMessage(sender, "�� ������",new DoubleString("[�÷��̾�]",args[0]));
		DataUtil.OperatorMessage(super.m.getMessage("�� ����").replace("[�÷��̾�]", args[0]).replace("[������]", sender.getName()));
		Bukkit.getConsoleSender().sendMessage(super.m.getMessage("�� ����").replace("[�÷��̾�]", args[0]).replace("[������]", sender.getName()));
		return false;
	}

}
