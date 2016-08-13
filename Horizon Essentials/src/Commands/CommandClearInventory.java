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
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(sender instanceof Player)
		{
			if(args.length <= 0)
			{
				((Player)sender).getInventory().clear();
				super.m.sendMessage(sender, "�κ��丮 Ŭ����");
			}else{
				if(Bukkit.getPlayer(args[0]) == null)
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
				else{
					Player p = Bukkit.getPlayer(args[0]);
					if(args.length == 1){
						p.getInventory().clear();
						super.m.sendMessage(p, "�κ��丮 Ŭ����");
						super.m.sendMessage(sender, "��� �κ��丮 Ŭ����",new DoubleString("[���]",args[0]));
					}else if(args.length >= 2){
						int code = 0;
						try{
							code = Integer.parseInt(args[1]);
							try {
								new ItemStack(Material.getMaterial(code));
							} catch (Exception e) {
								super.m.sendMessage(sender, "������ ������");
								return false;
							}
						}catch(Exception e){super.m.sendMessage(sender, "�߸��� �Ű� ����");return false;}
						for(int i = 0;i < p.getInventory().getSize();i++)
							if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType().getId() == code)
								p.getInventory().setItem(i, new ItemStack(Material.AIR));
						super.m.sendMessage(sender, "��� �κ��丮 �κ� Ŭ����",DataUtil.asArray(new DoubleString("[���]",args[0]),new DoubleString("[�ڵ�]",String.valueOf(code))));
					}
				}
			}
		}
		return false;
	}

}
