package me.Loikas.ExpandedEnchants;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
				ItemStack item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.NIGHT_VISION, true, false));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_elemental":
			{
				ItemStack item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.POISON, false, true));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_healthboost":
			{
				ItemStack item2 = new ItemStack(Material.POTION);
				PotionMeta meta2 = (PotionMeta) item2.getItemMeta();
				meta2.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
				item2.setItemMeta(meta2);
				return item2;
			}
			case "minecraft:ee_recipe_heavenslightness":
			{
				ItemStack item3 = new ItemStack(Material.POTION);
				PotionMeta meta3 = (PotionMeta) item3.getItemMeta();
				meta3.setBasePotionData(new PotionData(PotionType.SLOW_FALLING, true, false));
				item3.setItemMeta(meta3);
				return item3;
			}
			case "minecraft:ee_recipe_icy":
			{
				ItemStack item4 = new ItemStack(Material.SPLASH_POTION);
				PotionMeta meta4 = (PotionMeta) item4.getItemMeta();
				meta4.setBasePotionData(new PotionData(PotionType.SLOWNESS, false, true));
				item4.setItemMeta(meta4);
				return item4;
			}
			case "minecraft:ee_recipe_leaping":
			{
				ItemStack item5 = new ItemStack(Material.POTION);
				PotionMeta meta5 = (PotionMeta) item5.getItemMeta();
				meta5.setBasePotionData(new PotionData(PotionType.JUMP, false, true));
				item5.setItemMeta(meta5);
				return item5;
			}
			case "minecraft:ee_recipe_lifesteal":
			{
				ItemStack item6 = new ItemStack(Material.LINGERING_POTION);
				PotionMeta meta6 = (PotionMeta) item6.getItemMeta();
				meta6.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
				item6.setItemMeta(meta6);
				return item6;
			}
			case "minecraft:ee_recipe_stonefists":
			{
				ItemStack item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_thermalplating":
			{
				ItemStack item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE, true, false));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_traveler":
			{
				ItemStack item = new ItemStack(Material.POTION);
				PotionMeta meta = (PotionMeta) item.getItemMeta();
				meta.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
				item.setItemMeta(meta);			
				return item;
			}
			case "minecraft:ee_recipe_unbreakable":
			{
				ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
				EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
				meta.addStoredEnchant(Enchantment.DURABILITY, 10, true);
				item.setItemMeta(meta);		
				return item;
			}
		}
		return null;
	}
}
