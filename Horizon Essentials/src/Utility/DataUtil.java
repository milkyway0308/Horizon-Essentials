package Utility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import DataUtil.LocationCrafter;
import Horizon_Essentials.IMessageManager;
import Horizon_Essentials.Main;

public class DataUtil {
	static IMessageManager m2 = Main.getInst();
public static boolean isBoolean(String s)
{
	return s.equals("false") || s.equals("true");
}
public static EntityType getEntityType(String type)
{
	EntityType ret = null;
	type = type.toLowerCase().replace("_", "");
	switch(type)
	{
	case "ȭ��":
	case "arrow":
		ret = EntityType.ARROW;
		break;
	case "����":
	case "bat":
		ret = EntityType.BAT;
		break;
	case "blaze":
	case "������":
		ret = EntityType.BLAZE;
		break;
	case "boat":
	case "��Ʈ":
	case "��":
		ret = EntityType.BOAT;
		break;
	case "�����Ź�":
	case "���Ź�":
	case "cave_spider":
	case "cavespider":
		ret = EntityType.CAVE_SPIDER;
		break;
	case "��":
	case "chicken":
		ret = EntityType.CHICKEN;
		break;
	case "��":
	case "cow":
		ret = EntityType.COW;
		break;
	case "ũ����":
	case "creeper":
		ret = EntityType.CREEPER;
		break;
	case "���������":
	case "������":
	case "dropitem":
		ret = EntityType.DROPPED_ITEM;
		break;
	case "�����巡��":
	case "endergragon":
		ret = EntityType.ENDER_DRAGON;
		break;
	case "����ũ����Ż":
	case "endercrystal":
		ret = EntityType.ENDER_CRYSTAL;
		break;
	case "������":
	case "enderman":
		ret = EntityType.ENDERMAN;
		break;
	case "����ġ":
	case "experience":
	case "experience_orb":
		ret = EntityType.EXPERIENCE_ORB;
		break;
	case "���Ϻ�":
	case "fallingblock":
		ret = EntityType.FALLING_BLOCK;
		break;
	case "ȭ����":
	case "fireball":
		ret = EntityType.FIREBALL;
		break;
	case "����":
		ret = EntityType.FIREWORK;
		break;
	case "����Ʈ":
	case "ghast":
		ret = EntityType.GHAST;
		break;
	case "����":
	case "���̾�Ʈ":
	case "giant":
		ret = EntityType.GIANT;
		break;
	case "ö��":
	case "��":
	case "golem":
	case "irongolem":
		ret = EntityType.IRON_GOLEM;
		break;
	case "�����۾���":
	case "itemframe":
		ret = EntityType.ITEM_FRAME;
		break;
	case "���׸�ť��":
	case "magmacube":
		ret = EntityType.MAGMA_CUBE;
		break;
	case "mushroomcow":
	case "������":
		ret = EntityType.MUSHROOM_COW;
		break;
	case "������":
	case "ocelot":
		ret = EntityType.OCELOT;
		break;
	case "�׸�":
	case "painting":
		ret = EntityType.PAINTING;
		break;
	case "��������":
	case "�����Ǳ׸�":
	case "zombiepigman":
	case "zombiepig":
	case "pigzombie":
		ret = EntityType.PIG_ZOMBIE;
		break;
	case "pig":
	case "����":
		ret = EntityType.PIG;
		break;
	case "primtedtnt":
	case "Ƽ��Ƽ":
		ret = EntityType.PRIMED_TNT;
		break;
	case "��":
	case "sheep":
		ret = EntityType.SHEEP;
		break;
	case "������":
	case "�ǹ��ǽ�":
		ret = EntityType.SILVERFISH;
		break;
	case "���̷���":
	case "skeleton":
		ret = EntityType.SKELETON;
		break;
	case "������":
	case "slime":
		ret = EntityType.SLIME;
		break;
	case "����ȭ����":
	case "smallfireball":
		ret = EntityType.SMALL_FIREBALL;
		break;
	case "������":
	case "snowball":
		ret = EntityType.SNOWBALL;
		break;
	case "�����":
	case "snowman":
		ret = EntityType.SNOWMAN;
		break;
	case "�Ź�":
	case "spider":
		ret = EntityType.SPIDER;
		break;
	case "����":
	case "��ô������":
	case "potion":
		ret = EntityType.SPLASH_POTION;
		break;
	case "��¡��":
	case "squid":
		ret = EntityType.SQUID;
		break;
	case "����ġ��":
		ret = EntityType.THROWN_EXP_BOTTLE;
		break;
	case "�ֹ�":
	case "villager":
		ret = EntityType.VILLAGER;
		break;
	case "wither":
	case "����":
		ret = EntityType.WITCH;
		break;
	case "witherskull":
	case "�����Ӹ�":
		ret = EntityType.WITHER_SKULL;
		break;
	case "����":
	case "wolf":
		ret = EntityType.WOLF;
		break;
	case "����":
	case "zombie":
		ret = EntityType.ZOMBIE;
		break;
		
	}
	
	return ret;
}
@SuppressWarnings("unchecked")
public final static Collection<? extends Player> getOnlinePlayers() {
 try {
  Method m = Bukkit.class.getDeclaredMethod("getOnlinePlayers");

  Object o45 = m.invoke(null);

  if (o45 instanceof Collection<?>)
   return (Collection<? extends Player>) o45;
  else
   return Arrays.asList((Player[]) o45);
 } catch (Exception e) {

 }
 return Collections.emptyList();
}
public static void OperatorMessage(String s){
	for(Player p : DataUtil.getOnlinePlayers())
		if(p.isOp() || p.hasPermission("Operator.Operator"))
			p.sendMessage(s.replace("&", "��"));
}
public static void OperatorMessage(String s,List<Player> noMessage){
	for(Player p : DataUtil.getOnlinePlayers())
		if(p.isOp() || p.hasPermission("Operator.Operator") && !noMessage.contains(p))
			p.sendMessage(s.replace("&", "��"));
}
@SafeVarargs
public static <T> ArrayList<T> asArray(T... args){
	ArrayList<T> st = new ArrayList<T>();
	for(T t : args){
		st.add(t);
	}
	return st;
}
public static <T> ArrayList<T> ArrayAsList(T[] args){
	ArrayList<T> st = new ArrayList<T>();
	for(T t : args){
		st.add(t);
	}
	return st;
}
public static StringBuilder LocationCrafterHash2String(HashMap<String,LocationCrafter> craft){
	StringBuilder b = new StringBuilder();
	boolean first = false;
	for(String s : craft.keySet())
		if(first)
		{
			first = true;
			b.append(s + "," + craft.get(s).toString());
		}else{
			b.append("|*|" + s + "/*/" + craft.get(s).toString());
		}
	return b;
}
public static String ItemName(ItemStack st){
	ItemMeta m = st.getItemMeta();
	if(m.hasDisplayName())
		return m.getDisplayName();
	else
		return "������ �̸� ����";
	
}
public static String ItemLore(ItemStack st){
	ItemMeta m = st.getItemMeta();
	if(m.hasLore() && m.getLore().size() >= 1)
		{
		StringBuilder b = new StringBuilder(m.getLore().get(0));
		for(int i = 1;i < m.getLore().size();i++)
			b.append("��f|" + m.getLore().get(i));
			return b.toString();
		}
	else
		return "������ ���� ����";
	
}
public static String IntegerToTime(int i) {
	int[] a = new int[4];
	a[0] = i / 24 / 60 / 60;
	i -= a[0] * 24 * 60 * 60;
	a[1] = i / 60 / 60;
	i -= a[1] * 60 * 60;
	a[2] = i / 60;
	i -= a[2] * 60;
	a[3] = i;
	return new String( a[0] + "�� " + a[1] + "�ð� " + a[2] + "�� " + a[3] + "��");
}
public static String IntegerToTime(double i) {
	double[] a = new double[4];

	a[0] = i / 24 / 60 / 60;
	i -= a[0] * 24 * 60 * 60;
	a[1] = i / 60 / 60;
	i -= a[1] * 60 * 60;
	a[2] = i / 60;
	i -= a[2] * 60;
	a[3] = i;
	return new String( (int)a[0] + "�� " + (int)a[1] + "�ð� " + (int)a[2] + "�� " + (int)a[3] + "��");
}

public static String IntegerToTime(long i) {
	long[] a = new long[4];
	a[0] = i / 24 / 60 / 60;
	i -= a[0] * 24 * 60 * 60;
	a[1] = i / 60 / 60;
	i -= a[1] * 60 * 60;
	a[2] = i / 60;
	i -= a[2] * 60;
	a[3] = i;
	return new String( a[0] + "�� " + a[1] + "�ð� " + a[2] + "�� " + a[3] + "��");
}
public static void broadThanks(){
	Bukkit.getConsoleSender().sendMessage("��7 - ��6���߿� ������ �ֽ� ��Ÿ �׽��� �����в� ����帳�ϴ�!");
	Bukkit.getConsoleSender().sendMessage("��7 - Spetial Thanks to:");
	Bukkit.getConsoleSender().sendMessage("��fSM_seolmin,Soa_,");
}
public static Enchantment StringtoEnchant(String s )
{
	Enchantment c = null;
	try{
		return Enchantment.getById(Integer.parseInt(s));
		
	}catch(Exception e){try{c = Enchantment.getByName(s);
		}catch(Exception ex){}
	if(c != null)
		return c;
	}
	
	switch(s.toLowerCase().replace(" ", ""))
	{
	case "sharpness":
	case "��ī�ο�":
	case "damageall":
		c = Enchantment.DAMAGE_ALL;
		break;
	case "��":
	case "power":
	case "arrowdamage":
		c = Enchantment.ARROW_DAMAGE;
		break;
	case "Ȱȭ��":
	case "arrowfire":
		c = Enchantment.ARROW_FIRE;
		break;
	case "����":
	case "infinity":
		c = Enchantment.ARROW_INFINITE;
		break;
	case "Ȱ�˹�":
	case "arrowknockback":
		c = Enchantment.ARROW_KNOCKBACK;
		break;
	case "����":
	case "ARTHROPODS":
		c = Enchantment.DAMAGE_ARTHROPODS;
		break;
	case "��Ÿ":
	case "smite":
		c = Enchantment.DAMAGE_UNDEAD;
		break;
	case "ȿ��":
	case "efficiency":
		c = Enchantment.DIG_SPEED;
		break;
	case "������":
	case "������":
	case "durability":
		c = Enchantment.DURABILITY;
		break;
	case "ȭ��":
	case "fireaspect":
		c = Enchantment.FIRE_ASPECT;
		break;
	case "�˹�":
	case "knockback":
		c = Enchantment.KNOCKBACK;
		break;
	case "���":
	case "fortune":
	c = Enchantment.LOOT_BONUS_BLOCKS;
	break;
	case "��Ż":
	case "loot":
		c = Enchantment.LOOT_BONUS_MOBS;
	case "ȣ��":
	case "���":
	case "oxegen":
		c = Enchantment.OXYGEN;
		break;
	case "��ȣ":
	case "protection":
	case "protectionenvirment":
		c = Enchantment.PROTECTION_ENVIRONMENTAL;
		break;
	case "�������κ��ͺ�ȣ":
	case "���ߺ�ȣ":
	case "explosionprotect":
	case "protectexplosion":
		c = Enchantment.PROTECTION_EXPLOSIONS;
		break;
	case "����������":
	case "���ϵ���������":
	case "fallingprotection":
		c = Enchantment.PROTECTION_FALL;
		break;
	case "�߻�ü���κ��ͺ�ȣ":
	case "�߻�ü��ȣ":
	case "projectileprotection":
		c = Enchantment.PROTECTION_PROJECTILE;
		break;
	case "ȭ�����κ��ͺ�ȣ":
	case "ȭ����ȣ":
	case "fireprotection":
		c = Enchantment.PROTECTION_FIRE;
		break;
	case "��ũ��ġ":
	case "�����Ѽձ�":
	case "silktouch":
		c = Enchantment.SILK_TOUCH;
		break;
	case "����":
	case "thorn":
	case "thorns":
		c = Enchantment.THORNS;
		break;
	case "�����۾�":
	case "water_worker":
		c = Enchantment.WATER_WORKER;
	default:
		c = null;
	}
	return c;
}

public static ItemStack CommandApply(ItemStack st,String s,CommandSender sender)
{
	ItemMeta m = st.getItemMeta();
	int a = s.indexOf(":");
	if (a <= -1) 
		return st;
	String s4 = s.substring(0, a).replace("_"," ").replace("&", "��");
	String s5 = s.substring(a + 1, s.length()).replace("_"," ").replace("&", "��");
	String[] sp = new String[]{s4,s5};
	switch(sp[0].toLowerCase())
	{
	case "lore":
	case "����":
	{
		String[] list = sp[1].split("\\|");
		List<String> lore = new ArrayList<String>();
		for(String s2 : list)
			lore.add(s2);
		m.setLore(lore);			
	}break;
	
	case "name":
	case "�̸�":
		m.setDisplayName(sp[1]);
		break;
	default:
	{
		int level = 0;
		Enchantment c = StringtoEnchant(sp[0]);
		if(c == null)
			break;
		try{
			level = Integer.parseInt(sp[1]);
		}catch(Exception ex){m2.sendMessage(sender, "�߸��� ��� �Ű� ����");}
		if(level <= 0)
		{
			m2.sendMessage(sender, "�Ű� ���� ����");
			return null;
		}
		if(level >= 32768)
		{
			m2.sendMessage(sender, "��þƮ ���� ����");
			return null;
		}
		
			m.addEnchant(c,level,true);
	}
	}
	st.setItemMeta(m);
	return st;
}
public static boolean isAlpha(String s){
	return s.replaceAll("([a-zA-Z])", "").length() == 0;
}
public static boolean isAlphaandNumber(String s){
	return s.replaceAll("([a-zA-Z0-9])", "").length() == 0;
}
public static boolean isAlphaandNumberandKorean(String s){
	return s.replaceAll("([a-zA-Z0-9��-����-�R])", "").length() == 0;
}
public static String ArrayToString(List<String> list){
	if(list.size() <= 0)
		return "";
	else{
		StringBuilder b = new StringBuilder(list.get(0));
		for(int i = 1;i < list.size();i++)
			b.append("," + list.get(i));
		return (b.toString().length() >= 1) ? b.toString() : "";
	}
}
public static List<String> StringToArray(String s){
	List<String> a = new ArrayList<String>();
	for(String s2 : s.split(","))
		a.add(s2);
	return a;
}
public static boolean isAllInteger(String... args){
	
	try{
		for(String s : args)
		{
			Integer.parseInt(s);
		}
	}catch(Exception e){return false;}
	return true;
}
public static boolean isInteger(String args){
	
	try{
		Integer.parseInt(args);
	}catch(Exception e){return false;}
	return true;
}
public static boolean isAllDouble(String... args){
	
	try{
		for(String s : args)
		{
			Double.parseDouble(s);
		}
	}catch(Exception e){return false;}
	return true;
}
public static boolean isDouble(String args){
	
	try{
		Double.parseDouble(args);
	}catch(Exception e){return false;}
	return true;
}
public static boolean isAllLong(String... args){
	
	try{
		for(String s : args)
		{
			Long.parseLong(s);
		}
	}catch(Exception e){return false;}
	return true;
}
public static boolean isLong(String args){
	
	try{
		Long.parseLong(args);
	}catch(Exception e){return false;}
	return true;
}
public static class DateCalulator{
	public boolean isTimePassed(Date target){
		boolean isPassed = false;
		Date nowData = new Date();

		if(nowData.getYear() > target.getYear())
			return true;
		if(nowData.getMonth() > target.getMonth())
			return true;
		if(nowData.getDate() > target.getMonth())
			return true;
		if(nowData.getHours() > target.getHours())
			return true;
		if(nowData.getMinutes() > target.getMinutes())
			return true;
		if(nowData.getSeconds() > target.getSeconds())
			return true;
		return isPassed;
	}
}
}
