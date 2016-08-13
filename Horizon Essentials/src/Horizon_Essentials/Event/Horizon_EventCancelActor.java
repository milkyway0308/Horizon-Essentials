package Horizon_Essentials.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import DataUtil.PermissionType;
import Utility.PlayerUtil;

public class Horizon_EventCancelActor extends EventRegistry implements Listener{
public static boolean ItemPickup = false;
public static boolean ItemDrop = false;
public static boolean BlockBreak = false;
public static boolean BlockPlace = false;
public static boolean BlockDamage = false;
public static boolean Chat = false;
public static boolean move = false;
public static boolean Interact = false;
public static boolean Damage = false;
public static boolean pvp = false;
public static int level = 50;
public static boolean Level_Up = false;
public static boolean BlockExplosion = false;
@EventHandler(priority = EventPriority.HIGHEST)
public void ItemPickup(PlayerPickupItemEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelItemPickup))
	if(ItemPickup)
		e.setCancelled(true);
}
@EventHandler(priority = EventPriority.HIGHEST)
public void ItemPickup(PlayerDropItemEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelItemDrop))
	if(ItemDrop)
		e.setCancelled(true);
}
@EventHandler(priority = EventPriority.HIGHEST)
public void Explosion(EntityExplodeEvent e)
{
	if(BlockExplosion)
		e.blockList().clear();
}
@EventHandler(priority = EventPriority.HIGHEST)
public void BlockBreak(BlockBreakEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelBlockBreak))
		if(BlockBreak)
		{
			e.setCancelled(true);
			super.msg.sendMessage(e.getPlayer(),"블럭 파괴 제한");
		}
}
@EventHandler(priority = EventPriority.HIGHEST)
public void BlockPlace(BlockPlaceEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelBlockPlace))
	{
		if(BlockPlace)
		{
			e.setCancelled(true);
			super.msg.sendMessage(e.getPlayer(),"블럭 설치 제한");
		}
	}
}
@EventHandler(priority = EventPriority.HIGHEST)
public void BlockDamage(PlayerInteractEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelBlockDamage))
	if(BlockDamage)
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			e.setCancelled(true);
			super.msg.sendMessage(e.getPlayer(),"블럭 데미지 제한");
		}
}

@EventHandler(priority = EventPriority.HIGHEST)
public void PlayerChat(AsyncPlayerChatEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelChat))
	if(Chat)
	{
		e.setCancelled(true);
		super.msg.sendMessage(e.getPlayer(),"채팅 제한");
	}
}
@EventHandler(priority = EventPriority.HIGHEST)
public void PlayerDamage(EntityDamageByEntityEvent e)
{
	if(e.getEntity() instanceof Player){
		if(Damage)
		{
			e.setCancelled(true);
			return;

		}
		if(pvp){
			if(e.getDamager() instanceof Player){
				e.setCancelled(true);
				super.msg.sendMessage((Player)e.getDamager(),"PvP 제한");
				return;
			}
		}
	}
}
@EventHandler(priority = EventPriority.HIGHEST)
public void PlayerDamage(EntityDamageEvent e)
{
	if(e.getEntity() instanceof Player){
		if(Damage)
		{
			e.setCancelled(true);
		}
	}
}
@EventHandler(priority = EventPriority.HIGHEST)
public void PlayerDamage(PlayerInteractEntityEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelInteract))
	if(Interact)
	{
		e.setCancelled(true);
		super.msg.sendMessage(e.getPlayer(),"상호작용 제한");
	}
}
@EventHandler(priority = EventPriority.HIGHEST)
public void PlayerLevelChange(PlayerExpChangeEvent e)
{
	if(Level_Up)
	{
		if(e.getPlayer().getLevel() >= level)
			e.setAmount(0);
	}
}
@EventHandler(priority = EventPriority.HIGHEST)
public void PlayerMove(PlayerMoveEvent e)
{
	if(!PlayerUtil.hasPermission(e.getPlayer(), PermissionType.PassThroughCancelMove))
	if(move)
	{
		e.setCancelled(true);
	}
}
}
