package Horizon_Essentials;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import DataUtil.ErrorType;
import DataUtil.EssentialsPlayer;
import DataUtil.LocationCrafter;
import Horizon_Essentials.Event.Horizon_EventCancelActor;
import Utility.DataUtil;

public class DataManager {
private static IReportSystem r= new EssentialsPlayer();
public static HashMap<String,String> msg = new HashMap<String, String>();
public static HashMap<OfflinePlayer,EssentialsPlayer> players = new HashMap<OfflinePlayer, EssentialsPlayer>();
public static HashMap<List<Integer>,List<String>> name = new HashMap<List<Integer>, List<String>>();
public static HashMap<String,ItemStack> items = new HashMap<String, ItemStack>();
public static HashMap<String,List<String>> unrealname = new HashMap<String,List<String>>();
public static HashMap<String,LocationCrafter> spawn = new HashMap<String,LocationCrafter>();
public static HashMap<String,LocationCrafter> jail = new HashMap<String,LocationCrafter>();
public static HashMap<String,List<String>> connectedIPs = new HashMap<String,List<String>>();
public static int tpAskDelay = 180;
public static int waitDelay = 3;
public static boolean isColor = true;
public static String opprefix = "§4";
public static boolean tpaMovestop = true;
public static int configversion = 0;
public static boolean isUsingPermissionPrefix = false;
public static int configdefversion = 1;
public static boolean isSaveEventCancel = false;
public static int defaulthome = 1;
public static int addJail = 30;
public static HashMap<String,LocationCrafter> warps = new HashMap<String,LocationCrafter>();
public static int summonamount = 35;
public static void LoadPlayer(Player p)
{
	players.put(p,new EssentialsPlayer(p));
}
public static String getName(String name)
{
	return (players.containsKey(Bukkit.getOfflinePlayer(name))) ? players.get(Bukkit.getOfflinePlayer(name)).chatName.replace("&", "§") : name;
}
public static void UnLoadPlayer(Player p)
{
	players.remove(p);
}
public static void loadIPLogs(){
	try {
		File f = new File("plugins/Horizon_Essentials/Logs/IPResult.log");
		if(!f.exists())
			return;
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		while((str = r.readLine()) != null){
			String[] a = str.split("::");
			connectedIPs.put(a[0], DataUtil.StringToArray(a[1]));
		}
		r.close();
	}catch(Exception e){}
}
public static void saveIPLogs(){
	try {
		File f = new File("plugins/Horizon_Essentials/Logs/IPResult.log");
		if(f.exists())
			f.delete();
		f.getParentFile().mkdirs();
		f.createNewFile();
		BufferedWriter r = new BufferedWriter(new FileWriter(f));
		for(String s : connectedIPs.keySet())
			r.append(s + "::" + DataUtil.ArrayToString(connectedIPs.get(s)));
		r.flush();
		r.close();
	}catch(Exception e){}
}
public static int[] loadItems(){
	items.clear();
	name.clear();
	int loaded = 0;
	int check = 0;
	File f = new File("plugins/Horizon_Essentials/items.txt");
	if(!f.exists())
		Main.getInst().saveResource("items.txt", true);
	try {
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		List<String> temp = new ArrayList<String>();
		while((str = r.readLine()) != null)
		{
			loaded++;
			try{
			temp = new ArrayList<String>();
			int a = str.indexOf(":");
			if (a <= -1) 
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + 1, str.length());
			List<Integer> lister = new ArrayList<Integer>();
			String[] b = s1.split("\\+");
			if(b.length <= 1)
			{
				lister.add(Integer.parseInt(b[0]));
				lister.add(0);
			}
			else
			{
				lister.add(Integer.parseInt(b[0]));
				lister.add(Integer.parseInt(b[1]));
			}
			String[] b5 = s2.split("\\|");

			ItemStack stack = new ItemStack(Material.getMaterial(lister.get(0)),Short.valueOf(String.valueOf(lister.get(0))));
			if(lister.size() == 2)
				stack.setDurability(Short.valueOf(String.valueOf(lister.get(1))));
			for(String s : b5)
			{
				check++;
				temp.add(s);
				items.put(s, stack);
			}
			name.put(lister, temp);
			
			}catch(Exception e){e.printStackTrace();}
		}
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	return new int[]{loaded,check};
}

public static int loadWarps(){
	warps.clear();
	int loaded = 0;
	File f = new File("plugins/Horizon_Essentials/Location/Warp.txt");
	if(f.exists())
	{
	try {
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		while((str = r.readLine()) != null)
		{
			loaded++;
			try{
			int a = str.indexOf("!///!!//!/!");
			if (a <= -1) 
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + "!///!!//!/!".length(), str.length());
			LocationCrafter loc = LocationCrafter.toLocationCrafter(s2);
			if(loc != null)
				warps.put(s1, loc);
			}catch(Exception e){e.printStackTrace();}
			loaded++;
		}
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	}
	return loaded;
}

public static void saveJails(){
	File f = new File("plugins/Horizon_Essentials/Location/Jail.txt");
	if(f.exists())
		f.delete();
	f.getParentFile().mkdirs();
	try {
		f.createNewFile();
	} catch (IOException e1) {
	}
	try {
		BufferedWriter r = new BufferedWriter(new FileWriter(f));
		for(String s : jail.keySet())
			r.append(s + "!///!!//!/!" + jail.get(s).toString() + "\n");
		r.flush();
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	
}
public static int loadJails(){
	warps.clear();
	int loaded = 0;
	File f = new File("plugins/Horizon_Essentials/Location/Jail.txt");
	if(f.exists())
	{
	try {
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		while((str = r.readLine()) != null)
		{
			loaded++;
			try{
			int a = str.indexOf("!///!!//!/!");
			if (a <= -1) 
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + "!///!!//!/!".length(), str.length());
			LocationCrafter loc = LocationCrafter.toLocationCrafter(s2);
			if(loc != null)
				jail.put(s1, loc);
			}catch(Exception e){e.printStackTrace();}
			loaded++;
		}
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	}
	return loaded;
}

public static void saveWarps(){
	File f = new File("plugins/Horizon_Essentials/Location/Warp.txt");
	if(f.exists())
		f.delete();
	f.getParentFile().mkdirs();
	try {
		f.createNewFile();
	} catch (IOException e1) {
	}
	try {
		BufferedWriter r = new BufferedWriter(new FileWriter(f));
		for(String s : warps.keySet())
			r.append(s + "!///!!//!/!" + warps.get(s).toString() + "\n");
		r.flush();
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	
}
public static int loadSpawns(){
	warps.clear();
	int loaded = 0;
	File f = new File("plugins/Horizon_Essentials/Location/Spawn.txt");
	if(f.exists())
	{
	try {
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		while((str = r.readLine()) != null)
		{
			loaded++;
			try{
			int a = str.indexOf("!///!!//!/!");
			if (a <= -1) 
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + "!///!!//!/!".length(), str.length());
			
			LocationCrafter loc = LocationCrafter.toLocationCrafter(s2);
			if(loc != null)
				spawn.put(s1, loc);
			}catch(Exception e){e.printStackTrace();}
			loaded++;
		}
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	}
	return loaded;
}

public static void saveSpawns(){
	File f = new File("plugins/Horizon_Essentials/Location/Spawn.txt");
	if(f.exists())
		f.delete();
	f.getParentFile().mkdirs();
	try {
		f.createNewFile();
	} catch (IOException e1) {
	}
	try {
		BufferedWriter r = new BufferedWriter(new FileWriter(f));
		for(String s : spawn.keySet())
			r.append(s + "!///!!//!/!" + spawn.get(s).toString() + "\n");
		r.flush();
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	
}
public static void saveState(){
	File f = new File("plugins/Horizon_Essentials/State.txt");
	if(f.exists())
		f.delete();
	f.getParentFile().mkdirs();
	try {
		f.createNewFile();
	} catch (IOException e1) {
	}
	try {
		BufferedWriter r = new BufferedWriter(new FileWriter(f));
		r.append("동작:" + Horizon_EventCancelActor.move + "\n");
		r.append("아이템 픽업:" + Horizon_EventCancelActor.ItemPickup + "\n");
		r.append("블럭 파괴:" + Horizon_EventCancelActor.BlockBreak + "\n");
		r.append("블럭 설치:" + Horizon_EventCancelActor.BlockPlace + "\n");
		r.append("블럭 데미지:" + Horizon_EventCancelActor.BlockDamage + "\n");
		r.append("상호 작용:" + Horizon_EventCancelActor.Interact + "\n");
		r.append("채팅:" + Horizon_EventCancelActor.Chat + "\n");
		r.append("아이템 드랍:" + Horizon_EventCancelActor.ItemDrop + "\n");
		r.append("무적:" + Horizon_EventCancelActor.Damage + "\n");
		r.append("pvp:" + Horizon_EventCancelActor.pvp + "\n");
		r.append("블럭 폭발:" + Horizon_EventCancelActor.BlockExplosion + "\n");
		r.append("레벨 제한:" + Horizon_EventCancelActor.Level_Up + "\n");
		r.append("레벨:" + Horizon_EventCancelActor.level);
		r.flush();
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
	
}
public static void loadState(){

	File f = new File("plugins/Horizon_Essentials/State.txt");
	if(!f.exists())
		return;
	try {
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		while((str = r.readLine()) != null)
		{
			try{
			int a = str.indexOf(":");
			if (a <= -1) 
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + 1, str.length());
			
			switch(s1){
			case "동작":
				Horizon_EventCancelActor.move = Boolean.parseBoolean(s2);
				break;
			case "아이템 픽업":
				Horizon_EventCancelActor.ItemPickup = Boolean.parseBoolean(s2);
				break;
			case "블럭 파괴":
				Horizon_EventCancelActor.BlockBreak = Boolean.parseBoolean(s2);
				break;
			case "블럭 설치":
				Horizon_EventCancelActor.BlockPlace = Boolean.parseBoolean(s2);
				break;
			case "블럭 데미지":
				Horizon_EventCancelActor.BlockDamage = Boolean.parseBoolean(s2);
				break;
			case "상호 작용":
				Horizon_EventCancelActor.Interact = Boolean.parseBoolean(s2);
				break;
			case "채팅":
				Horizon_EventCancelActor.Chat = Boolean.parseBoolean(s2);
				break;
			case "아이템 드랍":
				Horizon_EventCancelActor.ItemDrop = Boolean.parseBoolean(s2);
				break;
			case "무적":
				Horizon_EventCancelActor.Damage = Boolean.parseBoolean(s2);
				break;
			case "pvp":
				Horizon_EventCancelActor.pvp = Boolean.parseBoolean(s2);
				break;
			case "레벨 제한":
				Horizon_EventCancelActor.Level_Up = Boolean.parseBoolean(s2);
				break;
			case "블럭 폭발":
				Horizon_EventCancelActor.BlockExplosion = Boolean.parseBoolean(s2);
				break;
			case "레벨":
				try{
					Horizon_EventCancelActor.level = Integer.parseInt(s2);
				}catch(Exception e){}
				break;
				
			}
			}catch(Exception e){e.printStackTrace();}
		}
		r.close();
	} catch (FileNotFoundException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);
	} catch (IOException e) {
		r.Report(Bukkit.getConsoleSender(), ErrorType.IOException);
	}
}
}
