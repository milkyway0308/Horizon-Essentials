package Horizon_Essentials.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import DataUtil.EssentialsPlayer;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class Horizon_PlayerJoinEvent extends EventRegistry implements Listener{
@EventHandler(priority = EventPriority.LOWEST)
public void JoinEvent(PlayerJoinEvent e)
{
	if(!e.getPlayer().hasPlayedBefore())
	{
		if(DataManager.spawn.containsKey("default")){
			e.getPlayer().teleport(DataManager.spawn.get("default").getLocation());
		}else{
			if(e.getPlayer().getBedSpawnLocation() != null)
				e.getPlayer().teleport(e.getPlayer().getBedSpawnLocation());
			else{
				e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
			}
		}
	}
	if(!DataManager.players.containsKey(e.getPlayer()))
		DataManager.players.put(e.getPlayer(),EssentialsPlayer.LoadEssentialPlayer(e.getPlayer()));
	else
		DataManager.players.get(e.getPlayer()).updatePlayer(e.getPlayer());
	PlayerUtil.updatePlayerNicknames(e.getPlayer());
	DataManager.players.get(e.getPlayer()).noAddTime = System.currentTimeMillis() + 400;
	String s = e.getPlayer().getAddress().toString().split(":")[0];
	String ip = s.substring(1, s.length());
	DataManager.players.get(e.getPlayer()).ip = ip;
	DataManager.players.get(e.getPlayer()).last = System.currentTimeMillis();
	if(DataManager.connectedIPs.containsKey(ip))
		DataManager.connectedIPs.get(ip).add(e.getPlayer().getName());
	else
		DataManager.connectedIPs.put(ip, DataUtil.asArray(e.getPlayer().getName()));
}

@EventHandler(priority = EventPriority.LOWEST)
public void QuitEvent(PlayerQuitEvent e)
{
	if(DataManager.unrealname.containsKey(DataManager.getName(e.getPlayer().getName())))
		DataManager.unrealname.get(DataManager.getName(e.getPlayer().getName())).remove(e.getPlayer().getName());
	DataManager.players.get(e.getPlayer()).SaveEssentialPlayer();
	DataManager.players.get(e.getPlayer()).teleportRequest.clear();
	DataManager.players.get(e.getPlayer()).last = System.currentTimeMillis();

}
}
