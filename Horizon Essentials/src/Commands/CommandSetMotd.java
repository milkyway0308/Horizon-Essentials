package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.Event.Horizon_MotdEvent;
import Utility.PlayerUtil;

public class CommandSetMotd  extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender.isOp() || PlayerUtil.hasPermission(sender, PermissionType.CommandChangeMotd))
			{
				if(args.length <= 0)
				{
					super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [add/remove/list/reload]"));
				}else{
					switch(args[0])
					{
					case "add":
						if(args.length <= 1)
						{
							super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " add [motd 내용]"));
							break;
						}
						StringBuilder b = new StringBuilder(args[1]);
						for(int i = 2;i < args.length;i++)
							b.append(" " + args[i]);
						Horizon_MotdEvent.motd.add(b.toString().replace("&", "§"));
						super.m.sendMessage(sender, "서버 motd 추가");
						break;
					case "remove":
						if(args.length <= 1)
						{
							super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " remove [motd 번호]"));
							break;
						}
						int a = 0;
						try{
						a = Integer.parseInt(args[1]);
						if(a <= 0)
						{
							super.m.sendMessage(sender, "매개 변수 제한");
							return false;
						}
						}catch(Exception e){super.m.sendMessage(sender, "잘못된 매개 변수"); return false;}
						if(Horizon_MotdEvent.motd.size() < a)
						{
							sender.sendMessage("§c등록된 motd보다 삭제할 motd의 번호가 더 높습니다.");
							return false;
						}
						Horizon_MotdEvent.motd.remove(a-1);
						super.m.sendMessage(sender, "서버 motd 제거");
						break;
					case "list":
						sender.sendMessage("§7  Registered Motd List");
						for(int i = 0;i < Horizon_MotdEvent.motd.size();i++)
						{
							sender.sendMessage("§7[" + (i+1) + "] §f " + Horizon_MotdEvent.motd.get(i));
						}
						break;
					case "reload":
						Horizon_Essentials.Event.Horizon_MotdEvent.Load();
						sender.sendMessage("§6MOTD를 다시 불러왔습니다.");
						break;
					}
				}
			}else{
				super.m.sendMessage(sender, "명령어 권한 부족");
			}
	return false;
	}
}