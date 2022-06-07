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

import me.Loikas.ExpandedEnchants.Util.LanguageManager;
import me.Loikas.ExpandedEnchants.Util.VanillaEnchantmentRecipe;

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
		benchMeta.setDisplayName("§b§l" + LanguageManager.instance.GetTranslatedValue("anvil-name"));
		benchMeta.setLore(Collections.singletonList("§f" + LanguageManager.instance.GetTranslatedValue("crafted-in-anvil")));
		bench.setItemMeta(benchMeta);
		inv.setItem(12, bench);
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
		inv.setItem(14, itemResult);
		ItemStack book = new ItemStack(Material.BOOK, 1);
		ItemMeta bookMeta = book.getItemMeta();
		bookMeta.setDisplayName("§b§l" + LanguageManager.instance.GetTranslatedValue("book-tool-weapon"));
		List<String> lore = new ArrayList<>();
		for(String string : LanguageManager.instance.GetTranslatedList("book-tool-weapon-lore")) lore.add("§f" + string);
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
