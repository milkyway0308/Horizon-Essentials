package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;
// 어우 렉이
public class CommandWarpList extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandLag))
		{
			if(args.length <= 0)
				if(DataManager.warps.keySet().size() <= 0)
					sender.sendMessage("§c등록된 워프가 없습니다.");
				else{
					StringBuilder b = new StringBuilder();
					for(String s : DataManager.warps.keySet())
						b.append("," + s);
					sender.sendMessage("§6워프 목록:");
					sender.sendMessage(b.toString().substring(1, b.toString().length()));
				}
		}else{
			super.m.sendMessage(sender, "명령어 권한 부족");
		}
		return false;
	}
	
}