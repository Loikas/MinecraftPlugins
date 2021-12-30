package me.Loikas.ExpandedEnchants.Inventories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.Loikas.ExpandedEnchants.VanillaEnchantmentRecipe;

public class VanillaRecipeInventory implements InventoryHolder
{
	public Inventory inv;
		
	public VanillaRecipeInventory(VanillaEnchantmentRecipe recipe, String name) {
		inv = Bukkit.createInventory(null, 27, name);
		ChangeRecipe(recipe);
	}
	
	@SuppressWarnings("deprecation")
	public void ChangeRecipe(VanillaEnchantmentRecipe recipe) {
		ItemStack bench = new ItemStack(Material.ANVIL);
		ItemMeta benchMeta = bench.getItemMeta();
		benchMeta.setDisplayName("§b§lAnvil");
		benchMeta.setLore(Collections.singletonList("§fHas to be crafted in an anvil."));
		bench.setItemMeta(benchMeta);
		inv.setItem(12, bench);
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner("MHF_ArrowLeft");
		meta.setDisplayName("§e§lBack");
		item.setItemMeta(meta);
		inv.setItem(0, item);
		ItemStack book = new ItemStack(Material.BOOK, 1);
		ItemMeta bookMeta = book.getItemMeta();
		bookMeta.setDisplayName("§b§lBook, Tool, Weapon or Enchanted Book");
		List<String> lore = new ArrayList<>();
		lore.add("§fCan be a book, weapon, tool");
		lore.add("§for enchanted book depending");
		lore.add("§fon the enchantment.");
		bookMeta.setLore(lore);
		book.setItemMeta(bookMeta);
		inv.setItem(11, book);
		inv.setItem(13, new ItemStack(recipe.mat, recipe.amount));
		ItemStack result = new ItemStack(Material.ENCHANTED_BOOK, 1);
		EnchantmentStorageMeta resultmeta = (EnchantmentStorageMeta) result.getItemMeta();
		resultmeta.addStoredEnchant(recipe.ench, 1, true);
		result.setItemMeta(resultmeta);
		inv.setItem(15, result);		
	}
	
	@Override
	public Inventory getInventory()
	{
		return inv;
	}

}
