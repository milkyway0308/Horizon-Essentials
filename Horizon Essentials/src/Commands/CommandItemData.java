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
						super.m.sendMessage(sender, "아이템 정보",DataUtil.asArray(new DoubleString("[이름]",DataUtil.ItemName(p.getItemInHand())),new DoubleString("[설명]",DataUtil.ItemLore(p.getItemInHand())),new DoubleString("[코드]",String.valueOf(p.getItemInHand().getTypeId()) + ":" + p.getItemInHand().getDurability())));
					}catch(Exception e){super.m.sendMessage(sender, "아이템 정보",DataUtil.asArray(new DoubleString("[이름]","아이템 이름 없음"),new DoubleString("[설명]","아이템 설명 없음"),new DoubleString("[코드]","0:0")));}
			}else{
				super.m.sendMessage(sender, "플레이어 전용 커맨드");
			}
		}else{
			super.m.sendMessage(sender, "명령어 권한 부족");
		}
	return false;
	}
}