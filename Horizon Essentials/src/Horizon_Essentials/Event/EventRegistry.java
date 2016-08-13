package Horizon_Essentials.Event;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import DataUtil.EssentialsPlayer;
import Horizon_Essentials.IMessageManager;
import Horizon_Essentials.IReportSystem;
import Horizon_Essentials.Main;

public class EventRegistry {
	public static IReportSystem m = new EssentialsPlayer();
	public IMessageManager msg = Main.getInst();
public static void registerEvents(){
	register(new Horizon_PlayerChatEvent());
	register(new Horizon_PlayerJoinEvent());
	register(new Horizon_MotdEvent());
	register(new Horizon_RespawnEvent());
	register(new Horizon_EventCancelActor());
	register(new Horizon_PlayerPreLoginEvent());
	register(new Horizon_PlayerTeleportEvent());
}

public static void register(Listener l)
{

	Bukkit.getPluginManager().registerEvents(l,Horizon_Essentials.Main.getInst());
}
}
