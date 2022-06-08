package me.Loikas.PearlFix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class EventsClass implements Listener
{
	
	@EventHandler
	public void onProjectileLaunchEvent(ProjectileLaunchEvent e) {
		if(!(e.getEntity() instanceof ThrowableProjectile)) return;
		ThrowableProjectile pearl = (ThrowableProjectile) e.getEntity();
		if(pearl.getType() != EntityType.ENDER_PEARL) return;
		Player player = (Player) pearl.getShooter();
		if(player.hasPermission("pearlfix.bypass")) return;
		if(player.getTargetBlockExact(2) != null) {
			ComponentBuilder message  = new ComponentBuilder(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "PearlFix" + ChatColor.DARK_AQUA + "]" + " " + ChatColor.WHITE + player.getName() + ChatColor.RED + " just tried to block glitch using an enderpearl at " + ChatColor.WHITE + "[" + Math.round(player.getLocation().getX()) + ", " + Math.round(player.getLocation().getY()) + ", " + Math.round(player.getLocation().getZ()) + "]");
			BaseComponent[] msg = message.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp " + player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ())).create();
 			for(Player p : Bukkit.getOnlinePlayers()) if(p.hasPermission("pearlfix.message")) p.spigot().sendMessage(msg);
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Block glitching is not allowed!");
			e.setCancelled(true);
		}
	}
	
}
