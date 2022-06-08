package me.Loikas.PearlFix;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private static Main plugin;
	
	private EventsClass eventsClass;
	
	@Override
	public void onEnable() {
		
		plugin = this;

		eventsClass = new EventsClass();
		
		this.getServer().getPluginManager().registerEvents(eventsClass, this);
	}	
	
	public static Main getPlugin() {
		return plugin;
	}
}
