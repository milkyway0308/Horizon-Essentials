package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandMute extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandMute))
			{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
			}
			if(args.length <= 1){
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] [�ð�(����)]"));
			}else{
				try{
					int a = Integer.parseInt(args[1]);
					if(a <= 0)
						super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");
					else{
						Player p = Bukkit.getPlayer(args[0]);
						if(p == null)
							super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
						else{
							DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).canChat = (double)System.currentTimeMillis() + (double)a * 1000;
							DataUtil.OperatorMessage(super.m.getMessage("ä�� ��Ʈ").replace("[�ð�]", DataUtil.IntegerToTime(a)).replace("[������]",sender.getName()).replace("[���]", p.getName()));
						}
					}
				}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");}
			}
		return false;
	}
}