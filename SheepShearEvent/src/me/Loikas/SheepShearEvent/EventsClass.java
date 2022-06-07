package me.Loikas.SheepShearEvent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockShearEntityEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class EventsClass implements Listener
{
	@EventHandler
	public void onPlayerShearEntityEvent(PlayerShearEntityEvent e) {
		if(!e.getPlayer().hasPermission("sse.allow")) return;
		List<String> list = Main.getPlugin().getConfig().getStringList("PlayerShearCommands");
		if(list.size() == 0) return;
		for(String s : list) {
			if(s.contains("(p)")) {
				s = s.replace("(p)", " ");
				try {
					s = s.replace("{p}", e.getPlayer().getName());
					s = s.strip();
					Bukkit.dispatchCommand(e.getPlayer(), s);
				}
				catch (CommandException ex)
				{
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SSE] ERROR: " + ex.getMessage());
				}
			}
			else if(s.contains("(c)")) {
				s = s.replace("(c)", " ");
				
				try {
					s = s.replace("{p}", e.getPlayer().getName());
					s = s.strip();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
				}
				catch (CommandException ex)
				{
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SSE] ERROR: " + ex.getMessage());
				}
			}
			else {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SSE] ERROR: Command not formatted properly! Missing (p) or (c) in: " + s);
			}
		}
	}
	
	@EventHandler
	public void onBlockShearEntityEvent(BlockShearEntityEvent e) {
		List<String> list = Main.getPlugin().getConfig().getStringList("BlockShearCommands");
		if(list.size() == 0) return;
		for(String s : list) {				
			try {
				s = s.strip();
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
			}
			catch (CommandException ex)
			{
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SSE] ERROR: " + ex.getMessage());
			}

		}
	}
	
}
