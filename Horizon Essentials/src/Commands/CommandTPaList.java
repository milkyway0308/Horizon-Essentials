package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandTPaList  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if(sender instanceof Player)
		{
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTpaList))
			{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
			}
			if(DataManager.players.get((Player)sender).teleportRequest.size() <= 0){
				 super.m.sendMessage(sender, "�ڷ���Ʈ ��û ������");
				 return false;
			}
			sender.sendMessage("   ��6���۵� �ڷ���Ʈ ��û ���");
			for(Player p : DataManager.players.get((Player)sender).teleportRequest){
				sender.sendMessage("��e- ��7" + p.getName());
			}
		}else{
			super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
		}
			return false;
	}
}