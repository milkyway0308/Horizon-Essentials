package Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import DataUtil.DoubleString;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandItem extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandGetItem))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if (args.length <= 0) {
				super.m.sendMessage(sender, "��ɾ� ����",
						new DoubleString("[����]", "/" + label + " [�������ڵ�/�������̸�]<:�����۸�Ÿ> [����] <������ �ɼ�:������>(/i 137 1)"));
				return false;
			}
			if (args.length == 1) {
				String[] s = args[0].toLowerCase().split(":");
				boolean usingItemCode = true;
				int itemcode = 0;
				int metacode = 0;
				if (s.length == 2) {
					try {
						metacode = Integer.parseInt(s[1]);
					} catch (NumberFormatException e) {
						super.m.sendMessage(sender, "�߸��� ��Ÿ������ �ڵ�");
						return false;
					}

					try {
						itemcode = Integer.parseInt(s[0]);
						usingItemCode = true;
					} catch (NumberFormatException e) {
						usingItemCode = false;
					}
					if(metacode <= -1)
					{
						super.m.sendMessage(sender, "�߸��� ��Ÿ������");
						return false;
					}
					ItemStack st;
					List<Integer> a = new ArrayList<Integer>();
					a.add(itemcode);
					a.add(metacode);
					if (usingItemCode)
						try {
							st = new ItemStack(Material.getMaterial(itemcode), (short) metacode);
						} catch (Exception e) {
							super.m.sendMessage(sender, "������ ������");
							return false;
						}
					else {
						if (DataManager.items.containsKey(s[0])) {
							st = DataManager.items.get(s[0]);
							if(st.getDurability() != 0)
								metacode = st.getDurability();
						} else {
							try {
								st = new ItemStack(Material.getMaterial(s[0]), metacode);
							} catch (Exception e) {
								super.m.sendMessage(sender, "������ ������");
								return false;
							}
						}

					}
					st.setAmount(64);
					st.setDurability((short) metacode);
					String name = usingItemCode
							? DataManager.name.containsKey(a) ? DataManager.name.get(a).get(0) : st.getType().toString()
							: s[0];
					super.m.sendMessage((Player) sender, "������ ����", Arrays
							.asList(new DoubleString("[������]", name.replace("_", " ")), new DoubleString("[����]", "64")));
					((Player) sender).getInventory().addItem(st);
				} else if (s.length >= 3) {
					super.m.sendMessage(sender, "�߸��� ��Ÿ������");
					return false;
				} else if (s.length == 1) {

					try {
						itemcode = Integer.parseInt(args[0]);
						usingItemCode = true;
					} catch (NumberFormatException e) {
						usingItemCode = false;
					}
					ItemStack st = new ItemStack(Material.AIR);
					List<Integer> a = new ArrayList<Integer>();
					a.add(itemcode);
					a.add(metacode);
					if (usingItemCode)
						try {
							st = new ItemStack(Material.getMaterial(itemcode), (short) metacode);
						} catch (Exception e) {
							super.m.sendMessage(sender, "������ ������");
							return false;
						}
					else {
						if (DataManager.items.containsKey(s[0])) {
							st = DataManager.items.get(s[0]);
							if(st.getDurability() != 0)
								metacode = st.getDurability();
						} else {
							try {
								st = new ItemStack(Material.getMaterial(s[0]), metacode);
							} catch (Exception e) {
								super.m.sendMessage(sender, "������ ������");
								return false;
							}
						}

					}
					st.setAmount(64);
					st.setDurability((short) metacode);
					String name = usingItemCode
							? DataManager.name.containsKey(a) ? DataManager.name.get(a).get(0) : st.getType().toString()
							: s[0];
					super.m.sendMessage((Player) sender, "������ ����", Arrays
							.asList(new DoubleString("[������]", name.replace("_", " ")), new DoubleString("[����]", "64")));
					((Player) sender).getInventory().addItem(st);
				}

			} else if (args.length == 2) {
				ItemStack st;
				String[] s = args[0].toLowerCase().split(":");
				boolean usingItemCode = true;
				int itemcode = 0;
				int metacode = 0;
				int amount = 1;
				if (s.length == 2) {
					try {
						metacode = Integer.parseInt(s[1]);
					} catch (NumberFormatException e) {
						super.m.sendMessage(sender, "�߸��� ��Ÿ������ �ڵ�");
						return false;
					}
					try {
						amount = Integer.parseInt(args[1]);
					} catch (Exception e) {
						super.m.sendMessage(sender, "�߸��� ����");
					}
					if(amount <= 0){
						super.m.sendMessage(sender, "�߸��� ����");
						return false;
					}
					if(metacode <= -1)
					{
						super.m.sendMessage(sender, "�߸��� ��Ÿ������");
						return false;
					}
					try {
						itemcode = Integer.parseInt(s[0]);
						usingItemCode = true;
					} catch (NumberFormatException e) {
						usingItemCode = false;
					}
					List<Integer> a = new ArrayList<Integer>();
					a.add(itemcode);
					a.add(metacode);
					if (usingItemCode)
						try {
							st = new ItemStack(Material.getMaterial(itemcode), (short) metacode);
						} catch (Exception e) {
							super.m.sendMessage(sender, "������ ������");
							return false;
						}
					else {
						if (DataManager.items.containsKey(s[0])) {
							st = DataManager.items.get(s[0]);
							if(st.getDurability() != 0)
								metacode = st.getDurability();
						} else {
							try {
								st = new ItemStack(Material.getMaterial(s[0]), metacode);
							} catch (Exception e) {
								super.m.sendMessage(sender, "������ ������");
								return false;
							}
						}

					}
					st.setAmount(amount);
					st.setDurability((short) metacode);
					String name = usingItemCode
							? DataManager.name.containsKey(a) ? DataManager.name.get(a).get(0) : st.getType().toString()
							: s[0];
					super.m.sendMessage((Player) sender, "������ ����",
							Arrays.asList(new DoubleString("[������]", name.replace("_", " ")),
									new DoubleString("[����]", String.valueOf(amount))));
					((Player) sender).getInventory().addItem(st);
				} else if (s.length >= 3) {
					super.m.sendMessage(sender, "�߸��� ��Ÿ������");
					return false;
				} else if (s.length == 1) {
					try {
						amount = Integer.parseInt(args[1]);
					} catch (Exception e) {
						super.m.sendMessage(sender, "�߸��� ����");
					}
					if(amount <= 0){
						super.m.sendMessage(sender, "�߸��� ����");
						return false;
					}
					if(metacode <= -1)
					{
						super.m.sendMessage(sender, "�߸��� ��Ÿ������");
						return false;
					}
					try {
						itemcode = Integer.parseInt(args[0]);
						usingItemCode = true;
					} catch (NumberFormatException e) {
						usingItemCode = false;
					}
					List<Integer> a = new ArrayList<Integer>();
					a.add(itemcode);
					a.add(metacode);
					if (usingItemCode)
						try {
							st = new ItemStack(Material.getMaterial(itemcode), (short) metacode);
						} catch (Exception e) {
							super.m.sendMessage(sender, "������ ������");
							return false;
						}
					else {
						if (DataManager.items.containsKey(s[0])) {
							st = DataManager.items.get(s[0]);
							if(st.getDurability() != 0)
								metacode = st.getDurability();
						} else {
							try {
								st = new ItemStack(Material.getMaterial(s[0]), metacode);
							} catch (Exception e) {
								super.m.sendMessage(sender, "������ ������");
								return false;
							}
						}

					}
					st.setAmount(amount);
					st.setDurability((short) metacode);
					String name = usingItemCode
							? DataManager.name.containsKey(a) ? DataManager.name.get(a).get(0) : st.getType().toString()
							: s[0];
					super.m.sendMessage((Player) sender, "������ ����",
							Arrays.asList(new DoubleString("[������]", name.replace("_", " ")),
									new DoubleString("[����]", String.valueOf(amount))));
					((Player) sender).getInventory().addItem(st);
				}

			} else if (args.length >= 3) {
				boolean msg = true;
				ItemStack st;
				String[] s = args[0].toLowerCase().split(":");
				boolean usingItemCode = true;
				int itemcode = 0;
				int metacode = 0;
				int amount = 1;
				if (s.length == 2) {
					try {
						metacode = Integer.parseInt(s[1]);
					} catch (NumberFormatException e) {
						super.m.sendMessage(sender, "�߸��� ��Ÿ������ �ڵ�");
						return false;
					}
					try {
						amount = Integer.parseInt(args[1]);
					} catch (Exception e) {
						super.m.sendMessage(sender, "�߸��� ����");
					}
					if(amount <= 0){
						super.m.sendMessage(sender, "�߸��� ����");
						return false;
					}
					if(metacode <= -1)
					{
						super.m.sendMessage(sender, "�߸��� ��Ÿ������");
						return false;
					}
					try {
						itemcode = Integer.parseInt(s[0]);
						usingItemCode = true;
					} catch (NumberFormatException e) {
						usingItemCode = false;
					}
					List<Integer> a = new ArrayList<Integer>();
					a.add(itemcode);
					a.add(metacode);
					if (usingItemCode)
						try {
							st = new ItemStack(Material.getMaterial(itemcode), (short) metacode);
						} catch (Exception e) {
							super.m.sendMessage(sender, "������ ������");
							return false;
						}
					else {
						if (DataManager.items.containsKey(s[0])) {
							st = DataManager.items.get(s[0]);
							if(st.getDurability() != 0)
								metacode = st.getDurability();
						} else {
							try {
								st = new ItemStack(Material.getMaterial(s[0]), metacode);
							} catch (Exception e) {
								super.m.sendMessage(sender, "������ ������");
								return false;
							}
						}

					}
					for(int i = 2;i < args.length;i++)
					{
						ItemStack s4 = Utility.DataUtil.CommandApply(st, args[i], sender);
						if(s4 == null)
							return false;
						st = s4;
					}
					st.setAmount(amount);
					st.setDurability((short) metacode);
					String name = usingItemCode
							? DataManager.name.containsKey(a) ? DataManager.name.get(a).get(0) : st.getType().toString()
							: s[0];
					if(msg)
						super.m.sendMessage((Player) sender, "������ ����",
								Arrays.asList(new DoubleString("[������]", name.replace("_", " ")),
										new DoubleString("[����]", String.valueOf(amount))));
					((Player) sender).getInventory().addItem(st);
				} else if (s.length >= 3) {
					super.m.sendMessage(sender, "�߸��� ��Ÿ������");
					return false;
				} else if (s.length == 1) {
					try {
						amount = Integer.parseInt(args[1]);
					} catch (Exception e) {
						super.m.sendMessage(sender, "�߸��� ����");
						return false;
					}
					if(amount <= 0){
						super.m.sendMessage(sender, "�߸��� ����");
						return false;
					}
					if(metacode <= -1)
					{
						super.m.sendMessage(sender, "�߸��� ��Ÿ������");
						return false;
					}
					try {
						itemcode = Integer.parseInt(s[0]);
						usingItemCode = true;
					} catch (NumberFormatException e) {
						usingItemCode = false;
					}
					List<Integer> a = new ArrayList<Integer>();
					a.add(itemcode);
					a.add(metacode);
					if (usingItemCode)
						try {
							st = new ItemStack(Material.getMaterial(itemcode), (short) metacode);
						} catch (Exception e) {
							super.m.sendMessage(sender, "������ ������");
							return false;
						}
					else {
						if (DataManager.items.containsKey(s[0])) {
							st = DataManager.items.get(s[0]);
							if(st.getDurability() != 0)
								metacode = st.getDurability();
						} else {
							try {
								st = new ItemStack(Material.getMaterial(s[0]), metacode);
							} catch (Exception e) {
								super.m.sendMessage(sender, "������ ������");
								return false;
							}
						}

					}
					for(int i = 2;i < args.length;i++)
					{
						ItemStack s4 = Utility.DataUtil.CommandApply(st, args[i], sender);
						if(s4 == null)
							return false;
						st = s4;
					}
					st.setAmount(amount);
					st.setDurability((short) metacode);
					String name = usingItemCode
							? DataManager.name.containsKey(a) ? DataManager.name.get(a).get(0) : st.getType().toString()
							: s[0];
					if(msg)
						super.m.sendMessage((Player) sender, "������ ����",
								Arrays.asList(new DoubleString("[������]", name.replace("_", " ")),
										new DoubleString("[����]", String.valueOf(amount))));
					((Player) sender).getInventory().addItem(st);
				}
			}
		} else {
			super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
		}
		return false;
	}
}