package Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandGMC extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandsGameMode))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if(sender instanceof Player){
				if(args.length <= 0)
				{
				((Player)sender).setGameMode(GameMode.CREATIVE);
				super.m.sendMessage(sender, "���Ӹ�� ũ������Ƽ��");
				}
				else{
					if(Bukkit.getPlayer(args[0]) == null)
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
					else{
						Bukkit.getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(Bukkit.getPlayer(args[0]), "���Ӹ�� ũ������Ƽ��");
						super.m.sendMessage(sender, "��� ���Ӹ�� ũ������Ƽ��",new DoubleString("[���]",args[0]));
					}
				}
			}else{
				if(args.length <= 0)
					super.m.sendMessage(sender,"��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�]"));
				else{
					if(Bukkit.getPlayer(args[0]) == null)
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
					else{
						Bukkit.getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
						super.m.sendMessage(Bukkit.getPlayer(args[0]), "���Ӹ�� ũ������Ƽ��");
						super.m.sendMessage(sender, "��� ���Ӹ�� ũ������Ƽ��",new DoubleString("[���]",args[0]));
					}
				}
			}
			return false;
	}
}