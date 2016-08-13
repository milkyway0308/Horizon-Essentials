package Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandGMC extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandsGameMode))
			{
				super.m.sendMessage(sender, "명령어 권한 부족");
				return false;
			}
			if(sender instanceof Player){
				if(args.length <= 0)
				{
				((Player)sender).setGameMode(GameMode.CREATIVE);
				super.m.sendMessage(sender, "게임모드 크리에이티브");
				}
				else{
					if(Bukkit.getPlayer(args[0]) == null)
						super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
					else{
						Bukkit.getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(Bukkit.getPlayer(args[0]), "게임모드 크리에이티브");
						super.m.sendMessage(sender, "대상 게임모드 크리에이티브",new DoubleString("[대상]",args[0]));
					}
				}
			}else{
				if(args.length <= 0)
					super.m.sendMessage(sender,"명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어]"));
				else{
					if(Bukkit.getPlayer(args[0]) == null)
						super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
					else{
						Bukkit.getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(Bukkit.getPlayer(args[0]), "게임모드 크리에이티브");
						super.m.sendMessage(sender, "대상 게임모드 크리에이티브",new DoubleString("[대상]",args[0]));
					}
				}
			}
			return false;
	}
}