package com.gmail.zant95.HorseProtections.Listeners;

import org.bukkit.Effect;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gmail.zant95.HorseProtections.HorseProtections;

public class EntityDamageByEntity implements Listener {
	HorseProtections plugin;

	public EntityDamageByEntity(HorseProtections instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity entity = event.getEntity();
		Entity damager = event.getDamager();
		
		if (entity instanceof Horse) {
			Horse horse = (Horse)entity;
			AnimalTamer owner = horse.getOwner();
			
			Projectile projectile = null;
			Player player = null;
			if (damager instanceof Player) {
				player = (Player)damager;
			} else if (damager instanceof Projectile) {
				projectile = (Projectile)damager;
				LivingEntity shooter = projectile.getShooter();
				if (shooter instanceof Player) {
					player = (Player)shooter;
				}
			}
			
			if (
				player != null &&
				!(
					owner == null ||
					(owner != null && horse.getOwner().equals(player)) ||
					player.hasPermission("horseprotections.damage")
				)
			) {
				if (projectile != null) projectile.remove();
				horse.getWorld().playEffect(horse.getLocation(), Effect.EXTINGUISH, 0);
				//player.sendMessage(Messages.CANNOT_DAMAGE);
				event.setCancelled(true);
			}
		}
	}
}
