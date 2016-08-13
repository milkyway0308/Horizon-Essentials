package Commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandWhisper extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandWhisper))
			{
				if(args.length <= 1)
					super.m.sendMessage(sender,"��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] [����]"));
				else{
					if(Bukkit.getPlayer(args[0]) == null)
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
					else{
						if(sender instanceof Player){
							Player p = Bukkit.getPlayer(args[0]);
							if(DataManager.players.get(p).FullyIgnore.contains(sender.getName().toLowerCase()) || DataManager.players.get(p).whisperIgnore.contains(sender.getName())){
								super.m.sendMessage(sender, "�ӼӸ� ���ܵ�",new DoubleString("[���]",args[0]));
								return false;
							}
							StringBuilder b = new StringBuilder(args[1]);
							for(int i = 2;i < args.length;i++)
								b.append(" " + args[i]);
							super.m.sendMessage(Bukkit.getPlayer(args[0]), "�ӼӸ����",Arrays.asList(new DoubleString("[�����]",DataManager.getName(sender.getName())),new DoubleString("[���]",DataManager.getName(args[0])),new DoubleString("[����]",b.toString())));
							super.m.sendMessage(sender, "�ӼӸ�",Arrays.asList(new DoubleString("[�����]",DataManager.getName(sender.getName())),new DoubleString("[���]",DataManager.getName(args[0])),new DoubleString("[����]",b.toString())));
						}else{
							StringBuilder b = new StringBuilder(args[1]);
							for(int i = 2;i < args.length;i++)
								b.append(" " + args[i]);
							super.m.sendMessage(Bukkit.getPlayer(args[0]), "�ӼӸ����",Arrays.asList(new DoubleString("[�����]","�ܼ�"),new DoubleString("[���]",DataManager.getName(args[0])),new DoubleString("[����]",b.toString())));
							super.m.sendMessage(sender, "�ӼӸ�",Arrays.asList(new DoubleString("[�����]","�ܼ�"),new DoubleString("[���]",DataManager.getName(args[0])),new DoubleString("[����]",b.toString())));
						}
					}
				}
			}else
				super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
	}
}