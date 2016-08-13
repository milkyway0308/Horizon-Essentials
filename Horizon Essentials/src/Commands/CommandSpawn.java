package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Horizon_Essentials.Main;
import Utility.PlayerUtil;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CommandSpawn extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandSpawn))
		{
			if(sender instanceof Player){
				Player p = (Player)sender;
					if(args.length <= 0)
					{
						if(Main.isPermissionEx()){
							System.out.println(Main.isPermissionEx());
							if(DataManager.spawn.containsKey(PermissionsEx.getUser(p).getGroups()[0].getName())){
								p.teleport(DataManager.spawn.get(PermissionsEx.getUser(p).getGroups()[0].getName()).getLocation());
							}else{
								if(DataManager.spawn.containsKey("default")){
									p.teleport(DataManager.spawn.get("default").getLocation());
								}else{
									Location loc = p.getBedSpawnLocation();
									if(loc == null)
										p.teleport(p.getWorld().getSpawnLocation());
									else
										p.teleport(loc);
								}
							}
						}else{
							if(DataManager.spawn.containsKey("default")){
								p.teleport(DataManager.spawn.get("default").getLocation());
							}else{
								Location loc = p.getBedSpawnLocation();
								if(loc == null)
									p.teleport(p.getWorld().getSpawnLocation());
								else
									p.teleport(loc);
							}
						}
					}else if(args.length == 1){
						Player p2 = Bukkit.getPlayer(args[0]);
						if(p2 == null){
							super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
							return false;
						}
						if(Main.isPermissionEx()){
							if(DataManager.spawn.containsKey(PermissionsEx.getUser(p2).getGroups()[0].getName())){
								p2.teleport(DataManager.spawn.get(PermissionsEx.getUser(p2).getGroups()[0].getName()).getLocation());
							}else{
								if(DataManager.spawn.containsKey("default")){
									p2.teleport(DataManager.spawn.get("default").getLocation());
								}else{
									Location loc = p.getBedSpawnLocation();
									if(loc == null)
										p.teleport(p.getWorld().getSpawnLocation());
									else
										p.teleport(loc);
								}
							}
						}else{
							if(DataManager.spawn.containsKey("default")){
								p2.teleport(DataManager.spawn.get("default").getLocation());
							}else{
								Location loc = p.getBedSpawnLocation();
								if(loc == null)
									p.teleport(p.getWorld().getSpawnLocation());
								else
									p.teleport(loc);
							}
						}
						super.m.sendMessage(sender, "���� �̵�");
					}else if(args.length == 2){
						Player p2 = Bukkit.getPlayer(args[0]);
						if(p2 == null){
							super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
							return false;
						}
						boolean msg = Boolean.parseBoolean(args[2]);
						if(Main.isPermissionEx()){
							if(DataManager.spawn.containsKey(PermissionsEx.getUser(p2).getGroups()[0].getName())){
								p2.teleport(DataManager.spawn.get(PermissionsEx.getUser(p2).getGroups()[0].getName()).getLocation());
							}else{
								if(DataManager.spawn.containsKey("default")){
									p2.teleport(DataManager.spawn.get("default").getLocation());
								}else{
									Location loc = p2.getBedSpawnLocation();
									if(loc == null)
										p2.teleport(p2.getWorld().getSpawnLocation());
									else
										p2.teleport(loc);
								}
							}
						}else{
							if(DataManager.spawn.containsKey("default")){
								p2.teleport(DataManager.spawn.get("default").getLocation());
							}else{
								Location loc = p2.getBedSpawnLocation();
								if(loc == null)
									p2.teleport(p2.getWorld().getSpawnLocation());
								else
									p2.teleport(loc);
							}
						}
						if(msg){
							super.m.sendMessage(sender, "���� ���� �̵�");
							super.m.sendMessage(p2, "���� �̵�");
						}
					}else{
						super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] [�޽��� ��� ����(true/false)]"));
					}
			}else{
				super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
			}
		}
	return false;
	}
}