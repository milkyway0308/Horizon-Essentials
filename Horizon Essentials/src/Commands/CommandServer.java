package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.PermissionType;
import Horizon_Essentials.Event.Horizon_EventCancelActor;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandServer extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandServer))
			{
				super.m.sendMessage(sender, "명령어 권한 부족");
				return false;
			}
			if(args.length <= 1)
			{
				sender.sendMessage("    §aH§7orizon §aE§7ssentials Server Controller");
				if(DataUtil.isAlpha(label))
				{
					sender.sendMessage("§7/" + label + " chat <enable/disable> §e - §7권한이 없는 유저의 전체 채팅을 활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " move <enable/disable> §e - §7권한이 없는 유저의 동작을 활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " blockdamage <enable/disable> §e - §7권한이 없는 유저의 블럭 데미지을  활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " break <enable/disable> §e - §7권한이 없는 유저의 블럭 파괴을  활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " place <enable/disable> §e - §7권한이 없는 유저의 블럭 설치을  활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " pickup <enable/disable> §e - §7권한이 없는 유저의 아이템 픽업을  활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " drop <enable/disable> §e - §7권한이 없는 유저의 아이템 드랍을  활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " pvp <enable/disable> §e - §7PvP를  활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " god <enable/disable> §e - §7플레이어 무적을 활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " interact <enable/disable> §e - §7권한이 없는 유저의 상호 작용을  활성화시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " explosionblock <활성화/비활성화> §e - §7폭발 발생시 블럭 파괴 여부를 설정합니다.");
					sender.sendMessage("§7/" + label + " levellimit <enable/disable> §e - §7레벨 제한을 켭니다.");
					sender.sendMessage("§7/" + label + " level [제한 레벨] §e - §7제한 레벨을 설정합니다.");
				}else{
					sender.sendMessage("§7/" + label + " 채팅 <활성화/비활성화> §e - §7권한이 없는 유저의 전체 채팅을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 동작 <활성화/비활성화> §e - §7권한이 없는 유저의 동작을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 블럭데미지 <활성화/비활성화> §e - §7권한이 없는 유저의 블럭 데미지을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 블럭파괴 <활성화/비활성화> §e - §7권한이 없는 유저의 블럭 파괴을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 블럭설치 <활성화/비활성화> §e - §7권한이 없는 유저의 블럭 설치을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 픽업 <활성화/비활성화> §e - §7권한이 없는 유저의 아이템 픽업을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 드랍 <활성화/비활성화> §e - §7권한이 없는 유저의 아이템 드랍을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " pvp <활성화/비활성화> §e - §7PvP를 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 무적 <활성화/비활성화> §e - §7플레이어 무적을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 폭발블럭파괴 <활성화/비활성화> §e - §7폭발 발생시 블럭 파괴 여부를 설정합니다.");
					sender.sendMessage("§7/" + label + " 상호작용 <활성화/비활성화> §e - §7권한이 없는 유저의 상호 작용을 활성화 시키거나 비활성화시킵니다.");
					sender.sendMessage("§7/" + label + " 레벨제한 <활성화/비활성화> §e - §7레벨 제한을 켭니다.");
					sender.sendMessage("§7/" + label + " 레벨 [제한 레벨] §e - §7제한 레벨을 설정합니다.");
				}
			}else{
				switch(args[0]){
					case "chat":
					case "채팅":
						Horizon_EventCancelActor.Chat = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.Chat)
							Bukkit.broadcastMessage(super.m.getMessage("채팅 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("채팅 활성화"));
						break;
					case "move":
					case "동작":
						Horizon_EventCancelActor.move = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.move)
							Bukkit.broadcastMessage(super.m.getMessage("동작 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("동작 활성화"));
						break;
					case "blockdamage":
					case "블럭데미지":
						Horizon_EventCancelActor.BlockDamage = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.BlockDamage)
							Bukkit.broadcastMessage(super.m.getMessage("블럭 데미지 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("블럭 데미지 활성화"));
						break;
					case "break":
					case "블럭파괴":
						Horizon_EventCancelActor.BlockBreak = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.BlockBreak)
							Bukkit.broadcastMessage(super.m.getMessage("블럭 파괴 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("블럭 파괴 활성화"));
						break;
					case "explosionblock":
					case "폭발블럭파괴":
						Horizon_EventCancelActor.BlockExplosion = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.BlockExplosion)
							Bukkit.broadcastMessage(super.m.getMessage("블럭 폭발 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("블럭 폭발 활성화"));
						break;
					case "place":
					case "블럭설치":
						Horizon_EventCancelActor.BlockPlace = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.BlockPlace)
							Bukkit.broadcastMessage(super.m.getMessage("블럭 설치 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("블럭 설치 활성화"));
						break;
					case "pickup":
					case "픽업":
						Horizon_EventCancelActor.ItemPickup = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.ItemPickup )
							Bukkit.broadcastMessage(super.m.getMessage("아이템 픽업 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("아이템 픽업 활성화"));
						break;
					case "drop":
					case "드랍":
						Horizon_EventCancelActor.ItemDrop = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.ItemDrop)
							Bukkit.broadcastMessage(super.m.getMessage("아이템 드랍 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("아이템 드랍 활성화"));
						break;
					case "pvp":
						Horizon_EventCancelActor.pvp = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "false").replaceAll("enable|활성화", "true"));
						if(Horizon_EventCancelActor.pvp)
							Bukkit.broadcastMessage(super.m.getMessage("pvp 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("pvp 활성화"));
						break;
					case "무적":
					case "god":
						Horizon_EventCancelActor.Damage = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "false").replaceAll("enable|활성화", "true"));
						if(Horizon_EventCancelActor.Damage)
							Bukkit.broadcastMessage(super.m.getMessage("무적 활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("무적 비활성화"));
						break;
					case "상호작용":
					case "interact":
						Horizon_EventCancelActor.Interact = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "true").replaceAll("enable|활성화", "false"));
						if(Horizon_EventCancelActor.Interact)
							Bukkit.broadcastMessage(super.m.getMessage("상호 작용 비활성화"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("상호 작용 활성화"));
						break;
					case "레벨제한":
					case "levellimit":
						Horizon_EventCancelActor.Level_Up = Boolean.parseBoolean(args[1].replaceAll("disable|비활성화", "false").replaceAll("enable|활성화", "true"));
						if(Horizon_EventCancelActor.Level_Up)
							Bukkit.broadcastMessage(super.m.getMessage("레벨 제한 활성화").replace("[레벨]",String.valueOf(Horizon_EventCancelActor.level)));
						else
							Bukkit.broadcastMessage(super.m.getMessage("레벨 제한 비활성화"));
						break;
					case "level":
					case "레벨":
						if(!DataUtil.isInteger(args[1]))
							sender.sendMessage( "§c레벨은 숫자만 가능합니다.");
						else{
							Horizon_EventCancelActor.level = Integer.parseInt(args[1]);
							sender.sendMessage("§f[§aH§7orizon §aE§7ssentials§f] §f성공적으로 레벨 제한이 " + args[1] + "으로 변경되었습니다.");
						}
				}
			}
		return false;
	}
}