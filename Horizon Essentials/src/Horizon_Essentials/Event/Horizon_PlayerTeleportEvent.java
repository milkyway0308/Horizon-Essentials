package Horizon_Essentials.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import DataUtil.DoubleString;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;

public class Horizon_PlayerTeleportEvent extends EventRegistry implements Listener{
@EventHandler
public void playerTeleport(PlayerTeleportEvent e){
	if(DataManager.players.get(e.getPlayer()).noAddTime <= System.currentTimeMillis()){
		if(DataManager.players.get(e.getPlayer()).isJail >= 1)
		{
			DataManager.players.get(e.getPlayer()).isJail += (double)DataManager.addJail * (double)1000;
			super.msg.sendMessage(e.getPlayer(), "Ż�� �õ� �ð� �߰�",new DoubleString("[�ð�]",DataUtil.IntegerToTime(DataManager.addJail)));
		}
	}
}
}
