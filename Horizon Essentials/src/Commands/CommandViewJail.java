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

public class CommandViewJail extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandViewJail)){
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 0)
			{
				Player p = (Player)sender;
				if(DataManager.players.get(p).isJail <= -1)
					super.m.sendMessage(sender, "���� �ȵ�");
				else
					super.m.sendMessage(sender, "���� �ð�",new DoubleString("[�ð�]",DataUtil.IntegerToTime((long)(DataManager.players.get(p).isJail - System.currentTimeMillis()) / 1000)));
			}else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
				else{
					if(!DataManager.players.containsKey(args[0]))
						super.m.sendMessage(sender, "Ÿ�� ���� �ȵ�",new DoubleString("[���]",args[0]));
					else{
						Player p = Bukkit.getPlayer(args[0]);
						if(DataManager.players.get(p).isJail <= -1)
							super.m.sendMessage(sender, "Ÿ�� ���� �ȵ�",new DoubleString("[���]",args[0]));
						else
							super.m.sendMessage(sender, "Ÿ�� ���� �ð�",DataUtil.asArray(new DoubleString("[�ð�]",DataUtil.IntegerToTime((long)(DataManager.players.get(p).isJail - System.currentTimeMillis()) / 1000)),new DoubleString("[���]",args[0])));
					}
				}
			}
		}else{
			
		}
		return false;
	}

}
