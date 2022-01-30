package me.Loikas.ExpandedEnchants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;



public class ItemManager
{
	@SuppressWarnings("deprecation")
	public ItemStack CreateCustomBook(Enchantment ench, int level) {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		item.addUnsafeEnchantment(ench, level);
		
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + ench.getName() + " " + EventsClass.functions.GetNameByLevel(item.getEnchantmentLevel(ench), ench.getMaxLevel()));
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack GetSpecialCraftItem(ShapedRecipe recipe) {
		
		switch(recipe.getKey().toString()) {
			case "minecraft:ee_recipe_owleyes":
			{
				
				ItemStack item = GetConfigItems("ee_recipe_owleyes");
				if(item != null) return item;
				
				item = new ItemStack(Material.POTION);
				
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.NIGHT_VISION, true, false));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_assassin":
			{
				ItemStack item = GetConfigItems("ee_recipe_assassin");
				if(item != null) return item;
				
				item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.INVISIBILITY, true, false));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_elemental":
			{
				ItemStack item = GetConfigItems("ee_recipe_elemental");
				if(item != null) return item;
				
				item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.POISON, false, true));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_healthboost":
			{
				ItemStack item2 = GetConfigItems("ee_recipe_healthboost");
				if(item2 != null) return item2;
				
				item2 = new ItemStack(Material.POTION);
				PotionMeta meta2 = (PotionMeta) item2.getItemMeta();
				meta2.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
				item2.setItemMeta(meta2);
				return item2;
			}
			case "minecraft:ee_recipe_heavenslightness":
			{
				ItemStack item3 = GetConfigItems("ee_recipe_heavenslightness");
				if(item3 != null) return item3;
				
				item3 = new ItemStack(Material.POTION);
				PotionMeta meta3 = (PotionMeta) item3.getItemMeta();
				meta3.setBasePotionData(new PotionData(PotionType.SLOW_FALLING, true, false));
				item3.setItemMeta(meta3);
				return item3;
			}
			case "minecraft:ee_recipe_icy":
			{
				ItemStack item4 = GetConfigItems("ee_recipe_icy");
				if(item4 != null) return item4;
				
				item4 = new ItemStack(Material.SPLASH_POTION);
				PotionMeta meta4 = (PotionMeta) item4.getItemMeta();
				meta4.setBasePotionData(new PotionData(PotionType.SLOWNESS, false, true));
				item4.setItemMeta(meta4);
				return item4;
			}
			case "minecraft:ee_recipe_leaping":
			{
				ItemStack item5 = GetConfigItems("ee_recipe_leaping");
				if(item5 != null) return item5;
				
				item5 = new ItemStack(Material.POTION);
				PotionMeta meta5 = (PotionMeta) item5.getItemMeta();
				meta5.setBasePotionData(new PotionData(PotionType.JUMP, false, true));
				item5.setItemMeta(meta5);
				return item5;
			}
			case "minecraft:ee_recipe_lifesteal":
			{
				ItemStack item6 = GetConfigItems("ee_recipe_lifesteal");
				if(item6 != null) return item6;
				
				item6 = new ItemStack(Material.LINGERING_POTION);
				PotionMeta meta6 = (PotionMeta) item6.getItemMeta();
				meta6.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
				item6.setItemMeta(meta6);
				return item6;
			}
			case "minecraft:ee_recipe_stonefists":
			{
				ItemStack item = GetConfigItems("ee_recipe_stonefists");
				if(item != null) return item;
				
				item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_thermalplating":
			{
				ItemStack item = GetConfigItems("ee_recipe_thermalplating");
				if(item != null) return item;
				
				item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE, true, false));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_traveler":
			{
				ItemStack item = GetConfigItems("ee_recipe_traveler");
				if(item != null) return item;
				
				item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_unbreakable":
			{
				ItemStack item = GetConfigItems("ee_recipe_unbreakable");
				if(item != null) return item;
				
				item = new ItemStack(Material.ENCHANTED_BOOK);
				EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
				meta.addStoredEnchant(Enchantment.DURABILITY, 10, true);
				item.setItemMeta(meta);		
				return item;
			}
			default:
				String str = recipe.getKey().toString().replace("minecraft:", "");
				ItemStack item = GetConfigItems(str);
				return item;
			
		}

	}
	
	public ItemStack GetConfigItems(String string) {
		FileConfiguration recipeData = new YamlConfiguration();
		try { recipeData.load("plugins/ExpandedEnchants/recipes.yml"); }
		catch (InvalidConfigurationException e) { e.printStackTrace(); recipeData = null;}
		catch(IOException e) {recipeData = null;};
		if(recipeData != null) { 
			if(recipeData.getConfigurationSection(string) != null) {
				if(recipeData.isConfigurationSection(string)) {
					if(recipeData.getConfigurationSection(string + ".Advanced").contains("Enchantment")) {
						NamespacedKey enchName = NamespacedKey.fromString(recipeData.getConfigurationSection(string + ".Advanced").getString("Enchantment"));
						Enchantment ench = Enchantment.getByKey(enchName);
						ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
						if(EventsClass.functions.IsCustomEnchant(ench)) {
							item = CreateCustomBook(ench, recipeData.getConfigurationSection(string + ".Advanced").getInt("Level"));
						}
						else {
							EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
							meta.addStoredEnchant(ench, recipeData.getConfigurationSection(string + ".Advanced").getInt("Level"), true);	
							item.setItemMeta(meta);
						}
						return item;
					}
					else if(recipeData.getConfigurationSection(string + ".Advanced").contains("Potion")) {
						ItemStack item = null;
						switch(recipeData.getConfigurationSection(string + ".Advanced").getString("Type")) {
						case "NORMAL":
							item = new ItemStack(Material.POTION);
							break;
						case "SPLASH":
							item = new ItemStack(Material.SPLASH_POTION);
							break;
						case "LINGERING":
							item = new ItemStack(Material.LINGERING_POTION);
							break;
						}
						
						String potionName = recipeData.getConfigurationSection(string + ".Advanced").getString("Potion");
						PotionType potion = PotionType.valueOf(potionName);
						PotionMeta meta = (PotionMeta) item.getItemMeta();
						meta.setBasePotionData(new PotionData(potion, recipeData.getConfigurationSection(string + ".Advanced").getBoolean("Extended"), recipeData.getConfigurationSection(string + ".Advanced").getBoolean("Upgraded")));
						item.setItemMeta(meta);
						return item;
					}
				}
			}
		}
		return null;
	}
	
}
