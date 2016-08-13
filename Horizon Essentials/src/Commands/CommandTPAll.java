package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandTPAll  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTpall))
			{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
			}
			if(args.length <= 0){
				
				if(sender instanceof Player){
					super.m.sendMessage(sender, "��ü �ڷ���Ʈ");
					for(Player p : Bukkit.getOnlinePlayers())
						if(!p.getName().equals(sender.getName()))
							p.teleport(((Player)sender).getLocation());
				}else{
					super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [x] [y] [z] [����]"));
				}
			}else if(args.length == 4){
				World w = Bukkit.getWorld(args[3]);
				if(w == null)
					super.m.sendMessage(sender, "���� ������");
				else{
					try{
						Location loc = new Location(w,Double.parseDouble(args[0]),Double.parseDouble(args[1]),Double.parseDouble(args[2]));
						super.m.sendMessage(sender, "��ü �ڷ���Ʈ ��ǥ",new DoubleString("[��ǥ]",w.getName() + "," + args[0] + "," + args[1] + "," + args[2]));
						for(Player p : Bukkit.getOnlinePlayers())
							p.teleport(loc);
					}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");}
				}
			}else{
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [x] [y] [z] [����]"));
			}
			return false;
	}
}