package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandSummon extends CommandsRegistry implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!PlayerUtil.hasPermission(sender, PermissionType.CommandSummon))
		{
			super.m.sendMessage(sender, "명령어 권한 부족");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 1)
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]" ,"/" + label + " [몬스터] [개체수] <추가 옵션..>"));
			else{
				EntityType toSummon = DataUtil.getEntityType(args[0]);
				int summonamount = 0;
				boolean forceedit = false;
				Player p = null;
				String name = "";
				boolean show = false;
				try{
					summonamount = Integer.parseInt(args[1]);
					if(summonamount <= 0)
					{
						super.m.sendMessage(sender, "매개 변수 제한");
						return false;
					}
				}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");return false;}
				if(toSummon == null){
					super.m.sendMessage(sender, "엔티티 미존재");
					return false;
				}
				if(!toSummon.isAlive())
				{
					super.m.sendMessage(sender, "엔티티 제한");
					
					return false;
				}
				if(summonamount > DataManager.summonamount)
				{
					forceedit = true;
					summonamount = DataManager.summonamount;
				}
				for(int i = 2;i < args.length;i++){
					String[] s = args[i].split(":");
					switch(s[0].toLowerCase())
					{
					case "플레이아":
					case "player":
					{
						if(Bukkit.getPlayer(s[1]) == null)
						{
							super.m.sendMessage(sender, "대상 플레이어 오프라인");
							return false;
						}
						p = Bukkit.getPlayer(s[1]);
					}
					break;
					case "name":
					case "이름":
						name = s[1].replace("&", "§").replace("_", " ");
						break;
					case "show":
					case "이름표":
						show = Boolean.parseBoolean(s[1]);
						break;
						
					}
				}
				if(p == null){
					Block b = ((Player)sender).getTargetBlock(null, 100);
					if(b == null || b.getType() == null || b.getType() == Material.AIR){
						super.m.sendMessage(sender, "타게팅된 블럭 없음");
						return false;
					}
					super.m.sendMessage(sender, "엔티티 소환",DataUtil.asArray(new DoubleString("[엔티티]",toSummon.name()),new DoubleString("[개체수]",String.valueOf(summonamount))));
					if(forceedit)
						super.m.sendMessage(sender, "엔티티 개체수 제한");
					while(summonamount >= 1)
					{
						Entity e = b.getWorld().spawnEntity(b.getLocation().add(0,2,0), toSummon);
						LivingEntity e2 = (LivingEntity)e;
						e2.setCustomName(name);
						e2.setCustomNameVisible(show);
						summonamount--;
					}
				}else{
					super.m.sendMessage(sender, "대상 엔티티 소환",DataUtil.asArray(new DoubleString("[대상]",p.getName()),new DoubleString("[엔티티]",toSummon.name()),new DoubleString("[개체수]",String.valueOf(summonamount))));
					if(forceedit)
						super.m.sendMessage(sender, "엔티티 개체수 제한");
					while(summonamount >= 1)
					{
						Entity e = p.getWorld().spawnEntity(p.getLocation().add(0,2,0), toSummon);
						LivingEntity e2 = (LivingEntity)e;
						e2.setCustomName(name);
						e2.setCustomNameVisible(show);
						summonamount--;
					}
					
				}
			}
		}else{
			if(args.length <= 2)
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]" ,"/" + label + " [몬스터] [개체수] [플레이어] <추가 옵션..>"));
			else{
				if(Bukkit.getPlayer(args[2]) == null)
				{
					super.m.sendMessage(sender, "대상 플레이어 오프라인");
					return false;
				}
				EntityType toSummon = DataUtil.getEntityType(args[0]);
				int summonamount = 0;
				boolean forceedit = false;
				Player p = Bukkit.getPlayer(args[2]);
				String name = "";
				boolean show = false;
				try{
					summonamount = Integer.parseInt(args[1]);
					if(summonamount <= 0)
					{
						super.m.sendMessage(sender, "매개 변수 제한");
						return false;
					}
				}catch(Exception e){super.m.sendMessage(sender, "잘못된 상수 매개 변수");return false;}
				if(toSummon == null){
					super.m.sendMessage(sender, "엔티티 미존재");
					return false;
				}
				if(!toSummon.isAlive())
				{
					super.m.sendMessage(sender, "엔티티 제한");
					
					return false;
				}
				if(summonamount > DataManager.summonamount)
				{
					forceedit = true;
					summonamount = DataManager.summonamount;
				}
				for(int i = 2;i < args.length;i++){
					String[] s = args[i].split(":");

					switch(s[0].toLowerCase())
					{
					case "name":
					case "이름":
						name = s[1].replace("&", "§").replace("_", " ");
						break;
					case "show":
					case "이름표":
						show = Boolean.parseBoolean(s[1]);
						break;
						
					}
				}
					super.m.sendMessage(sender, "타인 엔티티 소환",DataUtil.asArray(new DoubleString("[대상]",p.getName()),new DoubleString("[엔티티]",toSummon.name()),new DoubleString("[개체수]",String.valueOf(summonamount))));
					if(forceedit)
						super.m.sendMessage(sender, "엔티티 개체수 제한");
					while(summonamount >= 1)
					{
						Entity e = p.getWorld().spawnEntity(p.getLocation().add(0,2,0), toSummon);
						LivingEntity e2 = (LivingEntity)e;
						e2.setCustomName(name);
						e2.setCustomNameVisible(show);
						summonamount--;
					}
					
				
			}
		}
		return false;
	}

}
