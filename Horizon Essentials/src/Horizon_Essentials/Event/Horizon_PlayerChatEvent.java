package Horizon_Essentials.Event;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Horizon_Essentials.Main;
import Utility.DataUtil;
import Utility.PlayerUtil;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Horizon_PlayerChatEvent extends EventRegistry implements Listener{
@EventHandler(priority = EventPriority.LOWEST)
public void Chat(AsyncPlayerChatEvent e)
{
	if(DataManager.players.get(e.getPlayer()).canChat <= -10L)
		DataManager.players.get(e.getPlayer()).canChat = Long.MAX_VALUE;
	if(DataManager.players.get(e.getPlayer()).canChat >= System.currentTimeMillis()){
		e.setCancelled(true);
		double time = ( DataManager.players.get(e.getPlayer()).canChat - System.currentTimeMillis()) / 1000;
		super.msg.sendMessage(e.getPlayer(), "뮤트중",new DoubleString("[시간]",DataUtil.IntegerToTime((long)time)));
		return;
	}
	if(e.getPlayer().getName().equals(DataManager.players.get(e.getPlayer()).chatName)){
		if(e.getPlayer().isOp())
			e.setFormat(e.getFormat().replace("%1$s", DataManager.opprefix + DataManager.players.get(e.getPlayer()).chatName + "§f"));
	}else{
		e.setFormat(e.getFormat().replace("%1$s", DataManager.players.get(e.getPlayer()).chatName + "§f"));
	}

	if(DataManager.isColor)
		if(PlayerUtil.hasPermission(e.getPlayer(), PermissionType.ColorReplacer))
			 e.setMessage(e.getMessage().replaceAll("&([0-9a-fl-o])", "¡×$1"));;
	if(DataManager.isUsingPermissionPrefix)
	{
		if(Main.isPermissionEx())
			e.setFormat(PermissionsEx.getUser(e.getPlayer()).getGroups()[0].getPrefix().replace("&", "§") + "§f" + e.getFormat());
	}
	Set<Player> list = e.getRecipients();
	for(Player p : list)
		if(DataManager.players.get(p).chatIgnore.contains(e.getPlayer().getName().toLowerCase()) || DataManager.players.get(p).FullyIgnore.contains(e.getPlayer().getName().toLowerCase()))
			e.getRecipients().remove(p);
			
}
}
