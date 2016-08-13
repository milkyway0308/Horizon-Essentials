package Horizon_Essentials;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import Commands.CommandsRegistry;
import DataUtil.DoubleString;
import DataUtil.ErrorType;
import DataUtil.EssentialsPlayer;
import HorizonEssentials.Economy.EssentialsBase;
import Horizon_Essentials.Event.EventRegistry;
import Horizon_Essentials.Event.Horizon_MotdEvent;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class Main extends JavaPlugin implements IMessageManager{
private IReportSystem c = new EssentialsPlayer();
private static Main instance = null;
public static boolean endThread = false;
public static JavaPlugin usingPermission;
private static String UsingPermission = "noPermissionHooked";
public void onEnable() {
	Bukkit.getConsoleSender().sendMessage("  §7|  §cHorizon Essentials Version " + this.getDescription().getVersion());
	Bukkit.getConsoleSender().sendMessage(" §7- Loading Plugin");
	long time = System.currentTimeMillis();
	Bukkit.getConsoleSender().sendMessage(" §7* §6MainClass Load Success");
	Bukkit.getConsoleSender().sendMessage(" §7- Initalizing Variables");
	instance = this;
	ServerInfomation.last = System.currentTimeMillis();
	ServerInfomation.runTPSChecker();
	c = new EssentialsPlayer();
	Bukkit.getConsoleSender().sendMessage(" §7* §6Initalized Variables");
	Bukkit.getConsoleSender().sendMessage(" §7- Loading Plugin Message From File");
	int a = getMessageFromFile();
	DataManager.loadIPLogs();
	Bukkit.getConsoleSender().sendMessage(" §7* §6Complete (" + a + " Message Loaded)");
	Bukkit.getConsoleSender().sendMessage(" §7- Loading Plugin Location");
	int c = DataManager.loadWarps() + DataManager.loadSpawns() + DataManager.loadJails();
	Bukkit.getConsoleSender().sendMessage(" §7* §6Complete (" + c + " Location Loaded)");
	Bukkit.getConsoleSender().sendMessage(" §7- Loading Config Options From File");
	int e = this.getConfigFromFile();
	Bukkit.getConsoleSender().sendMessage(" §7* §6Complete (" + e + " Config Options)");
	Bukkit.getConsoleSender().sendMessage(" §7- Loading Item Names From File");
	int[] b = DataManager.loadItems();
	Bukkit.getConsoleSender().sendMessage(" §7* §6Complete (" + b[0] + " Item Code With " + b[1] + " name Loaded)");
	Bukkit.getConsoleSender().sendMessage(" §7- Initalizing Commands and Plugins");
	EventRegistry.registerEvents();
	CommandsRegistry.Registercommands();
	Horizon_MotdEvent.Load();
	Bukkit.getConsoleSender().sendMessage(" §7* §6Complete With " + (System.currentTimeMillis() - time) + "ms");
	if(DataUtil.getOnlinePlayers().size() >= 1)
	{
		for(Player p : DataUtil.getOnlinePlayers())
		{
			DataManager.players.put(p,EssentialsPlayer.LoadEssentialPlayer(p));
			PlayerUtil.updatePlayerNicknames(p);
		}
		Bukkit.getConsoleSender().sendMessage(" §7* §6Loaded Player's Data");
	}
	
	Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
		@Override
		public void run() {
			new Thread(){
				@SuppressWarnings("deprecation")
				public void run() {
					if(endThread)
						stop();
					long startms = System.currentTimeMillis();
					Bukkit.getConsoleSender().sendMessage(" §7* §6Starting running player saving thread..");
					for(OfflinePlayer p : DataManager.players.keySet())
					{
						DataManager.players.get(p).SaveEssentialPlayer();
					}
					Bukkit.getConsoleSender().sendMessage(" §7* §6Complete With " + (System.currentTimeMillis() - startms) + "ms");
					DataManager.saveWarps();
					DataManager.saveSpawns();
					DataManager.saveJails();
					DataManager.saveIPLogs();
				};
			}.start();
		}
	},0L,24000L);
	if(DataManager.configversion < DataManager.configdefversion)
		Bukkit.getConsoleSender().sendMessage(" §c* 콘피그 버전이 최신 버전보다 낮습니다.콘피그 파일을 삭제하고 재생성해주세요.");
	EssentialsPlayer.loadAllPlayer();
	Bukkit.getConsoleSender().sendMessage("[Horizon Essentials] §6Special Thanks to:");
	Bukkit.getConsoleSender().sendMessage("[Horizon Essentials] §7Baw_Appie,Soa_,PeraSite,SM_seolmin,ShiraiNa,kang0432");
	SchedulerRunner.runJailScheduler();
}
@Override
	public void onDisable() {
	endThread = true;
	Horizon_MotdEvent.Save();
	for(OfflinePlayer p : DataManager.players.keySet())
	{
		DataManager.players.get(p).SaveEssentialPlayer();
	}
	DataManager.saveWarps();
	DataManager.saveSpawns();
	DataManager.saveJails();
	DataManager.saveIPLogs();
	if(DataManager.isSaveEventCancel)
		DataManager.saveState();
	}
public void lookPermission(){
	if(Bukkit.getPluginManager().getPlugin("PermissionsEx") != null)
	{
		UsingPermission = "PermissionsEx";
		usingPermission = (JavaPlugin) Bukkit.getPluginManager().getPlugin("PermissionsEx");
	}
}
@Override
public int getMessageFromFile() { //이클래스는 방금 그해시맵에 있는게 정상아니던가..
	File f = new File("plugins/Horizon_Essentials/Message.lang");
	int loaded = 0;
	if(!f.exists())
		this.saveResource("Message.lang", true);
	try{
		BufferedReader r = new BufferedReader(new FileReader(f));
		String s;
		while((s = r.readLine()) != null)
		{
			int a = s.indexOf(":");
			if (a <= -1)
				continue;
			String s1 = s.substring(0, a);
			String s2 = s.substring(a + 1, s.length());
			DataManager.msg.put(s1, s2);
			loaded++;
		}
		r.close();;
	}catch(IOException e){c.Report(Bukkit.getConsoleSender(), ErrorType.IOException);} // 이거 문제는 아닌거같은데
	return loaded;
}

public int getConfigFromFile() { //이클래스는 방금 그해시맵에 있는게 정상아니던가..
	File f = new File("plugins/Horizon_Essentials/configuration.txt");
	int loaded = 0;
	if(!f.exists())
		this.saveResource("configuration.txt", true);
	try{
		BufferedReader r = new BufferedReader(new FileReader(f));
		String s;
		while((s = r.readLine()) != null)
		{
			if(s.startsWith("#"))
				continue;
			int a = s.indexOf(":");
			if (a <= -1)
				continue;
			String s1 = s.substring(0, a);
			String s2 = s.substring(a + 1, s.length());
			switch(s1){
			case "텔레포트 요청 시간":
				try{
					DataManager.tpAskDelay = Integer.parseInt(s2.replace("초", "").replace(" ", ""));
					loaded++;
				}catch(Exception e){}
				if(DataManager.tpAskDelay <= 0)
					DataManager.tpAskDelay  = 1;
				break;
			case "텔레포트 대기 시간":

				try{
					DataManager.waitDelay = Integer.parseInt(s2.replace("초", "").replace(" ", ""));
				}catch(Exception e){}
				loaded++;
				if(DataManager.waitDelay <= -1)
					DataManager.waitDelay = 0;
				break;
			case "색깔채팅":
				DataManager.isColor = Boolean.parseBoolean(s2);
				loaded++;
				break;
			case "관리자 채팅":
				DataManager.opprefix = s2.replace("&", "§");
				loaded++;
				break;
			case "텔레포트 동작 중단 여부":
				DataManager.tpaMovestop = Boolean.valueOf(s2);
				break;
			case "콘피그 버전":
				try{
					DataManager.configversion = Integer.parseInt(s2.replace(" ", ""));
				}catch(Exception e){DataManager.configversion = 0;}
				break;
			case "퍼미션 칭호":
				DataManager.isUsingPermissionPrefix = Boolean.parseBoolean(s2);
				break;
			case "이코노미 기본 통화":
				EssentialsBase.economyMain = s2;
				break;
			case "이코노미 서브 통화":
				EssentialsBase.economySub = s2;
				break;
			case "이코노미 서브 통화 사용":
				EssentialsBase.useSub = Boolean.parseBoolean(s2);
				break;
			case "이코노미 소숫점 표시":
				EssentialsBase.usePoint = Boolean.parseBoolean(s2);
				break;
			case "이코노미 기본":
				 try{
					 EssentialsBase.defaultmoney = Double.parseDouble(s2.replace(" ", ""));
				 }catch(Exception e){}
				 break;
			case "이코노미 최대":
				 try{
					 EssentialsBase.EssentialsMaxValue = Double.parseDouble(s2.replace(" ", ""));
				 }catch(Exception e){}
				 break;
			case "이코노미 최소":
				 try{
					 EssentialsBase.EssentialsMinValue = Double.parseDouble(s2.replace(" ", ""));
				 }catch(Exception e){}
				 break;
			case "이벤트 데이터 저장 여부":
				DataManager.isSaveEventCancel = Boolean.parseBoolean(s2);
				break;
			case "기본 홈":
				 try{
					DataManager.defaulthome = Integer.parseInt(s2.replace(" ", ""));
					if(DataManager.defaulthome <= -1)
						DataManager.defaulthome = 0;
				 }catch(Exception e){}
			case "엔티티 소환 제한":
				 try{
					DataManager.summonamount = Integer.parseInt(s2.replace(" ", ""));
					if(DataManager.summonamount <= 0)
						DataManager.summonamount = 1;
				 }catch(Exception e){}
			case "감옥 탈출 패널티":
				 try{
						DataManager.addJail = Integer.parseInt(s2.replace(" ", ""));
						if(DataManager.addJail <= -1)
							DataManager.addJail = 0;
				 }catch(Exception e){}
				break;
				
			}
		}
		r.close();
		if(DataManager.isSaveEventCancel)
			DataManager.loadState();
	}catch(IOException e){c.Report(Bukkit.getConsoleSender(), ErrorType.IOException);} // 이거 문제는 아닌거같은데
	return loaded;
}

@Override
public void sendMessage(Player p, String msgType) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s == null)
			c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);
		if(s.equals("null") || s.equals(""))
			return;
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		c.Report(p, ErrorType.NullMessageException);
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}

@Override
public String getMessage(String msgType) {
	String s = null;
	if(DataManager.msg.containsKey(msgType))
		s = DataManager.msg.get(msgType);
	else
		c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);
	return s;
}
@Override
public void sendMessage(CommandSender p, String msgType) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s == null)
			c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);
		if(s.equals("null") || s.equals(""))
			return;
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		c.Report(p, ErrorType.NullMessageException);
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}

public void sendMessage(CommandSender p, String msgType,boolean replacespace) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s == null)
			c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);
		if(s.equals("null") || s.equals(""))
			return;
		if(replacespace)
			s = s.replace(" ", "");
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		c.Report(p, ErrorType.NullMessageException);
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}
@Override
public void sendMessage(CommandSender p, String msgType, DoubleString replacement) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		s = s.replace(replacement.s1, replacement.s2);
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}

public void sendMessage(CommandSender p, String msgType, DoubleString replacement,boolean replacespace) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		if(replacespace)
			s = s.replace(" ", "");
		s = s.replace(replacement.s1, replacement.s2);
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}
@Override
public void sendMessage(CommandSender p, String msgType, List<DoubleString> replacement) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		for(DoubleString s1 : replacement)
		{
			s = s.replace(s1.s1, s1.s2);
		}
		
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}

public void sendMessage(CommandSender p, String msgType, List<DoubleString> replacement,boolean replacespace) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		for(DoubleString s1 : replacement)
		{
			s = s.replace(s1.s1, s1.s2);
		}
		if(replacespace)
			s = s.replace(" ", "");
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}



@Override
public void sendMessage(Player p, String msgType, DoubleString replacement) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		s = s.replace(replacement.s1, replacement.s2);
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}

public void sendMessage(Player p, String msgType, DoubleString replacement,boolean replacespace) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		s = s.replace(replacement.s1, replacement.s2);
		if(replacespace)
			s = s.replace(" ", "");
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}
@Override
public void sendMessage(Player p, String msgType, List<DoubleString> replacement) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		for(DoubleString s1 : replacement)
		{
			s = s.replace(s1.s1, s1.s2);
		}
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}


public void sendMessage(Player p, String msgType, List<DoubleString> replacement,boolean replacespace) {
	try{
	if(DataManager.msg.containsKey(msgType))
	{
		String s = DataManager.msg.get(msgType);
		if(s.equals("null") || s.equals(""))
			return;
		for(DoubleString s1 : replacement)
		{
			s = s.replace(s1.s1, s1.s2);
		}
		if(replacespace)
			s = s.replace(" ", "");
		for(String s1 : s.split("/\\*nextline\\*/"))
			p.sendMessage(s1);
	}
	else
		try{
			c.Report(p, ErrorType.NullMessageException);
		}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
	}catch(Exception e){c.Report(Bukkit.getConsoleSender(), ErrorType.NullMessageException);}
}

public static boolean isPermissionEx(){
	return UsingPermission.equals("PermissionsEx");
}
public static boolean checkEconomy(){
	if(Bukkit.getPluginManager().getPlugin("iConomy") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("MultiCurrency") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("MineConomy") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("AEco") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("McMoney") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("CraftConomy") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("CraftConomy3") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("eWallet") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("3co") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("BOSEconomy6") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("CurrencyCore") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("Gringotts") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("EconXP") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("GoldIsMoney") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("Dosh") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("CommandsEX") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("SDFEconom") != null)
		return true;
	if(Bukkit.getPluginManager().getPlugin("XPBank") != null)
		return true;
	
	return false;
}
public static Main getInst(){
	return instance;
}
}
