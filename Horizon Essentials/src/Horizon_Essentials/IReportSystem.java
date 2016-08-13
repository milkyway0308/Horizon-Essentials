package Horizon_Essentials;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import DataUtil.ErrorType;

public interface IReportSystem {
public abstract void Report(Player p,ErrorType type);
public abstract void Report(CommandSender p,ErrorType type);
public abstract void Report(CommandSender p,Player target,ErrorType type);
public abstract void Report(CommandSender p, String s, ErrorType type);

}
