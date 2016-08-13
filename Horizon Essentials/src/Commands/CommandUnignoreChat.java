package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandUnignoreChat extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender instanceof Player){
				if(!PlayerUtil.hasPermission(sender, PermissionType.CommandIgnoreChat))
				{
					super.m.sendMessage(sender, "명령어 권한 부족");
					return false;
				}
				if(args.length <= 0)
					super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어]"));
				else{
					if(!DataUtil.isAlphaandNumber(args[0]))
						super.m.sendMessage(sender, "영문 플레이어명");
					else{
						String name = args[0].toLowerCase();
						Player p = (Player)sender;
						if(DataManager.players.get(p).chatIgnore.contains(name))
						{
							DataManager.players.get(p).chatIgnore.remove(name);
							super.m.sendMessage(sender, "플레이어 차단 해제",new DoubleString("[대상]",name));
						}
						else{
							super.m.sendMessage(sender, "차단죄지 않은 플레이어",new DoubleString("[대상]",name));
						}
					}
				}
			}else{
				super.m.sendMessage(sender, "플레이어 전용 커맨드");
			}
		return false;
	}
	
}