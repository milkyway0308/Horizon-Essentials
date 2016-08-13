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

public class CommandIgnoreWhisper extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender instanceof Player){
				if(!PlayerUtil.hasPermission(sender, PermissionType.CommandIgnoreWhisper))
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
						if(name.equals(sender.getName().toLowerCase())){
							super.m.sendMessage(sender, "차단 대상 오류");
							return false;
						}
						if(DataManager.players.get(p).whisperIgnore.contains(name))
							super.m.sendMessage(sender, "이미 차단됨");
						else{
							DataManager.players.get(p).whisperIgnore.add(name);
							super.m.sendMessage(sender, "플레이어 귓속말 차단",new DoubleString("[대상]",args[0]));
						}
					}
				}
			}else{
				super.m.sendMessage(sender, "플레이어 전용 커맨드");
			}
		
		return false;
	}
	
}