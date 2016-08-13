package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandItemData  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandItemData))
		{
			if(sender instanceof Player){
				Player p = (Player)sender;
					try{
						super.m.sendMessage(sender, "������ ����",DataUtil.asArray(new DoubleString("[�̸�]",DataUtil.ItemName(p.getItemInHand())),new DoubleString("[����]",DataUtil.ItemLore(p.getItemInHand())),new DoubleString("[�ڵ�]",String.valueOf(p.getItemInHand().getTypeId()) + ":" + p.getItemInHand().getDurability())));
					}catch(Exception e){super.m.sendMessage(sender, "������ ����",DataUtil.asArray(new DoubleString("[�̸�]","������ �̸� ����"),new DoubleString("[����]","������ ���� ����"),new DoubleString("[�ڵ�]","0:0")));}
			}else{
				super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
			}
		}else{
			super.m.sendMessage(sender, "��ɾ� ���� ����");
		}
	return false;
	}
}