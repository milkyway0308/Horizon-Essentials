package Commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandWhisper extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandWhisper))
			{
				if(args.length <= 1)
					super.m.sendMessage(sender,"명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어] [내용]"));
				else{
					if(Bukkit.getPlayer(args[0]) == null)
						super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
					else{
						if(sender instanceof Player){
							Player p = Bukkit.getPlayer(args[0]);
							if(DataManager.players.get(p).FullyIgnore.contains(sender.getName().toLowerCase()) || DataManager.players.get(p).whisperIgnore.contains(sender.getName())){
								super.m.sendMessage(sender, "귓속말 차단됨",new DoubleString("[대상]",args[0]));
								return false;
							}
							StringBuilder b = new StringBuilder(args[1]);
							for(int i = 2;i < args.length;i++)
								b.append(" " + args[i]);
							super.m.sendMessage(Bukkit.getPlayer(args[0]), "귓속말대상",Arrays.asList(new DoubleString("[사용자]",DataManager.getName(sender.getName())),new DoubleString("[대상]",DataManager.getName(args[0])),new DoubleString("[내용]",b.toString())));
							super.m.sendMessage(sender, "귓속말",Arrays.asList(new DoubleString("[사용자]",DataManager.getName(sender.getName())),new DoubleString("[대상]",DataManager.getName(args[0])),new DoubleString("[내용]",b.toString())));
						}else{
							StringBuilder b = new StringBuilder(args[1]);
							for(int i = 2;i < args.length;i++)
								b.append(" " + args[i]);
							super.m.sendMessage(Bukkit.getPlayer(args[0]), "귓속말대상",Arrays.asList(new DoubleString("[사용자]","콘솔"),new DoubleString("[대상]",DataManager.getName(args[0])),new DoubleString("[내용]",b.toString())));
							super.m.sendMessage(sender, "귓속말",Arrays.asList(new DoubleString("[사용자]","콘솔"),new DoubleString("[대상]",DataManager.getName(args[0])),new DoubleString("[내용]",b.toString())));
						}
					}
				}
			}else
				super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
	}
}