package me.Loikas.ExpandedEnchants.Inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.Loikas.ExpandedEnchants.CustomEnchantmentRecipe;
import me.Loikas.ExpandedEnchants.CustomEnchantsManager;
import me.Loikas.ExpandedEnchants.Main;
import me.Loikas.ExpandedEnchants.StringList;

public class CustomRecipeInventory implements InventoryHolder, Listener
{
	public Inventory inv;
	
	public CustomRecipeInventory (CustomEnchantmentRecipe recipe) {
		Enchantment ench = (Enchantment) recipe.recipe.getResult().getItemMeta().getEnchants().keySet().toArray()[0];
		inv = Bukkit.createInventory(null, 45, "§5§l" + ench.getName());
		ChangeRecipe(recipe);
	}
	
	public void ChangeRecipe(CustomEnchantmentRecipe recipe) {
		ItemStack bench = new ItemStack(Material.CRAFTING_TABLE);
		inv.setItem(18, bench);
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner("MHF_ArrowLeft");
		meta.setDisplayName("§e§lBack");
		item.setItemMeta(meta);
		inv.setItem(0, item);
		ItemStack desc = new ItemStack(Material.PAPER, 1);
		ItemMeta descmeta = desc.getItemMeta();
		descmeta.setDisplayName("§b§lDescription");
		List<String> desclore = new ArrayList<>();
		for(String descString : CustomEnchantsManager.descriptions[CustomEnchantsManager.GetIndex((Enchantment) recipe.recipe.getResult().getEnchantments().keySet().toArray()[0])].stringList) {
			desclore.add("§f" + descString);
		}
		descmeta.setLore(desclore);
		desc.setItemMeta(descmeta);
		inv.setItem(2, desc);
		ItemStack canEnch = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta canMeta = canEnch.getItemMeta();
		canMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON);
		canMeta.setDisplayName("§b§lCan enchant");
		List<String> canLore = new ArrayList<>();
		for(String canString : CustomEnchantsManager.enchantableOn[CustomEnchantsManager.GetIndex((Enchantment) recipe.recipe.getResult().getEnchantments().keySet().toArray()[0])].stringList) {
			canLore.add("§f" + canString);
		}
		canMeta.setLore(canLore);
		canEnch.setItemMeta(canMeta);
		inv.setItem(3, canEnch);
		for(int i = 0; i < 9; i++) {
			if(recipe.items[i] == null) {
				inv.setItem(Main.eventsClass.functions.GetSlot(i), null);
				continue;
			}
			ItemStack topItem = recipe.items[i];
			if(topItem.getType() == Material.POTION || topItem.getType() == Material.LINGERING_POTION || topItem.getType() == Material.SPLASH_POTION || topItem.getType() == Material.ENCHANTED_BOOK) {
				inv.setItem(Main.eventsClass.functions.GetSlot(i), Main.itemManager.GetSpecialCraftItem(recipe.recipe));
			}
			else { 
				ItemStack craftItem = new ItemStack(recipe.items[i].getType(), recipe.amounts[i]);
				inv.setItem(Main.eventsClass.functions.GetSlot(i), craftItem);
			}
		}
		ItemStack result = new ItemStack(recipe.recipe.getResult());
		inv.setItem(24, result);
		
	}
	
	
	
	
	@Override
	public Inventory getInventory()
	{
		return inv;
	}
	
	
}
