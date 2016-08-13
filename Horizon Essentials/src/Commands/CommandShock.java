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
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 0)
			{
				Player p = (Player)sender;
				Block b = p.getTargetBlock(null, 100);
				if(b.getType() == null || b.getType() == Material.AIR)
					super.m.sendMessage(sender, "타게팅된 블럭 없음");
				else
					b.getLocation().getWorld().strikeLightning(b.getLocation());
			}else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
				else{
					Player p = Bukkit.getPlayer(args[0]);
					p.getWorld().strikeLightning(p.getLocation());
				}
			}
		}else{
			if(args.length <= 0)
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어]"));
			else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
				else{
					Player p = Bukkit.getPlayer(args[0]);
					p.getWorld().strikeLightning(p.getLocation());
				}
			}
		}
		return false;
	}

}
