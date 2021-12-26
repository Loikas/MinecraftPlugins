package Inventories;

import java.awt.MouseInfo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.Loikas.ExpandedEnchants.CustomEnchantsManager;
import me.Loikas.ExpandedEnchants.Main;

public class InventoryManager implements Listener
{
	public Inventory startInv;
	public Inventory vanillaChoose;
	public Inventory customChoose;
	
	public CustomRecipeInventory customRecipeInv = new CustomRecipeInventory(Main.customRecipes.get(0));
	public VanillaRecipeInventory vanillaRecipeInv = new VanillaRecipeInventory(Main.recipeManager.recipes[0], "");
	
	boolean shouldSwitchSoul = false;
	boolean shouldSwitchLumber = false;
	
	public InventoryManager() {
		new BukkitRunnable() {
			@Override
			public void run() {
				SwitchItems();
			}
		}.runTaskTimer(Main.getPlugin(), 0, 20);
		startInv = Bukkit.createInventory(null, 27, "§5§lCrafting Recipes");
		ItemStack vanillaItem = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemStack customItem = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta vanillaMeta = vanillaItem.getItemMeta();
		ItemMeta customaMeta = customItem.getItemMeta();
		vanillaMeta.setDisplayName("§b§lVanilla enchantment recipes");
		customaMeta.setDisplayName("§b§lCustom enchantment recipes");
		vanillaItem.setItemMeta(vanillaMeta);
		customItem.setItemMeta(customaMeta);
		startInv.setItem(12, vanillaItem);
		startInv.setItem(14, customItem);
		
		vanillaChoose = Bukkit.createInventory(null, 54, "§5§lChoose the enchantment");
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner("MHF_ArrowLeft");
		meta.setDisplayName("§e§lBack");
		item.setItemMeta(meta);
		vanillaChoose.setItem(0, item);
		customChoose = Bukkit.createInventory(null, 36, "§5§lChoose the enchantment");
		customChoose.setItem(0, item);
		for(int i = 9; i < 47; i++) {
			ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
			EnchantmentStorageMeta bookmeta = (EnchantmentStorageMeta) book.getItemMeta();
			bookmeta.addStoredEnchant(Main.recipeManager.recipes[i - 9].ench, 1, true);
			book.setItemMeta(bookmeta);
			vanillaChoose.setItem(i, book);
		}
		for(int i = 9; i < 32; i++) {
			ItemStack book = Main.itemManager.CreateCustomBook(CustomEnchantsManager.custom_enchants.get(i - 9), 1);
			customChoose.setItem(i, book);
		}
		
	}
	
	int log = 0;
	public void SwitchItems() {
		if(shouldSwitchSoul) {
			if(vanillaRecipeInv.inv.getItem(22).getType() == Material.SOUL_SAND) vanillaRecipeInv.inv.setItem(22, new ItemStack(Material.SOUL_SOIL, 64));
			else if(vanillaRecipeInv.inv.getItem(22).getType() == Material.SOUL_SOIL) vanillaRecipeInv.inv.setItem(22, new ItemStack(Material.SOUL_SAND, 64));
		}
		if(shouldSwitchLumber) {
			switch(log) {
			case 0:
				customRecipeInv.inv.setItem(12, new ItemStack(Material.OAK_LOG, 64));
				log++;
				break;
			case 1:
				customRecipeInv.inv.setItem(12, new ItemStack(Material.SPRUCE_LOG, 64));
				log++;
				break;
			case 2:
				customRecipeInv.inv.setItem(12, new ItemStack(Material.ACACIA_LOG, 64));
				log++;
				break;
			case 3:
				customRecipeInv.inv.setItem(12, new ItemStack(Material.BIRCH_LOG, 64));
				log++;
				break;
			case 4:
				customRecipeInv.inv.setItem(12, new ItemStack(Material.DARK_OAK_LOG, 64));
				log++;
				break;
			case 5:
				customRecipeInv.inv.setItem(12, new ItemStack(Material.JUNGLE_LOG, 64));
				log = 0;
				break;
			}
			
		}
	}
	
	@EventHandler
	public void onInventoryMoveItemEvent(InventoryMoveItemEvent e) {
		if(e.getDestination() == null) return;
		if(e.getDestination().equals(startInv) || e.getDestination().equals(vanillaChoose) || e.getDestination().equals(customChoose) || e.getDestination().equals(customRecipeInv.inv) || e.getDestination().equals(vanillaRecipeInv.inv)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == null) return;
		if(player.getOpenInventory().getTopInventory().equals(startInv) || player.getOpenInventory().getTopInventory().equals(vanillaChoose) || player.getOpenInventory().getTopInventory().equals(customChoose) || player.getOpenInventory().getTopInventory().equals(customRecipeInv.inv) || player.getOpenInventory().getTopInventory().equals(vanillaRecipeInv.inv))
		{
			e.setCancelled(true);
		}
		if(e.getClickedInventory().equals(startInv)) {
			if(e.getSlot() == 12) {
				player.closeInventory();
				player.openInventory(vanillaChoose);
			}
			if(e.getSlot() == 14) {
				player.closeInventory();
				player.openInventory(customChoose);
			}
		}
		
		if(e.getClickedInventory().equals(vanillaChoose)) {
			if(e.getSlot() == 0) {
				player.closeInventory();
				player.openInventory(startInv);
				return;
			}
			if(e.getCurrentItem() != null) {
				ItemStack item = e.getCurrentItem();
				EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
				Enchantment ench = (Enchantment) meta.getStoredEnchants().keySet().toArray()[0];
				String name = Main.eventsClass.functions.GetEnchantmentName(ench);
				player.closeInventory();
				vanillaRecipeInv = new VanillaRecipeInventory(Main.recipeManager.GetRecipe(ench), "§5§l" + name);
				if(ench.equals(Enchantment.SOUL_SPEED)) shouldSwitchSoul = true;
				player.openInventory(vanillaRecipeInv.inv);
			}
		}
		
		if(e.getClickedInventory().equals(customChoose)) {
			if(e.getSlot() == 0) {
				player.closeInventory();
				player.openInventory(startInv);
				return;
			}
			if(e.getCurrentItem() != null) {
				ItemStack item = e.getCurrentItem();
				ItemMeta meta = item.getItemMeta();
				Enchantment ench = (Enchantment) meta.getEnchants().keySet().toArray()[0];
				player.closeInventory();
				customRecipeInv = new CustomRecipeInventory(Main.GetCustomEnchantmentRecipe(ench));
				if(ench.equals(CustomEnchantsManager.LUMBERJACK)) shouldSwitchLumber = true;
				player.openInventory(customRecipeInv.inv);
			}
		}
		
		if(e.getClickedInventory().equals(vanillaRecipeInv.inv)) {
			if(e.getSlot() == 0) { 
				shouldSwitchSoul = false;
				player.closeInventory();
				player.openInventory(vanillaChoose);
			}
		}
		if(e.getClickedInventory().equals(customRecipeInv.inv)) {
			if(e.getSlot() == 0) {
				shouldSwitchLumber =  false;
				player.closeInventory();
				player.openInventory(customChoose);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
