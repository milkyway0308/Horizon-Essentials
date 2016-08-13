package Horizon_Essentials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import Utility.DataUtil;

public class SchedulerRunner {
	public static IMessageManager m = Main.getInst();
public static void runJailScheduler(){
	Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInst(), new Runnable(){
		@Override
		public void run() {
			for( Player p : DataUtil.getOnlinePlayers()){
				if(DataManager.players.get(p).isJail >= 0){
					if(DataManager.players.get(p).isJail <= System.currentTimeMillis()){
						DataManager.players.get(p).isJail = -1;
						if(DataManager.players.get(p).jail != null)
						{
							p.teleport(DataManager.players.get(p).jail.getLocation());
							DataManager.players.get(p).jail = null;
						}else{
							p.teleport(p.getWorld().getSpawnLocation());
						}
						
						m.sendMessage(p, "감옥에서 풀려남");
					}
				}
			}
		}
	},0L,10L);
}
}
