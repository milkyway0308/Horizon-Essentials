package Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;

import DataUtil.PermissionType;
import Horizon_Essentials.ServerInfomation;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandLag extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandLag))
		{
			sender.sendMessage("     §6Horiozon Essentials Server Status Report");
			sender.sendMessage("§6TPS[Tick per Seconds] §e: " + ServerInfomation.getString().replace("ColorCode", "§").replace("percent", "%"));
			sender.sendMessage("§6Server Uptime[구동 시간] §e: §a" + DataUtil.IntegerToTime(ServerInfomation.upTime));
			sender.sendMessage("§6할당된 메모리 §e: §a" + Runtime.getRuntime().maxMemory() / 1024L / 1024L + "MB");
			sender.sendMessage("§6서버 전체 메모리 §e: §a" + Runtime.getRuntime().totalMemory() / 1024L / 1024L + "MB");
			sender.sendMessage("§6서버 잔여 메모리 §e: §a" + Runtime.getRuntime().freeMemory() / 1024L / 1024L + "MB");
			sender.sendMessage("§6할당된 프로세서[CPU Core] §e: §a" + Runtime.getRuntime().availableProcessors()  + "개");
			for(World w : Bukkit.getWorlds()){
				sender.sendMessage("§6월드 §c" + w.getName());
				sender.sendMessage("§e- §6플레이어 §e" + w.getPlayers().size() + "명 §e/§6 엔티티 §e" + w.getLivingEntities().size() + " 엔티티 §e/ §6아이템 §e" + w.getEntitiesByClass(Item.class).size() + "개");
			}
		}else{
			super.m.sendMessage(sender, "명령어 권한 부족");
		}
		return false;
	}
	
}