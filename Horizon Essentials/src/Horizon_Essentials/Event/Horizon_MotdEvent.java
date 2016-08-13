package Horizon_Essentials.Event;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import DataUtil.ErrorType;

public class Horizon_MotdEvent extends EventRegistry implements Listener{
public static List<String> motd = new ArrayList<String>();
final static File f = new File("plugins/Horizon_Essentials/MotdList.txt");
@EventHandler(priority = EventPriority.LOWEST)
public void Motd(ServerListPingEvent e)
{
	if(motd.size() <= 0)
	{
		e.setMotd("§c§o[Horizon Essentials]§f motd가 설정되지 않았습니다.");
	}else if(motd.size() == 1)
	{	String s = motd.get(0);
		s = s.replace("[최대 플레이어]", String.valueOf(Bukkit.getMaxPlayers()));
		s = s.replace("[현재 플레이어]", String.valueOf(Bukkit.getOnlinePlayers().length));
		s = s.replace("[동접]", String.valueOf(Bukkit.getOnlinePlayers().length));
		e.setMotd(s);
	}else{
		String s = motd.get(new Random().nextInt(motd.size()));
		s = s.replace("[최대 플레이어]", String.valueOf(Bukkit.getMaxPlayers()));
		s = s.replace("[현재 플레이어]", String.valueOf(Bukkit.getOnlinePlayers().length));
		s = s.replace("[동접]", String.valueOf(Bukkit.getOnlinePlayers().length));
		e.setMotd(s);
	}		
}
	
public static void Save(){
	if(motd.size() >= 1)
	{
		try {
			f.delete();
				f.getParentFile().mkdirs();
				f.createNewFile();
			BufferedWriter r = new BufferedWriter(new FileWriter(f));
			for(String s : motd)
			{
				r.append(s + "\n");
			}
			r.flush();
			r.close();
		} catch (IOException e) {
			m.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
		}
	}
	return;
}

public static void Load(){

	motd.clear();
	if(!f.exists())
		return;
	try{
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		while((str = r.readLine()) != null)
		{
			motd.add(str);
		}
		r.close();
	}catch(FileNotFoundException e){m.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);}catch(IOException e){m.Report(Bukkit.getConsoleSender(), ErrorType.IOException);}
	
}

}
