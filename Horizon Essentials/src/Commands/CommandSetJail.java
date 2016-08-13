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

public class CommandSetJail  extends CommandsRegistry implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandSetJail)){
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 0)
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�����̸�]"));
			else{
					super.m.sendMessage(sender,"���� ����",new DoubleString("[�̸�]",args[0]));
					DataManager.jail.put(args[0], new LocationCrafter(((Player)sender).getLocation()));
			}
		}else{
			super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
		}
		return false;
	}
}