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
			super.m.sendMessage(sender, "��ɾ� ���� ����");
			return false;
		}
		if(sender instanceof Player){
			if(args.length <= 1)
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]" ,"/" + label + " [����] [��ü��] <�߰� �ɼ�..>"));
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
						super.m.sendMessage(sender, "�Ű� ���� ����");
						return false;
					}
				}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
				if(toSummon == null){
					super.m.sendMessage(sender, "��ƼƼ ������");
					return false;
				}
				if(!toSummon.isAlive())
				{
					super.m.sendMessage(sender, "��ƼƼ ����");
					
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
					case "�÷��̾�":
					case "player":
					{
						if(Bukkit.getPlayer(s[1]) == null)
						{
							super.m.sendMessage(sender, "��� �÷��̾� ��������");
							return false;
						}
						p = Bukkit.getPlayer(s[1]);
					}
					break;
					case "name":
					case "�̸�":
						name = s[1].replace("&", "��").replace("_", " ");
						break;
					case "show":
					case "�̸�ǥ":
						show = Boolean.parseBoolean(s[1]);
						break;
						
					}
				}
				if(p == null){
					Block b = ((Player)sender).getTargetBlock(null, 100);
					if(b == null || b.getType() == null || b.getType() == Material.AIR){
						super.m.sendMessage(sender, "Ÿ���õ� �� ����");
						return false;
					}
					super.m.sendMessage(sender, "��ƼƼ ��ȯ",DataUtil.asArray(new DoubleString("[��ƼƼ]",toSummon.name()),new DoubleString("[��ü��]",String.valueOf(summonamount))));
					if(forceedit)
						super.m.sendMessage(sender, "��ƼƼ ��ü�� ����");
					while(summonamount >= 1)
					{
						Entity e = b.getWorld().spawnEntity(b.getLocation().add(0,2,0), toSummon);
						LivingEntity e2 = (LivingEntity)e;
						e2.setCustomName(name);
						e2.setCustomNameVisible(show);
						summonamount--;
					}
				}else{
					super.m.sendMessage(sender, "��� ��ƼƼ ��ȯ",DataUtil.asArray(new DoubleString("[���]",p.getName()),new DoubleString("[��ƼƼ]",toSummon.name()),new DoubleString("[��ü��]",String.valueOf(summonamount))));
					if(forceedit)
						super.m.sendMessage(sender, "��ƼƼ ��ü�� ����");
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
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]" ,"/" + label + " [����] [��ü��] [�÷��̾�] <�߰� �ɼ�..>"));
			else{
				if(Bukkit.getPlayer(args[2]) == null)
				{
					super.m.sendMessage(sender, "��� �÷��̾� ��������");
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
						super.m.sendMessage(sender, "�Ű� ���� ����");
						return false;
					}
				}catch(Exception e){super.m.sendMessage(sender, "�߸��� ��� �Ű� ����");return false;}
				if(toSummon == null){
					super.m.sendMessage(sender, "��ƼƼ ������");
					return false;
				}
				if(!toSummon.isAlive())
				{
					super.m.sendMessage(sender, "��ƼƼ ����");
					
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
					case "�̸�":
						name = s[1].replace("&", "��").replace("_", " ");
						break;
					case "show":
					case "�̸�ǥ":
						show = Boolean.parseBoolean(s[1]);
						break;
						
					}
				}
					super.m.sendMessage(sender, "Ÿ�� ��ƼƼ ��ȯ",DataUtil.asArray(new DoubleString("[���]",p.getName()),new DoubleString("[��ƼƼ]",toSummon.name()),new DoubleString("[��ü��]",String.valueOf(summonamount))));
					if(forceedit)
						super.m.sendMessage(sender, "��ƼƼ ��ü�� ����");
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
