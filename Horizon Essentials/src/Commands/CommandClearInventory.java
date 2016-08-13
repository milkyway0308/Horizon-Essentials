package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandClearInventory extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandClearInventory)){
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(sender instanceof Player)
		{
			if(args.length <= 0)
			{
				((Player)sender).getInventory().clear();
				super.m.sendMessage(sender, "인벤토리 클리어");
			}else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
				else{
					Player p = Bukkit.getPlayer(args[0]);
					if(args.length == 1){
						p.getInventory().clear();
						super.m.sendMessage(p, "인벤토리 클리어");
						super.m.sendMessage(sender, "대상 인벤토리 클리어",new DoubleString("[대상]",args[0]));
					}else if(args.length >= 2){
						int code = 0;
						try{
							code = Integer.parseInt(args[1]);
							try {
								new ItemStack(Material.getMaterial(code));
							} catch (Exception e) {
								super.m.sendMessage(sender, "아이템 미존재");
								return false;
							}
						}catch(Exception e){super.m.sendMessage(sender, "잘못된 매개 변수");return false;}
						for(int i = 0;i < p.getInventory().getSize();i++)
							if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType().getId() == code)
								p.getInventory().setItem(i, new ItemStack(Material.AIR));
						super.m.sendMessage(sender, "대상 인벤토리 부분 클리어",DataUtil.asArray(new DoubleString("[대상]",args[0]),new DoubleString("[코드]",String.valueOf(code))));
					}
				}
			}
		}
		return false;
	}

}
