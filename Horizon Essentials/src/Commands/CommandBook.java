package Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandBook extends CommandsRegistry implements CommandExecutor{
	public final String Developer = "이 클래스는 KiwiYou님에 의해 작성되었습니다.";
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandBook)){
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(sender instanceof Player){
			 Player target = (Player) sender;
			 if(target.getItemInHand() == null || target.getItemInHand().getType() == null || target.getItemInHand().getType() != Material.WRITTEN_BOOK)
			 {
				 
				 return false;
			 }
			 ItemStack tmp = target.getItemInHand();
			 BookMeta meta = (BookMeta) tmp.getItemMeta();
			 tmp = new ItemStack(Material.BOOK_AND_QUILL, tmp.getAmount());
			 tmp.setItemMeta(meta);
			 target.setItemInHand(tmp);
		}else{
			super.m.sendMessage(sender, "플레이어 전용 커맨드");
		}
		return false;
	}

}
