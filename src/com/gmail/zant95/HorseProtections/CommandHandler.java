package com.gmail.zant95.HorseProtections;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import com.gmail.zant95.HorseProtections.Locale.Messages;

public final class CommandHandler implements CommandExecutor {
	HorseProtections plugin;
	public CommandHandler(HorseProtections instance) {
		plugin = instance;
	}
		
	public final boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		
		/* 
		 * ###############################
		 * # Syntax: /horseprotect       #
		 * #                    <on|off> #
		 * ###############################
		 */
		
		if (command.getName().equalsIgnoreCase("horseprotect")) {
			if (!sender.hasPermission("horseprotections.protect")) {
				sender.sendMessage(Messages.NO_PERMISSION);
			} else if (!(sender instanceof Player)) {
				sender.sendMessage(Messages.ONLY_A_PLAYER);
			} else if (args.length == 1 && (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off"))) {
				Player player = (Player)sender;
				if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
					Horse horse = (Horse)player.getVehicle();
					if (horse.isTamed() || horse.getInventory().getSaddle() != null) {
						if (args[0].equalsIgnoreCase("on")) {
							horse.setOwner(player);
							horse.setTamed(true);
							player.sendMessage(Messages.PROTECTED_HORSE(horse.getEntityId()));
						} else if (args[0].equalsIgnoreCase("off")) {
							horse.setOwner(null);
							horse.setTamed(true);
							player.sendMessage(Messages.UNPROTECTED_HORSE(horse.getEntityId()));
						}
					} else {
						player.sendMessage(Messages.NOT_TAMED);
					}
				} else {
					player.sendMessage(Messages.NOT_IN_A_HORSE);
				}
			} else {
				sender.sendMessage(Messages.BAD_SYNTAX);
			}
		}
		
		return true;
	}
}