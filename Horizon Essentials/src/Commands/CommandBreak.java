package Commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandBreak extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandShock)){
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(sender instanceof Player){
				Player p = (Player)sender;
				Block b = p.getTargetBlock(null, 100);
				if(b.getType() == null || b.getType() == Material.AIR)
					super.m.sendMessage(sender, "Ÿ���õ� �� ����");
				else
					b.setType(Material.AIR);
		}else{
			super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
		}
		return false;
	}

}