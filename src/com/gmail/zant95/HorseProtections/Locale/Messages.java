package com.gmail.zant95.HorseProtections.Locale;

import org.bukkit.ChatColor;

public final class Messages {
	private final static String SUCESSPREFIX = "&6 - &2", FAILPREFIX = "&6 - &c";
	
	public final static String
		NO_PERMISSION = FORMAT(FAILPREFIX + "You don't have permissions to use this command."),
		ONLY_A_PLAYER = FORMAT(FAILPREFIX + "This command must be used by a player."),
		BAD_SYNTAX = FORMAT(FAILPREFIX + "Error, bad syntax."),
		NOT_IN_A_HORSE = FORMAT(FAILPREFIX + "You must be mounted on a horse."),
		NOT_TAMED = FORMAT(FAILPREFIX + "This horse is not tamed."),
		CANNOT_DAMAGE = FORMAT(FAILPREFIX + "You can't damage this horse.");
	
	public final static String HORSE_OWNER_PLAYER(int id, String owner) {
		return FORMAT(SUCESSPREFIX + "This horse (&6id: &f" + id + "&2) is owned by &6" + owner + "&2.");
	}
	
	public final static String NOT_YOUR_HORSE(String owner) {
		return FORMAT(FAILPREFIX + "This horse is already tamed by &6" + owner + "&c.");
	}
	
	public final static String HORSE_OWNER_NULL(int id) {
		return FORMAT(SUCESSPREFIX + "This horse (&6id: &f" + id + "&2) has no owner.");
	}
	
	public final static String PROTECTED_HORSE(int id) {
		return FORMAT(SUCESSPREFIX + "Horse (&6id: &f" + id + "&2) successfully &6protected&2.");
	}
	
	public final static String UNPROTECTED_HORSE(int id) {
		return FORMAT(SUCESSPREFIX + "Horse (&6id: &f" + id + "&2) successfully &6unprotected&2.");
	}
	
	private final static String FORMAT(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
}
