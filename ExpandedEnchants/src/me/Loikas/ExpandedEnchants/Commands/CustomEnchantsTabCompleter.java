package me.Loikas.ExpandedEnchants.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;

import me.Loikas.ExpandedEnchants.CustomEnchantsManager;

public class CustomEnchantsTabCompleter implements TabCompleter
{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args) {
		
		ArrayList<String> returns = new ArrayList<String>();
		if(sender.hasPermission("ee.command.give") && (args[0].equalsIgnoreCase("") || args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("gi") || args[0].equalsIgnoreCase("giv") || args[0].equalsIgnoreCase("i") || args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("e"))) {
			returns.add("give");
		}
		if(sender.hasPermission("ee.command.reload") && (args[0].equalsIgnoreCase("") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("re") || args[0].equalsIgnoreCase("rel") || args[0].equalsIgnoreCase("relo") || args[0].equalsIgnoreCase("reloa"))) {
			returns.add("reload");
		}
		if(sender.hasPermission("ee.command.enchant") && (args[0].equalsIgnoreCase("") || args[0].equalsIgnoreCase("e") || args[0].equalsIgnoreCase("en") || args[0].equalsIgnoreCase("enc") || args[0].equalsIgnoreCase("ench") || args[0].equalsIgnoreCase("encha") || args[0].equalsIgnoreCase("enchan"))) {
			returns.add("enchant");
		}
		if(args[0].equalsIgnoreCase("") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("re") || args[0].equalsIgnoreCase("rec") || args[0].equalsIgnoreCase("reci") || args[0].equalsIgnoreCase("recip") || args[0].equalsIgnoreCase("recipe")) {
			returns.add("recipes");
		}
		if(returns.size() > 0) return returns;
		if(!sender.hasPermission("ee.give") && !sender.hasPermission("ee.command.enchant")) return null;
		if((args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("enchant")) && args.length <= 2) {
			returns.add("unbreakable");
			returns.add("direct");
			returns.add("autosmelt");
			returns.add("antigravity");
			returns.add("beheading");
			returns.add("expboost");
			returns.add("lavawalker");
			returns.add("traveler");
			returns.add("leaping");
			returns.add("stonefists");
			returns.add("healthboost");
			returns.add("owleyes");
			returns.add("heavenslightness");
			returns.add("thermalplating");
			returns.add("lifesteal");
			returns.add("icy");
			returns.add("deflect");
			returns.add("veinmine");
			returns.add("lumberjack");
			returns.add("wide");
			returns.add("feedingmodule");
			returns.add("shadowstep");
			returns.add("replanting");
			return returns;
		}
		
		if((args[0].equalsIgnoreCase("give") && !args[1].equals("")) || (args[0].equalsIgnoreCase("enchant") && !args[1].equals(""))) {
			int max = 1;
			if(args[1].equalsIgnoreCase("beheading")) max = 6;
			if(args[1].equalsIgnoreCase("expboost")) max = 6;
			if(args[1].equalsIgnoreCase("traveler")) max = 4;
			if(args[1].equalsIgnoreCase("healthboost")) max = 6;
			if(args[1].equalsIgnoreCase("stonefists")) max = 4;
			if(args[1].equalsIgnoreCase("leaping")) max = 4;
			if(args[1].equalsIgnoreCase("lifesteal")) max = 4;
			if(args[1].equalsIgnoreCase("icy")) max = 6;
			if(args[1].equalsIgnoreCase("deflect")) max = 5;
			if(args[1].equalsIgnoreCase("lumberjack")) max = 6;
			if(args[1].equalsIgnoreCase("veinmine")) max = 6;
			if(args[1].equalsIgnoreCase("shadowstep")) max = 6;
			
			for(int i = 1; i < max; i++) returns.add("" + i);
			return returns;
		}
		
		return null;
		
	}
}
