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
			sender.sendMessage("     ��6Horiozon Essentials Server Status Report");
			sender.sendMessage("��6TPS[Tick per Seconds] ��e: " + ServerInfomation.getString().replace("ColorCode", "��").replace("percent", "%"));
			sender.sendMessage("��6Server Uptime[���� �ð�] ��e: ��a" + DataUtil.IntegerToTime(ServerInfomation.upTime));
			sender.sendMessage("��6�Ҵ�� �޸� ��e: ��a" + Runtime.getRuntime().maxMemory() / 1024L / 1024L + "MB");
			sender.sendMessage("��6���� ��ü �޸� ��e: ��a" + Runtime.getRuntime().totalMemory() / 1024L / 1024L + "MB");
			sender.sendMessage("��6���� �ܿ� �޸� ��e: ��a" + Runtime.getRuntime().freeMemory() / 1024L / 1024L + "MB");
			sender.sendMessage("��6�Ҵ�� ���μ���[CPU Core] ��e: ��a" + Runtime.getRuntime().availableProcessors()  + "��");
			for(World w : Bukkit.getWorlds()){
				sender.sendMessage("��6���� ��c" + w.getName());
				sender.sendMessage("��e- ��6�÷��̾� ��e" + w.getPlayers().size() + "�� ��e/��6 ��ƼƼ ��e" + w.getLivingEntities().size() + " ��ƼƼ ��e/ ��6������ ��e" + w.getEntitiesByClass(Item.class).size() + "��");
			}
		}else{
			super.m.sendMessage(sender, "��ɾ� ���� ����");
		}
		return false;
	}
	
}