package me.Loikas.SheepShearEvent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player) if(!sender.hasPermission("sse.reload")) return true;
		if(args[0].equals("reload")) {
			Main.getPlugin().reloadConfig();
			sender.sendMessage(ChatColor.WHITE + "[SSE]" +  ChatColor.WHITE + " Config reloaded!");
		}
		return true;
	}
}
