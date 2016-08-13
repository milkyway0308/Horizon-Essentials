package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandMute extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandMute))
			{
					super.m.sendMessage(sender, "명령어 권한 부족");
					return false;
			}
			if(args.length <= 1){
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어] [시간(숫자)]"));
			}else{
				try{
					int a = Integer.parseInt(args[1]);
					if(a <= 0)
						super.m.sendMessage(sender, "잘못된 상수 매개 변수");
					else{
						Player p = Bukkit.getPlayer(args[0]);
						if(p == null)
							super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
						else{
							DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).canChat = (double)System.currentTimeMillis() + (double)a * 1000;
							DataUtil.OperatorMessage(super.m.getMessage("채팅 뮤트").replace("[시간]", DataUtil.IntegerToTime(a)).replace("[시전자]",sender.getName()).replace("[대상]", p.getName()));
						}
					}
				}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");}
			}
		return false;
	}
}