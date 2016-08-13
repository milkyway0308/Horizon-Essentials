package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandIgnoreChat extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender instanceof Player){
				if(!PlayerUtil.hasPermission(sender, PermissionType.CommandIgnoreChat))
				{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
				}
				if(args.length <= 0)
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�]"));
				else{
					if(!DataUtil.isAlphaandNumber(args[0]))
						super.m.sendMessage(sender, "���� �÷��̾��");
					else{
						String name = args[0].toLowerCase();
						Player p = (Player)sender;
						if(name.equals(sender.getName().toLowerCase())){
							super.m.sendMessage(sender, "���� ��� ����");
							return false;
						}
						if(DataManager.players.get(p).chatIgnore.contains(name))
							super.m.sendMessage(sender, "�̹� ���ܵ�");
						else{
							DataManager.players.get(p).chatIgnore.add(name);
							super.m.sendMessage(sender, "�÷��̾� ä�� ����",new DoubleString("[���]",args[0]));
						}
					}
				}
			}else{
				super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
			}
		return false;
	}
	
}