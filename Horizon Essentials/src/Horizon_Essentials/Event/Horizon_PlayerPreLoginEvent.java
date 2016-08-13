package Horizon_Essentials.Event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import Horizon_Essentials.DataManager;
import Utility.DataUtil;

public class Horizon_PlayerPreLoginEvent extends EventRegistry implements Listener{
	@EventHandler(priority = EventPriority.MONITOR)
public void PreLogin(PlayerLoginEvent e){
	if(!DataManager.players.containsKey(Bukkit.getOfflinePlayer(e.getPlayer().getName())))
		DataManager.LoadPlayer(e.getPlayer());
	if(DataManager.players.get((Bukkit.getOfflinePlayer(e.getPlayer().getName()))).tempBan >= System.currentTimeMillis())
	{
		e.disallow(Result.KICK_BANNED, super.msg.getMessage("시간 밴 메시지").replace("/*nextline*/", "\n").replace("[시간]", DataUtil.IntegerToTime((long)((DataManager.players.get((Bukkit.getOfflinePlayer(e.getPlayer().getName()))).tempBan) - (double)System.currentTimeMillis())/1000)).replace("[사유]", DataManager.players.get((Bukkit.getOfflinePlayer(e.getPlayer().getName()))).banCause));
	}
}
}
