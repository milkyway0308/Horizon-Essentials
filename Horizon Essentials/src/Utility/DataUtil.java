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
	case "화살":
	case "arrow":
		ret = EntityType.ARROW;
		break;
	case "박쥐":
	case "bat":
		ret = EntityType.BAT;
		break;
	case "blaze":
	case "블레이즈":
		ret = EntityType.BLAZE;
		break;
	case "boat":
	case "보트":
	case "배":
		ret = EntityType.BOAT;
		break;
	case "동굴거미":
	case "독거미":
	case "cave_spider":
	case "cavespider":
		ret = EntityType.CAVE_SPIDER;
		break;
	case "닭":
	case "chicken":
		ret = EntityType.CHICKEN;
		break;
	case "소":
	case "cow":
		ret = EntityType.COW;
		break;
	case "크리퍼":
	case "creeper":
		ret = EntityType.CREEPER;
		break;
	case "드랍아이템":
	case "아이템":
	case "dropitem":
		ret = EntityType.DROPPED_ITEM;
		break;
	case "엔더드래곤":
	case "endergragon":
		ret = EntityType.ENDER_DRAGON;
		break;
	case "엔더크리스탈":
	case "endercrystal":
		ret = EntityType.ENDER_CRYSTAL;
		break;
	case "엔더맨":
	case "enderman":
		ret = EntityType.ENDERMAN;
		break;
	case "경험치":
	case "experience":
	case "experience_orb":
		ret = EntityType.EXPERIENCE_ORB;
		break;
	case "낙하블럭":
	case "fallingblock":
		ret = EntityType.FALLING_BLOCK;
		break;
	case "화염구":
	case "fireball":
		ret = EntityType.FIREBALL;
		break;
	case "폭죽":
		ret = EntityType.FIREWORK;
		break;
	case "가스트":
	case "ghast":
		ret = EntityType.GHAST;
		break;
	case "거인":
	case "자이언트":
	case "giant":
		ret = EntityType.GIANT;
		break;
	case "철골렘":
	case "골렘":
	case "golem":
	case "irongolem":
		ret = EntityType.IRON_GOLEM;
		break;
	case "아이템액자":
	case "itemframe":
		ret = EntityType.ITEM_FRAME;
		break;
	case "마그마큐브":
	case "magmacube":
		ret = EntityType.MAGMA_CUBE;
		break;
	case "mushroomcow":
	case "버섯소":
		ret = EntityType.MUSHROOM_COW;
		break;
	case "오셀롯":
	case "ocelot":
		ret = EntityType.OCELOT;
		break;
	case "그림":
	case "painting":
		ret = EntityType.PAINTING;
		break;
	case "돼지좀비":
	case "좀비피그맨":
	case "zombiepigman":
	case "zombiepig":
	case "pigzombie":
		ret = EntityType.PIG_ZOMBIE;
		break;
	case "pig":
	case "돼지":
		ret = EntityType.PIG;
		break;
	case "primtedtnt":
	case "티엔티":
		ret = EntityType.PRIMED_TNT;
		break;
	case "양":
	case "sheep":
		ret = EntityType.SHEEP;
		break;
	case "좀벌레":
	case "실버피시":
		ret = EntityType.SILVERFISH;
		break;
	case "스켈레톤":
	case "skeleton":
		ret = EntityType.SKELETON;
		break;
	case "슬라임":
	case "slime":
		ret = EntityType.SLIME;
		break;
	case "작은화염구":
	case "smallfireball":
		ret = EntityType.SMALL_FIREBALL;
		break;
	case "눈덩이":
	case "snowball":
		ret = EntityType.SNOWBALL;
		break;
	case "눈사람":
	case "snowman":
		ret = EntityType.SNOWMAN;
		break;
	case "거미":
	case "spider":
		ret = EntityType.SPIDER;
		break;
	case "포션":
	case "투척용포션":
	case "potion":
		ret = EntityType.SPLASH_POTION;
		break;
	case "오징어":
	case "squid":
		ret = EntityType.SQUID;
		break;
	case "경험치병":
		ret = EntityType.THROWN_EXP_BOTTLE;
		break;
	case "주민":
	case "villager":
		ret = EntityType.VILLAGER;
		break;
	case "wither":
	case "위더":
		ret = EntityType.WITCH;
		break;
	case "witherskull":
	case "위더머리":
		ret = EntityType.WITHER_SKULL;
		break;
	case "늑대":
	case "wolf":
		ret = EntityType.WOLF;
		break;
	case "좀비":
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
			p.sendMessage(s.replace("&", "§"));
}
public static void OperatorMessage(String s,List<Player> noMessage){
	for(Player p : DataUtil.getOnlinePlayers())
		if(p.isOp() || p.hasPermission("Operator.Operator") && !noMessage.contains(p))
			p.sendMessage(s.replace("&", "§"));
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
		return "아이템 이름 없음";
	
}
public static String ItemLore(ItemStack st){
	ItemMeta m = st.getItemMeta();
	if(m.hasLore() && m.getLore().size() >= 1)
		{
		StringBuilder b = new StringBuilder(m.getLore().get(0));
		for(int i = 1;i < m.getLore().size();i++)
			b.append("§f|" + m.getLore().get(i));
			return b.toString();
		}
	else
		return "아이템 설명 없음";
	
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
	return new String( a[0] + "일 " + a[1] + "시간 " + a[2] + "분 " + a[3] + "초");
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
	return new String( (int)a[0] + "일 " + (int)a[1] + "시간 " + (int)a[2] + "분 " + (int)a[3] + "초");
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
	return new String( a[0] + "일 " + a[1] + "시간 " + a[2] + "분 " + a[3] + "초");
}
public static void broadThanks(){
	Bukkit.getConsoleSender().sendMessage("§7 - §6개발에 도움을 주신 베타 테스터 여러분께 감사드립니다!");
	Bukkit.getConsoleSender().sendMessage("§7 - Spetial Thanks to:");
	Bukkit.getConsoleSender().sendMessage("§fSM_seolmin,Soa_,");
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
	case "날카로움":
	case "damageall":
		c = Enchantment.DAMAGE_ALL;
		break;
	case "힘":
	case "power":
	case "arrowdamage":
		c = Enchantment.ARROW_DAMAGE;
		break;
	case "활화염":
	case "arrowfire":
		c = Enchantment.ARROW_FIRE;
		break;
	case "무한":
	case "infinity":
		c = Enchantment.ARROW_INFINITE;
		break;
	case "활넉백":
	case "arrowknockback":
		c = Enchantment.ARROW_KNOCKBACK;
		break;
	case "살충":
	case "ARTHROPODS":
		c = Enchantment.DAMAGE_ARTHROPODS;
		break;
	case "강타":
	case "smite":
		c = Enchantment.DAMAGE_UNDEAD;
		break;
	case "효율":
	case "efficiency":
		c = Enchantment.DIG_SPEED;
		break;
	case "내구성":
	case "내구도":
	case "durability":
		c = Enchantment.DURABILITY;
		break;
	case "화염":
	case "fireaspect":
		c = Enchantment.FIRE_ASPECT;
		break;
	case "넉백":
	case "knockback":
		c = Enchantment.KNOCKBACK;
		break;
	case "행운":
	case "fortune":
	c = Enchantment.LOOT_BONUS_BLOCKS;
	break;
	case "약탈":
	case "loot":
		c = Enchantment.LOOT_BONUS_MOBS;
	case "호흡":
	case "산소":
	case "oxegen":
		c = Enchantment.OXYGEN;
		break;
	case "보호":
	case "protection":
	case "protectionenvirment":
		c = Enchantment.PROTECTION_ENVIRONMENTAL;
		break;
	case "폭빌으로부터보호":
	case "폭발보호":
	case "explosionprotect":
	case "protectexplosion":
		c = Enchantment.PROTECTION_EXPLOSIONS;
		break;
	case "가벼운착지":
	case "낙하데미지감소":
	case "fallingprotection":
		c = Enchantment.PROTECTION_FALL;
		break;
	case "발사체으로부터보호":
	case "발사체보호":
	case "projectileprotection":
		c = Enchantment.PROTECTION_PROJECTILE;
		break;
	case "화염으로부터보호":
	case "화염보호":
	case "fireprotection":
		c = Enchantment.PROTECTION_FIRE;
		break;
	case "실크터치":
	case "섬세한손길":
	case "silktouch":
		c = Enchantment.SILK_TOUCH;
		break;
	case "가시":
	case "thorn":
	case "thorns":
		c = Enchantment.THORNS;
		break;
	case "수중작업":
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
	String s4 = s.substring(0, a).replace("_"," ").replace("&", "§");
	String s5 = s.substring(a + 1, s.length()).replace("_"," ").replace("&", "§");
	String[] sp = new String[]{s4,s5};
	switch(sp[0].toLowerCase())
	{
	case "lore":
	case "설명":
	{
		String[] list = sp[1].split("\\|");
		List<String> lore = new ArrayList<String>();
		for(String s2 : list)
			lore.add(s2);
		m.setLore(lore);			
	}break;
	
	case "name":
	case "이름":
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
		}catch(Exception ex){m2.sendMessage(sender, "잘못된 상수 매개 변수");}
		if(level <= 0)
		{
			m2.sendMessage(sender, "매개 변수 제한");
			return null;
		}
		if(level >= 32768)
		{
			m2.sendMessage(sender, "인첸트 레벨 제한");
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
	return s.replaceAll("([a-zA-Z0-9ㄱ-ㅎ가-힣])", "").length() == 0;
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
