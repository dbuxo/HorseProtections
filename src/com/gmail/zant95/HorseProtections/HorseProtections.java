package com.gmail.zant95.HorseProtections;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.zant95.HorseProtections.Listeners.EntityDamageByEntity;
import com.gmail.zant95.HorseProtections.Listeners.PlayerInteractEntity;

public class HorseProtections extends JavaPlugin {
	@Override
	public void onEnable() {
		//Implement commands
		this.getCommand("horseprotect").setExecutor(new CommandHandler(this));
		
		//Implement listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerInteractEntity(this), this);
		pm.registerEvents(new EntityDamageByEntity(this), this);
		
		//Enable message
		this.getLogger().info("HorseProtections enabled!");
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Goodbye HorseProtections!");
	}
}
