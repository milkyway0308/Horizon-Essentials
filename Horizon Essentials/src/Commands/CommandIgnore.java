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

public class CommandIgnore  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender instanceof Player){
				if(!PlayerUtil.hasPermission(sender, PermissionType.CommandIgnore))
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
						if(DataManager.players.get(p).FullyIgnore.contains(name))
							super.m.sendMessage(sender, "�̹� ���ܵ�");
						else{
							if(DataManager.players.get(p).chatIgnore.contains(name))
								DataManager.players.get(p).chatIgnore.remove(name);
							if(DataManager.players.get(p).tpaIgnore.contains(name))
								DataManager.players.get(p).tpaIgnore.remove(name);
							if(DataManager.players.get(p).whisperIgnore.contains(name))
								DataManager.players.get(p).whisperIgnore.remove(m);
							DataManager.players.get(p).FullyIgnore.add(name);
							super.m.sendMessage(sender, "�÷��̾� ����",new DoubleString("[���]",args[0]));
						}
					}
				}
			}else{
				super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
			}
		return false;
	}
	
}