package Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandKillAll extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandKillall))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			int li = 0;
			for(World w : Bukkit.getWorlds())
			{
				
				for(Entity e : w.getEntities())
					if(e instanceof LivingEntity && !(e instanceof Player))
					{
						li++;
						e.remove();
					}		
			}
			super.m.sendMessage(sender, "���� ��ƼƼ ų��",new DoubleString("[��ü��]",String.valueOf(li)));
			return false;
	}
}