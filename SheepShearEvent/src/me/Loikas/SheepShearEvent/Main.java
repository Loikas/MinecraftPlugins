package me.Loikas.SheepShearEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private static Main plugin;
	
	private EventsClass eventsClass;
	
	@Override
	public void onEnable() {
		
		plugin = this;
		
		this.saveDefaultConfig();
		
		eventsClass = new EventsClass();
		
		this.getServer().getPluginManager().registerEvents(eventsClass, this);
		this.getCommand("sheepshearevent").setExecutor(new CommandHandler());
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
