package me.Loikas.ExpandedEnchants.Inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.Loikas.ExpandedEnchants.CustomEnchantsManager;
import me.Loikas.ExpandedEnchants.EnchantmentInformation;
import me.Loikas.ExpandedEnchants.Main;
import me.Loikas.ExpandedEnchants.Util.CustomRecipe;
import me.Loikas.ExpandedEnchants.Util.Functions;
import me.Loikas.ExpandedEnchants.Util.LanguageManager;

public class CustomRecipeInventory implements InventoryHolder
{
	public Inventory inv;
	
	@SuppressWarnings("deprecation")
	public CustomRecipeInventory (CustomRecipe recipe) {
		if(recipe.recipe == null) {
			inv = Bukkit.createInventory(null, 54, "§5§l" + LanguageManager.instance.GetTranslatedValue("empty-book-inventory-title"));
		}
		else {
			Enchantment ench = (Enchantment) recipe.recipe.getResult().getItemMeta().getEnchants().keySet().toArray()[0];
			inv = Bukkit.createInventory(null, 54, "§5§l" + ench.getName());
		}
		ChangeRecipe(recipe);
	}
	
	@SuppressWarnings("deprecation")
	public void ChangeRecipe(CustomRecipe recipe) {
		ItemStack bench = new ItemStack(Material.CRAFTING_TABLE);
		inv.setItem(27, bench);
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner("MHF_ArrowLeft");
		meta.setDisplayName("§e§l" + LanguageManager.instance.GetTranslatedValue("inventory-back"));
		item.setItemMeta(meta);
		inv.setItem(0, item);
		ItemStack itemResult = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta metaResult = (SkullMeta) itemResult.getItemMeta();
		metaResult.setOwner("MHF_ArrowRight");
		metaResult.setDisplayName("§e§l" + LanguageManager.instance.GetTranslatedValue("inventory-result"));
		itemResult.setItemMeta(metaResult);
		inv.setItem(32, itemResult);
		ItemStack result = null;
		if(recipe.recipe != null) {
			ItemStack desc = new ItemStack(Material.PAPER, 1);
			ItemMeta descmeta = desc.getItemMeta();
			descmeta.setDisplayName("§b§l" + LanguageManager.instance.GetTranslatedValue("inventory-description"));
			List<String> desclore = new ArrayList<>();
			for(String descString : EnchantmentInformation.descriptions[Functions.GetEnchIndex((Enchantment) recipe.recipe.getResult().getEnchantments().keySet().toArray()[0], CustomEnchantsManager.custom_enchants)].stringList) {
				desclore.add("§f" + descString);
			}
			descmeta.setLore(desclore);
			desc.setItemMeta(descmeta);
			inv.setItem(2, desc);
			
			ItemStack canEnch = new ItemStack(Material.IRON_CHESTPLATE);
			ItemMeta canMeta = canEnch.getItemMeta();
			canMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON);
			canMeta.setDisplayName("§b§l" + LanguageManager.instance.GetTranslatedValue("inventory-can-enchant"));
			List<String> canLore = new ArrayList<>();
			for(String canString : EnchantmentInformation.enchantableOn[Functions.GetEnchIndex((Enchantment) recipe.recipe.getResult().getEnchantments().keySet().toArray()[0], CustomEnchantsManager.custom_enchants)].stringList) {
				canLore.add("§f" + canString);
			}
			canMeta.setLore(canLore);
			canEnch.setItemMeta(canMeta);
			inv.setItem(3, canEnch);
			
			ItemStack conflict = new ItemStack(Material.BARRIER);
			ItemMeta conflictMeta = conflict.getItemMeta();
			conflictMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON);
			conflictMeta.setDisplayName("§c§l" + LanguageManager.instance.GetTranslatedValue("inventory-conflicts"));
			List<String> conflictLore = new ArrayList<>();
			for(String conflictString : EnchantmentInformation.conflictsWith[Functions.GetEnchIndex((Enchantment) recipe.recipe.getResult().getEnchantments().keySet().toArray()[0], CustomEnchantsManager.custom_enchants)].stringList) {
				conflictLore.add("§f" + conflictString);
			}
			conflictMeta.setLore(conflictLore);
			conflict.setItemMeta(conflictMeta);
			inv.setItem(4, conflict);
			
			ItemStack maxLevel = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta maxMeta = maxLevel.getItemMeta();
			maxMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON);
			Enchantment ench = (Enchantment)recipe.recipe.getResult().getEnchantments().keySet().toArray()[0];
			maxMeta.setDisplayName("§b§l" + LanguageManager.instance.GetTranslatedValue("inventory-max-level") + " §f" + ench.getMaxLevel());
			maxLevel.setItemMeta(maxMeta);
			inv.setItem(5, maxLevel);
			
			for(int i = 0; i < 9; i++) {
				if(recipe.items[i] == null) {
					ItemStack noItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
					ItemMeta noMeta = noItem.getItemMeta();
					noMeta.setDisplayName("§c§oNo item");
					noItem.setItemMeta(noMeta);
					inv.setItem(Functions.GetSlot(i), noItem);	
					continue;
				}
				ItemStack topItem = new ItemStack(Material.AIR);
				if (i == 1) topItem = recipe.items[i];
				if(topItem.getType() == Material.POTION || topItem.getType() == Material.LINGERING_POTION || topItem.getType() == Material.SPLASH_POTION || topItem.getType() == Material.ENCHANTED_BOOK) {
					inv.setItem(Functions.GetSlot(i), Main.itemManager.GetSpecialCraftItem(recipe.recipe));
				}
				else { 
					ItemStack craftItem = new ItemStack(recipe.items[i]);
					if(i == 4) if(craftItem.getType() == Material.ENCHANTED_BOOK) {
						ItemMeta craftMeta = craftItem.getItemMeta();
						List<String> craftLore = new ArrayList<>(); 
						for(String string : LanguageManager.instance.GetTranslatedList("inventory-lore-empty-book-recipe-part1")) craftLore.add("§f" + string); 
						for(String string : LanguageManager.instance.GetTranslatedList("inventory-lore-empty-book-recipe-part2")) craftLore.add("§b" + string); 
						craftMeta.setLore(craftLore);
						craftItem.setItemMeta(craftMeta);
					}
					inv.setItem(Functions.GetSlot(i), craftItem);
				}
				
			}
			result = new ItemStack(recipe.recipe.getResult());
		}
		else {
			inv.setItem(21, new ItemStack(Material.EMERALD));
			ItemStack noItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
			ItemMeta noMeta = noItem.getItemMeta();
			noMeta.setDisplayName("§c§oNo item");
			noItem.setItemMeta(noMeta);
			inv.setItem(20, noItem);
			inv.setItem(22, noItem);
			inv.setItem(31, noItem);
			inv.setItem(29, noItem);
			inv.setItem(38, noItem);
			inv.setItem(39, noItem);
			inv.setItem(40, noItem);
			inv.setItem(30, new ItemStack(Material.BOOK));
			result = new ItemStack(Material.ENCHANTED_BOOK);
		}
		inv.setItem(33, result);
		
	}
	
	
	
	
	@Override
	public Inventory getInventory()
	{
		return inv;
	}
	
	
}
