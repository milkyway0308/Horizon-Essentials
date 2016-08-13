package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;
// ��� ����
public class CommandWarpList extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandLag))
		{
			if(args.length <= 0)
				if(DataManager.warps.keySet().size() <= 0)
					sender.sendMessage("��c��ϵ� ������ �����ϴ�.");
				else{
					StringBuilder b = new StringBuilder();
					for(String s : DataManager.warps.keySet())
						b.append("," + s);
					sender.sendMessage("��6���� ���:");
					sender.sendMessage(b.toString().substring(1, b.toString().length()));
				}
		}else{
			super.m.sendMessage(sender, "��ɾ� ���� ����");
		}
		return false;
	}
	
}