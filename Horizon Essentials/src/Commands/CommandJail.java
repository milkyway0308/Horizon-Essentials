package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.LocationCrafter;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandJail extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandJail)){
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(args.length <= 2)
		{
			super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] [�����̸�] [�ð�]"));
			return false;
		}
		if(Bukkit.getPlayer(args[0]) == null)
			super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
		else{
			Player p = Bukkit.getPlayer(args[0]);
			if(!DataManager.jail.containsKey(args[1]))
				super.m.sendMessage(sender, "���� ������");
			else{
				int time = 0;
				try{
					time = Integer.parseInt(args[2]);
				}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
				DataManager.players.get(p).jail = new LocationCrafter(p.getLocation());
				p.teleport(DataManager.jail.get(args[1]).getLocation());
				super.m.sendMessage(sender, "���� ����",DataUtil.asArray(new DoubleString("[���]",args[0]),new DoubleString("[����]",args[1]),new DoubleString("[�ð�]",DataUtil.IntegerToTime(time))));
				super.m.sendMessage(p,"���� ������",new DoubleString("[�ð�]",DataUtil.IntegerToTime(time)));
				DataManager.players.get(p).isJail = System.currentTimeMillis() + (double)time * (double)1000;
				

			}
		}
		return false;
	}

}
