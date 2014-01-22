package com.gmail.zant95.HorseProtections.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.gmail.zant95.HorseProtections.HorseProtections;
import com.gmail.zant95.HorseProtections.Locale.Messages;

public class PlayerInteractEntity implements Listener {
	HorseProtections plugin;

	public PlayerInteractEntity(HorseProtections instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Entity entity = event.getRightClicked();
		
		if (entity instanceof Horse) {
			Horse horse = (Horse)entity;
			
			Player player = event.getPlayer();
			AnimalTamer owner = horse.getOwner();
			
			boolean canBeMounted = (
				owner == null ||
				owner.getName().equals(player.getName()) ||
				player.hasPermission("horseprotections.bypass")
			) ? true : false;
			
			/* Check horse owner */
			if (
				player.hasPermission("horseprotections.checkowner") &&
				player.getItemInHand().getType().equals(Material.STICK)
			) {
				if (owner == null) {
					player.sendMessage(Messages.HORSE_OWNER_NULL(horse.getEntityId()));
				} else {
					player.sendMessage(Messages.HORSE_OWNER_PLAYER(horse.getEntityId(), owner.getName()));
				}
				
				Location location = player.getLocation();
				event.setCancelled(true);
				player.teleport(location);
			}
			/* ----------------- */
			
			/* Cancel mount event */
			else if (!canBeMounted) {
				player.sendMessage(Messages.NOT_YOUR_HORSE(owner.getName()));
				
				Location location = player.getLocation();
				event.setCancelled(true);
				player.teleport(location);
			}
			/* ------------ */
		}
	}
}
