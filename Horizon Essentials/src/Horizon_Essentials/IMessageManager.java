package Horizon_Essentials;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;

public interface IMessageManager {
public abstract int getMessageFromFile();
public abstract void sendMessage(Player p,String msgType);
public abstract void sendMessage(CommandSender p,String msgType);
public abstract void sendMessage(CommandSender p,String msgType,DoubleString replacement);
public abstract void sendMessage(CommandSender p,String msgType,List<DoubleString> replacement);
public abstract void sendMessage(Player p,String msgType,DoubleString replacement);
public abstract void sendMessage(Player p,String msgType,List<DoubleString> replacement);
public abstract String getMessage(String msgType);


public abstract void sendMessage(CommandSender p,String msgType,boolean replacespace);
public abstract void sendMessage(CommandSender p,String msgType,DoubleString replacement,boolean replacespace);
public abstract void sendMessage(CommandSender p,String msgType,List<DoubleString> replacement,boolean replacespace);
public abstract void sendMessage(Player p,String msgType,DoubleString replacement,boolean replacespace);
public abstract void sendMessage(Player p,String msgType,List<DoubleString> replacement,boolean replacespace);
}
