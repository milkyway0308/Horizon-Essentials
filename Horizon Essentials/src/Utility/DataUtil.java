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
	case "È­»ì":
	case "arrow":
		ret = EntityType.ARROW;
		break;
	case "¹ÚÁã":
	case "bat":
		ret = EntityType.BAT;
		break;
	case "blaze":
	case "ºí·¹ÀÌÁî":
		ret = EntityType.BLAZE;
		break;
	case "boat":
	case "º¸Æ®":
	case "¹è":
		ret = EntityType.BOAT;
		break;
	case "µ¿±¼°Å¹Ì":
	case "µ¶°Å¹Ì":
	case "cave_spider":
	case "cavespider":
		ret = EntityType.CAVE_SPIDER;
		break;
	case "´ß":
	case "chicken":
		ret = EntityType.CHICKEN;
		break;
	case "¼Ò":
	case "cow":
		ret = EntityType.COW;
		break;
	case "Å©¸®ÆÛ":
	case "creeper":
		ret = EntityType.CREEPER;
		break;
	case "µå¶ø¾ÆÀÌÅÛ":
	case "¾ÆÀÌÅÛ":
	case "dropitem":
		ret = EntityType.DROPPED_ITEM;
		break;
	case "¿£´õµå·¡°ï":
	case "endergragon":
		ret = EntityType.ENDER_DRAGON;
		break;
	case "¿£´õÅ©¸®½ºÅ»":
	case "endercrystal":
		ret = EntityType.ENDER_CRYSTAL;
		break;
	case "¿£´õ¸Ç":
	case "enderman":
		ret = EntityType.ENDERMAN;
		break;
	case "°æÇèÄ¡":
	case "experience":
	case "experience_orb":
		ret = EntityType.EXPERIENCE_ORB;
		break;
	case "³«ÇÏºí·°":
	case "fallingblock":
		ret = EntityType.FALLING_BLOCK;
		break;
	case "È­¿°±¸":
	case "fireball":
		ret = EntityType.FIREBALL;
		break;
	case "ÆøÁ×":
		ret = EntityType.FIREWORK;
		break;
	case "°¡½ºÆ®":
	case "ghast":
		ret = EntityType.GHAST;
		break;
	case "°ÅÀÎ":
	case "ÀÚÀÌ¾ðÆ®":
	case "giant":
		ret = EntityType.GIANT;
		break;
	case "Ã¶°ñ·½":
	case "°ñ·½":
	case "golem":
	case "irongolem":
		ret = EntityType.IRON_GOLEM;
		break;
	case "¾ÆÀÌÅÛ¾×ÀÚ":
	case "itemframe":
		ret = EntityType.ITEM_FRAME;
		break;
	case "¸¶±×¸¶Å¥ºê":
	case "magmacube":
		ret = EntityType.MAGMA_CUBE;
		break;
	case "mushroomcow":
	case "¹ö¼¸¼Ò":
		ret = EntityType.MUSHROOM_COW;
		break;
	case "¿À¼¿·Ô":
	case "ocelot":
		ret = EntityType.OCELOT;
		break;
	case "±×¸²":
	case "painting":
		ret = EntityType.PAINTING;
		break;
	case "µÅÁöÁ»ºñ":
	case "Á»ºñÇÇ±×¸Ç":
	case "zombiepigman":
	case "zombiepig":
	case "pigzombie":
		ret = EntityType.PIG_ZOMBIE;
		break;
	case "pig":
	case "µÅÁö":
		ret = EntityType.PIG;
		break;
	case "primtedtnt":
	case "Æ¼¿£Æ¼":
		ret = EntityType.PRIMED_TNT;
		break;
	case "¾ç":
	case "sheep":
		ret = EntityType.SHEEP;
		break;
	case "Á»¹ú·¹":
	case "½Ç¹öÇÇ½Ã":
		ret = EntityType.SILVERFISH;
		break;
	case "½ºÄÌ·¹Åæ":
	case "skeleton":
		ret = EntityType.SKELETON;
		break;
	case "½½¶óÀÓ":
	case "slime":
		ret = EntityType.SLIME;
		break;
	case "ÀÛÀºÈ­¿°±¸":
	case "smallfireball":
		ret = EntityType.SMALL_FIREBALL;
		break;
	case "´«µ¢ÀÌ":
	case "snowball":
		ret = EntityType.SNOWBALL;
		break;
	case "´«»ç¶÷":
	case "snowman":
		ret = EntityType.SNOWMAN;
		break;
	case "°Å¹Ì":
	case "spider":
		ret = EntityType.SPIDER;
		break;
	case "Æ÷¼Ç":
	case "ÅõÃ´¿ëÆ÷¼Ç":
	case "potion":
		ret = EntityType.SPLASH_POTION;
		break;
	case "¿ÀÂ¡¾î":
	case "squid":
		ret = EntityType.SQUID;
		break;
	case "°æÇèÄ¡º´":
		ret = EntityType.THROWN_EXP_BOTTLE;
		break;
	case "ÁÖ¹Î":
	case "villager":
		ret = EntityType.VILLAGER;
		break;
	case "wither":
	case "À§´õ":
		ret = EntityType.WITCH;
		break;
	case "witherskull":
	case "À§´õ¸Ó¸®":
		ret = EntityType.WITHER_SKULL;
		break;
	case "´Á´ë":
	case "wolf":
		ret = EntityType.WOLF;
		break;
	case "Á»ºñ":
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
			p.sendMessage(s.replace("&", "¡×"));
}
public static void OperatorMessage(String s,List<Player> noMessage){
	for(Player p : DataUtil.getOnlinePlayers())
		if(p.isOp() || p.hasPermission("Operator.Operator") && !noMessage.contains(p))
			p.sendMessage(s.replace("&", "¡×"));
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
		return "¾ÆÀÌÅÛ ÀÌ¸§ ¾øÀ½";
	
}
public static String ItemLore(ItemStack st){
	ItemMeta m = st.getItemMeta();
	if(m.hasLore() && m.getLore().size() >= 1)
		{
		StringBuilder b = new StringBuilder(m.getLore().get(0));
		for(int i = 1;i < m.getLore().size();i++)
			b.append("¡×f|" + m.getLore().get(i));
			return b.toString();
		}
	else
		return "¾ÆÀÌÅÛ ¼³¸í ¾øÀ½";
	
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
	return new String( a[0] + "ÀÏ " + a[1] + "½Ã°£ " + a[2] + "ºÐ " + a[3] + "ÃÊ");
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
	return new String( (int)a[0] + "ÀÏ " + (int)a[1] + "½Ã°£ " + (int)a[2] + "ºÐ " + (int)a[3] + "ÃÊ");
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
	return new String( a[0] + "ÀÏ " + a[1] + "½Ã°£ " + a[2] + "ºÐ " + a[3] + "ÃÊ");
}
public static void broadThanks(){
	Bukkit.getConsoleSender().sendMessage("¡×7 - ¡×6°³¹ß¿¡ µµ¿òÀ» ÁÖ½Å º£Å¸ Å×½ºÅÍ ¿©·¯ºÐ²² °¨»çµå¸³´Ï´Ù!");
	Bukkit.getConsoleSender().sendMessage("¡×7 - Spetial Thanks to:");
	Bukkit.getConsoleSender().sendMessage("¡×fSM_seolmin,Soa_,");
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
	case "³¯Ä«·Î¿ò":
	case "damageall":
		c = Enchantment.DAMAGE_ALL;
		break;
	case "Èû":
	case "power":
	case "arrowdamage":
		c = Enchantment.ARROW_DAMAGE;
		break;
	case "È°È­¿°":
	case "arrowfire":
		c = Enchantment.ARROW_FIRE;
		break;
	case "¹«ÇÑ":
	case "infinity":
		c = Enchantment.ARROW_INFINITE;
		break;
	case "È°³Ë¹é":
	case "arrowknockback":
		c = Enchantment.ARROW_KNOCKBACK;
		break;
	case "»ìÃæ":
	case "ARTHROPODS":
		c = Enchantment.DAMAGE_ARTHROPODS;
		break;
	case "°­Å¸":
	case "smite":
		c = Enchantment.DAMAGE_UNDEAD;
		break;
	case "È¿À²":
	case "efficiency":
		c = Enchantment.DIG_SPEED;
		break;
	case "³»±¸¼º":
	case "³»±¸µµ":
	case "durability":
		c = Enchantment.DURABILITY;
		break;
	case "È­¿°":
	case "fireaspect":
		c = Enchantment.FIRE_ASPECT;
		break;
	case "³Ë¹é":
	case "knockback":
		c = Enchantment.KNOCKBACK;
		break;
	case "Çà¿î":
	case "fortune":
	c = Enchantment.LOOT_BONUS_BLOCKS;
	break;
	case "¾àÅ»":
	case "loot":
		c = Enchantment.LOOT_BONUS_MOBS;
	case "È£Èí":
	case "»ê¼Ò":
	case "oxegen":
		c = Enchantment.OXYGEN;
		break;
	case "º¸È£":
	case "protection":
	case "protectionenvirment":
		c = Enchantment.PROTECTION_ENVIRONMENTAL;
		break;
	case "ÆøºôÀ¸·ÎºÎÅÍº¸È£":
	case "Æø¹ßº¸È£":
	case "explosionprotect":
	case "protectexplosion":
		c = Enchantment.PROTECTION_EXPLOSIONS;
		break;
	case "°¡º­¿îÂøÁö":
	case "³«ÇÏµ¥¹ÌÁö°¨¼Ò":
	case "fallingprotection":
		c = Enchantment.PROTECTION_FALL;
		break;
	case "¹ß»çÃ¼À¸·ÎºÎÅÍº¸È£":
	case "¹ß»çÃ¼º¸È£":
	case "projectileprotection":
		c = Enchantment.PROTECTION_PROJECTILE;
		break;
	case "È­¿°À¸·ÎºÎÅÍº¸È£":
	case "È­¿°º¸È£":
	case "fireprotection":
		c = Enchantment.PROTECTION_FIRE;
		break;
	case "½ÇÅ©ÅÍÄ¡":
	case "¼¶¼¼ÇÑ¼Õ±æ":
	case "silktouch":
		c = Enchantment.SILK_TOUCH;
		break;
	case "°¡½Ã":
	case "thorn":
	case "thorns":
		c = Enchantment.THORNS;
		break;
	case "¼öÁßÀÛ¾÷":
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
	String s4 = s.substring(0, a).replace("_"," ").replace("&", "¡×");
	String s5 = s.substring(a + 1, s.length()).replace("_"," ").replace("&", "¡×");
	String[] sp = new String[]{s4,s5};
	switch(sp[0].toLowerCase())
	{
	case "lore":
	case "¼³¸í":
	{
		String[] list = sp[1].split("\\|");
		List<String> lore = new ArrayList<String>();
		for(String s2 : list)
			lore.add(s2);
		m.setLore(lore);			
	}break;
	
	case "name":
	case "ÀÌ¸§":
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
		}catch(Exception ex){m2.sendMessage(sender, "Àß¸øµÈ »ó¼ö ¸Å°³ º¯¼ö");}
		if(level <= 0)
		{
			m2.sendMessage(sender, "¸Å°³ º¯¼ö Á¦ÇÑ");
			return null;
		}
		if(level >= 32768)
		{
			m2.sendMessage(sender, "ÀÎÃ¾Æ® ·¹º§ Á¦ÇÑ");
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
	return s.replaceAll("([a-zA-Z0-9¤¡-¤¾°¡-ÆR])", "").length() == 0;
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
