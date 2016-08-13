package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandMore extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandMore))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.getItemInHand() != null && p.getItemInHand().getType() != null){
					if(p.getItemInHand().getAmount() <= 64)
						p.getItemInHand().setAmount(64);
				}
			}else{
				super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
			}
		return false;
	}
}
	