package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class CommandFeed extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player)
		{
			if(args.length <= 0)
			{
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandFeed))
				{
					((Player)sender).setFoodLevel(20);
					super.m.sendMessage(sender, "허기 회복");
				}else{
					super.m.sendMessage(sender, "명령어 권한 부족");
				}
			}else if(args.length == 1){
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandFeedOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("대상 플레이어 오프라인").replace("[대상]", args[0]));
						return false;
					}
					p.setFoodLevel(20);
					super.m.sendMessage(p, "허기 회복");
					super.m.sendMessage(sender, "타인 허기 회복",new DoubleString("[대상]",p.getName()));
				}else{
					super.m.sendMessage(sender, "명령어 권한 부족");
				}
			}else if(args.length == 2){
				boolean b = false;
					if(Utility.DataUtil.isBoolean(args[1]))
						b = Boolean.parseBoolean(args[1]);
					else
					{
						sender.sendMessage(super.m.getMessage("명령어 사용법").replace("[사용법]", "/" + label + " [플레이어] <메시지 출력 여부(true/false)>"));
						return false;
					}
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandFeedOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("대상 플레이어 오프라인").replace("[대상]", args[0]));
						return false;
					}
					p.setFoodLevel(20);
					super.m.sendMessage(p, "허기 회복");
					if(b)
						super.m.sendMessage(sender, "타인 허기 회복",new DoubleString("[대상]",p.getName()));
				}else{
					super.m.sendMessage(sender, "명령어 권한 부족");
				}
			}else if(args.length == 3){
				boolean b = false;
				boolean b2 = false;
				if(Utility.DataUtil.isBoolean(args[1]) && Utility.DataUtil.isBoolean(args[2]))
				{
					b = Boolean.parseBoolean(args[1]);
					b2 = Boolean.parseBoolean(args[2]);
				}
				else
				{
					sender.sendMessage(super.m.getMessage("명령어 사용법").replace("[사용법]", "/" + label + " [플레이어] <메시지 출력 여부(true/false)> <대상 메시지 출력 여부(true/false>"));
					return false;
				}
				
				if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandFeedOther))
				{
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("대상 플레이어 오프라인").replace("[대상]", args[0]));
						return false;
					}
					p.setFoodLevel(20);
					if(b)
						super.m.sendMessage(sender, "타인 허기 회복",new DoubleString("[대상]",p.getName()));
					if(b2)
						super.m.sendMessage(p, "허기 회복");
				}else{
					super.m.sendMessage(sender, "명령어 권한 부족");
				}
			}
		}else{
			
			
			if(args.length <= 0)
			{
				sender.sendMessage(super.m.getMessage("명령어 사용법").replace("[사용법]", "/" + label + " [플레이어]"));
			}else if(args.length == 1){
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("대상 플레이어 오프라인").replace("[대상]", args[0]));
						return false;
					}
					p.setFoodLevel(20);
					super.m.sendMessage(sender, "타인 허기 회복",new DoubleString("[대상]",p.getName()));
					super.m.sendMessage(p, "허기 회복");
			}else if(args.length == 2){
				boolean b = false;
				if(Utility.DataUtil.isBoolean(args[1]))
					b = Boolean.parseBoolean(args[1]);
				else
				{
					sender.sendMessage(super.m.getMessage("명령어 사용법").replace("[사용법]", "/" + label + " [플레이어] <메시지 출력 여부(true/false)>"));
					return false;
				}
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("대상 플레이어 오프라인").replace("[대상]", args[0]));
						return false;
					}
					p.setFoodLevel(20);
					super.m.sendMessage(p, "허기 회복");
					if(b)
					{
						super.m.sendMessage(sender, "타인 허기 회복",new DoubleString("[대상]",p.getName()));
						super.m.sendMessage(p, "허기 회복");
					}
				
			}else if(args.length == 3){
				boolean b = false;
				boolean b2 = false;
				if(Utility.DataUtil.isBoolean(args[1]) && Utility.DataUtil.isBoolean(args[2]))
				{
					b = Boolean.parseBoolean(args[1]);
					b2 = Boolean.parseBoolean(args[2]);
				}
				else
				{
					sender.sendMessage(super.m.getMessage("명령어 사용법").replace("[사용법]", "/" + label + " [플레이어] <메시지 출력 여부(true/false)> <대상 메시지 출력 여부(true/false>"));
					return false;
				}
					Player p = Bukkit.getPlayer(args[0]);
					if(p == null)
					{
						sender.sendMessage(super.m.getMessage("대상 플레이어 오프라인").replace("[대상]", args[0]));
						return false;
					}
					p.setFoodLevel(20);
					if(b)
						super.m.sendMessage(sender, "타인 허기 회복",new DoubleString("[대상]",p.getName()));
					if(b2)
						super.m.sendMessage(p, "허기 회복");
			}
		}
		return false;
	}

}
