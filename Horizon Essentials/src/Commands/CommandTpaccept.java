package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandTpaccept extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if(sender instanceof Player)
		{
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTpaccept))
			{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
			}
			if(DataManager.players.get((Player)sender).teleportRequest.size() <= 0){
				 super.m.sendMessage(sender, "�ڷ���Ʈ ��û ������");
				 return false;
			}
			if(args.length <= 0)
			{
				Player p = (DataManager.players.get((Player)sender).teleportRequest.get((DataManager.players.get((Player)sender).teleportRequest.size()-1)));
				
				super.m.sendMessage(sender, "�ڷ���Ʈ ��û ����",new DoubleString("[���]",DataManager.getName(p.getName())));
				DataManager.players.get((Player)sender).teleportRequest.remove(DataManager.players.get((Player)sender).teleportRequest.size()-1);
			}else if(args.length == 1){
				Player p = Bukkit.getPlayer(args[0]);
				if(p == null)
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
				else{
					if(DataManager.players.get((Player)sender).teleportRequest.contains(p)){
						DataManager.players.get((Player)sender).teleportRequest.remove(p);
						super.m.sendMessage(sender, "�ڷ���Ʈ ��û ����",new DoubleString("[���]",DataManager.getName(p.getName())));
					}
				}
			}else{
				super.m.sendMessage(sender, "��ɾ� ����");
			}
		}else{
			super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
		}
		return false;
	}
}