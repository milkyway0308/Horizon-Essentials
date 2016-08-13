package HorizonEssentials.Economy;

import org.bukkit.OfflinePlayer;

import DataUtil.EssentialsPlayer;
import Horizon_Essentials.DataManager;

public class EssentialsBase {
	public static double defaultmoney = 1000;
	public static String moneyName = "¿ø";
	public static String economyMain = "°ñµå";
	public static String economySub = "½Ç¹ö";
	public static boolean useSub = false;
	public static boolean usePoint = false;
	public static double EssentialsMinValue = -5000;
	public static double EssentialsMaxValue = 9999999999999999999999d;
public static void addMoney(OfflinePlayer p,double amount){
	if(DataManager.players.containsKey(p)){
		DataManager.players.get(p).Account += amount;
		if(DataManager.players.get(p).Account >= EssentialsMaxValue )
			DataManager.players.get(p).Account = EssentialsMaxValue;
	}
	else
	{
		EssentialsPlayer pl = new EssentialsPlayer(p.getName());
		pl.Account += amount + defaultmoney;
		DataManager.players.put(p, pl);
	}
}

public static boolean removeMoney(OfflinePlayer p,double amount){
	hasMoney(p,amount);
	if(DataManager.players.containsKey(p))
	{
		double d = DataManager.players.get(p).Account - amount;
		if(d <= EssentialsMinValue)
			DataManager.players.get(p).Account = EssentialsMinValue;
		else
			DataManager.players.get(p).Account -= amount;
		
	}else{
		double d = defaultmoney - amount;
		if(d <= EssentialsMinValue)
			DataManager.players.get(p).Account = EssentialsMinValue;
		else
			DataManager.players.get(p).Account -= amount;
	}
	return true;
}

public static void setMoney(OfflinePlayer p,double amount){
	if(DataManager.players.containsKey(p)){
		if(DataManager.players.get(p).Account >= EssentialsMaxValue )
			DataManager.players.get(p).Account = EssentialsMaxValue;
		DataManager.players.get(p).Account = amount;
	}
	else
	{
		EssentialsPlayer pl = new EssentialsPlayer(p.getName());
		pl.Account = amount;
		DataManager.players.put(p, pl);
	}
}

public static double getMoney(OfflinePlayer p)
{
	if(DataManager.players.containsKey(p))
	{
		if(DataManager.players.get(p).Account >= EssentialsMaxValue )
			DataManager.players.get(p).Account = EssentialsMaxValue;
		return DataManager.players.get(p).Account;
	}

	else
	{
		DataManager.players.put(p, new EssentialsPlayer(p.getName(), p.getName(),p.getName(),defaultmoney,-1l));
		return DataManager.players.get(p).Account;
	}
}

public static boolean hasMoney(OfflinePlayer p,double amount)
{
	if(DataManager.players.containsKey(p))
	{
		if(DataManager.players.get(p).Account >= EssentialsMaxValue )
			DataManager.players.get(p).Account = EssentialsMaxValue;
		return DataManager.players.get(p).Account >= amount;
	}
	else
	{
		DataManager.players.put(p, new EssentialsPlayer(p.getName(), p.getName(),p.getName(),defaultmoney,-1l));
		return DataManager.players.get(p).Account >= amount;
	}
}
}
