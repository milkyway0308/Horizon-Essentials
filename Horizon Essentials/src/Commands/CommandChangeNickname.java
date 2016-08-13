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
					super.m.sendMessage(sender, "이름 초기화");
					PlayerUtil.redoPlayerNicknames((Player)sender);
					DataManager.players.get((Player)sender).changeAll(((Player)sender).getName());
				}else{
					super.m.sendMessage(sender, "명령어 권한 부족");
				}
			}else if(args.length == 1)
			{
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandChangeNickname))
				{
					String name = args[0].replace("&", "§");
					super.m.sendMessage(sender, "이름 변경",new DoubleString("[닉네임]",name));
					PlayerUtil.redoPlayerNicknames((Player)sender);
					DataManager.players.get((Player)sender).changeAll(name);
					PlayerUtil.updatePlayerNicknames((Player)sender);
				}else{
					super.m.sendMessage(sender, "명령어 권한 부족");
				}
			}else{
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandChangeNicknameOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",DataManager.getName(args[0])));
						return false;
					}
					StringBuilder name = new StringBuilder(args[1]);
					for(int i = 2;i < args.length;i++)
						name.append(" " + args[i]);
					super.m.sendMessage(p, "이름 변경",new DoubleString("[닉네임]",name.toString().replace("&", "§")));
					PlayerUtil.redoPlayerNicknames((Player)sender);
					DataManager.players.get(p).changeAll(name.toString().replace("&", "§"));
					PlayerUtil.updatePlayerNicknames(p);
				}else{
					super.m.sendMessage(sender, "명령어 권한 부족");
				}
			}
		}else{
			if(args.length <= 1)
			{
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어] [닉네임]"));
			}else{
				Player p = Bukkit.getPlayer(args[0]);
				if(p == null)
				{
					super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",DataManager.getName(args[0])));
					return false;
				}
				StringBuilder name = new StringBuilder(args[1]);
				for(int i = 2;i < args.length;i++)
					name.append(" " + args[i]);
				super.m.sendMessage(p, "이름 변경",new DoubleString("[닉네임]",name.toString().replace("&", "§")));
				PlayerUtil.redoPlayerNicknames((Player)sender);
				DataManager.players.get(p).changeAll(name.toString().replace("&", "§"));
				PlayerUtil.updatePlayerNicknames(p);
				super.m.sendMessage(sender, "대상 이름 변경",Arrays.asList(new DoubleString("[대상]",DataManager.getName(args[0])),new DoubleString("[닉네임]",name.toString().replace("&", "§"))));
			}
		}
		return false;
	}

}
