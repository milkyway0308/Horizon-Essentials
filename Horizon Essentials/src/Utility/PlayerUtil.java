package Utility;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;

public class PlayerUtil {
public static boolean hasPermission(CommandSender sender,PermissionType perm)
{
	if(sender.isOp())
		return true;
	return sender.hasPermission(perm.getValue()) || sender.hasPermission(perm.getValue().toLowerCase());
}
public static boolean hasPermission(Player sender,PermissionType perm)
{
	if(sender.isOp())
		return true;
	return sender.hasPermission(perm.getValue()) || sender.hasPermission(perm.getValue().toLowerCase());
}
public static void removeBuff(Player p)
{
	for(PotionEffectType f : PotionEffectType.values())
	{
		try{p.removePotionEffect(f);}catch(Exception e){}
	}
}

public static void updatePlayerNicknames(Player p)
{
	if(p.equals(DataManager.players.get(p).chatName)){
		return;
	}else{
		if(!DataManager.unrealname.containsKey(DataManager.players.get(p).chatName))
		{
		DataManager.unrealname.put(DataManager.players.get(p).chatName, new ArrayList<String>());
		DataManager.unrealname.get(DataManager.players.get(p).chatName).add(p.getName());
		}else{
			DataManager.unrealname.get(DataManager.players.get(p).chatName).add(p.getName());
		}
	}
}
public static void redoPlayerNicknames(Player p)
{
		DataManager.unrealname.remove(DataManager.players.get(p).chatName);
}
}
