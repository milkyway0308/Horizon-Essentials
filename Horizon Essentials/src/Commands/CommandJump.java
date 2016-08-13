package Commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandJump extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandJump))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if(sender instanceof Player){
				Location loc = ((Player)sender).getTargetBlock(null, 300).getLocation();
				if(loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == null){
					super.m.sendMessage(sender, "Ÿ���õ� �� ����");
				}else{
					Location tel = new Location(loc.getWorld(),loc.getX(),loc.getY(),loc.getZ());
					if(tel.add(0,1,0).getBlock().getType() == null || tel.add(0,1,0).getBlock().getType() == Material.AIR)
					{
						
					}else{
						l1:for(int i = -1;i < 2;i++){
							for(int i2 = -1;i2 < 2;i2++){
								if(tel.add(i,1,i2).getBlock().getType() == null || tel.add(i,1,i2).getBlock().getType() == Material.AIR)
								{
									tel = tel.add(i,0,i2);
									break l1;
								}
							}
						}
					}
					tel.setYaw(((Player)sender).getLocation().getYaw());
					tel.setPitch(((Player)sender).getLocation().getPitch());
					((Player)sender).teleport(tel);
				}
			}else{
				super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
			}
		return false;
	}
}
	