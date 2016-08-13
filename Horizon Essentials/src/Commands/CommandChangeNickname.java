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

public class CommandChangeNickname  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player)
		{
			if(args.length <= 0)
			{
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandChangeNickname))
				{
					super.m.sendMessage(sender, "�̸� �ʱ�ȭ");
					PlayerUtil.redoPlayerNicknames((Player)sender);
					DataManager.players.get((Player)sender).changeAll(((Player)sender).getName());
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}else if(args.length == 1)
			{
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandChangeNickname))
				{
					String name = args[0].replace("&", "��");
					super.m.sendMessage(sender, "�̸� ����",new DoubleString("[�г���]",name));
					PlayerUtil.redoPlayerNicknames((Player)sender);
					DataManager.players.get((Player)sender).changeAll(name);
					PlayerUtil.updatePlayerNicknames((Player)sender);
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}else{
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandChangeNicknameOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",DataManager.getName(args[0])));
						return false;
					}
					StringBuilder name = new StringBuilder(args[1]);
					for(int i = 2;i < args.length;i++)
						name.append(" " + args[i]);
					super.m.sendMessage(p, "�̸� ����",new DoubleString("[�г���]",name.toString().replace("&", "��")));
					PlayerUtil.redoPlayerNicknames((Player)sender);
					DataManager.players.get(p).changeAll(name.toString().replace("&", "��"));
					PlayerUtil.updatePlayerNicknames(p);
				}else{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
				}
			}
		}else{
			if(args.length <= 1)
			{
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�] [�г���]"));
			}else{
				Player p = Bukkit.getPlayer(args[0]);
				if(p == null)
				{
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",DataManager.getName(args[0])));
					return false;
				}
				StringBuilder name = new StringBuilder(args[1]);
				for(int i = 2;i < args.length;i++)
					name.append(" " + args[i]);
				super.m.sendMessage(p, "�̸� ����",new DoubleString("[�г���]",name.toString().replace("&", "��")));
				PlayerUtil.redoPlayerNicknames((Player)sender);
				DataManager.players.get(p).changeAll(name.toString().replace("&", "��"));
				PlayerUtil.updatePlayerNicknames(p);
				super.m.sendMessage(sender, "��� �̸� ����",Arrays.asList(new DoubleString("[���]",DataManager.getName(args[0])),new DoubleString("[�г���]",name.toString().replace("&", "��"))));
			}
		}
		return false;
	}

}
