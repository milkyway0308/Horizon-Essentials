package Horizon_Essentials.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import Horizon_Essentials.DataManager;
import Horizon_Essentials.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Horizon_RespawnEvent implements Listener{
@EventHandler
public void RespawnEvent(PlayerRespawnEvent e){
	if(Main.isPermissionEx()){
		if(DataManager.spawn.containsKey(PermissionsEx.getUser(e.getPlayer()).getGroups()[0].getName())){
			e.setRespawnLocation(DataManager.spawn.get(PermissionsEx.getUser(e.getPlayer()).getGroups()[0].getName()).getLocation());
		}else{
			if(DataManager.spawn.containsKey("default")){
				e.setRespawnLocation(DataManager.spawn.get("default").getLocation());
			}else{
				if(e.getPlayer().getBedSpawnLocation() != null)
					e.setRespawnLocation(e.getPlayer().getBedSpawnLocation());
				else{
					e.setRespawnLocation(e.getPlayer().getWorld().getSpawnLocation());
				}
			}
		}
	}else{
		if(DataManager.spawn.containsKey("default")){
			e.setRespawnLocation(DataManager.spawn.get("default").getLocation());
		}else{
			if(e.getPlayer().getBedSpawnLocation() != null)
				e.setRespawnLocation(e.getPlayer().getBedSpawnLocation());
			else{
				e.setRespawnLocation(e.getPlayer().getWorld().getSpawnLocation());
			}
		}
	}
		
}
}
