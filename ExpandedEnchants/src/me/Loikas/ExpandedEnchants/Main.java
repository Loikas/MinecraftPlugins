package me.Loikas.ExpandedEnchants;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import me.Loikas.ExpandedEnchants.Commands.*;
import me.Loikas.ExpandedEnchants.Inventories.InventoryManager;

public class Main extends JavaPlugin
{
	
	private static Main plugin;
	
	public static EventsClass eventsClass;
	public static RecipeManager recipeManager;
	public static ItemManager itemManager;
	public static InventoryManager inventoryManager;
	
	//FileConfiguration config = this.getConfig();
	
	public static ArrayList<CustomEnchantmentRecipe> customRecipes = new ArrayList<CustomEnchantmentRecipe>();
	
	@Override
	public void onEnable() 
	{
		plugin = this;
		
		this.saveDefaultConfig();
		
		itemManager = new ItemManager();
		
		AddAllCustomEnchants();
		RegisterRecipes();
		
		CustomEnchantsManager.Register();
		
		eventsClass = new EventsClass();
		recipeManager = new RecipeManager(); 
		inventoryManager = new InventoryManager();
		
		this.getServer().getPluginManager().registerEvents(eventsClass, this);
		this.getServer().getPluginManager().registerEvents(inventoryManager, this);
		
		this.getCommand("expandedenchants").setTabCompleter(new CustomEnchantsTabCompleter());
		this.getCommand("expandedenchants").setExecutor(new CommandHandler());
		
		new BukkitRunnable() {
			@Override
			public void run() {
				eventsClass.ConstantCheckMethods();
			}
		}.runTaskTimer(this, 0, 20);
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	@Override
	public void onDisable() 
	{
		eventsClass = null;
		itemManager = null;
		recipeManager = null;
	}
	
	public static CustomEnchantmentRecipe GetCustomEnchantmentRecipe(Enchantment ench) {
		for(CustomEnchantmentRecipe e : customRecipes) {
			if(e.recipe.getResult().getItemMeta().getEnchants().keySet().toArray()[0].equals(ench)) return e;
		}
		return null;
	}
	
	public void AddAllCustomEnchants() {
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.ANTIGRAVITY);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.AUTOSMELT);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.BEHEADING);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.DEFLECT);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.DIRECT);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.ELEMENTALPROTECTION);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.OWLEYES);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.EXP_BOOST);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.FEEDINGMODULE);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.GOURMAND);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.HEALTHBOOST);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.HEAVENSLIGHTNESS);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.ICY);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.LAVA_WALKER);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.LEAPING);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.LIFESTEAL);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.LUMBERJACK);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.REPLANTING);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.SHADOWSTEP);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.SOULBOUND);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.STEPPING);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.STONEFISTS);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.THERMALPLATING);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.TRAVELER);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.NOBREAKABLE);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.VEINMINE);
		CustomEnchantsManager.custom_enchants.add(CustomEnchantsManager.WIDE);		
	}
	
	public void RegisterRecipes() {
			if(getPlugin().getConfig().getBoolean("LeatherSmeltEnabled")){
				FurnaceRecipe leathersmelt = new FurnaceRecipe(NamespacedKey.minecraft("ee_recipe_leather_smelt"), new ItemStack(Material.LEATHER, 1), Material.ROTTEN_FLESH, 1.0f, 5 * 20);
				Bukkit.getServer().addRecipe(leathersmelt);
				SmokingRecipe leathersmoke = new SmokingRecipe(NamespacedKey.minecraft("ee_recipe_leather_smoke"), new ItemStack(Material.LEATHER, 1), Material.ROTTEN_FLESH, 1.1f, 50);
				Bukkit.getServer().addRecipe(leathersmoke);
			}
		if(getPlugin().getConfig().getBoolean("AntigravityEnabled"))
		{
			ShapedRecipe AntiGravity = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_antigravity"), itemManager.CreateCustomBook(CustomEnchantsManager.ANTIGRAVITY, 1));
			AntiGravity.shape(" H ", "FBS", " L ");
			AntiGravity.setIngredient('H', Material.DRAGON_HEAD);
			AntiGravity.setIngredient('F', Material.FEATHER);
			AntiGravity.setIngredient('B', Material.BOOK);
			AntiGravity.setIngredient('S', Material.AMETHYST_SHARD);
			AntiGravity.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(AntiGravity, new Integer[] { 0, 1, 0, 48, 1, 36, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(AntiGravity);	
		}
		if(getPlugin().getConfig().getBoolean("SteppingEnabled"))
		{
			ShapedRecipe AntiGravity = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_stepping"), itemManager.CreateCustomBook(CustomEnchantsManager.STEPPING, 1));
			AntiGravity.shape(" H ", "FBS", " L ");
			AntiGravity.setIngredient('H', Material.DIAMOND_HORSE_ARMOR);
			AntiGravity.setIngredient('F', Material.QUARTZ_STAIRS);
			AntiGravity.setIngredient('B', Material.BOOK);
			AntiGravity.setIngredient('S', Material.PRISMARINE_STAIRS);
			AntiGravity.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(AntiGravity, new Integer[] { 0, 1, 0, 16, 1, 16, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(AntiGravity);	
		}
		if(getPlugin().getConfig().getBoolean("GourmandEnabled"))
		{
			ShapedRecipe AntiGravity = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_gourmand"), itemManager.CreateCustomBook(CustomEnchantsManager.GOURMAND, 1));
			AntiGravity.shape(" H ", "FBS", " L ");
			AntiGravity.setIngredient('H', Material.ENCHANTED_GOLDEN_APPLE);
			AntiGravity.setIngredient('F', Material.COOKED_BEEF);
			AntiGravity.setIngredient('B', Material.BOOK);
			AntiGravity.setIngredient('S', Material.HONEY_BOTTLE);
			AntiGravity.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(AntiGravity, new Integer[] { 0, 1, 0, 64, 1, 16, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(AntiGravity);	
		}
		if(getPlugin().getConfig().getBoolean("SoulboundEnabled"))
		{
			ShapedRecipe AntiGravity = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_soulbound"), itemManager.CreateCustomBook(CustomEnchantsManager.SOULBOUND, 1));
			AntiGravity.shape("XHX", "FBS", "XLX");
			AntiGravity.setIngredient('H', Material.TOTEM_OF_UNDYING);
			AntiGravity.setIngredient('F', Material.CHAIN);
			AntiGravity.setIngredient('B', Material.BOOK);
			AntiGravity.setIngredient('S', Material.NAME_TAG);
			AntiGravity.setIngredient('L', Material.LAPIS_LAZULI);
			AntiGravity.setIngredient('X', Material.SHULKER_SHELL);
			customRecipes.add(new CustomEnchantmentRecipe(AntiGravity, new Integer[] { 6, 1, 6, 32, 1, 16, 6, 16, 6 }));
			Bukkit.getServer().addRecipe(AntiGravity);	
		}
		if(getPlugin().getConfig().getBoolean("AutosmeltEnabled"))
		{
			ShapedRecipe AutoSmelt = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_autosmelt"), itemManager.CreateCustomBook(CustomEnchantsManager.AUTOSMELT, 1));
			AutoSmelt.shape(" M ", "FAB", " L ");
			AutoSmelt.setIngredient('M', Material.MAGMA_BLOCK);
			AutoSmelt.setIngredient('F', Material.FLINT_AND_STEEL);
			AutoSmelt.setIngredient('A', Material.BOOK);
			AutoSmelt.setIngredient('B', Material.LAVA_BUCKET);
			AutoSmelt.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(AutoSmelt, new Integer[] { 0, 40, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(AutoSmelt);
		}
		if(getPlugin().getConfig().getBoolean("BeheadingEnabled"))
		{
			ShapedRecipe Beheading = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_beheading"), itemManager.CreateCustomBook(CustomEnchantsManager.BEHEADING, 1));
			Beheading.shape(" M ", "FAF", " L ");
			Beheading.setIngredient('M', Material.WITHER_SKELETON_SKULL);
			Beheading.setIngredient('F', Material.DIAMOND_AXE);
			Beheading.setIngredient('A', Material.BOOK);
			Beheading.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Beheading, new Integer[] { 0, 2, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Beheading);
		}
		if(getPlugin().getConfig().getBoolean("DeflectEnabled"))
		{
			ShapedRecipe Deflect = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_deflect"), itemManager.CreateCustomBook(CustomEnchantsManager.DEFLECT, 1));
			Deflect.shape(" M ", "FAB", " L ");
			Deflect.setIngredient('M', Material.SCUTE);
			Deflect.setIngredient('F', Material.SHIELD);
			Deflect.setIngredient('A', Material.BOOK);
			Deflect.setIngredient('B', Material.SHIELD);
			Deflect.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Deflect, new Integer[] { 0, 40, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Deflect);
		}
		if(getPlugin().getConfig().getBoolean("DirectEnabled"))
		{
			ShapedRecipe Direct = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_direct"), itemManager.CreateCustomBook(CustomEnchantsManager.DIRECT, 1));
			Direct.shape(" M ", "FAB", " L ");
			Direct.setIngredient('M', Material.EMERALD);
			Direct.setIngredient('F', Material.CHORUS_FRUIT);
			Direct.setIngredient('A', Material.BOOK);
			Direct.setIngredient('B', Material.ENDER_PEARL);
			Direct.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Direct, new Integer[] { 0, 20, 0, 32, 1, 16, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Direct);
		}
		if(getPlugin().getConfig().getBoolean("ElementalprotectionEnabled"))
		{
			ShapedRecipe Elemental = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_elemental"), itemManager.CreateCustomBook(CustomEnchantsManager.ELEMENTALPROTECTION, 1));
			Elemental.shape(" M ", "FAB", " L ");
			Elemental.setIngredient('M', Material.POTION);
			Elemental.setIngredient('F', Material.END_CRYSTAL);
			Elemental.setIngredient('A', Material.BOOK);
			Elemental.setIngredient('B', Material.WITHER_ROSE);
			Elemental.setIngredient('L', Material.NETHER_STAR);
			customRecipes.add(new CustomEnchantmentRecipe(Elemental, new Integer[] { 0, 1, 0, 5, 1, 5, 0, 1, 0 }));
			Bukkit.getServer().addRecipe(Elemental);
		}
		if(getPlugin().getConfig().getBoolean("ExperienceboostEnabled"))
		{
			ShapedRecipe ExpBoost = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_expboost"), itemManager.CreateCustomBook(CustomEnchantsManager.EXP_BOOST, 1));
			ExpBoost.shape(" M ", "FAB", " L ");
			ExpBoost.setIngredient('M', Material.BOOKSHELF);
			ExpBoost.setIngredient('F', Material.EXPERIENCE_BOTTLE);
			ExpBoost.setIngredient('A', Material.BOOK);
			ExpBoost.setIngredient('B', Material.EXPERIENCE_BOTTLE);
			ExpBoost.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(ExpBoost, new Integer[] { 0, 36, 0, 64, 1, 64, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(ExpBoost);
		}
		if(getPlugin().getConfig().getBoolean("EyesofowlEnabled"))
		{
			ShapedRecipe OwlEyes = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_owleyes"), itemManager.CreateCustomBook(CustomEnchantsManager.OWLEYES, 1));
			OwlEyes.shape(" M ", "FAB", " L ");
			OwlEyes.setIngredient('M', Material.POTION);
			OwlEyes.setIngredient('F', Material.ENDER_EYE);
			OwlEyes.setIngredient('A', Material.BOOK);
			OwlEyes.setIngredient('B', Material.ENDER_EYE);
			OwlEyes.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(OwlEyes, new Integer[] { 0, 1, 0, 25, 1, 25, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(OwlEyes);
		}
		if(getPlugin().getConfig().getBoolean("HealthboostEnabled"))
		{
			ShapedRecipe HealthBoost = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_healthboost"), itemManager.CreateCustomBook(CustomEnchantsManager.HEALTHBOOST, 1));
			HealthBoost.shape(" M ", "FAB", " L ");
			HealthBoost.setIngredient('M', Material.POTION);
			HealthBoost.setIngredient('F', Material.ENCHANTED_GOLDEN_APPLE);
			HealthBoost.setIngredient('A', Material.BOOK);
			HealthBoost.setIngredient('B', Material.CAKE);
			HealthBoost.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(HealthBoost, new Integer[] { 0, 1, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(HealthBoost);
		}
		if(getPlugin().getConfig().getBoolean("HeavenslightnessEnabled"))
		{
			ShapedRecipe HeavensLightness = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_heavenslightness"), itemManager.CreateCustomBook(CustomEnchantsManager.HEAVENSLIGHTNESS, 1));
			HeavensLightness.shape(" M ", "FAB", " L ");
			HeavensLightness.setIngredient('M', Material.POTION);
			HeavensLightness.setIngredient('F', Material.FEATHER);
			HeavensLightness.setIngredient('A', Material.BOOK);
			HeavensLightness.setIngredient('B', Material.PHANTOM_MEMBRANE);
			HeavensLightness.setIngredient('L', Material.NETHER_STAR);
			customRecipes.add(new CustomEnchantmentRecipe(HeavensLightness, new Integer[] { 0, 1, 0, 40, 1, 40, 0, 1, 0 }));
			Bukkit.getServer().addRecipe(HeavensLightness);
		}
		if(getPlugin().getConfig().getBoolean("IcyEnabled"))
		{
			ShapedRecipe Icy = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_icy"), itemManager.CreateCustomBook(CustomEnchantsManager.ICY, 1));
			Icy.shape(" M ", "FAB", " L ");
			Icy.setIngredient('M', Material.SPLASH_POTION);
			Icy.setIngredient('F', Material.BLUE_ICE);
			Icy.setIngredient('A', Material.BOOK);
			Icy.setIngredient('B', Material.END_CRYSTAL);
			Icy.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Icy, new Integer[] { 0, 1, 0, 40, 1, 4, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Icy);
		}
		if(getPlugin().getConfig().getBoolean("LavawalkerEnabled"))
		{
			ShapedRecipe LavaWalker = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lavawalker"), itemManager.CreateCustomBook(CustomEnchantsManager.LAVA_WALKER, 1));
			LavaWalker.shape(" M ", "FAB", " L ");
			LavaWalker.setIngredient('M', Material.NETHERITE_SCRAP);
			LavaWalker.setIngredient('F', Material.SMOOTH_BASALT);
			LavaWalker.setIngredient('A', Material.BOOK);
			LavaWalker.setIngredient('B', Material.SMOOTH_BASALT);
			LavaWalker.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(LavaWalker, new Integer[] { 0, 6, 0, 64, 1, 64, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(LavaWalker);
		}
		if(getPlugin().getConfig().getBoolean("LeapingEnabled"))
		{
			ShapedRecipe Leaping = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_leaping"), itemManager.CreateCustomBook(CustomEnchantsManager.LEAPING, 1));
			Leaping.shape(" M ", "FAB", " L ");
			Leaping.setIngredient('M', Material.POTION);
			Leaping.setIngredient('F', Material.GLOWSTONE);
			Leaping.setIngredient('A', Material.BOOK);
			Leaping.setIngredient('B', Material.RABBIT_FOOT);
			Leaping.setIngredient('L', Material.NETHER_STAR);
			customRecipes.add(new CustomEnchantmentRecipe(Leaping, new Integer[] { 0, 1, 0, 24, 1, 12, 0, 1, 0 }));
			Bukkit.getServer().addRecipe(Leaping);
		}
		if(getPlugin().getConfig().getBoolean("LifestealEnabled"))
		{
			ShapedRecipe Lifesteal = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lifesteal"), itemManager.CreateCustomBook(CustomEnchantsManager.LIFESTEAL, 1));
			Lifesteal.shape(" M ", "FAB", " L ");
			Lifesteal.setIngredient('M', Material.LINGERING_POTION);
			Lifesteal.setIngredient('F', Material.TOTEM_OF_UNDYING);
			Lifesteal.setIngredient('A', Material.BOOK);
			Lifesteal.setIngredient('B', Material.TOTEM_OF_UNDYING);
			Lifesteal.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Lifesteal, new Integer[] { 0, 1, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Lifesteal);
		}
		if(getPlugin().getConfig().getBoolean("StonefistsEnabled"))
		{
			ShapedRecipe StoneFists = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_stonefists"), itemManager.CreateCustomBook(CustomEnchantsManager.STONEFISTS, 1));
			StoneFists.shape(" M ", "FAB", " L ");
			StoneFists.setIngredient('M', Material.POTION);
			StoneFists.setIngredient('F', Material.SMOOTH_STONE);
			StoneFists.setIngredient('A', Material.BOOK);
			StoneFists.setIngredient('B', Material.SMOOTH_STONE);
			StoneFists.setIngredient('L', Material.NETHER_STAR);
			customRecipes.add(new CustomEnchantmentRecipe(StoneFists, new Integer[] { 0, 1, 0, 64, 1, 64, 0, 1, 0 }));
			Bukkit.getServer().addRecipe(StoneFists);
		}
		if(getPlugin().getConfig().getBoolean("ThermalplatingEnabled"))
		{
			ShapedRecipe ThermalPlating = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_thermalplating"), itemManager.CreateCustomBook(CustomEnchantsManager.THERMALPLATING, 1));
			ThermalPlating.shape(" M ", "FAB", " L ");
			ThermalPlating.setIngredient('M', Material.POTION);
			ThermalPlating.setIngredient('F', Material.NETHERITE_SCRAP);
			ThermalPlating.setIngredient('A', Material.BOOK);
			ThermalPlating.setIngredient('B', Material.MAGMA_CREAM);
			ThermalPlating.setIngredient('L', Material.NETHER_STAR);
			customRecipes.add(new CustomEnchantmentRecipe(ThermalPlating, new Integer[] { 0, 1, 0, 5, 1, 50, 0, 1, 0 }));
			Bukkit.getServer().addRecipe(ThermalPlating);
		}
		if(getPlugin().getConfig().getBoolean("TravelerEnabled"))
		{
			ShapedRecipe Traveler = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_traveler"), itemManager.CreateCustomBook(CustomEnchantsManager.TRAVELER, 1));
			Traveler.shape(" M ", "FAB", " L ");
			Traveler.setIngredient('M', Material.POTION);
			Traveler.setIngredient('F', Material.REDSTONE_BLOCK);
			Traveler.setIngredient('A', Material.BOOK);
			Traveler.setIngredient('B', Material.GLOWSTONE);
			Traveler.setIngredient('L', Material.NETHER_STAR);
			customRecipes.add(new CustomEnchantmentRecipe(Traveler, new Integer[] { 0, 1, 0, 24, 1, 24, 0, 1, 0 }));
			Bukkit.getServer().addRecipe(Traveler);
		}
		if(getPlugin().getConfig().getBoolean("UnbreakableEnabled"))
		{
			ShapedRecipe Unbreakable = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_unbreakable"), itemManager.CreateCustomBook(CustomEnchantsManager.NOBREAKABLE, 1));
			Unbreakable.shape(" M ", "FAB", " L ");
			Unbreakable.setIngredient('M', Material.ENCHANTED_BOOK);
			Unbreakable.setIngredient('F', Material.OBSIDIAN);
			Unbreakable.setIngredient('A', Material.BOOK);
			Unbreakable.setIngredient('B', Material.CRYING_OBSIDIAN);
			Unbreakable.setIngredient('L', Material.NETHER_STAR);
			customRecipes.add(new CustomEnchantmentRecipe(Unbreakable, new Integer[] { 0, 1, 0, 40, 1, 40, 0, 1, 0 }));
			Bukkit.getServer().addRecipe(Unbreakable);
		}
		if(getPlugin().getConfig().getBoolean("VeinmineEnabled"))
		{
			ShapedRecipe Veinmine = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_veinmine"), itemManager.CreateCustomBook(CustomEnchantsManager.VEINMINE, 1));
			Veinmine.shape("RMS", "FAB", "NLG");
			Veinmine.setIngredient('R', Material.COAL);
			Veinmine.setIngredient('M', Material.AMETHYST_SHARD);
			Veinmine.setIngredient('S', Material.COPPER_INGOT);
			Veinmine.setIngredient('F', Material.NETHERITE_SCRAP);
			Veinmine.setIngredient('A', Material.BOOK);
			Veinmine.setIngredient('B', Material.IRON_INGOT);
			Veinmine.setIngredient('N', Material.DIAMOND);
			Veinmine.setIngredient('L', Material.EMERALD);
			Veinmine.setIngredient('G', Material.GOLD_INGOT);
			customRecipes.add(new CustomEnchantmentRecipe(Veinmine, new Integer[] { 64, 48, 32, 1, 1, 24, 4, 8, 16 }));
			Bukkit.getServer().addRecipe(Veinmine);
		}
		if(getPlugin().getConfig().getBoolean("WideEnabled"))
		{
			ShapedRecipe Wide = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_wide"), itemManager.CreateCustomBook(CustomEnchantsManager.WIDE, 1));
			Wide.shape("RMS", "AAA", "NLG");
			Wide.setIngredient('R', Material.STONE);
			Wide.setIngredient('M', Material.DRAGON_BREATH);
			Wide.setIngredient('S', Material.DEEPSLATE);
			Wide.setIngredient('A', Material.BOOK);
			Wide.setIngredient('N', Material.NETHERRACK);
			Wide.setIngredient('L', Material.LAPIS_LAZULI);
			Wide.setIngredient('G', Material.END_STONE);
			customRecipes.add(new CustomEnchantmentRecipe(Wide, new Integer[] { 64, 30, 64, 1, 1, 1, 64, 16, 64 }));
			Bukkit.getServer().addRecipe(Wide);
		}
		if(getPlugin().getConfig().getBoolean("LumberjackEnabled"))
		{
			ShapedRecipe Lumberjack = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lumberjack1"), itemManager.CreateCustomBook(CustomEnchantsManager.LUMBERJACK, 1));
			Lumberjack.shape(" M ", "FAB", " L ");
			Lumberjack.setIngredient('M', Material.OAK_LOG);
			Lumberjack.setIngredient('F', Material.DIAMOND_AXE);
			Lumberjack.setIngredient('A', Material.BOOK);
			Lumberjack.setIngredient('B', Material.SHEARS);
			Lumberjack.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Lumberjack, new Integer[] { 0, 64, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Lumberjack);
		}
		if(getPlugin().getConfig().getBoolean("LumberjackEnabled"))
		{
			ShapedRecipe Lumberjack = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lumberjack2"), itemManager.CreateCustomBook(CustomEnchantsManager.LUMBERJACK, 1));
			Lumberjack.shape(" M ", "FAB", " L ");
			Lumberjack.setIngredient('M', Material.SPRUCE_LOG);
			Lumberjack.setIngredient('F', Material.DIAMOND_AXE);
			Lumberjack.setIngredient('A', Material.BOOK);
			Lumberjack.setIngredient('B', Material.SHEARS);
			Lumberjack.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Lumberjack, new Integer[] { 0, 64, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Lumberjack);
		}
		if(getPlugin().getConfig().getBoolean("LumberjackEnabled"))
		{
			ShapedRecipe Lumberjack = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lumberjack3"), itemManager.CreateCustomBook(CustomEnchantsManager.LUMBERJACK, 1));
			Lumberjack.shape(" M ", "FAB", " L ");
			Lumberjack.setIngredient('M', Material.ACACIA_LOG);
			Lumberjack.setIngredient('F', Material.DIAMOND_AXE);
			Lumberjack.setIngredient('A', Material.BOOK);
			Lumberjack.setIngredient('B', Material.SHEARS);
			Lumberjack.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Lumberjack, new Integer[] { 0, 64, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Lumberjack);
		}
		if(getPlugin().getConfig().getBoolean("LumberjackEnabled"))
		{
			ShapedRecipe Lumberjack = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lumberjack4"), itemManager.CreateCustomBook(CustomEnchantsManager.LUMBERJACK, 1));
			Lumberjack.shape(" M ", "FAB", " L ");
			Lumberjack.setIngredient('M', Material.BIRCH_LOG);
			Lumberjack.setIngredient('F', Material.DIAMOND_AXE);
			Lumberjack.setIngredient('A', Material.BOOK);
			Lumberjack.setIngredient('B', Material.SHEARS);
			Lumberjack.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Lumberjack, new Integer[] { 0, 64, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Lumberjack);
		}
		if(getPlugin().getConfig().getBoolean("LumberjackEnabled"))
		{
			ShapedRecipe Lumberjack = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lumberjack5"), itemManager.CreateCustomBook(CustomEnchantsManager.LUMBERJACK, 1));
			Lumberjack.shape(" M ", "FAB", " L ");
			Lumberjack.setIngredient('M', Material.DARK_OAK_LOG);
			Lumberjack.setIngredient('F', Material.DIAMOND_AXE);
			Lumberjack.setIngredient('A', Material.BOOK);
			Lumberjack.setIngredient('B', Material.SHEARS);
			Lumberjack.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Lumberjack, new Integer[] { 0, 64, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Lumberjack);
		}
		if(getPlugin().getConfig().getBoolean("LumberjackEnabled"))
		{
			ShapedRecipe Lumberjack = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_lumberjack6"), itemManager.CreateCustomBook(CustomEnchantsManager.LUMBERJACK, 1));
			Lumberjack.shape(" M ", "FAB", " L ");
			Lumberjack.setIngredient('M', Material.JUNGLE_LOG);
			Lumberjack.setIngredient('F', Material.DIAMOND_AXE);
			Lumberjack.setIngredient('A', Material.BOOK);
			Lumberjack.setIngredient('B', Material.SHEARS);
			Lumberjack.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Lumberjack, new Integer[] { 0, 64, 0, 1, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Lumberjack);
		}
		if(getPlugin().getConfig().getBoolean("FeedingmoduleEnabled"))
		{
			ShapedRecipe FeedingModule = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_feedingmodule"), itemManager.CreateCustomBook(CustomEnchantsManager.FEEDINGMODULE, 1));
			FeedingModule.shape(" M ", "FAB", " L ");
			FeedingModule.setIngredient('M', Material.BAMBOO);
			FeedingModule.setIngredient('F', Material.BOWL);
			FeedingModule.setIngredient('A', Material.BOOK);
			FeedingModule.setIngredient('B', Material.GLASS_BOTTLE);
			FeedingModule.setIngredient('L', Material.SLIME_BALL);
			customRecipes.add(new CustomEnchantmentRecipe(FeedingModule, new Integer[] { 0, 30, 0, 30, 1, 30, 0, 30, 0 }));
			Bukkit.getServer().addRecipe(FeedingModule);
		}
		if(getPlugin().getConfig().getBoolean("ReplantingEnabled"))
		{
			ShapedRecipe Replanting = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_replanting"), itemManager.CreateCustomBook(CustomEnchantsManager.REPLANTING, 1));
			Replanting.shape(" M ", "FAB", " L ");
			Replanting.setIngredient('M', Material.DIAMOND_HOE);
			Replanting.setIngredient('F', Material.POTATO);
			Replanting.setIngredient('A', Material.BOOK);
			Replanting.setIngredient('B', Material.CARROT);
			Replanting.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(Replanting, new Integer[] { 0, 1, 0, 32, 1, 32, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(Replanting);
		}
		if(getPlugin().getConfig().getBoolean("ShadowstepEnabled"))
		{
			ShapedRecipe ShadowStep = new ShapedRecipe(NamespacedKey.minecraft("ee_recipe_shadowstep"), itemManager.CreateCustomBook(CustomEnchantsManager.SHADOWSTEP, 1));
			ShadowStep.shape(" M ", "FAB", " L ");
			ShadowStep.setIngredient('M', Material.WHITE_WOOL);
			ShadowStep.setIngredient('F', Material.GRASS);
			ShadowStep.setIngredient('A', Material.BOOK);
			ShadowStep.setIngredient('B', Material.SPYGLASS);
			ShadowStep.setIngredient('L', Material.LAPIS_LAZULI);
			customRecipes.add(new CustomEnchantmentRecipe(ShadowStep, new Integer[] { 0, 32, 0, 32, 1, 1, 0, 16, 0 }));
			Bukkit.getServer().addRecipe(ShadowStep);
		}
	}
}
