package DataUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import HorizonEssentials.Economy.EssentialsBase;
import Horizon_Essentials.DataManager;
import Horizon_Essentials.IReportSystem;
import Utility.DataUtil;

public class EssentialsPlayer implements IReportSystem{
public static IReportSystem l = new EssentialsPlayer();
public String chatName = "noDataException";
public String displayName = "noDataException";
private String mainName = "noDataException";
public double Account = 0;
public List<Player> teleportRequest = new ArrayList<Player>();
public List<Player> denyRequest = new ArrayList<Player>();
public List<String> FullyIgnore = new ArrayList<String>();
public List<String> tpaIgnore = new ArrayList<String>();
public List<String> whisperIgnore = new ArrayList<String>();
public List<String> chatIgnore = new ArrayList<String>();
public HashMap<String,LocationCrafter> homelist = new HashMap<String,LocationCrafter>();
public int HomeLimit = 1;
public double canChat = -1L;
public double tempBan = -1L;
public double isJail = -1L;
public LocationCrafter jail = null;
public String banCause = "§c서버에서 차단되었습니다.";
public boolean isGodmod = false;
public double noAddTime = -1;
public String ip = "0.0.0.0";
public double last = -1;
public EssentialsPlayer(Player p)
{
	this.chatName = p.getName();
	this.displayName = p.getName();
	this.Account = EssentialsBase.defaultmoney;
	this.mainName = p.getName();
}
public EssentialsPlayer(Player p,String chatName,String displayName,double Account,long mute)
{
	this.chatName = chatName;
	this.displayName = displayName;
	this.Account = Account;
	this.mainName = p.getName();
	this.canChat = mute;
}
public EssentialsPlayer(Player p,String chatName,String displayName,double Account,double mute)
{
	this.chatName = chatName;
	this.displayName = displayName;
	this.Account = Account;
	this.mainName = p.getName();
	this.canChat = mute;
}

public EssentialsPlayer()
{
	this.chatName = "noDataException";
	this.displayName = "noDataException";
	this.Account = 0f;
	this.mainName = "noDataException";
}

public EssentialsPlayer(String s,String chatName,String displayName,double Account,long mute)
{
	this.chatName = chatName;
	this.displayName = displayName;
	this.Account = Account;
	this.mainName = s;
	this.canChat = mute;
}

public EssentialsPlayer(String s)
{
	this.chatName = "noDataException";
	this.displayName = "noDataException";
	this.Account = 0f;
	this.mainName = s;
}
public void changeChatName(String name)
{
	this.chatName = name;
}
public void changeDisplayName(String name)
{
	this.displayName = name;
}

public void changeAll(String name)
{
	this.changeChatName(name);
	this.changeDisplayName(name);
}

public void SaveEssentialPlayer(){
	try {
		File f = new File("plugins/Horizon_Essentials/Players/" + this.mainName + ".essentialData");
		if(!f.exists())
		{
			f.getParentFile().mkdirs();
			f.createNewFile();
		}
		BufferedWriter r = new BufferedWriter(new FileWriter(f));
		r.append("ChatNickName:" + this.chatName + "\n");
		r.append("TabNickName:" + this.displayName + "\n");
		r.append("MuteData:" + this.canChat + "\n");
		r.append("EconomyData:" + String.valueOf(this.Account) + "\n");
		r.append("TPAIgnore:" + DataUtil.ArrayToString(tpaIgnore) + "\n");
		r.append("ChatIgnore:" + DataUtil.ArrayToString(chatIgnore) + "\n");
		r.append("WhisperIgnore:" + DataUtil.ArrayToString(whisperIgnore) + "\n");
		r.append("FullyIgnore:" + DataUtil.ArrayToString(FullyIgnore) + "\n");
		r.append("TempBan:" + tempBan + "\n");
		r.append("BanCause:" + banCause + "\n");
		r.append("JailTime:" + isJail + "\n");
		r.append("LastIP:" + ip + "\n");
		r.append("LastConnect:" + last + "\n");
		if(jail != null)
			r.append("JailLoc:" + jail.toString() + "\n");
		r.append("HomeList:" + DataUtil.LocationCrafterHash2String(homelist) + "\n");
		r.append("GodMode:" + this.isGodmod );
		r.flush();
		r.close();
	}catch(FileNotFoundException e){
		l.Report(Bukkit.getConsoleSender(), ErrorType.FileCannotFoundException);}
	catch (IOException e) {
		Report(Bukkit.getConsoleSender(),ErrorType.IOException);
	}
}
public void updatePlayer(Player p)
{
	this.mainName = p.getName();
}

public static EssentialsPlayer LoadEssentialPlayer(Player p){
	EssentialsPlayer p2 = new EssentialsPlayer(p);
	try {
		File f = new File("plugins/Horizon_Essentials/Players/" + p.getName() + ".essentialData");
		if(!f.exists())
			return new EssentialsPlayer(p);
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		String ss1 = p.getName();
		String ss2 = p.getName();
		List<String> tpaig = new ArrayList<String>();
		List<String> whisperig = new ArrayList<String>();
		List<String> chatig = new ArrayList<String>();
		List<String> fullig = new ArrayList<String>();
		boolean god = false;
		double chattime = -1L;
		double ac = 0d;
		double tb = -1d;
		double jt = -1d;
		String ip = "0.0.0.0";
		String cs = "§c서버에서 차단되었습니다.";
		double last = -1;
		LocationCrafter loc = null;
		HashMap<String,LocationCrafter> hc = new HashMap<String,LocationCrafter>();
		while((str = r.readLine()) != null)
		{
			int a = str.indexOf(":");
			if (a <= -1)
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + 1, str.length());
			
			switch(s1)
			{
			case "ChatNickName":
				ss1 = s2;
				break;
			case "TabNickName":
				ss2 = s2;
				break;
			case "EconomyData":
				try{
					ac = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "MuteData":
				try{
					chattime = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "TPAIgnore":
				tpaig = DataUtil.StringToArray(s2);
				break;
			case "ChatIgnore":
				chatig = DataUtil.StringToArray(s2);
				break;
			case "WhisperIgnore":
				whisperig = DataUtil.StringToArray(s2);
				break;
			case "FullyIgnore":
				fullig = DataUtil.StringToArray(s2);
				break;
			case "GodMode":
				god = Boolean.parseBoolean(s2);
				break;
			case "TempBan":
				tb = Double.parseDouble(s2);
				break;
			case "BanCause":
				cs = s2;
				break;
			case "HomeList":
				String[] list = s2.split("\\|\\*\\|");
				for(String s : list)
				{
					try{
					String[] s3 = s.split("/\\*/");
					hc.put(s3[0], LocationCrafter.toLocationCrafter(s3[1]));
					}catch(Exception e){}
				}
				break;
			case "JailTime":
				try{
					jt = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "JailLoc":
				try{
					loc = LocationCrafter.toLocationCrafter(s2);
				}catch(Exception e){}
				break;
			case "LastIP":
				ip = String.valueOf(s2);
				break;
			case "LastConnect":
				last = Double.parseDouble(s2);
				break;
			}
		}
		p2 = new EssentialsPlayer(p,ss1,ss2,ac,chattime);
		p2.last = last;
		p2.tpaIgnore = tpaig;
		p2.chatIgnore = chatig;
		p2.whisperIgnore = whisperig;
		p2.FullyIgnore = fullig;
		p2.isGodmod = god;
		p2.tempBan = tb;
		p2.banCause = cs;
		p2.homelist = hc;
		p2.isJail = jt;
		p2.ip = ip;
		p2.jail = loc;
		r.close();
	
	}catch(FileNotFoundException e){
		l.Report(Bukkit.getConsoleSender(),p, ErrorType.FileCannotFoundException);}
	catch (IOException e) {
		l.Report(Bukkit.getConsoleSender(),p,ErrorType.IOException);
	}
	return p2;
}
public static EssentialsPlayer LoadEssentialPlayer(File f,String p){
	EssentialsPlayer p2 = new EssentialsPlayer(p);
	try {
		if(!f.exists())
			return new EssentialsPlayer(p);
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		String ss1 = p;
		String ss2 = p;
		List<String> tpaig = new ArrayList<String>();
		List<String> whisperig = new ArrayList<String>();
		List<String> chatig = new ArrayList<String>();
		List<String> fullig = new ArrayList<String>();
		boolean god = false;
		double chattime = -1L;
		double ac = 0d;
		double tb = -1d;
		double jt = -1d;
		double last = -1;
		String cs = "§c서버에서 차단되었습니다.";
		String ip = "0.0.0.0";
		LocationCrafter loc = null;
		HashMap<String,LocationCrafter> hc = new HashMap<String,LocationCrafter>();
		while((str = r.readLine()) != null)
		{
			int a = str.indexOf(":");
			if (a <= -1)
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + 1, str.length());
			switch(s1)
			{
			case "ChatNickName":
				ss1 = s2;
				break;
			case "TabNickName":
				ss2 = s2;
				break;
			case "EconomyData":
				try{
					ac = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "MuteData":
				try{
					chattime = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "TPAIgnore":
				tpaig = DataUtil.StringToArray(s2);
				break;
			case "ChatIgnore":
				chatig = DataUtil.StringToArray(s2);
				break;
			case "WhisperIgnore":
				whisperig = DataUtil.StringToArray(s2);
				break;
			case "FullyIgnore":
				fullig = DataUtil.StringToArray(s2);
				break;
			case "GodMode":
				god = Boolean.parseBoolean(s2);
				break;
			case "TempBan":
				tb = Double.parseDouble(s2);
				break;
			case "BanCause":
				cs = s2;
				break;
			case "HomeList":
				String[] list = s2.split("\\|\\*\\|");
				for(String s : list)
				{
					try{
					String[] s3 = s.split("/\\*/");
					hc.put(s3[0], LocationCrafter.toLocationCrafter(s3[1]));
					}catch(Exception e){}
				}
				break;
			case "JailTime":
				try{
					jt = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "JailLoc":
				try{
					loc = LocationCrafter.toLocationCrafter(s2);
				}catch(Exception e){}
				break;
			case "LastIP":
				ip = String.valueOf(s2);
				break;
			case "LastConnect":
				last = Double.parseDouble(s2);
				break;
			}
		}
		p2 = new EssentialsPlayer(p);
		p2.Account = ac;
		p2.chatName = ss1;
		p2.displayName = ss2;
		p2.canChat = chattime;
		p2.tpaIgnore = tpaig;
		p2.chatIgnore = chatig;
		p2.whisperIgnore = whisperig;
		p2.FullyIgnore = fullig;
		p2.isGodmod = god;
		p2.tempBan = tb;
		p2.banCause = cs;
		p2.homelist = hc;
		p2.isJail = jt;
		p2.jail = loc;
		p2.last = last;
		p2.ip = ip;
		r.close();
	
	}catch(FileNotFoundException e){
		l.Report(Bukkit.getConsoleSender(),p, ErrorType.FileCannotFoundException);}
	catch (IOException e) {
		l.Report(Bukkit.getConsoleSender(),p,ErrorType.IOException);
	}
	return p2;
}
public static void loadAllPlayer(){
	File f = new File("plugins/Horizon_Essentials/Players");
	if(!f.exists() || !f.isDirectory())
		return;
	for(File f2 : f.listFiles()){
		DataManager.players.put(Bukkit.getOfflinePlayer(f2.getName().substring(0,f2.getName().length()-14)), LoadEssentialPlayer(f2,f2.getName().substring(0,f2.getName().length()-14)));
	}
}

public static EssentialsPlayer LoadEssentialPlayer(String s){
	EssentialsPlayer p2 = new EssentialsPlayer(s);
	try {
		File f = new File("plugins/Horizon_Essentials/Players/" + s + ".essentialData");
		if(!f.exists())
			return new EssentialsPlayer(s);
		BufferedReader r = new BufferedReader(new FileReader(f));
		String str;
		String ss1 = s;
		String ss2 = s;
		List<String> tpaig = new ArrayList<String>();
		List<String> whisperig = new ArrayList<String>();
		List<String> chatig = new ArrayList<String>();
		List<String> fullig = new ArrayList<String>();
		boolean god = false;
		double chattime = -1L;
		double ac = 0d;
		double tb = -1d;
		double jt = -1d;
		double last = 1d;
		String ip = "0.0.0.0";
		String cs = "§c서버에서 차단되었습니다.";
		LocationCrafter loc = null;
		HashMap<String,LocationCrafter> hc = new HashMap<String,LocationCrafter>();
		while((str = r.readLine()) != null)
		{
			int a = str.indexOf(":");
			if (a <= -1)
				continue;
			String s1 = str.substring(0, a);
			String s2 = str.substring(a + 1, str.length());
			switch(s1)
			{
			case "ChatNickName":
				ss1 = s2;
				break;
			case "TabNickName":
				ss2 = s2;
				break;
			case "EconomyData":
				try{
					ac = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "MuteData":
				try{
					chattime = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "TPAIgnore":
				tpaig = DataUtil.StringToArray(s2);
				break;
			case "ChatIgnore":
				chatig = DataUtil.StringToArray(s2);
				break;
			case "WhisperIgnore":
				whisperig = DataUtil.StringToArray(s2);
				break;
			case "FullyIgnore":
				fullig = DataUtil.StringToArray(s2);
				break;
			case "GodMode":
				god = Boolean.parseBoolean(s2);
				break;
			case "TempBan":
				tb = Double.parseDouble(s2);
				break;
			case "BanCause":
				cs = s2;
				break;
			case "HomeList":
				String[] list = s2.split("\\|\\*\\|");
				for(String s5 : list)
				{
					try{
					String[] s3 = s5.split("/\\*/");
					hc.put(s3[0], LocationCrafter.toLocationCrafter(s3[1]));
					}catch(Exception e){}
				}
				break;
			case "JailTime":
				try{
					jt = Double.parseDouble(s2);
				}catch(Exception e){}
				break;
			case "JailLoc":
				try{
					loc = LocationCrafter.toLocationCrafter(s2);
				}catch(Exception e){}
				break;
			case "LastIP":
				ip = s2;
				break;
			case "LastConnect":
				last = Double.parseDouble(s2);
				break;
				
			}
		}
		p2 = new EssentialsPlayer(s);
		p2.chatName = ss1;
		p2.displayName = ss2;
		p2.tpaIgnore = tpaig;
		p2.chatIgnore = chatig;
		p2.whisperIgnore = whisperig;
		p2.FullyIgnore = fullig;
		p2.isGodmod = god;
		p2.tempBan = tb;
		p2.banCause = cs;
		p2.displayName = ss1;
		p2.Account = ac;
		p2.canChat = chattime;
		p2.homelist = hc;
		p2.isJail = jt;
		p2.jail = loc;
		p2.ip = ip;
		p2.last = last;
		r.close();
	
	}catch(FileNotFoundException e){
		l.Report(Bukkit.getConsoleSender(),s, ErrorType.FileCannotFoundException);}
	catch (IOException e) {
		l.Report(Bukkit.getConsoleSender(),s,ErrorType.IOException);
	}
	return p2;
}
@Override
public void Report(Player p, ErrorType type) {
	
	switch(type)
	{
	case NullMessageException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c존재하지 않는 메시지를 전송하려 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case ClassNotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c클래스가 존재하지 않습니다 : 플러그인이 편집되었거나 정상이 아닙니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case FileCannotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일이 존재하지 않아 로드가 불가능합니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case NullPointerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c존재하지 않는 값을 참조 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case TryToForceNullPlayerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c온라인이 아닌 플레이어를 강제로 캐스팅 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case IOException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일에 이미 다른 프로그램이 접근중입니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	default:
		break;
	}
}
@Override
public void Report(CommandSender p, ErrorType type) {
	switch(type)
	{
	case NullMessageException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c존재하지 않는 메시지를 전송하려 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case ClassNotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c클래스가 존재하지 않습니다 : 플러그인이 편집되었거나 정상이 아닙니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case FileCannotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일이 존재하지 않아 로드가 불가능합니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case NullPointerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c존재하지 않는 값을 참조 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case TryToForceNullPlayerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c온라인이 아닌 플레이어를 강제로 캐스팅 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	case IOException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일에 이미 다른 프로그램이 접근중입니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + Bukkit.getConsoleSender().getName());
		break;
	default:
		break;
	}
}

@SuppressWarnings("deprecation")
public static String getTime(){
	Date a = new Date();
    int year = a.getYear()+1900;
    int month = a.getMonth()+1;
    int date = a.getDate();
    int hour = a.getHours();
    int minute = a.getMinutes();
    int second = a.getSeconds();
    return new String(year + "년 " + month + "월 " + date + "일 " + hour + "시 " + minute + "분 " + second + "초");
}
@Override
public void Report(CommandSender p, Player target, ErrorType type) {
	switch(type)
	{
	case NullMessageException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c시스템에 메시지가 등록되지 않았습니다.");
		Bukkit.getConsoleSender().sendMessage("§5권장 수정법 : §cMessage.lang을 삭제하고,리부팅을 시도하여 재생성하세요.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + target.getName());
		break;
	case ClassNotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c클래스가 존재하지 않습니다 : 플러그인이 편집되었거나 정상이 아닙니다.");
		Bukkit.getConsoleSender().sendMessage("§5권장 수정법 : §c플러그인을 재 다운로드하여 시도해보시고,그래도 지속된다면 개발자에게 문의하여 주세요.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + target.getName());
		break;
	case FileCannotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일이 존재하지 않아 로드가 불가능합니다.");
		Bukkit.getConsoleSender().sendMessage("§5권장 수정법 : §c개발자에게 문의해주세요.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + target.getName());
		break;
	case NullPointerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c존재하지 않는 값을 참조 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + target.getName());
		break;
	case TryToForceNullPlayerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c온라인이 아닌 플레이어를 강제로 캐스팅 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + target.getName());
		break;
	case IOException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일에 이미 다른 프로그램이 접근중입니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + target.getName());
		break;
	case SaveFailException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일에 이미 다른 프로그램이 접근중입니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + target.getName());
		break;
	default:
		break;
	}
}



public void Report(CommandSender p, String s, ErrorType type) {
	switch(type)
	{
	case NullMessageException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c시스템에 메시지가 등록되지 않았습니다.");
		Bukkit.getConsoleSender().sendMessage("§5권장 수정법 : §cMessage.lang을 삭제하고,리부팅을 시도하여 재생성하세요.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + s);
		break;
	case ClassNotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c클래스가 존재하지 않습니다 : 플러그인이 편집되었거나 정상이 아닙니다.");
		Bukkit.getConsoleSender().sendMessage("§5권장 수정법 : §c플러그인을 재 다운로드하여 시도해보시고,그래도 지속된다면 개발자에게 문의하여 주세요.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" +s);
		break;
	case FileCannotFoundException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일이 존재하지 않아 로드가 불가능합니다.");
		Bukkit.getConsoleSender().sendMessage("§5권장 수정법 : §c개발자에게 문의해주세요.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + s);
	case NullPointerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c존재하지 않는 값을 참조 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + s);
		break;
	case TryToForceNullPlayerException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c온라인이 아닌 플레이어를 강제로 캐스팅 시도하였습니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + s);
		break;
	case IOException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일에 이미 다른 프로그램이 접근중입니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상:  §c" + s);
		break;
	case SaveFailException:
		Bukkit.getConsoleSender().sendMessage("§4  Horizon Essential Error Reporting System");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 시각: §e" + getTime());
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 원인: §c파일에 이미 다른 프로그램이 접근중입니다.");
		Bukkit.getConsoleSender().sendMessage("§5오류 발생 대상: §c" + s);
		break;
	default:
		break;
	}
}
}
