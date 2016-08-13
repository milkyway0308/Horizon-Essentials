package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandShock extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandShock)){
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 0)
			{
				Player p = (Player)sender;
				Block b = p.getTargetBlock(null, 100);
				if(b.getType() == null || b.getType() == Material.AIR)
					super.m.sendMessage(sender, "Ÿ���õ� �� ����");
				else
					b.getLocation().getWorld().strikeLightning(b.getLocation());
			}else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
				else{
					Player p = Bukkit.getPlayer(args[0]);
					p.getWorld().strikeLightning(p.getLocation());
				}
			}
		}else{
			if(args.length <= 0)
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�]"));
			else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
				else{
					Player p = Bukkit.getPlayer(args[0]);
					p.getWorld().strikeLightning(p.getLocation());
				}
			}
		}
		return false;
	}

}
