package me.Loikas.ExpandedEnchants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.loot.LootTables;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class EventsClass implements Listener
{
	public static Functions functions = new Functions();

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("SoulboundEnabled"))
			HandleSoulboundEnchant(e);
	}

	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("SoulboundEnabled"))
			HandleSoulboundRespawn(e);
	}

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("DeflectEnabled"))
			HandleDeflectEnchant(e);
		if (Main.getPlugin().getConfig().getBoolean("ElementalprotectionEnabled"))
			HandleElementalProtectionEnchant(e);
	}

	@EventHandler
	public void onEntityShootBowEvent(EntityShootBowEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("AntigravityEnabled"))
			HandleAntiGravityEnchant(e);
	}

	@EventHandler()
	public void onBlockBreakEvent(BlockBreakEvent event)
	{
		if (Main.getPlugin().getConfig().getBoolean("LumberjackEnabled"))
			HandleLumberjackEnchant(event);
		if (Main.getPlugin().getConfig().getBoolean("VeinmineEnabled"))
			HandleVeinmineEnchant(event);
		if (Main.getPlugin().getConfig().getBoolean("WideEnabled"))
			HandleWideEnchant(event);
	}

	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent event)
	{
		if (Main.getPlugin().getConfig().getBoolean("BeheadingEnabled"))
			HandleBeheadingEnchant(event);
		if (Main.getPlugin().getConfig().getBoolean("DirectEnabled"))
			HandleDirectEntityEnchant(event);
		if (Main.getPlugin().getConfig().getBoolean("LifestealEnabled"))
			HandleLifestealEnchant(event);
	}

	@EventHandler
	public void onBlockDropItemEvent(BlockDropItemEvent event)
	{
		if (Main.getPlugin().getConfig().getBoolean("AutosmeltEnabled"))
			HandleAutoSmeltEnchant(event);
		if (Main.getPlugin().getConfig().getBoolean("DirectEnabled"))
			HandleDirectBlockEnchant(event);
		if (Main.getPlugin().getConfig().getBoolean("ReplantingEnabled"))
			CheckReplantingItemDrop(event);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPrepareAnvilEvent(PrepareAnvilEvent event)
	{
		if (event.getInventory() == null)
			return;
		ItemStack fs = event.getInventory().getItem(0);
		ItemStack ss = event.getInventory().getItem(1);
		if (fs == null || ss == null)
			return;
		event.getInventory().setRepairCost(5);
		for (HumanEntity he : event.getViewers())
			isModified.put(he.getUniqueId(), false);
		if (Main.getPlugin().getConfig().getBoolean("AllowResourceEnchant"))
			HandleResourceEnchants(fs, ss, event);
		HandleSameItemUpgrades(fs, ss, event);
		HandleCustomEnchantBooks(fs, ss, event);
		HandleBookMerge(fs, ss, event);
		HandleBookEnchant(fs, ss, event);
		if (Main.getPlugin().getConfig().getBoolean("AllowResourceEnchant"))
			HandleBookResourceEnchant(fs, ss, event);
		if (Main.getPlugin().getConfig().getBoolean("AllowDisenchanting"))
			HandleDisenchant(fs, ss, event);
	}

	@EventHandler
	public void onPlayerExpChangeEvent(PlayerExpChangeEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("ExperienceboostEnabled"))
			HandleExpBoostEnchant(e);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerMoveEvent(PlayerMoveEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("LavawalkerEnabled"))
			HandleLavaWalkerEnchant(e);
		if (Main.getPlugin().getConfig().getBoolean("SteppingEnabled"))
			HandleSteppingEnchant(e);
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("IcyEnabled"))
			HandleIcyEnchant(e);
	}

	@EventHandler
	public void onPrepareItemCraftEvent(PrepareItemCraftEvent e)
	{
		CheckForCustomRecipes(e);
	}

	@EventHandler
	public void onCraftItemEvent(CraftItemEvent e)
	{
		HandleCraftedItem(e);
		CheckForDiscoverRecipes(e);
	}

	@EventHandler
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("FeedingmoduleEnabled"))
			HandleFeedingModuleEnchant(e);
		if (Main.getPlugin().getConfig().getBoolean("GourmandEnabled"))
			HandleGourmandEnchant(e);
	}

	@EventHandler
	public void onEntityTargetEvent(EntityTargetEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("ShadowstepEnabled"))
			HandleShadowStepEnchant(e);
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e)
	{
		if (Main.getPlugin().getConfig().getBoolean("ReplantingEnabled"))
			CheckReplantingEnchant(e);
		if (Main.getPlugin().getConfig().getBoolean("WideEnabled"))
			HandleWideSpecialActionEnchant(e);
	}

	@EventHandler
	public void onEntitySpawnEvent(EntitySpawnEvent e)
	{
		CheckWanderingTrader(e);
	}

	@EventHandler
	public void onLootGenerateEvent(LootGenerateEvent e) {
		CheckEndCityLoot(e);
	}
	
	public void CheckEndCityLoot(LootGenerateEvent e) {
		if(!LootTables.END_CITY_TREASURE.getLootTable().equals(e.getLootTable())) return;
		int ran = functions.GetRandomNumber(0, 100);
		if (ran > Main.getPlugin().getConfig().getInt("EndCityChance"))
			return;
		List<ItemStack> items = e.getLoot();
		int ranEnch = functions.GetRandomNumber(0, functions.GetEnabledEnchants().size());
		ItemStack enchant = Main.itemManager.CreateCustomBook(functions.GetEnabledEnchants().get(ranEnch), 1);
		e.getLoot().add(enchant);
	}
	
	public void CheckWanderingTrader(EntitySpawnEvent e)
	{
		if (e.getEntityType() != EntityType.WANDERING_TRADER)
			return;
		int ran = functions.GetRandomNumber(0, 100);
		if (ran > Main.getPlugin().getConfig().getInt("WanderingTraderChance"))
			return;
		WanderingTrader trader = (WanderingTrader) e.getEntity();
		List<MerchantRecipe> recipes = trader.getRecipes();
		List<MerchantRecipe> newRecipes = new ArrayList<MerchantRecipe>();
		for (MerchantRecipe r : recipes)
			newRecipes.add(r);

		if (functions.GetEnabledEnchants().size() == 0)
			return;
		int ranEnch = functions.GetRandomNumber(0, functions.GetEnabledEnchants().size());
		ItemStack enchant = Main.itemManager.CreateCustomBook(functions.GetEnabledEnchants().get(ranEnch), 1);
		MerchantRecipe bookRecipe = new MerchantRecipe(enchant, functions.GetRandomNumber(2, 5));
		ArrayList<ItemStack> ing = CustomEnchantsManager.tradeCosts[ranEnch].getItems();
		bookRecipe.setIngredients(ing);
		newRecipes.add(bookRecipe);
		trader.setRecipes(newRecipes);
	}

	Ageable replantingBlock = null;

	public void CheckReplantingEnchant(PlayerInteractEvent e)
	{
		if (!e.hasBlock())
			return;
		if (!e.hasItem())
			return;
		if (e.getItem().getItemMeta() == null)
			return;
		if (!e.getItem().getItemMeta().hasEnchant(CustomEnchantsManager.REPLANTING))
			return;
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		BlockData data = e.getClickedBlock().getBlockData();
		Location loc = e.getClickedBlock().getLocation();
		if (data instanceof Ageable)
		{
			Ageable ageData = (Ageable) data;
			if (ageData.getAge() != ageData.getMaximumAge())
				return;
			if (data.getMaterial() == Material.WHEAT)
			{
				replantingBlock = ageData;
				e.getPlayer().breakBlock(e.getClickedBlock());
				loc.getBlock().setType(Material.WHEAT);
				Damageable dam = (Damageable) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				dam.setDamage(dam.getDamage() + 1);
				e.getItem().setItemMeta(dam);
			}
			if (data.getMaterial() == Material.CARROTS)
			{
				replantingBlock = ageData;
				e.getPlayer().breakBlock(e.getClickedBlock());
				loc.getBlock().setType(Material.CARROTS);
				Damageable dam = (Damageable) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				dam.setDamage(dam.getDamage() + 1);
				e.getItem().setItemMeta(dam);
			}
			if (data.getMaterial() == Material.POTATOES)
			{
				replantingBlock = ageData;
				e.getPlayer().breakBlock(e.getClickedBlock());
				loc.getBlock().setType(Material.POTATOES);
				Damageable dam = (Damageable) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				dam.setDamage(dam.getDamage() + 1);
				e.getItem().setItemMeta(dam);
			}
			if (data.getMaterial() == Material.BEETROOTS)
			{
				replantingBlock = ageData;
				e.getPlayer().breakBlock(e.getClickedBlock());
				loc.getBlock().setType(Material.BEETROOTS);
				Damageable dam = (Damageable) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				dam.setDamage(dam.getDamage() + 1);
				e.getItem().setItemMeta(dam);
			}
			if (data.getMaterial() == Material.NETHER_WART)
			{
				replantingBlock = ageData;
				e.getPlayer().breakBlock(e.getClickedBlock());
				loc.getBlock().setType(Material.NETHER_WART);
				Damageable dam = (Damageable) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				dam.setDamage(dam.getDamage() + 1);
				e.getItem().setItemMeta(dam);
			}
		}
	}

	public void CheckReplantingItemDrop(BlockDropItemEvent e)
	{
		if (e.getPlayer() == null)
			return;
		Player player = e.getPlayer();
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (player.getInventory().getItemInMainHand().getItemMeta() == null)
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.REPLANTING))
			return;
		BlockData data = e.getBlock().getBlockData();
		if (data == null)
			return;
		if (data.getMaterial() == Material.AIR)
			data = replantingBlock;
		if (data instanceof Ageable)
		{
			Ageable ageData = (Ageable) data;
			if (ageData.getAge() != ageData.getMaximumAge())
				return;
			List<Item> items = e.getItems();
			if (data.getMaterial() == Material.WHEAT)
			{
				for (Item item : items)
				{
					if (item.getItemStack().getType().equals(Material.WHEAT_SEEDS))
					{
						item.getItemStack().setAmount(item.getItemStack().getAmount() - 1);
						break;
					}
				}
			}
			if (data.getMaterial() == Material.CARROTS)
			{
				for (Item item : items)
					if (item.getItemStack().getType().equals(Material.CARROT))
					{
						item.getItemStack().setAmount(item.getItemStack().getAmount() - 1);
						break;
					}
			}
			if (data.getMaterial() == Material.POTATOES)
			{
				for (Item item : items)
					if (item.getItemStack().getType().equals(Material.POTATO))
					{
						item.getItemStack().setAmount(item.getItemStack().getAmount() - 1);
						break;
					}
			}
			if (data.getMaterial() == Material.BEETROOTS)
			{
				for (Item item : items)
					if (item.getItemStack().getType().equals(Material.BEETROOT))
					{
						item.getItemStack().setAmount(item.getItemStack().getAmount() - 1);
						break;
					}
			}
			if (data.getMaterial() == Material.NETHER_WART)
			{
				for (Item item : items)
					if (item.getItemStack().getType().equals(Material.NETHER_WART))
					{
						item.getItemStack().setAmount(item.getItemStack().getAmount() - 1);
						break;
					}
			}
		}
		replantingBlock = null;
	}

	public void CheckForDiscoverRecipes(CraftItemEvent e)
	{
		if (e.getInventory() == null)
			return;
		if (e.getInventory().getResult() == null)
			return;
		if (e.getInventory().getResult().getType() == Material.CRAFTING_TABLE)
		{
			ArrayList<NamespacedKey> keys = new ArrayList<NamespacedKey>();
			for (CustomEnchantmentRecipe recipe : Main.customRecipes)
				keys.add(recipe.recipe.getKey());
		}
	}

	public void CheckForCustomRecipes(PrepareItemCraftEvent e)
	{
		Recipe recipe = e.getRecipe();
		if (recipe == null)
			return;
		if (((Keyed) recipe).getKey() == null)
			return;

		CustomEnchantmentRecipe customRecipe = null;
		for (CustomEnchantmentRecipe cer : Main.customRecipes)
		{
			if (cer.recipe.getKey().equals(((Keyed) recipe).getKey()))
				customRecipe = cer;
		}
		if (customRecipe == null)
			return;

		CraftingInventory inv = e.getInventory();
		ItemStack[] ingredients = inv.getMatrix();
		boolean canCraft = true;

		canCraft = CheckItemInRecipes(customRecipe.recipe.getKey().toString(), ingredients[1]);

		for (int i = 0; i < customRecipe.amounts.length; i++)
		{
			if (ingredients[i] == null)
			{
				if (customRecipe.amounts[i] == 0)
					continue;
				else
				{
					canCraft = false;
					break;
				}
			}
			if (customRecipe.amounts[i] > ingredients[i].getAmount())
			{
				canCraft = false;
				break;
			}
		}
		if (!canCraft)
			inv.setResult(null);

	}

	public boolean CheckItemInRecipes(String recipe, ItemStack ingredient)
	{
		boolean canCraft = true;
		switch (recipe)
		{
		case "minecraft:ee_recipe_owleyes":
			PotionMeta nightmeta = (PotionMeta) ingredient.getItemMeta();
			if (nightmeta.getBasePotionData().getType() == PotionType.NIGHT_VISION
					&& nightmeta.getBasePotionData().isExtended())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_elemental":
			PotionMeta elementalMeta = (PotionMeta) ingredient.getItemMeta();
			if (elementalMeta.getBasePotionData().getType() == PotionType.POISON
					&& elementalMeta.getBasePotionData().isUpgraded())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_healthboost":
			PotionMeta healthmeta = (PotionMeta) ingredient.getItemMeta();
			if (healthmeta.getBasePotionData().getType() == PotionType.INSTANT_HEAL
					&& healthmeta.getBasePotionData().isUpgraded())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_heavenslightness":
			PotionMeta heavensmeta = (PotionMeta) ingredient.getItemMeta();
			if (heavensmeta.getBasePotionData().getType() == PotionType.SLOW_FALLING
					&& heavensmeta.getBasePotionData().isExtended())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_icy":
			PotionMeta icymeta = (PotionMeta) ingredient.getItemMeta();
			if (icymeta.getBasePotionData().getType() == PotionType.SLOWNESS
					&& icymeta.getBasePotionData().isUpgraded())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_leaping":
			PotionMeta leapingmeta = (PotionMeta) ingredient.getItemMeta();
			if (leapingmeta.getBasePotionData().getType() == PotionType.JUMP
					&& leapingmeta.getBasePotionData().isUpgraded())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_lifesteal":
			PotionMeta lifemeta = (PotionMeta) ingredient.getItemMeta();
			if (lifemeta.getBasePotionData().getType() == PotionType.INSTANT_HEAL
					&& lifemeta.getBasePotionData().isUpgraded())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_stonefists":
			PotionMeta stonemeta = (PotionMeta) ingredient.getItemMeta();
			if (stonemeta.getBasePotionData().getType() == PotionType.STRENGTH
					&& stonemeta.getBasePotionData().isUpgraded())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_thermalplating":
			PotionMeta thermalmeta = (PotionMeta) ingredient.getItemMeta();
			if (thermalmeta.getBasePotionData().getType() == PotionType.FIRE_RESISTANCE
					&& thermalmeta.getBasePotionData().isExtended())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_traveler":
			PotionMeta travelermeta = (PotionMeta) ingredient.getItemMeta();
			if (travelermeta.getBasePotionData().getType() == PotionType.SPEED
					&& travelermeta.getBasePotionData().isUpgraded())
				;
			else
				canCraft = false;
			break;
		case "minecraft:ee_recipe_unbreakable":
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) ingredient.getItemMeta();

			if (meta.hasStoredEnchant(Enchantment.DURABILITY))
			{
				if (meta.getStoredEnchantLevel(Enchantment.DURABILITY) == 10)
					;
				else
					canCraft = false;
			} else
				canCraft = false;
			break;
		}
		return canCraft;
	}

	Map<UUID, List<ItemStack>> itemsToPreserve = new HashMap<>();

	public void HandleSoulboundEnchant(PlayerDeathEvent e)
	{
		if (e.getEntity() == null)
			return;
		if (e.getKeepInventory())
			return;
		List<ItemStack> preserveItems = new ArrayList<>();
		List<ItemStack> items = e.getDrops();
		for (ItemStack item : items)
			if (item.getItemMeta().hasEnchant(CustomEnchantsManager.SOULBOUND))
			{
				preserveItems.add(item);
			}
		for (ItemStack item : preserveItems)
			e.getDrops().remove(item);
		itemsToPreserve.put(e.getEntity().getUniqueId(), preserveItems);
	}

	public void HandleSoulboundRespawn(PlayerRespawnEvent e)
	{
		if (!itemsToPreserve.containsKey(e.getPlayer().getUniqueId()))
			return;
		Player player = e.getPlayer();
		for (ItemStack item : itemsToPreserve.get(player.getUniqueId()))
		{
			player.getInventory().addItem(item);
		}
		itemsToPreserve.remove(player.getUniqueId());
	}

	List<Player> noFallDamage = new ArrayList<>();

	public void HandleElementalProtectionEnchant(EntityDamageEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			if (player == null)
				return;
			if (player.getInventory().getChestplate() == null)
				return;
			if (!player.getInventory().getChestplate().hasItemMeta())
				return;
			if (!player.getInventory().getChestplate().getItemMeta()
					.hasEnchant(CustomEnchantsManager.ELEMENTALPROTECTION))
				return;
			if (player.getGameMode() == GameMode.SPECTATOR)
				return;

			int level = player.getInventory().getChestplate().getItemMeta()
					.getEnchantLevel(CustomEnchantsManager.ELEMENTALPROTECTION);
			if (e.getCause() == DamageCause.POISON)
				e.setCancelled(true);
			if (e.getCause() == DamageCause.WITHER && level == 2)
				e.setCancelled(true);
			if (e.getCause() == DamageCause.VOID && level == 3)
			{
				e.setCancelled(true);
				if (!noFallDamage.contains(player))
					noFallDamage.add(player);
				player.teleport(
						new Location(player.getWorld(), player.getLocation().getX(), 128, player.getLocation().getZ()),
						TeleportCause.PLUGIN);
			}
			if (e.getCause() == DamageCause.FALL && noFallDamage.contains(player))
			{
				e.setCancelled(true);
				noFallDamage.remove(player);
			}
		}
	}

	public void HandleShadowStepEnchant(EntityTargetEvent e)
	{
		if (e.getTarget() instanceof Player)
		{
			Player player = (Player) e.getTarget();
			if (player.getInventory().getBoots() == null)
				return;
			if (player.getInventory().getBoots().getItemMeta() == null)
				return;
			if (!player.getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchantsManager.SHADOWSTEP))
				return;

			int level = player.getInventory().getBoots().getItemMeta()
					.getEnchantLevel(CustomEnchantsManager.SHADOWSTEP);
			double followRange = 16;
			switch (level)
			{
			case 1:
				followRange = 12;
				break;
			case 2:
				followRange = 8;
				break;
			case 3:
				followRange = 6;
				break;
			case 4:
				followRange = 4;
				break;
			case 5:
				followRange = 2;
				break;
			}
			if (e.getEntity().getLocation().distance(player.getLocation()) > followRange)
			{
				e.setCancelled(true);
			}

		}
	}

	public void HandleCraftedItem(CraftItemEvent e)
	{
		Recipe recipe = e.getRecipe();
		if (recipe == null)
			return;
		if (((Keyed) recipe).getKey() == null)
			return;
		CustomEnchantmentRecipe customRecipe = null;
		for (CustomEnchantmentRecipe cer : Main.customRecipes)
		{
			if (cer.recipe.getKey().equals(((Keyed) recipe).getKey()))
				customRecipe = cer;
		}
		if (customRecipe == null)
			return;
		CraftingInventory inv = e.getInventory();
		ItemStack[] ingredients = inv.getMatrix();
		ItemStack[] leftOvers = ingredients;
		ItemStack result = inv.getResult();
		for (int i = 0; i < customRecipe.amounts.length; i++)
		{
			if (ingredients[i] == null)
			{
				continue;
			}
			int leftOverAmount = ingredients[i].getAmount() - customRecipe.amounts[i];
			if (leftOverAmount == 0)
				leftOvers[i] = null;
			else
				leftOvers[i].setAmount(leftOverAmount);
		}
		inv.setMatrix(leftOvers);
		e.getWhoClicked().setItemOnCursor(result);
	}

	public void ConstantCheckMethods()
	{
		if (Bukkit.getOnlinePlayers().size() == 0)
			return;
		for (Player player : Bukkit.getOnlinePlayers())
		{
			HandleOwlEyesEnchant(player);
			HandleHeavensLightnessEnchant(player);
			HandleThermalPlatingEnchant(player);
			HandleLeapingEnchant(player);
			CountdownDeflectEnchant();
			if (canFreeze.containsKey(player))
			{
				double value = canFreeze.get(player);
				if (value == 0)
					return;
				canFreeze.put(player, value - 1);
			}
		}
	}

	public void CountdownDeflectEnchant()
	{
		for (Player player : deflectCountdown.keySet())
		{
			int oldValue = deflectCountdown.get(player);
			if (oldValue > 0)
				deflectCountdown.put(player, oldValue - 1);
			else
				oldValue = 0;

		}
	}

	public HashMap<Player, Integer> deflectCountdown = new HashMap<Player, Integer>();

	public void HandleDeflectEnchant(EntityDamageEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			if (player.getInventory().getChestplate() == null)
				return;
			if (!player.getInventory().getChestplate().hasItemMeta())
				return;
			if (!player.getInventory().getChestplate().getItemMeta().hasEnchant(CustomEnchantsManager.DEFLECT))
				return;
			if (player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.CREATIVE)
				return;
			if (!player.getInventory().contains(Material.EMERALD))
			{
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR);
				TextComponent component = new TextComponent("No emeralds to deflect damage!");
				component.setColor(ChatColor.RED);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
				return;
			}
			if (!player.getInventory().containsAtLeast(new ItemStack(Material.EMERALD),
					Main.getPlugin().getConfig().getInt("DeflectCost")))
			{
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR);
				TextComponent component = new TextComponent("Not enough emeralds to deflect damage!");
				component.setColor(ChatColor.RED);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
				return;
			}
			if (!deflectCountdown.containsKey(player))
			{
				e.setCancelled(true);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR);
				TextComponent component = new TextComponent("Damage deflected!");
				component.setColor(ChatColor.GREEN);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
				player.getInventory().removeItem(
						new ItemStack(Material.EMERALD, Main.getPlugin().getConfig().getInt("DeflectCost")));
			} else if (deflectCountdown.get(player) == 0)
			{
				e.setCancelled(true);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR);
				TextComponent component = new TextComponent("Damage deflected!");
				component.setColor(ChatColor.GREEN);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
				player.getInventory().removeItem(
						new ItemStack(Material.EMERALD, Main.getPlugin().getConfig().getInt("DeflectCost")));
			} else
			{
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR);
				TextComponent component = new TextComponent(
						"Deflect has a cooldown left of " + deflectCountdown.get(player) + " seconds!");
				component.setColor(ChatColor.RED);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
				return;
			}
			deflectCountdown.put(player, 31 - player.getInventory().getChestplate().getItemMeta()
					.getEnchantLevel(CustomEnchantsManager.DEFLECT));
		}
	}

	public ArrayList<Player> thermalPlayers = new ArrayList<Player>();

	public void HandleThermalPlatingEnchant(Player player)
	{
		if (player != null)
		{
			if (player.getInventory().getChestplate() != null && player.getInventory().getHelmet() != null
					&& player.getInventory().getBoots() != null && player.getInventory().getLeggings() != null)
			{
				if (player.getInventory().getChestplate().hasItemMeta()
						&& player.getInventory().getHelmet().hasItemMeta()
						&& player.getInventory().getBoots().hasItemMeta()
						&& player.getInventory().getLeggings().hasItemMeta())
				{
					if (player.getInventory().getChestplate().getItemMeta()
							.hasEnchant(CustomEnchantsManager.THERMALPLATING)
							&& player.getInventory().getHelmet().getItemMeta()
									.hasEnchant(CustomEnchantsManager.THERMALPLATING)
							&& player.getInventory().getBoots().getItemMeta()
									.hasEnchant(CustomEnchantsManager.THERMALPLATING)
							&& player.getInventory().getLeggings().getItemMeta()
									.hasEnchant(CustomEnchantsManager.THERMALPLATING))
					{
						PotionEffect effect = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 99999999, 0, false,
								false, false);
						if (!player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE))
							player.addPotionEffect(effect);
						if (!thermalPlayers.contains(player))
							thermalPlayers.add(player);
						return;
					}
				}
			}
		}
		if (thermalPlayers.contains(player))
		{
			player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			thermalPlayers.remove(player);
		}
	}

	public ArrayList<Player> heavensPlayers = new ArrayList<Player>();

	public void HandleHeavensLightnessEnchant(Player player)
	{
		if (player != null)
		{
			if (player.getInventory().getChestplate() != null)
			{
				if (player.getInventory().getChestplate().hasItemMeta())
				{
					if (player.getInventory().getChestplate().getItemMeta()
							.hasEnchant(CustomEnchantsManager.HEAVENSLIGHTNESS))
					{
						PotionEffect effect = new PotionEffect(PotionEffectType.SLOW_FALLING, 99999999, 0, false, false,
								false);
						if (!player.hasPotionEffect(PotionEffectType.SLOW_FALLING))
							player.addPotionEffect(effect);
						if (!heavensPlayers.contains(player))
							heavensPlayers.add(player);
						return;
					}
				}
			}
		}
		if (heavensPlayers.contains(player))
		{
			player.removePotionEffect(PotionEffectType.SLOW_FALLING);
			heavensPlayers.remove(player);
		}
	}

	public ArrayList<Player> owleyesPlayers = new ArrayList<Player>();

	public void HandleOwlEyesEnchant(Player player)
	{
		if (player != null)
		{
			if (player.getInventory().getHelmet() != null)
			{
				if (player.getInventory().getHelmet().hasItemMeta())
				{
					if (player.getInventory().getHelmet().getItemMeta().hasEnchant(CustomEnchantsManager.OWLEYES))
					{
						PotionEffect effect = new PotionEffect(PotionEffectType.NIGHT_VISION, 99999999, 0, false, false,
								false);
						if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
							player.addPotionEffect(effect);
						if (!owleyesPlayers.contains(player))
							owleyesPlayers.add(player);
						return;
					}
				}
			}
		}
		if (owleyesPlayers.contains(player))
		{
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			owleyesPlayers.remove(player);
		}
	}

	public ArrayList<Player> leapingPlayers = new ArrayList<Player>();

	public void HandleLeapingEnchant(Player player)
	{
		if (player != null)
		{
			if (player.getInventory().getBoots() != null)
			{
				if (player.getInventory().getBoots().hasItemMeta())
				{
					if (player.getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchantsManager.LEAPING))
					{
						PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, 999999, player.getInventory()
								.getBoots().getItemMeta().getEnchantLevel(CustomEnchantsManager.LEAPING), false, false,
								false);
						player.removePotionEffect(PotionEffectType.JUMP);
						player.addPotionEffect(effect);
						if (!leapingPlayers.contains(player))
							leapingPlayers.add(player);
						return;
					}
				}
			}
		}
		if (leapingPlayers.contains(player))
		{
			player.removePotionEffect(PotionEffectType.JUMP);
			leapingPlayers.remove(player);
		}
	}

	public HashMap<Player, Double> canFreeze = new HashMap<Player, Double>();

	public void HandleIcyEnchant(EntityDamageByEntityEvent e)
	{
		if (e.getEntity() instanceof LivingEntity)
		{
			LivingEntity other = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player)
			{
				Player player = (Player) e.getDamager();
				if (other.isDead())
					return;
				if (player == null)
					return;
				if (player.getInventory().getItemInMainHand() == null)
					return;
				if (!player.getInventory().getItemInMainHand().hasItemMeta())
					return;
				if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.ICY))
					return;
				if (player.getGameMode() == GameMode.SPECTATOR)
					return;

				int level = player.getInventory().getItemInMainHand().getItemMeta()
						.getEnchantLevel(CustomEnchantsManager.ICY) * 20;
				if (!canFreeze.containsKey(player) || (canFreeze.get(player) == 0 && canFreeze.containsKey(player)))
				{
					PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, level, 3, false, false, false);
					other.addPotionEffect(effect);
					other.getWorld().playSound(other.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
					canFreeze.put(player, (double) 10);
					if (other instanceof Player)
					{
						PotionEffect effect2 = new PotionEffect(PotionEffectType.JUMP, level, 128, false, false, false);
						other.addPotionEffect(effect2);
					}
				}
			}
		}
	}

	ArrayList<Block> checkedBlocks = new ArrayList<Block>();
	ArrayList<Block> veinMinedBlocks = new ArrayList<Block>();

	public void HandleLumberjackEnchant(BlockBreakEvent e)
	{
		if (veinMinedBlocks.contains(e.getBlock()))
			return;
		Block originBlock = e.getBlock();
		if (!EventsClass.functions.IsLumberjackBlock(originBlock.getType()))
			return;
		Player player = e.getPlayer();
		if (player == null)
			return;
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.LUMBERJACK))
			return;
		if (player.getGameMode() == GameMode.SPECTATOR)
			return;
		int maxMineSize = 128;
		checkedBlocks.clear();
		CheckSurroundingBlocks(originBlock, maxMineSize * 100 / 185);
		switch (Main.getPlugin().getConfig().getString("LumberjackMode"))
		{
		case "SNEAKING":
			if (!player.isSneaking())
				return;
			break;
		case "STANDING":
			if (player.isSneaking())
				return;
			break;
		case "BOTH":
			break;
		}
		Damageable itemMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
		if (!itemMeta.isUnbreakable())
		{
			if (itemMeta.hasEnchant(Enchantment.DURABILITY))
				itemMeta.setDamage(itemMeta.getDamage()
						+ (checkedBlocks.size() / itemMeta.getEnchantLevel(Enchantment.DURABILITY) + 1));
			else
				itemMeta.setDamage(itemMeta.getDamage() + checkedBlocks.size());
		}
		player.setFoodLevel(player.getFoodLevel() - (5 - itemMeta.getEnchantLevel(CustomEnchantsManager.LUMBERJACK)));
		for (Block block : checkedBlocks)
		{
			if (block != null)
			{
				veinMinedBlocks.add(block);
				player.breakBlock(block);
			}
		}
		player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
		if (veinMinedBlocks.size() > 0)
			e.setDropItems(false);
		veinMinedBlocks.clear();

	}

	public void HandleVeinmineEnchant(BlockBreakEvent e)
	{
		if (veinMinedBlocks.contains(e.getBlock()))
			return;
		Block originBlock = e.getBlock();
		if (!EventsClass.functions.IsVeinmineBlock(originBlock.getType()))
			return;
		Player player = e.getPlayer();
		if (player == null)
			return;
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.VEINMINE))
			return;
		if (player.getGameMode() == GameMode.SPECTATOR)
			return;
		int maxMineSize = 128;
		checkedBlocks.clear();
		CheckSurroundingBlocks(originBlock, maxMineSize * 100 / 185);
		switch (Main.getPlugin().getConfig().getString("VeinmineMode"))
		{
		case "SNEAKING":
			if (!player.isSneaking())
				return;
			break;
		case "STANDING":
			if (player.isSneaking())
				return;
			break;
		case "BOTH":
			break;
		}
		Damageable itemMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
		if (!itemMeta.isUnbreakable())
		{
			if (itemMeta.hasEnchant(Enchantment.DURABILITY))
				itemMeta.setDamage(itemMeta.getDamage()
						+ (checkedBlocks.size() / itemMeta.getEnchantLevel(Enchantment.DURABILITY) + 1));
			else
				itemMeta.setDamage(itemMeta.getDamage() + checkedBlocks.size());
		}
		player.setFoodLevel(player.getFoodLevel() - (5 - itemMeta.getEnchantLevel(CustomEnchantsManager.VEINMINE)));
		for (Block block : checkedBlocks)
		{
			if (block != null)
			{
				veinMinedBlocks.add(block);
				player.breakBlock(block);
			}
		}
		player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
		if (veinMinedBlocks.size() > 0)
			e.setDropItems(false);
		veinMinedBlocks.clear();
	}

	public void CheckSurroundingBlocks(Block block, int maxSize)
	{
		Location blockLoc = block.getLocation();
		if (checkedBlocks.size() == maxSize)
			return;
		Location under = new Location(blockLoc.getWorld(), blockLoc.getX(), blockLoc.getY() - 1, blockLoc.getZ());
		Block underBlock = under.getBlock();
		if (underBlock.getType().equals(block.getType()))
			if (!checkedBlocks.contains(underBlock))
			{
				checkedBlocks.add(underBlock);
				if (checkedBlocks.size() < maxSize)
					CheckSurroundingBlocks(underBlock, maxSize);
				else
					return;
			}
		Location up = new Location(blockLoc.getWorld(), blockLoc.getX(), blockLoc.getY() + 1, blockLoc.getZ());
		Block upBlock = up.getBlock();
		if (upBlock.getType().equals(block.getType()))
			if (!checkedBlocks.contains(upBlock))
			{
				checkedBlocks.add(upBlock);
				if (checkedBlocks.size() < maxSize)
					CheckSurroundingBlocks(upBlock, maxSize);
				else
					return;
			}
		Location north = new Location(blockLoc.getWorld(), blockLoc.getX(), blockLoc.getY(), blockLoc.getZ() - 1);
		Block northBlock = north.getBlock();
		if (northBlock.getType().equals(block.getType()))
			if (!checkedBlocks.contains(northBlock))
			{
				checkedBlocks.add(northBlock);
				if (checkedBlocks.size() < maxSize)
					CheckSurroundingBlocks(northBlock, maxSize);
				else
					return;
			}
		Location south = new Location(blockLoc.getWorld(), blockLoc.getX(), blockLoc.getY(), blockLoc.getZ() + 1);
		Block southBlock = south.getBlock();
		if (southBlock.getType().equals(block.getType()))
			if (!checkedBlocks.contains(southBlock))
			{
				checkedBlocks.add(southBlock);
				if (checkedBlocks.size() < maxSize)
					CheckSurroundingBlocks(southBlock, maxSize);
				else
					return;
			}
		Location east = new Location(blockLoc.getWorld(), blockLoc.getX() + 1, blockLoc.getY(), blockLoc.getZ());
		Block eastBlock = east.getBlock();
		if (eastBlock.getType().equals(block.getType()))
			if (!checkedBlocks.contains(eastBlock))
			{
				checkedBlocks.add(eastBlock);
				if (checkedBlocks.size() < maxSize)
					CheckSurroundingBlocks(eastBlock, maxSize);
				else
					return;
			}
		Location west = new Location(blockLoc.getWorld(), blockLoc.getX() - 1, blockLoc.getY(), blockLoc.getZ());
		Block westBlock = west.getBlock();
		if (westBlock.getType().equals(block.getType()))
			if (!checkedBlocks.contains(westBlock))
			{
				checkedBlocks.add(westBlock);
				if (checkedBlocks.size() < maxSize)
					CheckSurroundingBlocks(westBlock, maxSize);
				else
					return;
			}
	}

	public void HandleWideEnchant(BlockBreakEvent e)
	{
		if (veinMinedBlocks.contains(e.getBlock()))
			return;
		Player player = e.getPlayer();
		if (player == null)
			return;
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.WIDE))
			return;
		if (player.getGameMode() == GameMode.SPECTATOR)
			return;

		BlockFace face = GetBlockFace(player);
		if (face == null)
			return;
		ArrayList<Block> blocks = CheckWideBlocks(e.getBlock(), face);

		switch (Main.getPlugin().getConfig().getString("WideActivationMode"))
		{
		case "SNEAKING":
			if (!player.isSneaking())
				return;
			break;
		case "STANDING":
			if (player.isSneaking())
				return;
			break;
		case "BOTH":
			break;
		}

		Damageable itemMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
		if (!itemMeta.isUnbreakable())
		{
			if (itemMeta.hasEnchant(Enchantment.DURABILITY))
				itemMeta.setDamage(
						itemMeta.getDamage() + (blocks.size() / itemMeta.getEnchantLevel(Enchantment.DURABILITY) + 1));
			else
				itemMeta.setDamage(itemMeta.getDamage() + blocks.size());
		}
		for (Block block : blocks)
		{
			if (block != null)
			{
				veinMinedBlocks.add(block);
				player.breakBlock(block);
			}
		}
		player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
		veinMinedBlocks.clear();
		// e.setDropItems(false);
	}

	public void HandleWideSpecialActionEnchant(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
		if (player == null)
			return;
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.WIDE))
			return;
		if (player.getGameMode() == GameMode.SPECTATOR)
			return;
		ItemStack item = player.getInventory().getItemInMainHand();
		switch (Main.getPlugin().getConfig().getString("WideSpecialActionMode"))
		{
		case "SNEAKING":
			if (!player.isSneaking())
				return;
			break;
		case "STANDING":
			if (player.isSneaking())
				return;
			break;
		case "BOTH":
			break;
		}
		if (functions.CheckHoeTypes(item))
		{
			Material mat = e.getClickedBlock().getType();
			if (mat == Material.DIRT || mat == Material.GRASS_BLOCK || mat == Material.DIRT_PATH)
			{
				ArrayList<Block> blocks = GetSurroundingWideBlocks(e.getClickedBlock(), BlockFace.UP);
				for (Block block : blocks)
				{
					Material bmat = block.getType();
					if (bmat == Material.DIRT || bmat == Material.GRASS_BLOCK || bmat == Material.DIRT_PATH)
					{
						if (new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ()).getBlock()
								.getType() == Material.AIR)
						{
							block.setType(Material.FARMLAND);
						}
					}
				}
				Damageable itemMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
				if (!itemMeta.isUnbreakable())
				{
					if (itemMeta.hasEnchant(Enchantment.DURABILITY))
						itemMeta.setDamage(itemMeta.getDamage()
								+ (blocks.size() / itemMeta.getEnchantLevel(Enchantment.DURABILITY) + 1));
					else
						itemMeta.setDamage(itemMeta.getDamage() + blocks.size());
				}
				item.setItemMeta(itemMeta);
			}
		}
		if (functions.CheckAxeTypes(item))
		{
			if (!functions.CheckNonStrippedLogTypes(e.getClickedBlock().getType()))
				return;
			ArrayList<Block> blocks = GetSurroundingWideBlocks(e.getClickedBlock(), GetBlockFace(player));
			for (Block block : blocks)
			{
				Material bmat = block.getType();
				if (functions.CheckNonStrippedLogTypes(bmat))
				{
					block.setType(functions.GetStrippedVariantWood(bmat));
				}
			}
			Damageable itemMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
			if (!itemMeta.isUnbreakable())
			{
				if (itemMeta.hasEnchant(Enchantment.DURABILITY))
					itemMeta.setDamage(itemMeta.getDamage()
							+ (blocks.size() / itemMeta.getEnchantLevel(Enchantment.DURABILITY) + 1));
				else
					itemMeta.setDamage(itemMeta.getDamage() + blocks.size());
			}
			item.setItemMeta(itemMeta);
		}
		if (functions.CheckShovelTypes(item))
		{
			Material mat = e.getClickedBlock().getType();
			if (mat == Material.DIRT || mat == Material.GRASS_BLOCK || mat == Material.COARSE_DIRT
					|| mat == Material.MYCELIUM || mat == Material.PODZOL || mat == Material.ROOTED_DIRT)
			{
				ArrayList<Block> blocks = GetSurroundingWideBlocks(e.getClickedBlock(), BlockFace.UP);
				for (Block block : blocks)
				{
					Material bmat = block.getType();
					if (bmat == Material.DIRT || bmat == Material.GRASS_BLOCK || bmat == Material.COARSE_DIRT
							|| bmat == Material.MYCELIUM || bmat == Material.PODZOL || bmat == Material.ROOTED_DIRT)
					{
						if (new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ()).getBlock()
								.getType() == Material.AIR)
						{
							block.setType(Material.DIRT_PATH);
						}
					}
				}
				Damageable itemMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
				if (!itemMeta.isUnbreakable())
				{
					if (itemMeta.hasEnchant(Enchantment.DURABILITY))
						itemMeta.setDamage(itemMeta.getDamage()
								+ (blocks.size() / itemMeta.getEnchantLevel(Enchantment.DURABILITY) + 1));
					else
						itemMeta.setDamage(itemMeta.getDamage() + blocks.size());
				}
				item.setItemMeta(itemMeta);
			}
		}
	}

	public BlockFace GetBlockFace(Player player)
	{
		List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
		if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding())
			return null;
		Block targetBlock = lastTwoTargetBlocks.get(1);
		Block adjacentBlock = lastTwoTargetBlocks.get(0);
		return targetBlock.getFace(adjacentBlock);
	}

	public ArrayList<Block> GetSurroundingWideBlocks(Block block, BlockFace face)
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
		Location loc = block.getLocation();

		if (face.equals(BlockFace.UP) || face.equals(BlockFace.DOWN))
		{
			blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() + 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() + 1).getBlock());
		}
		if (face.equals(BlockFace.SOUTH) || face.equals(BlockFace.NORTH))
		{
			blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY() + 1, loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY() - 1, loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY() - 1, loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY() + 1, loc.getZ()).getBlock());
		}
		if (face.equals(BlockFace.EAST) || face.equals(BlockFace.WEST))
		{
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ()).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ() + 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ() - 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ() - 1).getBlock());
			blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ() + 1).getBlock());
		}

		return blocks;
	}

	public ArrayList<Block> CheckWideBlocks(Block block, BlockFace face)
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
		Location loc = block.getLocation();

		if (face.equals(BlockFace.UP) || face.equals(BlockFace.DOWN))
		{
			if (new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() + 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() + 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() + 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() + 1).getBlock());
		}
		if (face.equals(BlockFace.SOUTH) || face.equals(BlockFace.NORTH))
		{
			if (new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX() + 1, loc.getY() + 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY() + 1, loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX() - 1, loc.getY() - 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY() - 1, loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX() + 1, loc.getY() - 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() + 1, loc.getY() - 1, loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX() - 1, loc.getY() + 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX() - 1, loc.getY() + 1, loc.getZ()).getBlock());
		}
		if (face.equals(BlockFace.EAST) || face.equals(BlockFace.WEST))
		{
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ()).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ()).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ() + 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ() + 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ() - 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ() - 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ() - 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ() - 1).getBlock());
			if (new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ() + 1).getBlock().getType()
					.equals(block.getType()))
				blocks.add(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ() + 1).getBlock());
		}

		return blocks;
	}

	@SuppressWarnings("deprecation")
	public void HandleSteppingEnchant(PlayerMoveEvent e)
	{
		Player player = e.getPlayer();
		if (player == null)
			return;
		if (player.getInventory().getBoots() == null) return;
		if (!player.getInventory().getBoots().hasItemMeta()) return;
		if (!player.getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchantsManager.STEPPING)) return;
		
		Vector v = e.getPlayer().getVelocity();
		// Bukkit.getServer().getConsoleSender().sendMessage("x: " + (e.getTo().getX() -
		// e.getFrom().getX()) + ", " + (e.getTo().getY() - e.getFrom().getY()) + ", " +
		// (e.getTo().getZ() - e.getFrom().getZ()));
		if (new Location(e.getTo().getWorld(), e.getTo().getX() + 0.5, Math.round(e.getTo().getY()), e.getTo().getZ())
				.getBlock().getType() != Material.AIR)
		{// block infront exists
			if (e.getTo().getX() > e.getFrom().getX())
			{
				if (e.getTo().getX() - e.getFrom().getX() > 0.11)
				{
					if (new Location(e.getTo().getWorld(), e.getTo().getX() + 0.5, Math.round(e.getTo().getY()),
							e.getTo().getZ()).getBlock().getType().isSolid())
					{ // is solid
						if (new Location(e.getTo().getWorld(), e.getTo().getX() + 0.5, Math.round(e.getTo().getY()) + 1,
								e.getTo().getZ()).getBlock().getType().isTransparent())
						{ // block above other is air
							if (new Location(e.getFrom().getWorld(), e.getFrom().getX(),
									Math.round(e.getFrom().getY()) - 1, e.getFrom().getZ()).getBlock()
											.getType() != Material.AIR)
							{ // underneath is not air
								e.setTo(new Location(e.getTo().getWorld(), e.getTo().getX() + 0.3,
										Math.round(e.getTo().getY()) + 1, e.getTo().getZ(), e.getTo().getYaw(),
										e.getTo().getPitch()));
								Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
								{
									e.getPlayer().setVelocity(v);
								});

							}
						}
					}
				}
			}
		}
		if (new Location(e.getTo().getWorld(), e.getTo().getX() - 0.5, Math.round(e.getTo().getY()), e.getTo().getZ())
				.getBlock().getType() != Material.AIR)
		{// block infront exists
			if (e.getTo().getX() < e.getFrom().getX())
			{
				if (e.getTo().getX() - e.getFrom().getX() < -0.11)
				{
					if (new Location(e.getTo().getWorld(), e.getTo().getX() - 0.5, Math.round(e.getTo().getY()),
							e.getTo().getZ()).getBlock().getType().isSolid())
					{ // is solid
						if (new Location(e.getTo().getWorld(), e.getTo().getX() - 0.5, Math.round(e.getTo().getY()) + 1,
								e.getTo().getZ()).getBlock().getType().isTransparent())
						{ // block above other is air
							if (new Location(e.getFrom().getWorld(), e.getFrom().getX(),
									Math.round(e.getFrom().getY()) - 1, e.getFrom().getZ()).getBlock()
											.getType() != Material.AIR)
							{ // underneath is not air
								e.setTo(new Location(e.getTo().getWorld(), e.getTo().getX() - 0.3,
										Math.round(e.getTo().getY()) + 1, e.getTo().getZ(), e.getTo().getYaw(),
										e.getTo().getPitch()));
								Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
								{
									e.getPlayer().setVelocity(v);
								});

							}
						}
					}
				}
			}
		}
		if (new Location(e.getTo().getWorld(), e.getTo().getX(), Math.round(e.getTo().getY()), e.getTo().getZ() + 0.5)
				.getBlock().getType() != Material.AIR)
		{// block infront exists
			if (e.getTo().getZ() > e.getFrom().getZ())
			{
				if (e.getTo().getZ() - e.getFrom().getZ() > 0.11)
				{
					if (new Location(e.getTo().getWorld(), e.getTo().getX(), Math.round(e.getTo().getY()),
							e.getTo().getZ() + 0.5).getBlock().getType().isSolid())
					{ // is solid
						if (new Location(e.getTo().getWorld(), e.getTo().getX(), Math.round(e.getTo().getY()) + 1,
								e.getTo().getZ() + 0.5).getBlock().getType().isTransparent())
						{ // block above other is air
							if (new Location(e.getFrom().getWorld(), e.getFrom().getX(),
									Math.round(e.getFrom().getY()) - 1, e.getFrom().getZ()).getBlock()
											.getType() != Material.AIR)
							{ // underneath is not air
								e.setTo(new Location(e.getTo().getWorld(), e.getTo().getX(),
										Math.round(e.getTo().getY()) + 1, e.getTo().getZ() + 0.3, e.getTo().getYaw(),
										e.getTo().getPitch()));
								Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
								{
									e.getPlayer().setVelocity(v);
								});

							}
						}
					}
				}
			}
		}
		if (new Location(e.getTo().getWorld(), e.getTo().getX(), Math.round(e.getTo().getY()), e.getTo().getZ() - 0.5)
				.getBlock().getType() != Material.AIR)
		{// block infront exists
			if (e.getTo().getZ() < e.getFrom().getZ())
			{
				if (e.getTo().getZ() - e.getFrom().getZ() < -0.11)
				{
					if (new Location(e.getTo().getWorld(), e.getTo().getX(), Math.round(e.getTo().getY()),
							e.getTo().getZ() - 0.5).getBlock().getType().isSolid())
					{ // is solid
						if (new Location(e.getTo().getWorld(), e.getTo().getX(), Math.round(e.getTo().getY()) + 1,
								e.getTo().getZ() - 0.5).getBlock().getType().isTransparent())
						{ // block above other is air
							if (new Location(e.getFrom().getWorld(), e.getFrom().getX(),
									Math.round(e.getFrom().getY()) - 1, e.getFrom().getZ()).getBlock()
											.getType() != Material.AIR)
							{ // underneath is not air
								e.setTo(new Location(e.getTo().getWorld(), e.getTo().getX(),
										Math.round(e.getTo().getY()) + 1, e.getTo().getZ() - 0.3, e.getTo().getYaw(),
										e.getTo().getPitch()));
								Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
								{
									e.getPlayer().setVelocity(v);
								});

							}
						}
					}
				}
			}
		}
	}

	public void HandleGourmandEnchant(FoodLevelChangeEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			if (player == null)
				return;
			if (player.getInventory().getHelmet() == null)
				return;
			if (!player.getInventory().getHelmet().hasItemMeta())
				return;
			if (!player.getInventory().getHelmet().getItemMeta().hasEnchant(CustomEnchantsManager.GOURMAND))
				return;
			if (player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.CREATIVE)
				return;
			if (player.getInventory().getHelmet().getItemMeta().hasEnchant(CustomEnchantsManager.FEEDINGMODULE))
				return;
			if (e.getItem() == null)
				return;
			double level = 1.3;
			if (player.getInventory().getHelmet().getItemMeta().getEnchantLevel(CustomEnchantsManager.GOURMAND) == 2)
				level = 1.5;
			int extra = (int) Math.round(functions.GetFoodPoints(e.getItem().getType()) * level);
			e.setFoodLevel(player.getFoodLevel() + extra);
		}
	}

	public void HandleFeedingModuleEnchant(FoodLevelChangeEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			if (player == null)
				return;
			if (player.getInventory().getHelmet() == null)
				return;
			if (!player.getInventory().getHelmet().hasItemMeta())
				return;
			if (!player.getInventory().getHelmet().getItemMeta().hasEnchant(CustomEnchantsManager.FEEDINGMODULE))
				return;
			if (player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.CREATIVE)
				return;
			if (!functions.PlayerContainsFoodItems(player))
				return;
			ItemStack item = functions.GetFirstFoodItem(player);
			if (item == null)
				return;
			double multiplier = 1;
			if (player.getInventory().getHelmet().getItemMeta().hasEnchant(CustomEnchantsManager.GOURMAND)
					&& Main.getPlugin().getConfig().getBoolean("GourmandEnabled"))
			{
				if (player.getInventory().getHelmet().getItemMeta()
						.getEnchantLevel(CustomEnchantsManager.GOURMAND) == 1)
					multiplier = 1.3;
				else
					multiplier = 1.5;
			}
			int foodValue = (int) Math.round(functions.GetFoodPoints(item.getType()) * multiplier);
			if (player.getFoodLevel() == 20)
				return;
			if ((20 - player.getFoodLevel()) >= foodValue)
			{
				int totalEat = player.getFoodLevel() + foodValue;
				player.setFoodLevel(totalEat);
				e.setFoodLevel(totalEat);
			} else if (Main.getPlugin().getConfig().getBoolean("EnableSaturationMode") && player.getFoodLevel() < 20)
			{
				e.setFoodLevel(player.getFoodLevel() + foodValue);
			} else
				return;
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
			item.setAmount(item.getAmount() - 1);
		}
	}

	@SuppressWarnings("deprecation")
	public void HandleLifestealEnchant(EntityDeathEvent e)
	{
		LivingEntity mob = e.getEntity();
		Player player = mob.getKiller();
		if (player == null)
			return;
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.LIFESTEAL))
			return;
		if (player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.CREATIVE)
			return;
		int level = player.getInventory().getItemInMainHand().getItemMeta()
				.getEnchantLevel(CustomEnchantsManager.LIFESTEAL);
		double health = player.getHealth();
		double healing = 1 * level;
		double total = health + healing;
		if (total > player.getMaxHealth())
			total = player.getMaxHealth();
		player.setHealth(total);
	}

	public void HandleLavaWalkerEnchant(PlayerMoveEvent e)
	{
		Player player = e.getPlayer();
		if (player == null)
			return;
		if (player.getInventory().getBoots() == null)
			return;
		if (!player.getInventory().getBoots().hasItemMeta())
			return;
		if (!player.getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchantsManager.LAVA_WALKER))
			return;

		Location location = e.getTo();
		Location underLoc = new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ());
		Block block = underLoc.getBlock();
		if (block.getType() == Material.LAVA)
		{
			block.setType(Material.BASALT);
		}
	}

	public void HandleExpBoostEnchant(PlayerExpChangeEvent e)
	{
		Player player = e.getPlayer();
		if (player == null)
			return;
		if (player.getInventory().getChestplate() == null)
			return;
		if (!player.getInventory().getChestplate().hasItemMeta())
			return;
		if (!player.getInventory().getChestplate().getItemMeta().hasEnchant(CustomEnchantsManager.EXP_BOOST))
			return;
		if (player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.CREATIVE)
			return;

		double chance = player.getInventory().getChestplate().getItemMeta()
				.getEnchantLevel(CustomEnchantsManager.EXP_BOOST) * 0.15;
		double random = Math.random();

		if (random < chance)
		{
			e.setAmount(e.getAmount() * 2);
		}

	}

	public void HandleBeheadingEnchant(EntityDeathEvent e)
	{
		LivingEntity mob = e.getEntity();
		Player player = mob.getKiller();
		if (player == null)
			return;
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.BEHEADING))
			return;
		if (player.getGameMode() == GameMode.SPECTATOR)
			return;

		double chance = player.getInventory().getItemInMainHand().getItemMeta()
				.getEnchantLevel(CustomEnchantsManager.BEHEADING) * 0.05;
		double random = Math.random();

		if (random < chance)
		{
			if (mob.getType() == EntityType.ZOMBIE || mob.getType() == EntityType.ZOMBIE_VILLAGER
					|| mob.getType() == EntityType.HUSK)
				e.getDrops().add(new ItemStack(Material.ZOMBIE_HEAD));
			if (mob.getType() == EntityType.SKELETON || mob.getType() == EntityType.STRAY)
				e.getDrops().add(new ItemStack(Material.SKELETON_SKULL));
			if (mob.getType() == EntityType.WITHER_SKELETON)
				e.getDrops().add(new ItemStack(Material.WITHER_SKELETON_SKULL));
			if (mob.getType() == EntityType.CREEPER)
				e.getDrops().add(new ItemStack(Material.CREEPER_HEAD));
			if (mob.getType() == EntityType.ENDER_DRAGON)
				e.getDrops().add(new ItemStack(Material.DRAGON_HEAD));
		}
	}

	public void HandleAntiGravityEnchant(EntityShootBowEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			if (player.getInventory().getItemInMainHand() == null)
				return;
			if (!player.getInventory().getItemInMainHand().hasItemMeta())
				return;
			if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.ANTIGRAVITY))
				return;
			Entity arrow = e.getProjectile();
			arrow.setGravity(false);
		}
	}

	public void HandleDirectEntityEnchant(EntityDeathEvent e)
	{
		LivingEntity mob = e.getEntity();
		Player player = mob.getKiller();
		if (player == null)
			return;
		if (player.getInventory().getItemInMainHand() == null)
			return;
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.DIRECT))
			return;
		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
			return;
		if (player.getInventory().firstEmpty() == -1)
			return;

		List<ItemStack> drops = e.getDrops();
		if (drops.size() == 0)
			return;
		for (ItemStack item : drops)
		{
			player.getInventory().addItem(item);
		}
		e.getDrops().clear();
	}

	public void HandleDirectBlockEnchant(BlockDropItemEvent event)
	{
		if (event.getPlayer().getInventory().getItemInMainHand() == null)
			return;
		if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta()
				.hasEnchant(CustomEnchantsManager.DIRECT))
			return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE
				|| event.getPlayer().getGameMode() == GameMode.SPECTATOR)
			return;
		if (event.getPlayer().getInventory().firstEmpty() == -1)
			return;
		if (event.getBlock().getState() instanceof Container)
			return;

		if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta()
				.hasEnchant(CustomEnchantsManager.AUTOSMELT))
		{
			event.setCancelled(true);

		} else
			return;
		Player player = event.getPlayer();

		List<Item> drops = event.getItems();
		if (drops.isEmpty())
			return;
		player.getInventory().addItem(drops.iterator().next().getItemStack());
	}

	public void HandleAutoSmeltEnchant(BlockDropItemEvent e)
	{
		if (e.getBlock() == null)
			return;
		if (e.getPlayer().getInventory().getItemInMainHand() == null)
			return;
		if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.AUTOSMELT))
			return;
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR)
			return;
		if (e.getBlock().getState() instanceof Container)
			return;
		List<Item> items = e.getItems();
		boolean isOre = false;
		for (Item item : items)
		{
			if (item.getItemStack().getType() == Material.RAW_IRON || item.getItemStack().getType() == Material.RAW_GOLD
					|| item.getItemStack().getType() == Material.RAW_COPPER
					|| item.getItemStack().getType() == Material.ANCIENT_DEBRIS)
				isOre = true;
		}
		if (!isOre)
		{
			if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.DIRECT))
			{
				Player player = e.getPlayer();
				// Block block = e.getBlock();

				player.getInventory().addItem(items.iterator().next().getItemStack());
				e.setCancelled(true);
			}
			return;
		}
		int i = 0;
		for (Item item : items)
		{
			if (item == null)
				return;
			if (functions.CheckPickaxeTypes(e.getPlayer().getInventory().getItemInMainHand()))
			{
				if (item.getItemStack().getType() == Material.RAW_IRON)
				{
					if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta()
							.hasEnchant(CustomEnchantsManager.DIRECT))
						e.getPlayer().getInventory()
								.addItem(new ItemStack(Material.IRON_INGOT, item.getItemStack().getAmount()));
					else
						e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(),
								new ItemStack(Material.IRON_INGOT, item.getItemStack().getAmount()));
					i++;
				}
				if (item.getItemStack().getType() == Material.RAW_GOLD)
				{
					if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta()
							.hasEnchant(CustomEnchantsManager.DIRECT))
						e.getPlayer().getInventory()
								.addItem(new ItemStack(Material.GOLD_INGOT, item.getItemStack().getAmount()));
					else
						e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(),
								new ItemStack(Material.GOLD_INGOT, item.getItemStack().getAmount()));
					i++;
				}
				if (item.getItemStack().getType() == Material.RAW_COPPER)
				{
					if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta()
							.hasEnchant(CustomEnchantsManager.DIRECT))
						e.getPlayer().getInventory()
								.addItem(new ItemStack(Material.COPPER_INGOT, item.getItemStack().getAmount()));
					else
						e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(),
								new ItemStack(Material.COPPER_INGOT, item.getItemStack().getAmount()));
					i++;
				}
				if (item.getItemStack().getType() == Material.ANCIENT_DEBRIS)
				{
					if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta()
							.hasEnchant(CustomEnchantsManager.DIRECT))
						e.getPlayer().getInventory()
								.addItem(new ItemStack(Material.NETHERITE_SCRAP, item.getItemStack().getAmount()));
					else
						e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(),
								new ItemStack(Material.NETHERITE_SCRAP, item.getItemStack().getAmount()));
					i++;
				}
			}
		}
		for (int y = 0; y < i; y++)
		{
			e.getItems().remove(y);
		}

	}

	public Map<UUID, Boolean> removeLastEnchant = new HashMap<>();
	public Map<UUID, Boolean> isModified = new HashMap<>();

	@SuppressWarnings("deprecation")
	public void HandleDisenchant(ItemStack fs, ItemStack ss, PrepareAnvilEvent e)
	{
		if (ss.getType() != Material.BOOK)
			return;
		if (fs.getType() == Material.ENCHANTED_BOOK)
		{
			if (functions.ContainsCustomEnchant(fs))
				return;
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) fs.getItemMeta();
			Object[] enchs = meta.getStoredEnchants().keySet().toArray();
			if (enchs.length == 1)
				return;
			Enchantment removeEnchant = (Enchantment) enchs[enchs.length - 1];
			ItemStack resultBook = new ItemStack(Material.ENCHANTED_BOOK);
			EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) resultBook.getItemMeta();
			resultMeta.addStoredEnchant(removeEnchant, meta.getStoredEnchantLevel(removeEnchant), true);
			resultMeta.setDisplayName(e.getInventory().getRenameText());
			resultBook.setItemMeta(resultMeta);
			meta.removeStoredEnchant(removeEnchant);
			for (HumanEntity he : e.getInventory().getViewers()) {
				removeLastEnchant.put(he.getUniqueId(), true);
			}
			e.setResult(resultBook);
			for (HumanEntity he : e.getViewers())
				isModified.put(he.getUniqueId(), false);
		} else
		{
			Object[] enchs = fs.getItemMeta().getEnchants().keySet().toArray();
			if (enchs.length == 0)
				return;
			Enchantment removeEnchant = (Enchantment) enchs[enchs.length - 1];
			ItemStack resultBook = new ItemStack(Material.ENCHANTED_BOOK);
			EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) resultBook.getItemMeta();
			if (functions.IsCustomEnchant(removeEnchant))
			{
				resultBook.addUnsafeEnchantment(removeEnchant, fs.getEnchantmentLevel(removeEnchant));
				List<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + removeEnchant.getName() + " "
						+ functions.GetNameByLevel(fs.getEnchantmentLevel(removeEnchant), removeEnchant.getMaxLevel()));
				ItemMeta customResultMeta = resultBook.getItemMeta();
				customResultMeta.setLore(lore);
				customResultMeta.setDisplayName(e.getInventory().getRenameText());
				resultBook.setItemMeta(customResultMeta);
				for (HumanEntity he : e.getInventory().getViewers()) {
					removeLastEnchant.put(he.getUniqueId(), true);
				}
				e.setResult(resultBook);
				for (HumanEntity he : e.getInventory().getViewers())
					isModified.put(he.getUniqueId(), true);
			} else
			{
				resultMeta.addStoredEnchant(removeEnchant, fs.getEnchantmentLevel(removeEnchant), true);
				for (HumanEntity he : e.getInventory().getViewers()) {
					removeLastEnchant.put(he.getUniqueId(), true);
				}
				resultMeta.setDisplayName(e.getInventory().getRenameText());
				resultBook.setItemMeta(resultMeta);
				e.setResult(resultBook);
				for (HumanEntity he : e.getViewers())
					isModified.put(he.getUniqueId(), true);
			}
		}
	}

	public void HandleBookResourceEnchant(ItemStack fs, ItemStack ss, PrepareAnvilEvent e)
	{
		if (fs.getType() == Material.BOOK)
		{
			Enchantment ench = Main.recipeManager.GetEnchantment(ss.getType());
			if (ench == null)
				return;
			ItemStack resultItem = new ItemStack(Material.ENCHANTED_BOOK);
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) resultItem.getItemMeta();
			if (ss.getAmount() < Main.recipeManager.GetAmount(ench))
				return;
			meta.addStoredEnchant(ench, 1, true);
			meta.setDisplayName(e.getInventory().getRenameText());
			resultItem.setItemMeta(meta);
			e.setResult(resultItem);
			for (HumanEntity he : e.getViewers())
				isModified.put(he.getUniqueId(), false);
			Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
			{
				if (Main.getPlugin().getConfig().getBoolean("ResourceEnchantXpEnabled"))
					e.getInventory().setRepairCost(1);
				for (HumanEntity he : e.getViewers())
				{
					Player pl = (Player) he;
					pl.updateInventory();
				}
			});
		}
		if (fs.getType() == Material.ENCHANTED_BOOK)
		{
			if (functions.ContainsCustomEnchant(fs))
				return;
			Enchantment ench = Main.recipeManager.GetEnchantment(ss.getType());
			if (ench == null)
				return;
			ItemStack resultItem = new ItemStack(fs);
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) fs.getItemMeta();
			;
			if (ss.getAmount() < Main.recipeManager.GetAmount(ench))
				return;
			int MaxLevel = Main.getPlugin().getConfig().getInt("MaxVanillaEnchantLevel");
			if(!Main.getPlugin().getConfig().getBoolean("EnableVanillaOverride")) MaxLevel = ench.getMaxLevel();
			if (meta.hasStoredEnchant(ench))
			{
				if (ench.getMaxLevel() == 1)
					meta.addStoredEnchant(ench, 1, false);
				else if (ench == Enchantment.LURE)
				{
					if (meta.getStoredEnchantLevel(ench) < (MaxLevel < 3 ? MaxLevel : 3))
					{
						meta.addStoredEnchant(ench, meta.getStoredEnchantLevel(ench) + 1, true);
					}
				} else if (ench == Enchantment.DEPTH_STRIDER)
				{
					if (meta.getStoredEnchantLevel(ench) < (MaxLevel < 3 ? MaxLevel : 3))
					{
						meta.addStoredEnchant(ench, meta.getStoredEnchantLevel(ench) + 1, true);
					}
				} else if (ench == Enchantment.QUICK_CHARGE)
				{
					if (meta.getStoredEnchantLevel(ench) < (MaxLevel < 5 ? MaxLevel : 5))
					{
						meta.addStoredEnchant(ench, meta.getStoredEnchantLevel(ench) + 1, true);
					}
				} else if (meta.getStoredEnchantLevel(ench) < MaxLevel)
				{
					meta.addStoredEnchant(ench, meta.getStoredEnchantLevel(ench) + 1, true);
				}
			} else if (!meta.hasConflictingStoredEnchant(ench))
			{
				meta.addStoredEnchant(ench, 1, true);
			}
			meta.setDisplayName(e.getInventory().getRenameText());
			resultItem.setItemMeta(meta);
			if (resultItem.equals(fs))
				resultItem = null;
			e.setResult(resultItem);
			for (HumanEntity he : e.getViewers())
				isModified.put(he.getUniqueId(), false);
			Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
			{
				if (Main.getPlugin().getConfig().getBoolean("ResourceEnchantXpEnabled"))
					e.getInventory().setRepairCost(
							(meta.getStoredEnchantLevel(ench) + 1) * (meta.getStoredEnchantLevel(ench) + 1));
				for (HumanEntity he : e.getViewers())
				{
					Player pl = (Player) he;
					pl.updateInventory();
				}
			});
		}

	}

	public void HandleBookEnchant(ItemStack fs, ItemStack ss, PrepareAnvilEvent e)
	{
		if (ss.getType() != Material.ENCHANTED_BOOK || fs.getType() == Material.ENCHANTED_BOOK)
			return;

		if (functions.ContainsCustomEnchant(ss))
			return;
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) ss.getItemMeta();
		ItemMeta itemMeta = fs.getItemMeta();
		ItemStack resultItem = new ItemStack(fs);
		ItemMeta resultMeta = resultItem.getItemMeta();
		for (Enchantment item : meta.getStoredEnchants().keySet())
		{
			for (Enchantment item2 : resultMeta.getEnchants().keySet())
			{
				if (item.equals(item2))
					continue;
				if (item.conflictsWith(item2))
					return;
			}
			if (!item.canEnchantItem(fs))
				return;
			if (fs.getItemMeta().hasEnchant(item))
			{
				if (meta.getStoredEnchantLevel(item) == itemMeta.getEnchantLevel(item))
				{
					int MaxLvl = Main.getPlugin().getConfig().getInt("MaxVanillaEnchantLevel");
					if(!Main.getPlugin().getConfig().getBoolean("EnableVanillaOverride")) MaxLvl = item.getMaxLevel();
					if (item.getMaxLevel() == 1);
					else if (item == Enchantment.QUICK_CHARGE)
					{
						if (itemMeta.getEnchantLevel(item) < (MaxLvl < 5 ? MaxLvl : 5))
							resultMeta.addEnchant(item, itemMeta.getEnchantLevel(item) + 1, true);
					} else if (item == Enchantment.LURE)
					{
						if (itemMeta.getEnchantLevel(item) < (MaxLvl < 3 ? MaxLvl : 3))
							resultMeta.addEnchant(item, itemMeta.getEnchantLevel(item) + 1, true);
					} else if (item == Enchantment.DEPTH_STRIDER)
					{
						if (itemMeta.getEnchantLevel(item) < (MaxLvl < 3 ? MaxLvl : 3))
							resultMeta.addEnchant(item, itemMeta.getEnchantLevel(item) + 1, true);
					} else if (itemMeta.getEnchantLevel(item) < MaxLvl)
					{
						resultMeta.addEnchant(item, itemMeta.getEnchantLevel(item) + 1, true);
					}
				}
				if (meta.getStoredEnchantLevel(item) > itemMeta.getEnchantLevel(item))
				{
					resultMeta.addEnchant(item, meta.getStoredEnchantLevel(item), true);
				}
				if (meta.getStoredEnchantLevel(item) < itemMeta.getEnchantLevel(item))
				{
					resultMeta.addEnchant(item, itemMeta.getEnchantLevel(item), true);
				}
			} else
			{
				resultMeta.addEnchant(item, meta.getStoredEnchantLevel(item), true);
			}
			resultMeta.setDisplayName(e.getInventory().getRenameText());
			resultItem.setItemMeta(resultMeta);
		}
		if (fs.equals(resultItem))
			resultItem = null;
		e.setResult(resultItem);
		for (HumanEntity he : e.getViewers())
			isModified.put(he.getUniqueId(), false);
		Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
		{
			e.getInventory().setRepairCost(5);
			for (HumanEntity he : e.getViewers())
			{
				Player pl = (Player) he;
				pl.updateInventory();
			}
		});

	}

	@SuppressWarnings("deprecation")
	public void HandleBookMerge(ItemStack fs, ItemStack ss, PrepareAnvilEvent e)
	{
		if (fs.getType() != Material.ENCHANTED_BOOK || ss.getType() != Material.ENCHANTED_BOOK)
			return;
		if (functions.ContainsCustomEnchant(fs) && functions.ContainsCustomEnchant(ss))
		{
			Enchantment ench = (Enchantment) fs.getEnchantments().keySet().toArray()[0];
			if (!ench.equals(ss.getEnchantments().keySet().toArray()[0]))
				return;
			int highestlevel = fs.getItemMeta().getEnchantLevel(ench) >= ss.getItemMeta().getEnchantLevel(ench)
					? fs.getItemMeta().getEnchantLevel(ench)
					: ss.getItemMeta().getEnchantLevel(ench);
			ItemStack item = new ItemStack(fs);
			if (fs.getItemMeta().getEnchantLevel(ench) == ss.getItemMeta().getEnchantLevel(ench))
				highestlevel = fs.getItemMeta().getEnchantLevel(ench) + 1;
			if (highestlevel > ench.getMaxLevel())
				return;
			item.addUnsafeEnchantment(ench, highestlevel);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + ench.getName() + " "
					+ functions.GetNameByLevel(item.getItemMeta().getEnchantLevel(ench), ench.getMaxLevel()));
			meta.setLore(lore);
			meta.setDisplayName(e.getInventory().getRenameText());
			item.setItemMeta(meta);
			e.setResult(item);
			for (HumanEntity he : e.getViewers())
				isModified.put(he.getUniqueId(), false);
			Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
			{
				e.getInventory().setRepairCost(5);
				for (HumanEntity he : e.getViewers())
				{
					Player pl = (Player) he;
					pl.updateInventory();
				}
			});
			return;
		} else if (functions.ContainsCustomEnchant(fs) || functions.ContainsCustomEnchant(ss))
		{
			e.setResult(null);
			return;
		}
		ItemStack resultItem = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta fsMeta = (EnchantmentStorageMeta) fs.getItemMeta();
		EnchantmentStorageMeta ssMeta = (EnchantmentStorageMeta) ss.getItemMeta();
		EnchantmentStorageMeta resultMeta = fsMeta;

		Set<Enchantment> ssEnchs = ssMeta.getStoredEnchants().keySet();

		for (Enchantment item : ssEnchs)
		{
			if (resultMeta.hasStoredEnchant(item))
			{
				if (item.getMaxLevel() == 1)
					resultMeta.addStoredEnchant(item, 1, false);
				else if (item.equals(Enchantment.QUICK_CHARGE) && (resultMeta.getStoredEnchantLevel(item) == 5 || ssMeta.getStoredEnchantLevel(item) == 5))
				{
					resultMeta.addStoredEnchant(item, 5, true);
				}
				else if (item.equals(Enchantment.LURE) && (resultMeta.getStoredEnchantLevel(item) == 3 || ssMeta.getStoredEnchantLevel(item) == 3))
				{
					resultMeta.addStoredEnchant(item, 3, true);
				} 
				else if (item.equals(Enchantment.DEPTH_STRIDER) && (resultMeta.getStoredEnchantLevel(item) == 3 || ssMeta.getStoredEnchantLevel(item) == 3))
				{
					resultMeta.addStoredEnchant(item, 3, true);
				} 
				else if (resultMeta.getStoredEnchantLevel(item) == 10 || ssMeta.getStoredEnchantLevel(item) == 10)
				{
					resultMeta.addStoredEnchant(item, 10, true);
				} 
				else if (resultMeta.getStoredEnchantLevel(item) == ssMeta.getStoredEnchantLevel(item))
				{
				int Maxlvl = Main.getPlugin().getConfig().getInt("MaxVanillaEnchantLevel");
				if(!Main.getPlugin().getConfig().getBoolean("EnableVanillaOverride")) Maxlvl = item.getMaxLevel();		
				if (resultMeta.getStoredEnchantLevel(item) < Maxlvl)
						resultMeta.addStoredEnchant(item, resultMeta.getStoredEnchantLevel(item) + 1, true);
				}
				else if (resultMeta.getStoredEnchantLevel(item) > ssMeta.getStoredEnchantLevel(item))
				{
					resultMeta.addStoredEnchant(item, resultMeta.getStoredEnchantLevel(item), true);
				} 
				else if (resultMeta.getStoredEnchantLevel(item) < ssMeta.getStoredEnchantLevel(item))
				{
					resultMeta.addStoredEnchant(item, ssMeta.getStoredEnchantLevel(item), true);
				}

			} else if (!resultMeta.hasConflictingStoredEnchant(item))
			{
				resultMeta.addStoredEnchant(item, ssMeta.getStoredEnchantLevel(item), true);
			}
		}
		resultMeta.setDisplayName(e.getInventory().getRenameText());
		resultItem.setItemMeta(resultMeta);
		if (resultItem.equals(fs))
			resultItem = null;
		e.setResult(resultItem);
		for (HumanEntity he : e.getViewers())
			isModified.put(he.getUniqueId(), false);
		Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
		{
			e.getInventory().setRepairCost(5);
			for (HumanEntity he : e.getViewers())
			{
				Player pl = (Player) he;
				pl.updateInventory();
			}
		});

	}

	@SuppressWarnings("deprecation")
	public void HandleCustomEnchantBooks(ItemStack fs, ItemStack ss, PrepareAnvilEvent e)
	{
		if (ss.getType() != Material.ENCHANTED_BOOK)
			return;
		if (fs.getType() == Material.ENCHANTED_BOOK)
			return;
		if (!functions.ContainsCustomEnchant(ss))
			return;
		Enchantment ench = (Enchantment) ss.getEnchantments().keySet().toArray()[0];
		int level = ss.getItemMeta().getEnchantLevel(ench);
		if (fs.getItemMeta().hasEnchant(ench))
		{
			if (fs.getItemMeta().getEnchantLevel(ench) > level)
				return;
			if (ench.getMaxLevel() == 1)
				return;
			ItemStack resultItem = new ItemStack(fs);
			int addLevel = fs.getItemMeta().getEnchantLevel(ench) == level ? level + 1 : level;
			if (addLevel > ench.getMaxLevel())
				return;
			resultItem.addUnsafeEnchantment(ench, addLevel);
			ItemMeta meta = resultItem.getItemMeta();
			List<String> lore = new ArrayList<String>();
			if (meta.hasLore())
				lore = meta.getLore();
			int i = 0;
			int num = -1;
			for (String line : lore)
			{
				if (line.contains(ench.getName()))
				{
					num = i;
				}
				i++;
			}
			lore.remove(num);
			lore.add(ChatColor.GRAY + ench.getName() + " "
					+ functions.GetNameByLevel(resultItem.getItemMeta().getEnchantLevel(ench), ench.getMaxLevel()));
			meta.setLore(lore);
			meta.setDisplayName(e.getInventory().getRenameText());
			resultItem.setItemMeta(meta);
			if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.TRAVELER))
			{
				ItemMeta itemMeta = resultItem.getItemMeta();
				itemMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
				itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
						new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed",
								itemMeta.getEnchantLevel(CustomEnchantsManager.TRAVELER) * .4,
								AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.LEGS));
				itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				resultItem.setItemMeta(itemMeta);
			}
			if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.STONEFISTS))
			{
				ItemMeta itemMeta = resultItem.getItemMeta();
				itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
								itemMeta.getEnchantLevel(CustomEnchantsManager.STONEFISTS) * 3,
								AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				resultItem.setItemMeta(itemMeta);
			}
			if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.HEALTHBOOST))
			{
				ItemMeta itemMeta = resultItem.getItemMeta();
				itemMeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
				itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
						new AttributeModifier(UUID.randomUUID(), "generic.maxHealth",
								itemMeta.getEnchantLevel(CustomEnchantsManager.HEALTHBOOST) * 2,
								AttributeModifier.Operation.ADD_NUMBER));
				itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				resultItem.setItemMeta(itemMeta);
			}
			e.setResult(resultItem);
			for (HumanEntity he : e.getViewers())
				isModified.put(he.getUniqueId(), false);
			Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
			{
				e.getInventory().setRepairCost(10);
				for (HumanEntity he : e.getViewers())
				{
					Player pl = (Player) he;
					pl.updateInventory();
				}
			});
			return;
		}
		if (!ench.canEnchantItem(fs))
			return;
		if (fs.getItemMeta().hasConflictingEnchant(ench))
			return;

		ItemStack resultItem = new ItemStack(fs);
		resultItem.addUnsafeEnchantment(ench, ss.getItemMeta().getEnchantLevel(ench));
		ItemMeta meta = resultItem.getItemMeta();
		List<String> lore = new ArrayList<String>();
		if (meta.hasLore())
			lore = meta.getLore();
		lore.add(ChatColor.GRAY + ench.getName() + " "
				+ functions.GetNameByLevel(resultItem.getItemMeta().getEnchantLevel(ench), ench.getMaxLevel()));
		meta.setLore(lore);
		meta.setDisplayName(e.getInventory().getRenameText());
		resultItem.setItemMeta(meta);
		if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.NOBREAKABLE))
		{
			resultItem.setDurability((short) 100000);
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.setUnbreakable(true);
			resultItem.setItemMeta(itemMeta);
			resultItem.removeEnchantment(Enchantment.DURABILITY);
			resultItem.removeEnchantment(Enchantment.MENDING);
		}
		if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.TRAVELER))
		{
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
			itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
					new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed",
							itemMeta.getEnchantLevel(CustomEnchantsManager.TRAVELER) * .4,
							AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.LEGS));
			itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			resultItem.setItemMeta(itemMeta);
		}
		if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.STONEFISTS))
		{
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
			itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
					new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
							itemMeta.getEnchantLevel(CustomEnchantsManager.STONEFISTS) * 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			resultItem.setItemMeta(itemMeta);
		}
		if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.HEALTHBOOST))
		{
			ItemMeta itemMeta = resultItem.getItemMeta();
			itemMeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
			itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
					new AttributeModifier(UUID.randomUUID(), "generic.maxHealth",
							itemMeta.getEnchantLevel(CustomEnchantsManager.HEALTHBOOST) * 2,
							AttributeModifier.Operation.ADD_NUMBER));
			itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			resultItem.setItemMeta(itemMeta);
		}
		e.setResult(resultItem);
		for (HumanEntity he : e.getViewers())
			isModified.put(he.getUniqueId(), false);
		Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
		{
			e.getInventory().setRepairCost(10);
			for (HumanEntity he : e.getViewers())
			{
				Player pl = (Player) he;
				pl.updateInventory();
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void HandleSameItemUpgrades(ItemStack fs, ItemStack ss, PrepareAnvilEvent e)
	{
		if (fs.getType() != ss.getType())
			return;
		if (fs.getType() == Material.ENCHANTED_BOOK)
			return;

		Object[] ssEnchants = ss.getItemMeta().getEnchants().keySet().toArray();

		ItemStack resultItem = new ItemStack(fs);

		for (Object ench : ssEnchants)
		{
			Enchantment enc = (Enchantment) ench;
			int ssLevel = ss.getItemMeta().getEnchantLevel(enc);
			if (fs.getItemMeta().hasEnchant(enc))
			{
				int fsLevel = fs.getItemMeta().getEnchantLevel(enc);
				if (functions.IsCustomEnchant(enc))
				{
					if (ssLevel > fsLevel)
						resultItem.addUnsafeEnchantment(enc, ssLevel);
					if (ssLevel == fsLevel)
					{
						if (ssLevel == enc.getMaxLevel())
							continue;
						else
						{
							resultItem.addUnsafeEnchantment(enc, ssLevel + 1);
							if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.TRAVELER))
							{
								ItemMeta itemMeta = resultItem.getItemMeta();
								itemMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
								itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
										new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed",
												itemMeta.getEnchantLevel(CustomEnchantsManager.TRAVELER) * .4,
												AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.LEGS));
								itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
								resultItem.setItemMeta(itemMeta);
							}
							if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.STONEFISTS))
							{
								ItemMeta itemMeta = resultItem.getItemMeta();
								itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
								itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
										new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
												itemMeta.getEnchantLevel(CustomEnchantsManager.STONEFISTS) * 3,
												AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
								itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
								resultItem.setItemMeta(itemMeta);
							}
							if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.HEALTHBOOST))
							{
								ItemMeta itemMeta = resultItem.getItemMeta();
								itemMeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
								itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
										new AttributeModifier(UUID.randomUUID(), "generic.maxHealth",
												itemMeta.getEnchantLevel(CustomEnchantsManager.HEALTHBOOST) * 2,
												AttributeModifier.Operation.ADD_NUMBER));
								itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
								resultItem.setItemMeta(itemMeta);
							}
						}
					}
				} else
				{
					if (ssLevel > fsLevel)
						resultItem.addUnsafeEnchantment(enc, ssLevel);
					if (ssLevel == fsLevel)
					{
						int Mxlvl = Main.getPlugin().getConfig().getInt("MaxVanillaEnchantLevel");
						if(!Main.getPlugin().getConfig().getBoolean("EnableVanillaOverride")) Mxlvl = enc.getMaxLevel();
						if (enc.getMaxLevel() == 1)
							continue;
						else if (ssLevel == (Mxlvl < 3 ? Mxlvl : 3) && enc.equals(Enchantment.LURE))
							continue;
						else if (ssLevel == (Mxlvl < 3 ? Mxlvl : 3) && enc.equals(Enchantment.DEPTH_STRIDER))
							continue;
						else if (ssLevel == (Mxlvl < 5 ? Mxlvl : 5) && enc.equals(Enchantment.QUICK_CHARGE))
							continue;
						else if (ssLevel == Mxlvl)
							continue;
						else
							resultItem.addUnsafeEnchantment(enc, ssLevel + 1);
					}
				}
				if (functions.IsCustomEnchant(enc))
				{
					ItemMeta meta = resultItem.getItemMeta();
					List<String> lore = new ArrayList<String>();
					if (meta.hasLore())
						lore = meta.getLore();
					int num = -1;
					for (int i = 0; i < lore.size(); i++)
						if (lore.get(i).contains(enc.getName()))
							num = i;
					lore.remove(num);
					lore.add(ChatColor.GRAY + enc.getName() + " "
							+ functions.GetNameByLevel(resultItem.getEnchantmentLevel(enc), enc.getMaxLevel()));
					meta.setLore(lore);
					meta.setDisplayName(e.getInventory().getRenameText());
					resultItem.setItemMeta(meta);
				}
			} else
			{
				if (!fs.getItemMeta().hasConflictingEnchant(enc))
				{
					resultItem.addUnsafeEnchantment(enc, ssLevel);
					if (functions.IsCustomEnchant(enc))
					{
						ItemMeta meta = resultItem.getItemMeta();
						List<String> lore = new ArrayList<String>();
						if (meta.hasLore())
							lore = meta.getLore();
						lore.add(ChatColor.GRAY + enc.getName() + " "
								+ functions.GetNameByLevel(resultItem.getEnchantmentLevel(enc), enc.getMaxLevel()));
						meta.setLore(lore);
						meta.setDisplayName(e.getInventory().getRenameText());
						resultItem.setItemMeta(meta);
						if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.NOBREAKABLE))
						{
							resultItem.setDurability((short) 100000);
							ItemMeta itemMeta = resultItem.getItemMeta();
							itemMeta.setUnbreakable(true);
							resultItem.setItemMeta(itemMeta);
							resultItem.removeEnchantment(Enchantment.DURABILITY);
							resultItem.removeEnchantment(Enchantment.MENDING);
						}
						if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.TRAVELER))
						{
							ItemMeta itemMeta = resultItem.getItemMeta();
							itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
									new AttributeModifier("generic.movementSpeed",
											itemMeta.getEnchantLevel(CustomEnchantsManager.TRAVELER) * .4,
											AttributeModifier.Operation.ADD_SCALAR));
							itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
							resultItem.setItemMeta(itemMeta);
						}
						if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.STONEFISTS))
						{
							ItemMeta itemMeta = resultItem.getItemMeta();
							itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
							itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
									new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
											itemMeta.getEnchantLevel(CustomEnchantsManager.STONEFISTS) * 3,
											AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
							resultItem.setItemMeta(itemMeta);
						}
						if (resultItem.getItemMeta().hasEnchant(CustomEnchantsManager.HEALTHBOOST))
						{
							ItemMeta itemMeta = resultItem.getItemMeta();
							itemMeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
							itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
									new AttributeModifier(UUID.randomUUID(), "generic.maxHealth",
											itemMeta.getEnchantLevel(CustomEnchantsManager.HEALTHBOOST) * 2,
											AttributeModifier.Operation.ADD_NUMBER));
							itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
							resultItem.setItemMeta(itemMeta);
						}
					}
				}
			}
		}
		if (resultItem.equals(fs))
			resultItem = null;
		e.setResult(resultItem);
		for (HumanEntity he : e.getViewers())
			isModified.put(he.getUniqueId(), false);
		Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
		{
			e.getInventory().setRepairCost(5);
			for (HumanEntity he : e.getViewers())
			{
				Player pl = (Player) he;
				pl.updateInventory();
			}
		});

	}

	public void HandleResourceEnchants(ItemStack fs, ItemStack ss, PrepareAnvilEvent e)
	{
		if (fs.getType() == Material.ENCHANTED_BOOK)
			return;
		Enchantment goal = Main.recipeManager.GetEnchantment(ss.getType());
		if (goal == null)
			return;
		if (!goal.canEnchantItem(fs))
			return;
		if (ss.getAmount() < Main.recipeManager.GetAmount(goal))
			return;

		if (fs.getItemMeta().hasConflictingEnchant(goal))
		{

			if (fs.getItemMeta().hasEnchant(goal))
			{
				int enchLvl = fs.getItemMeta().getEnchantLevel(goal);
				ItemMeta im = fs.getItemMeta();
				if (goal.getMaxLevel() == 1)
					return;
				im.removeEnchant(goal);
				fs.setItemMeta(im);
				if (fs.getItemMeta().hasConflictingEnchant(goal))
					return;
				im.addEnchant(goal, enchLvl, true);
				fs.setItemMeta(im);
			} else
				return;

		}
		;

		if (fs.getItemMeta().hasEnchants())
		{
			int MaxLevel = Main.getPlugin().getConfig().getInt("MaxVanillaEnchantLevel");
			if(!Main.getPlugin().getConfig().getBoolean("EnableVanillaOverride")) MaxLevel = goal.getMaxLevel();
			if (fs.getItemMeta().hasEnchant(goal))
			{
				if ((goal == Enchantment.DEPTH_STRIDER
						&& fs.getItemMeta().getEnchantLevel(goal) == (MaxLevel < 3 ? MaxLevel : 3))
						|| (goal == Enchantment.LURE
								&& fs.getItemMeta().getEnchantLevel(goal) == (MaxLevel < 3 ? MaxLevel : 3))
						|| (goal == Enchantment.QUICK_CHARGE
								&& fs.getItemMeta().getEnchantLevel(goal) == (MaxLevel < 5 ? MaxLevel : 5)))
					return;
				if (fs.getItemMeta()
						.getEnchantLevel(goal) == MaxLevel)
					return;
				if (goal.getMaxLevel() == 1)
					return;
			}
		}

		ItemStack result = new ItemStack(fs);

		ItemMeta meta = fs.getItemMeta();
		AnvilInventory inv = e.getInventory();
		meta.setDisplayName(inv.getRenameText());

		int lvl = 1;
		int extra = 0;
		if (fs.getItemMeta().hasEnchant(goal))
			extra = meta.getEnchantLevel(goal);
		int tot = lvl + extra;
		meta.addEnchant(goal, tot, true);
		meta.setDisplayName(e.getInventory().getRenameText());
		result.setItemMeta(meta);
		e.getInventory().setItem(2, result);
		e.setResult(result);
		for (HumanEntity he : e.getViewers())
			isModified.put(he.getUniqueId(), false);
		Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
		{
			if (Main.getPlugin().getConfig().getBoolean("ResourceEnchantXpEnabled"))
				e.getInventory().setRepairCost(tot * tot);
			for (HumanEntity he : e.getViewers())
			{
				Player pl = (Player) he;
				pl.updateInventory();
			}
		});
	}

	@SuppressWarnings("deprecation")

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if (!e.isCancelled())
		{
			HumanEntity ent = e.getWhoClicked();
			if (ent == null)
				return;
			// not really necessary
			if (ent instanceof Player)
			{
				Player player = (Player) ent;
				Inventory inv = e.getInventory();

				// see if the event is about an anvil
				if (inv instanceof AnvilInventory)
				{
					InventoryView view = e.getView();
					int rawSlot = e.getRawSlot();
					if (rawSlot == view.convertSlot(rawSlot))
					{
						AnvilInventory ainv = (AnvilInventory) inv;
						if (rawSlot == 2)
						{
							if(!isModified.containsKey(ent.getUniqueId())) return;
							if (!isModified.get(ent.getUniqueId())) return;
							ItemStack item = e.getCurrentItem();
							if (item != null)
							{
								ItemMeta meta = item.getItemMeta();
								if (meta != null)
								{

									if (ainv.getRepairCost() > player.getLevel())
										return;
									player.setItemOnCursor(item);
									player.setLevel(player.getLevel() - ainv.getRepairCost());
									ItemStack cost = new ItemStack(inv.getContents()[1]);
									Enchantment ench = Main.recipeManager.GetEnchantment(cost.getType());
									if (ench != null)
									{
										int amount = Main.recipeManager.GetAmount(ench);
										int tot = cost.getAmount();
										int left = tot - amount;
										cost.setAmount(left);

										ItemStack[] content = new ItemStack[] { null, cost, null };
										inv.setContents(content);

									} else if (inv.getContents()[1].getType() == Material.BOOK)
									{
										ItemStack firstItem = inv.getContents()[0];
										int amount = inv.getContents()[1].getAmount();
										ItemStack bookItem = inv.getContents()[1];
										bookItem.setAmount(amount - 1);
										if (removeLastEnchant.containsKey(ent.getUniqueId()))
										{
											if (removeLastEnchant.get(ent.getUniqueId()))
											{
												if (firstItem.getType() == Material.ENCHANTED_BOOK)
												{
													EnchantmentStorageMeta firstMeta = (EnchantmentStorageMeta) firstItem
															.getItemMeta();
													firstMeta.removeStoredEnchant((Enchantment) firstMeta
															.getStoredEnchants().keySet()
															.toArray()[firstMeta.getStoredEnchants().keySet().size()
																	- 1]);
													firstItem.setItemMeta(firstMeta);
												} else
												{
													Enchantment removeEnch = (Enchantment) firstItem.getEnchantments()
															.keySet()
															.toArray()[firstItem.getEnchantments().keySet().size() - 1];
													firstItem.removeEnchantment(removeEnch);
													if (functions.IsCustomEnchant(removeEnch))
													{
														ItemMeta enchmeta = firstItem.getItemMeta();
														if (removeEnch.equals(CustomEnchantsManager.NOBREAKABLE))
															enchmeta.setUnbreakable(false);
														if (removeEnch.equals(CustomEnchantsManager.TRAVELER))
															enchmeta.removeAttributeModifier(
																	Attribute.GENERIC_MOVEMENT_SPEED);
														if (removeEnch.equals(CustomEnchantsManager.HEALTHBOOST))
															enchmeta.removeAttributeModifier(
																	Attribute.GENERIC_MAX_HEALTH);
														if (removeEnch.equals(CustomEnchantsManager.STONEFISTS))
															enchmeta.removeAttributeModifier(
																	Attribute.GENERIC_ATTACK_DAMAGE);
														List<String> lore = enchmeta.getLore();
														for (int i = 0; i < lore.size(); i++)
														{
															if (lore.get(i).contains(removeEnch.getName()))
															{
																lore.remove(i);
															}
														}
														enchmeta.setLore(lore);
														firstItem.setItemMeta(enchmeta);
													}
												}
												for (HumanEntity he : e.getClickedInventory().getViewers())
													removeLastEnchant.put(he.getUniqueId(), false);
											}
										}
										inv.setContents(new ItemStack[] { firstItem, bookItem, null });
									} else
										inv.setContents(new ItemStack[] { null, null, null });
									player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
									isModified.put(ent.getUniqueId(), false);

								}
							}
						}
					}
				}
			}
		}
	}
}
