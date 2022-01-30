package me.Loikas.ExpandedEnchants.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.Loikas.ExpandedEnchants.CustomEnchantsManager;
import me.Loikas.ExpandedEnchants.EventsClass;
import me.Loikas.ExpandedEnchants.Main;
import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
			if(!(sender instanceof Player))
				return true; 
			Player player = (Player) sender;
			if(args.length == 0) {
				if(!player.hasPermission("ee.command.creative")) {
					sender.sendMessage(ChatColor.RED + "You don't have permission for this command!");
					return true;
				}
				player.setGameMode(GameMode.CREATIVE);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("recipes")) {
				player.openInventory(Main.inventoryManager.startInv);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("give")) {
				if(args.length == 1) return false;
				int level = 1;
				if(args.length > 2) if(!args[2].equals("")) level = Integer.parseInt(args[2]);
				if(args[1].equalsIgnoreCase("assassin")) return GiveCustomBook(CustomEnchantsManager.ASSASSIN, level, player);
				if(args[1].equalsIgnoreCase("unbreakable")) return GiveCustomBook(CustomEnchantsManager.NOBREAKABLE, level, player);
				if(args[1].equalsIgnoreCase("direct")) return GiveCustomBook(CustomEnchantsManager.DIRECT, level, player);
				if(args[1].equalsIgnoreCase("disarming")) return GiveCustomBook(CustomEnchantsManager.DISARMING, level, player);
				if(args[1].equalsIgnoreCase("disruption")) return GiveCustomBook(CustomEnchantsManager.DISRUPTION, level, player);
				if(args[1].equalsIgnoreCase("autosmelt")) return GiveCustomBook(CustomEnchantsManager.AUTOSMELT, level, player);
				if(args[1].equalsIgnoreCase("antigravity")) return GiveCustomBook(CustomEnchantsManager.ANTIGRAVITY, level, player);
				if(args[1].equalsIgnoreCase("beheading")) return GiveCustomBook(CustomEnchantsManager.BEHEADING, level, player);
				if(args[1].equalsIgnoreCase("expboost")) return GiveCustomBook(CustomEnchantsManager.EXP_BOOST, level, player);
				if(args[1].equalsIgnoreCase("elementalprotection")) return GiveCustomBook(CustomEnchantsManager.ELEMENTALPROTECTION, level, player);
				if(args[1].equalsIgnoreCase("traveler")) return GiveCustomBook(CustomEnchantsManager.TRAVELER, level, player);
				if(args[1].equalsIgnoreCase("lavawalker")) return GiveCustomBook(CustomEnchantsManager.LAVA_WALKER, level, player);
				if(args[1].equalsIgnoreCase("healthboost")) return GiveCustomBook(CustomEnchantsManager.HEALTHBOOST, level, player);
				if(args[1].equalsIgnoreCase("stonefists")) return GiveCustomBook(CustomEnchantsManager.STONEFISTS, level, player);
				if(args[1].equalsIgnoreCase("leaping")) return GiveCustomBook(CustomEnchantsManager.LEAPING, level, player);
				if(args[1].equalsIgnoreCase("owleyes")) return GiveCustomBook(CustomEnchantsManager.OWLEYES, level, player);
				if(args[1].equalsIgnoreCase("heavenslightness")) return GiveCustomBook(CustomEnchantsManager.HEAVENSLIGHTNESS, level, player);
				if(args[1].equalsIgnoreCase("thermalplating")) return GiveCustomBook(CustomEnchantsManager.THERMALPLATING, level, player);
				if(args[1].equalsIgnoreCase("lifesteal")) return GiveCustomBook(CustomEnchantsManager.LIFESTEAL, level, player);
				if(args[1].equalsIgnoreCase("icy")) return GiveCustomBook(CustomEnchantsManager.ICY, level, player);
				if(args[1].equalsIgnoreCase("deflect")) return GiveCustomBook(CustomEnchantsManager.DEFLECT, level, player);
				if(args[1].equalsIgnoreCase("lumberjack")) return GiveCustomBook(CustomEnchantsManager.LUMBERJACK, level, player);
				if(args[1].equalsIgnoreCase("veinmine")) return GiveCustomBook(CustomEnchantsManager.VEINMINE, level, player);
				if(args[1].equalsIgnoreCase("wide")) return GiveCustomBook(CustomEnchantsManager.WIDE, level, player);
				if(args[1].equalsIgnoreCase("gourmand")) return GiveCustomBook(CustomEnchantsManager.GOURMAND, level, player);
				if(args[1].equalsIgnoreCase("stepping")) return GiveCustomBook(CustomEnchantsManager.STEPPING, level, player);
				if(args[1].equalsIgnoreCase("feedingmodule")) return GiveCustomBook(CustomEnchantsManager.FEEDINGMODULE, level, player);
				if(args[1].equalsIgnoreCase("splitting")) return GiveCustomBook(CustomEnchantsManager.SPLITTING, level, player);
				if(args[1].equalsIgnoreCase("shadowstep")) return GiveCustomBook(CustomEnchantsManager.SHADOWSTEP, level, player);
				if(args[1].equalsIgnoreCase("thrusters")) return GiveCustomBook(CustomEnchantsManager.THRUSTERS, level, player);
				if(args[1].equalsIgnoreCase("soulbound")) return GiveCustomBook(CustomEnchantsManager.SOULBOUND, level, player);
				if(args[1].equalsIgnoreCase("replanting")) return GiveCustomBook(CustomEnchantsManager.REPLANTING, level, player);
				if(args[1].equalsIgnoreCase("oresight")) return GiveCustomBook(CustomEnchantsManager.ORESIGHT, level, player);
			}
			
			if(args[0].equalsIgnoreCase("reload")) {
				if(!sender.hasPermission("ee.command.reload")) {
					sender.sendMessage(ChatColor.RED + "You don't have permission for this command!");
					return true;
				}
				Main.getPlugin().reloadConfig();
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[EE]" +  ChatColor.WHITE + " Config reloaded!");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("repair")) {
				if(!sender.hasPermission("ee.command.repair")) {
					sender.sendMessage(ChatColor.RED + "You don't have permission for this command!");
					return true;
				}
				return BuyRepair(player, false);
			}
			
			if(args[0].equalsIgnoreCase("buy")) {
				if(Main.econ == null) { sender.sendMessage(ChatColor.RED + "Vault is not enabled on this server. Contact your server administrator for more information."); return true; }
				int amount = 1;
				if(args.length >= 3) {
					try {
						amount = Integer.parseInt(args[2]);
					}
					catch(NumberFormatException e) {
						sender.sendMessage(ChatColor.RED + "Please fill in a valid amount!");
						return true;
					}
				}
				return BuyEnchants(player, args, false, amount);
			}
			
			if(args[0].equalsIgnoreCase("cost")) {
				if(Main.econ == null) { sender.sendMessage(ChatColor.RED + "Vault is not enabled on this server. Contact your server administrator for more information."); return true; }
				if(args[1].equalsIgnoreCase("repair")) { BuyRepair(player, true); return true;}
				int amount = 1;
				if(args.length >= 3) {
					try {
						amount = Integer.parseInt(args[2]);
					}
					catch(NumberFormatException e) {
						sender.sendMessage(ChatColor.RED + "Please fill in a valid amount!");
						return true;
					}
				}
				return BuyEnchants(player, args, true, amount);
			}
			
			if(args[0].equalsIgnoreCase("enchant")) {
				if(args.length == 1) return false;
				int level = 1;
				if(args.length >= 3) level = Integer.parseInt(args[2]);
				if(args[1].equalsIgnoreCase("assassin")) return EnchantPlayerItem(CustomEnchantsManager.ASSASSIN, level, player, true, true);
				if(args[1].equalsIgnoreCase("unbreakable")) return EnchantPlayerItem(CustomEnchantsManager.NOBREAKABLE, level, player, true, true);
				if(args[1].equalsIgnoreCase("direct")) return EnchantPlayerItem(CustomEnchantsManager.DIRECT, level, player, true, true);
				if(args[1].equalsIgnoreCase("disarming")) return EnchantPlayerItem(CustomEnchantsManager.DISARMING, level, player, true, true);
				if(args[1].equalsIgnoreCase("disruption")) return EnchantPlayerItem(CustomEnchantsManager.DISRUPTION, level, player, true, true);
				if(args[1].equalsIgnoreCase("autosmelt")) return EnchantPlayerItem(CustomEnchantsManager.AUTOSMELT, level, player, true, true);
				if(args[1].equalsIgnoreCase("antigravity")) return EnchantPlayerItem(CustomEnchantsManager.ANTIGRAVITY, level, player, true, true);
				if(args[1].equalsIgnoreCase("beheading")) return EnchantPlayerItem(CustomEnchantsManager.BEHEADING, level, player, true, true);
				if(args[1].equalsIgnoreCase("expboost")) return EnchantPlayerItem(CustomEnchantsManager.EXP_BOOST, level, player, true, true);
				if(args[1].equalsIgnoreCase("traveler")) return EnchantPlayerItem(CustomEnchantsManager.TRAVELER, level, player, true, true);
				if(args[1].equalsIgnoreCase("gourmand")) return EnchantPlayerItem(CustomEnchantsManager.GOURMAND, level, player, true, true);
				if(args[1].equalsIgnoreCase("lavawalker")) return EnchantPlayerItem(CustomEnchantsManager.LAVA_WALKER, level, player, true, true);
				if(args[1].equalsIgnoreCase("elementalprotection")) return EnchantPlayerItem(CustomEnchantsManager.ELEMENTALPROTECTION, level, player, true, true);
				if(args[1].equalsIgnoreCase("healthboost")) return EnchantPlayerItem(CustomEnchantsManager.HEALTHBOOST, level, player, true, true);
				if(args[1].equalsIgnoreCase("stonefists")) return EnchantPlayerItem(CustomEnchantsManager.STONEFISTS, level, player, true, true);
				if(args[1].equalsIgnoreCase("leaping")) return EnchantPlayerItem(CustomEnchantsManager.LEAPING, level, player, true, true);
				if(args[1].equalsIgnoreCase("owleyes")) return EnchantPlayerItem(CustomEnchantsManager.OWLEYES, level, player, true, true);
				if(args[1].equalsIgnoreCase("heavenslightness")) return EnchantPlayerItem(CustomEnchantsManager.HEAVENSLIGHTNESS, level, player, true, true);
				if(args[1].equalsIgnoreCase("thermalplating")) return EnchantPlayerItem(CustomEnchantsManager.THERMALPLATING, level, player, true, true);
				if(args[1].equalsIgnoreCase("lifesteal")) return EnchantPlayerItem(CustomEnchantsManager.LIFESTEAL, level, player, true, true);
				if(args[1].equalsIgnoreCase("icy")) return EnchantPlayerItem(CustomEnchantsManager.ICY, level, player, true, true);
				if(args[1].equalsIgnoreCase("deflect")) return EnchantPlayerItem(CustomEnchantsManager.DEFLECT, level, player, true, true);
				if(args[1].equalsIgnoreCase("lumberjack")) return EnchantPlayerItem(CustomEnchantsManager.LUMBERJACK, level, player, true, true);
				if(args[1].equalsIgnoreCase("veinmine")) return EnchantPlayerItem(CustomEnchantsManager.VEINMINE, level, player, true, true);
				if(args[1].equalsIgnoreCase("stepping")) return EnchantPlayerItem(CustomEnchantsManager.STEPPING, level, player, true, true);
				if(args[1].equalsIgnoreCase("wide")) return EnchantPlayerItem(CustomEnchantsManager.WIDE, level, player, true, true);
				if(args[1].equalsIgnoreCase("thrusters")) return EnchantPlayerItem(CustomEnchantsManager.THRUSTERS, level, player, true, true);
				if(args[1].equalsIgnoreCase("feedingmodule")) return EnchantPlayerItem(CustomEnchantsManager.FEEDINGMODULE, level, player, true, true);
				if(args[1].equalsIgnoreCase("soulbound")) return EnchantPlayerItem(CustomEnchantsManager.SOULBOUND, level, player, true, true);
				if(args[1].equalsIgnoreCase("splitting")) return EnchantPlayerItem(CustomEnchantsManager.SPLITTING, level, player, true, true);
				if(args[1].equalsIgnoreCase("shadowstep")) return EnchantPlayerItem(CustomEnchantsManager.SHADOWSTEP, level, player, true, true);
				if(args[1].equalsIgnoreCase("replanting")) return EnchantPlayerItem(CustomEnchantsManager.REPLANTING, level, player, true, true);
				if(args[1].equalsIgnoreCase("oresight")) return EnchantPlayerItem(CustomEnchantsManager.ORESIGHT, level, player, true, true);
			}
			return false;
	}
	
	public boolean BuyRepair(Player sender, boolean onlyCost) {
		if(sender.getInventory().getItemInMainHand() == null) { sender.sendMessage(ChatColor.RED + "You are not holding an item!"); return true; }
		if(sender.getInventory().getItemInMainHand().getItemMeta() == null) { sender.sendMessage(ChatColor.RED + "You are not holding a valid item!"); return true; }
		if(!(sender.getInventory().getItemInMainHand().getItemMeta() instanceof Damageable)) { sender.sendMessage(ChatColor.RED + "This item can't be repaired!"); return true; }
		Damageable dam = (Damageable) sender.getInventory().getItemInMainHand().getItemMeta();
		if(!dam.hasDamage()) { sender.sendMessage(ChatColor.RED + "This item has full durability!"); return true; }
		ItemStack item = sender.getInventory().getItemInMainHand();
		
		FileConfiguration repair = new YamlConfiguration();
		try { repair.load("plugins/ExpandedEnchants/repair.yml"); }
		catch (InvalidConfigurationException e) { e.printStackTrace(); repair = null;}
		catch(IOException e) {repair = null;};
		if(repair == null) { sender.sendMessage(ChatColor.RED + "Repairing is not enabled on this server. Contact your server administrator for more information!"); return true; } 
		Set<Enchantment> enchs = item.getItemMeta().getEnchants().keySet();
		if(!repair.isConfigurationSection("Cost")) { sender.sendMessage(ChatColor.RED + "Repairing is not enabled on this server. Contact your server administrator for more information!"); return true; } 
		Set<String> enchNameList = repair.getConfigurationSection("Cost").getKeys(false);
		List<Enchantment> configEnchants = new ArrayList<>();
		if(enchNameList.size() == 0) { sender.sendMessage(ChatColor.RED + "Repairing is not enabled on this server. Contact your server administrator for more information!"); return true; } 
		for(String string : enchNameList) { configEnchants.add(Enchantment.getByKey(NamespacedKey.fromString(string))); } 
		double price = 0;
		for(Enchantment ench : configEnchants) {
			for(Enchantment itemEnch : enchs) {
				if(itemEnch.equals(ench)) {
					boolean containsKey = false;
					for(String string : repair.getConfigurationSection("Cost").getStringList("" + ench.getKey())) if(string.split(":")[0].equals("" + dam.getEnchantLevel(ench))) containsKey = true;
					if(containsKey) {
						for(String string : repair.getConfigurationSection("Cost").getStringList("" + ench.getKey())) {
							if(string.split(":")[0].equals("" + dam.getEnchantLevel(ench))) price += Double.parseDouble(string.split(":")[1]);
						}
					}
					else {
						int lowest = 0;
						for(String string : repair.getConfigurationSection("Cost").getStringList("" + ench.getKey())) {
							int value = Integer.parseInt(string.split(":")[0]);
							if(value > lowest && value < dam.getEnchantLevel(ench)) lowest = value;
						}
						if(lowest != 0) {
							for(String string : repair.getConfigurationSection("Cost").getStringList("" + ench.getKey())) {
								if(string.split(":")[0].equals("" + lowest)) price += Double.parseDouble(string.split(":")[1]);
							}
							
						}
					}
				}
			}
		}
		if(price == 0) {
			if(!Main.getPlugin().getConfig().getBoolean("AllowNoEnchantRepair")) { sender.sendMessage(ChatColor.RED + "Repairing this item is not possible because it doesn't have any enchantments!"); return true; }
			else price = Main.getPlugin().getConfig().getDouble("DefaultRepairCost");
		}
		if(onlyCost) sender.sendMessage(ChatColor.LIGHT_PURPLE + "Repairing this item would cost: " + ChatColor.WHITE + price + Main.econ.currencyNamePlural());
		else {
			if(Main.econ.has(sender, price)) Main.econ.withdrawPlayer(sender, price);
			else { sender.sendMessage(ChatColor.RED + "You can't afford this! The price is: " + price + Main.econ.currencyNamePlural()); return true; }
			dam.setDamage(0);
			sender.getInventory().getItemInMainHand().setItemMeta(dam);
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean BuyEnchants(Player sender, String[] args, boolean checkCost, int amount) {
		if(args.length < 2) { sender.sendMessage(ChatColor.RED + "You haven't specified which package you want to buy!"); return true; }
		if(!sender.hasPermission("ee.command.buy." + args[1])) { sender.sendMessage(ChatColor.RED + "You don't have permission for this package!"); return true; }
		if(sender.getInventory().getItemInMainHand() == null) { sender.sendMessage(ChatColor.RED + "You are not holding an item!"); return true; }
		if(sender.getInventory().getItemInMainHand().getItemMeta() == null) { sender.sendMessage(ChatColor.RED + "You are not holding a valid item!"); return true; }
		ItemStack item = sender.getInventory().getItemInMainHand();
		List<String> enchantList = Main.getPlugin().getConfig().getStringList("BuyCommandEnchants." + args[1] + ".Enchants");
		List<Enchantment> enchants = new ArrayList<>();
		List<Integer> maxLevels = new ArrayList<>();
		
		if(enchantList.size() == 0) { sender.sendMessage(ChatColor.RED + "This package doesn't exist or doesn't contain any enchantments."); return true; }
		for(String string : enchantList) enchants.add(Enchantment.getByKey(NamespacedKey.fromString(string.split(":")[0] + ":" + string.split(":")[1])));
		for(String string : enchantList) maxLevels.add(Integer.parseInt(string.split(":")[2]));
		HashMap<Enchantment, Integer> possibleEnchants = new HashMap<>();
		for(int i = 0; i < enchants.size(); i++) {
			if(enchants.get(i) == null) continue;
			if(enchants.get(i).canEnchantItem(sender.getInventory().getItemInMainHand())) {
				boolean noConflict = true;
				for(Enchantment enchant : item.getItemMeta().getEnchants().keySet()) if(enchant.conflictsWith(enchants.get(i)) && !enchant.equals(enchants.get(i))) {
					noConflict = false;
				}
				if(noConflict) {
					
					if(item.getItemMeta().hasEnchant(enchants.get(i))) { 
						if(enchants.get(i) == Enchantment.LURE) maxLevels.set(i, 3);
						if(enchants.get(i) == Enchantment.DEPTH_STRIDER) maxLevels.set(i, 3);
						if(enchants.get(i) == Enchantment.QUICK_CHARGE) maxLevels.set(i, 5);
						if(item.getItemMeta().getEnchantLevel(enchants.get(i)) < maxLevels.get(i)) {
							possibleEnchants.put(enchants.get(i), maxLevels.get(i) - item.getItemMeta().getEnchantLevel(enchants.get(i))); 
						}
					}
					else possibleEnchants.put(enchants.get(i), maxLevels.get(i));
				}
			}
		}
		if(possibleEnchants.size() == 0) { sender.sendMessage(ChatColor.RED + "No enchants from this package are valid for your current item."); return true; }
		for(Entry<Enchantment, Integer> set : possibleEnchants.entrySet()) {
			int maxAddition = set.getValue();
			int toAdd = amount < maxAddition ? amount : maxAddition;
			possibleEnchants.put(set.getKey(), toAdd);
		}
		
		double price = 0;
		for(Enchantment ench : possibleEnchants.keySet()) {
			if(item.getItemMeta().hasEnchant(ench)) {
				for(int i = item.getItemMeta().getEnchantLevel(ench) + possibleEnchants.get(ench); i > item.getItemMeta().getEnchantLevel(ench); i--) {
					price += i * Main.getPlugin().getConfig().getDouble("BuyCommandEnchants." + args[1] + ".Price");
				}
			}
			else price += Main.getPlugin().getConfig().getDouble("BuyCommandEnchants." + args[1] + ".Price");
		}
		Main.Log("Price: " + price);
		if(!checkCost) {
			if(Main.econ.has(sender, price)) Main.econ.withdrawPlayer(sender, price);
			else { sender.sendMessage(ChatColor.RED + "You can't afford this! The price is: " + price + Main.econ.currencyNamePlural()); return true; }
			for(Enchantment ench : possibleEnchants.keySet()) {
				if(item.getItemMeta().hasEnchant(ench)) {
					if(EventsClass.functions.IsCustomEnchant(ench)) EnchantPlayerItem(ench, item.getItemMeta().getEnchantLevel(ench) + possibleEnchants.get(ench), sender, false, false);
					else item.addUnsafeEnchantment(ench, item.getItemMeta().getEnchantLevel(ench) + possibleEnchants.get(ench));
				}
				else {
					if(EventsClass.functions.IsCustomEnchant(ench)) EnchantPlayerItem(ench, 1, sender, false, false);
					else item.addUnsafeEnchantment(ench, 1);
				}
			}
			sender.sendMessage(ChatColor.GREEN + "Succesfully bought package: '" + args[1] + "' for: " + price + Main.econ.currencyNamePlural());
		}
		else {
			String string = ChatColor.LIGHT_PURPLE + "Buying this package would add the following enchantments: ";
			int i = 0;
			for(Enchantment ench : possibleEnchants.keySet()) {
				i++;
				if(EventsClass.functions.IsCustomEnchant(ench)) string += ChatColor.WHITE + ench.getName() + (item.getItemMeta().hasEnchant(ench) ? " " +  (item.getItemMeta().getEnchantLevel(ench) + possibleEnchants.get(ench)) : ench.getMaxLevel() > 1 ? " " +  1 : "");
				else string += ChatColor.WHITE + EventsClass.functions.GetEnchantmentName(ench) + (item.getItemMeta().hasEnchant(ench) ? " " +  (item.getItemMeta().getEnchantLevel(ench) + possibleEnchants.get(ench)) : ench.getMaxLevel() > 1 ? " " +  1 : "");
				
				if(i != possibleEnchants.size()) string += ", ";
				else string += ".";
			}
			sender.sendMessage(string);
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "For a price of: " + ChatColor.WHITE + price + Main.econ.currencyNamePlural());
		}
		return true;
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean EnchantPlayerItem(Enchantment ench, int level, Player sender, boolean sendMessage, boolean needsPermissions) {
		if(needsPermissions) {
			if(!sender.hasPermission("ee.command.enchant")) {
				sender.sendMessage(ChatColor.RED + "You don't have permission for this command!");
				return true;
			}
			if(sender.getInventory().getItemInMainHand() == null) {
				sender.sendMessage(ChatColor.RED + "You are not holding an item!");
				return true;
			}
			if(sender.getInventory().getItemInMainHand().getItemMeta() == null) {
				sender.sendMessage(ChatColor.RED + "You are not holding a valid item!");
				return true;
			}
		}
		ItemStack fs = sender.getInventory().getItemInMainHand();
		if(fs.getItemMeta().hasEnchant(ench)) {
			if(fs.getItemMeta().getEnchantLevel(ench) > level) {
				sender.sendMessage(ChatColor.RED + "Item contains a higher level enchantment!");
				return true;
			}
			if(ench.getMaxLevel() == 1) {
				sender.sendMessage(ChatColor.RED + "Item already contains enchantment!");
				return true;
			}
			ItemStack resultItem = new ItemStack(fs);
			int addLevel = fs.getItemMeta().getEnchantLevel(ench) == level ? level + 1 : level;
			if(addLevel > ench.getMaxLevel()) {
				if(sendMessage) sender.sendMessage(ChatColor.RED + "Enchantment is already max level!");
				return true;
			}
			resultItem.addUnsafeEnchantment(ench, addLevel);
			ItemMeta meta = resultItem.getItemMeta();
			List<String> lore = new ArrayList<String>();
			if(meta.hasLore()) lore = meta.getLore();
			int i = 0;
			int num = -1;
			for(String line : lore) {
				if(line.contains(ench.getName())) {
					num = i;
				}
				i++;
			}
			lore.remove(num);
			lore.add(ChatColor.GRAY + ench.getName() + " " + EventsClass.functions.GetNameByLevel(resultItem.getItemMeta().getEnchantLevel(ench), ench.getMaxLevel()));
			meta.setLore(lore);
			resultItem.setItemMeta(meta);
			if(resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.TRAVELER)) {
				ItemMeta itemMeta = resultItem.getItemMeta();
				itemMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
				Main.Log("ArmorPoints: " + EventsClass.functions.GetArmorPoints(resultItem.getType()));
				if(EventsClass.functions.GetArmorPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", EventsClass.functions.GetArmorPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				if(EventsClass.functions.GetToughnessPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", EventsClass.functions.GetToughnessPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				if(EventsClass.functions.GetKnockbackPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", EventsClass.functions.GetKnockbackPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				
				itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", itemMeta.getEnchantLevel(CustomEnchantsManager.TRAVELER) * .4, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.LEGS));
				itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				resultItem.setItemMeta(itemMeta);
			}
			if(resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.STONEFISTS)) {
				ItemMeta itemMeta = resultItem.getItemMeta();
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
				if(EventsClass.functions.GetArmorPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", EventsClass.functions.GetArmorPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				if(EventsClass.functions.GetToughnessPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", EventsClass.functions.GetToughnessPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				if(EventsClass.functions.GetKnockbackPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", EventsClass.functions.GetKnockbackPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				
				itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", itemMeta.getEnchantLevel(CustomEnchantsManager.STONEFISTS) * 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				resultItem.setItemMeta(itemMeta);
			}
			if(resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.HEALTHBOOST)) {
				ItemMeta itemMeta = resultItem.getItemMeta();
				itemMeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
				itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
				if(EventsClass.functions.GetArmorPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", EventsClass.functions.GetArmorPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				if(EventsClass.functions.GetToughnessPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", EventsClass.functions.GetToughnessPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				if(EventsClass.functions.GetKnockbackPoints(resultItem.getType()) > 0)
					itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", EventsClass.functions.GetKnockbackPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				
				itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", itemMeta.getEnchantLevel(CustomEnchantsManager.HEALTHBOOST) * 2, AttributeModifier.Operation.ADD_NUMBER));
				itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				resultItem.setItemMeta(itemMeta);
			}
			sender.getInventory().setItemInMainHand(resultItem);
			if(sendMessage) sender.sendMessage("Enchanted item with [" + ChatColor.LIGHT_PURPLE + ench.getName() + (ench.getMaxLevel() > 1 ? " " : "") + EventsClass.functions.GetNameByLevel(level, ench.getMaxLevel()) + ChatColor.WHITE +  "]");
			return true;
			
		}
		if(!ench.canEnchantItem(fs)) {
			sender.sendMessage(ChatColor.RED + "Can't apply enchantment to this item!");
			return true;
		}
		if(fs.getItemMeta().hasConflictingEnchant(ench)) {
			sender.sendMessage(ChatColor.RED + "Item contains conflicting enchantment!");
			return true;
		}

		ItemStack resultItem = new ItemStack(fs);
		resultItem.addUnsafeEnchantment(ench, level);
		ItemMeta meta = resultItem.getItemMeta();
		List<String> lore = new ArrayList<String>();
		if(meta.hasLore()) lore = meta.getLore();
		lore.add(ChatColor.GRAY + ench.getName() + " " + EventsClass.functions.GetNameByLevel(resultItem.getItemMeta().getEnchantLevel(ench), ench.getMaxLevel()));
		meta.setLore(lore);
		resultItem.setItemMeta(meta);
		if(resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.NOBREAKABLE)) {
			resultItem.setDurability((short) 100000);
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.setUnbreakable(true);
			resultItem.setItemMeta(itemMeta);
			resultItem.removeEnchantment(Enchantment.DURABILITY);
			resultItem.removeEnchantment(Enchantment.MENDING);
		}
		if(resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.TRAVELER)) {
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
			Main.Log("ArmorPoints: " + EventsClass.functions.GetArmorPoints(resultItem.getType()));
			if(EventsClass.functions.GetArmorPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", EventsClass.functions.GetArmorPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			if(EventsClass.functions.GetToughnessPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", EventsClass.functions.GetToughnessPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			if(EventsClass.functions.GetKnockbackPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", EventsClass.functions.GetKnockbackPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			
			itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", itemMeta.getEnchantLevel(CustomEnchantsManager.TRAVELER) * .4, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.LEGS));
			itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			resultItem.setItemMeta(itemMeta);
		}
		if(resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.STONEFISTS)) {
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
			if(EventsClass.functions.GetArmorPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", EventsClass.functions.GetArmorPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(EventsClass.functions.GetToughnessPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", EventsClass.functions.GetToughnessPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(EventsClass.functions.GetKnockbackPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", EventsClass.functions.GetKnockbackPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", itemMeta.getEnchantLevel(CustomEnchantsManager.STONEFISTS) * 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			resultItem.setItemMeta(itemMeta);
		}
		if(resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.HEALTHBOOST)) {
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
			itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
			if(EventsClass.functions.GetArmorPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", EventsClass.functions.GetArmorPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(EventsClass.functions.GetToughnessPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", EventsClass.functions.GetToughnessPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(EventsClass.functions.GetKnockbackPoints(resultItem.getType()) > 0)
				itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", EventsClass.functions.GetKnockbackPoints(resultItem.getType()), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			
			itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", itemMeta.getEnchantLevel(CustomEnchantsManager.HEALTHBOOST) * 2, AttributeModifier.Operation.ADD_NUMBER));
			itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			resultItem.setItemMeta(itemMeta);
		}
		
		sender.getInventory().setItemInMainHand(resultItem);
		if(sendMessage) sender.sendMessage("Enchanted item with [" + ChatColor.LIGHT_PURPLE + ench.getName() + (ench.getMaxLevel() > 1 ? " " : "") + EventsClass.functions.GetNameByLevel(level, ench.getMaxLevel()) + ChatColor.WHITE +  "]");
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean GiveCustomBook(Enchantment ench, int level, Player sender) {
		if(!sender.hasPermission("ee.command.give")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission for this command!");
			return true;
		}
		ItemStack item = Main.itemManager.CreateCustomBook(ench, level);
		if(level > ench.getMaxLevel()) return false;
		if(level < 1) return false;
		sender.getInventory().addItem(item);
		
		sender.sendMessage(ChatColor.WHITE + "Gave Enchanted Book with Enchantment: [" + ChatColor.LIGHT_PURPLE + ench.getName() + (ench.getMaxLevel() > 1 ? " " : "") + EventsClass.functions.GetNameByLevel(level, ench.getMaxLevel()) + ChatColor.WHITE +  "]");
		return true;
	}
}
