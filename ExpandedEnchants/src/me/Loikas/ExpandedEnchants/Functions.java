package me.Loikas.ExpandedEnchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Functions
{
	public int GetRandomNumber(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}
	
	public double GetRandomNumber(double min, double max) {
		return Math.random() * (max - min) + min;
	}
	
	public List<Enchantment> GetEnabledEnchants() {
		List<Enchantment> enchs = new ArrayList<>();
		if(Main.getPlugin().getConfig().getBoolean("AntigravityEnabled")) enchs.add(CustomEnchantsManager.ANTIGRAVITY);
		if(Main.getPlugin().getConfig().getBoolean("AutosmeltEnabled")) enchs.add(CustomEnchantsManager.AUTOSMELT);
		if(Main.getPlugin().getConfig().getBoolean("BeheadingEnabled")) enchs.add(CustomEnchantsManager.BEHEADING);
		if(Main.getPlugin().getConfig().getBoolean("DeflectEnabled")) enchs.add(CustomEnchantsManager.DEFLECT);
		if(Main.getPlugin().getConfig().getBoolean("DirectEnabled")) enchs.add(CustomEnchantsManager.DIRECT);
		if(Main.getPlugin().getConfig().getBoolean("EyesofowlEnabled")) enchs.add(CustomEnchantsManager.OWLEYES);
		if(Main.getPlugin().getConfig().getBoolean("ExperienceboostEnabled")) enchs.add(CustomEnchantsManager.EXP_BOOST);
		if(Main.getPlugin().getConfig().getBoolean("FeedingmoduleEnabled")) enchs.add(CustomEnchantsManager.FEEDINGMODULE);
		if(Main.getPlugin().getConfig().getBoolean("HealthboostEnabled")) enchs.add(CustomEnchantsManager.HEALTHBOOST);
		if(Main.getPlugin().getConfig().getBoolean("HeavenslightnessEnabled")) enchs.add(CustomEnchantsManager.HEAVENSLIGHTNESS);
		if(Main.getPlugin().getConfig().getBoolean("IcyEnabled")) enchs.add(CustomEnchantsManager.ICY);
		if(Main.getPlugin().getConfig().getBoolean("LavawalkerEnabled")) enchs.add(CustomEnchantsManager.LAVA_WALKER);
		if(Main.getPlugin().getConfig().getBoolean("LeapingEnabled")) enchs.add(CustomEnchantsManager.LEAPING);
		if(Main.getPlugin().getConfig().getBoolean("LifestealEnabled")) enchs.add(CustomEnchantsManager.LIFESTEAL);
		if(Main.getPlugin().getConfig().getBoolean("LumberjackEnabled")) enchs.add(CustomEnchantsManager.LUMBERJACK);
		if(Main.getPlugin().getConfig().getBoolean("ReplantingEnabled")) enchs.add(CustomEnchantsManager.REPLANTING);
		if(Main.getPlugin().getConfig().getBoolean("ShadowstepEnabled")) enchs.add(CustomEnchantsManager.SHADOWSTEP);
		if(Main.getPlugin().getConfig().getBoolean("StonefistsEnabled")) enchs.add(CustomEnchantsManager.STONEFISTS);
		if(Main.getPlugin().getConfig().getBoolean("ThermalplatingEnabled")) enchs.add(CustomEnchantsManager.THERMALPLATING);
		if(Main.getPlugin().getConfig().getBoolean("TravelerEnabled")) enchs.add(CustomEnchantsManager.TRAVELER);
		if(Main.getPlugin().getConfig().getBoolean("UnbreakableEnabled")) enchs.add(CustomEnchantsManager.NOBREAKABLE);
		if(Main.getPlugin().getConfig().getBoolean("VeinmineEnabled")) enchs.add(CustomEnchantsManager.VEINMINE);
		if(Main.getPlugin().getConfig().getBoolean("WideEnabled")) enchs.add(CustomEnchantsManager.WIDE);
		return enchs;
	}
	
	public int GetSlot(int i) {
		switch(i) {
		case 0:
			return 11;
		case 1:
			return 12;
		case 2:
			return 13;
		case 3:
			return 20;
		case 4:
			return 21;
		case 5:
			return 22;
		case 6:
			return 29;
		case 7:
			return 30;
		case 8:
			return 31;
		}
		return -1;
	}
	public boolean CheckPickaxeTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_PICKAXE: return true;
			case GOLDEN_PICKAXE: return true;
			case WOODEN_PICKAXE: return true;
			case IRON_PICKAXE: return true;
			case STONE_PICKAXE: return true;
			case NETHERITE_PICKAXE: return true;
			default:
				return false;
		}
	}
	
	public  boolean CheckSwordTypes(ItemStack item) {
		switch(item.getType()) {
		case DIAMOND_SWORD: return true;
		case GOLDEN_SWORD: return true;
		case WOODEN_SWORD: return true;
		case IRON_SWORD: return true;
		case STONE_SWORD: return true;
		case NETHERITE_SWORD: return true;
			default: return false;
		}
	}
	
	public  boolean CheckShovelTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_SHOVEL: return true;
			case GOLDEN_SHOVEL: return true;
			case WOODEN_SHOVEL: return true;
			case IRON_SHOVEL: return true;
			case STONE_SHOVEL: return true;
			case NETHERITE_SHOVEL: return true;
			default: return false;
		}
	}
	
	public  boolean CheckHoeTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_HOE: return true;
			case GOLDEN_HOE: return true;
			case WOODEN_HOE: return true;
			case IRON_HOE: return true;
			case STONE_HOE: return true;
			case NETHERITE_HOE: return true;
			default: return false;
		}
	}
	
	public  boolean CheckAxeTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_AXE: return true;
			case GOLDEN_AXE: return true;
			case WOODEN_AXE: return true;
			case IRON_AXE: return true;
			case STONE_AXE: return true;
			case NETHERITE_AXE: return true;
			default: return false;
		}
	}
	
	public  boolean CheckHelmetTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_HELMET: return true;
			case GOLDEN_HELMET: return true;
			case LEATHER_HELMET: return true;
			case IRON_HELMET: return true;
			case TURTLE_HELMET: return true;
			case NETHERITE_HELMET: return true;
			case CHAINMAIL_HELMET: return true;
			default: return false;
		}
	}
	
	public  boolean CheckChestplateTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_CHESTPLATE: return true;
			case GOLDEN_CHESTPLATE: return true;
			case LEATHER_CHESTPLATE: return true;
			case IRON_CHESTPLATE: return true;
			case NETHERITE_CHESTPLATE: return true;
			case CHAINMAIL_CHESTPLATE: return true;
			default: return false;
		}
	}
	
	public  boolean CheckLeggingsTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_LEGGINGS: return true;
			case GOLDEN_LEGGINGS: return true;
			case LEATHER_LEGGINGS: return true;
			case IRON_LEGGINGS: return true;
			case NETHERITE_LEGGINGS: return true;
			case CHAINMAIL_LEGGINGS: return true;
			default: return false;
		}
	}
	
	public  boolean CheckBootsTypes(ItemStack item) {
		switch(item.getType()) {
			case DIAMOND_BOOTS: return true;
			case GOLDEN_BOOTS: return true;
			case LEATHER_BOOTS: return true;
			case IRON_BOOTS: return true;
			case NETHERITE_BOOTS: return true;
			case CHAINMAIL_BOOTS: return true;
			default: return false;
		}
	}
	
	public boolean CheckStrippedLogTypes(Material item) {
		switch(item) {
			case STRIPPED_ACACIA_LOG: return true;
			case STRIPPED_ACACIA_WOOD: return true;
			case STRIPPED_BIRCH_LOG: return true;
			case STRIPPED_BIRCH_WOOD: return true;
			case STRIPPED_CRIMSON_HYPHAE: return true;
			case STRIPPED_CRIMSON_STEM: return true;
			case STRIPPED_DARK_OAK_LOG: return true;
			case STRIPPED_DARK_OAK_WOOD: return true;
			case STRIPPED_JUNGLE_LOG: return true;
			case STRIPPED_JUNGLE_WOOD: return true;
			case STRIPPED_OAK_LOG: return true;
			case STRIPPED_OAK_WOOD: return true;
			case STRIPPED_SPRUCE_LOG: return true;
			case STRIPPED_SPRUCE_WOOD: return true;
			case STRIPPED_WARPED_HYPHAE: return true;
			case STRIPPED_WARPED_STEM: return true;
			default: return false;
		}
	}
	
	public boolean CheckNonStrippedLogTypes(Material item) {
		switch(item) {
			case ACACIA_LOG: return true;
			case ACACIA_WOOD: return true;
			case BIRCH_LOG: return true;
			case BIRCH_WOOD: return true;
			case CRIMSON_HYPHAE: return true;
			case CRIMSON_STEM: return true;
			case DARK_OAK_LOG: return true;
			case DARK_OAK_WOOD: return true;
			case JUNGLE_LOG: return true;
			case JUNGLE_WOOD: return true;
			case OAK_LOG: return true;
			case OAK_WOOD: return true;
			case SPRUCE_LOG: return true;
			case SPRUCE_WOOD: return true;
			case WARPED_HYPHAE: return true;
			case WARPED_STEM: return true;
			default: return false;
		}
	}
	
	public Material GetStrippedVariantWood(Material item) {
		switch(item) {
			case ACACIA_LOG: return Material.STRIPPED_ACACIA_LOG;
			case ACACIA_WOOD: return Material.STRIPPED_ACACIA_WOOD;
			case BIRCH_LOG: return Material.STRIPPED_BIRCH_LOG;
			case BIRCH_WOOD: return Material.STRIPPED_BIRCH_WOOD;
			case CRIMSON_HYPHAE: return Material.STRIPPED_CRIMSON_HYPHAE;
			case CRIMSON_STEM: return Material.STRIPPED_CRIMSON_STEM;
			case DARK_OAK_LOG: return Material.STRIPPED_DARK_OAK_LOG;
			case DARK_OAK_WOOD: return Material.STRIPPED_DARK_OAK_WOOD;
			case JUNGLE_LOG: return Material.STRIPPED_JUNGLE_LOG;
			case JUNGLE_WOOD: return Material.STRIPPED_JUNGLE_WOOD;
			case OAK_LOG: return Material.STRIPPED_OAK_LOG;
			case OAK_WOOD: return Material.STRIPPED_OAK_WOOD;
			case SPRUCE_LOG: return Material.STRIPPED_SPRUCE_LOG;
			case SPRUCE_WOOD: return Material.STRIPPED_SPRUCE_WOOD;
			case WARPED_HYPHAE: return Material.STRIPPED_WARPED_HYPHAE;
			case WARPED_STEM: return Material.STRIPPED_WARPED_STEM;
			default: return null;
		}
	}
	
	public  boolean CheckArmorTypes(ItemStack item) {
		 if(CheckHelmetTypes(item) || CheckChestplateTypes(item) || CheckLeggingsTypes(item) || CheckBootsTypes(item)) return true;
		 return false;
	}
	
	public  boolean CheckToolsTypes(ItemStack item) {
		if(CheckPickaxeTypes(item) || CheckShovelTypes(item) || CheckHoeTypes(item) || CheckAxeTypes(item)) return true;
		return false;
	}
	
	public  String GetNameByLevel(int l, int max) {
		if(l == 1 && max == 1) return "";
		
		switch(l) {
		case 1:
			return "I";
		case 2:
			return "II";
		case 3:
			return "III";
		case 4:
			return "IV";
		case 5:
			return "V";
		case 6:
			return "VI";
		case 7:
			return "VII";
		case 8:
			return "VIII";
		case 9:
			return "IX";
		case 10:
			return "X";
		case 11:
			return "XI";
		case 12:
			return "XII";
		case 13:
			return "XIII";
		case 14:
			return "XIV";
		case 15:
			return "XV";
		case 16:
			return "XVI";
		case 17:
			return "XVII";
		case 18:
			return "XVIII";
		case 19:
			return "XIX";
		case 20:
			return "XX";
		}
		return "";
	}
	
	public  boolean ContainsCustomEnchant(ItemStack item) {
		boolean containsCustom = false;		
		for(Enchantment ench : CustomEnchantsManager.custom_enchants) {
			if(item.getItemMeta().hasEnchant(ench)) containsCustom = true;
		}
		return containsCustom;
	}
	
	public  boolean IsCustomEnchant(Enchantment ench) {
		boolean isCustom = false;
		
		for(Enchantment enchant : CustomEnchantsManager.custom_enchants) {
			if(ench.equals(enchant)) isCustom = true;
		}
		return isCustom;
	}

	public boolean IsLumberjackBlock(Material item) {
		Material[] mats = LumberjackBlocks();
		for(Material mat : mats) {
			if(mat.equals(item)) return true;
		}
		return false;
	}
	
	public boolean IsVeinmineBlock(Material item) {
		Material[] mats = VeinmineBlocks();
		for(Material mat : mats) {
			if(mat.equals(item)) return true;
		}
		return false;
	}
	
	public boolean PlayerContainsFoodItems(Player player) {
		PlayerInventory inv = player.getInventory();
		ItemStack[] content = inv.getContents();
		for(ItemStack item : content) {
			if(item == null) continue;
			for(Material food : FeedingModuleFoods()) 
			{
				if(food == item.getType()) return true;
			}
		}
		return false;
	}
	
	public ItemStack GetFirstFoodItem(Player player) {
		PlayerInventory inv = player.getInventory();
		ItemStack[] content = inv.getContents();
		for(ItemStack item : content) {
			if(item == null) continue;
			for(Material food : FeedingModuleFoods()) {
				if(item.getType() == food) return item;
			}
		}
		return null;
	}
	
	public Material[] FeedingModuleFoods() {
		List<String> configEntries = Main.getPlugin().getConfig().getStringList("FeedingModuleFoods");
		Material[] mats = new Material[configEntries.size()];
		for(int i = 0; i < configEntries.size(); i++){
			Material mat = Material.valueOf(configEntries.get(i));
			mats[i] = mat;
		}
		return mats;
	}
	
	public Material[] VeinmineBlocks() {
		List<String> configEntries = Main.getPlugin().getConfig().getStringList("VeinmineBlocks");
		Material[] mats = new Material[configEntries.size()];
		for(int i = 0; i < configEntries.size(); i++){
			Material mat = Material.valueOf(configEntries.get(i));
			mats[i] = mat;
		}
		return mats;
	}

	public Material[] LumberjackBlocks() {
		List<String> configEntries = Main.getPlugin().getConfig().getStringList("LumberjackBlocks");
		
		Material[] mats = new Material[configEntries.size()];
		
		for(int i = 0; i < configEntries.size(); i++){
			Material mat = Material.valueOf(configEntries.get(i));
			mats[i] = mat;
		}
		
		
		return mats;
	}

	public int GetFoodPoints(Material mat) {
		switch(mat) {
		case APPLE: return 4;
		case BAKED_POTATO: return 5;
		case BEETROOT: return 1;
		case BEETROOT_SOUP: return 6;
		case BREAD: return 5;
		case CARROT: return 3;
		case COOKED_BEEF: return 8;
		case COOKED_CHICKEN: return 6;
		case COOKED_COD: return 5;
		case COOKED_MUTTON: return 6;
		case COOKED_PORKCHOP: return 8;
		case COOKED_RABBIT: return 5;
		case COOKED_SALMON: return 6;
		case COOKIE: return 2;
		case GLOW_BERRIES: return 2;
		case GOLDEN_CARROT: return 6;
		case HONEY_BOTTLE: return 6;
		case MELON_SLICE: return 2;
		case MUSHROOM_STEW: return 6;
		case PUMPKIN_PIE: return 8;
		case POTATO: return 1;
		case RABBIT_STEW: return 10;
		case SWEET_BERRIES: return 2;
		case CHORUS_FRUIT: return 4;
		case DRIED_KELP: return 1;
		case ENCHANTED_GOLDEN_APPLE: return 4;
		case GOLDEN_APPLE: return 4;
		case POISONOUS_POTATO: return 2;
		case PUFFERFISH: return 1;
		case BEEF: return 3;
		case CHICKEN: return 2;
		case COD: return 2;
		case MUTTON: return 2;
		case PORKCHOP: return 3;
		case RABBIT: return 3;
		case SALMON: return 2;
		case ROTTEN_FLESH: return 4;
		case SPIDER_EYE: return 2;
		case SUSPICIOUS_STEW: return 6;
		case TROPICAL_FISH: return 1; 
		}

		return 0;
	}

	public String GetEnchantmentName(Enchantment ench) {
		if(ench.equals(Enchantment.ARROW_DAMAGE)) return "Power";
		if(ench.equals(Enchantment.ARROW_FIRE)) return "Flame";
		if(ench.equals(Enchantment.ARROW_INFINITE)) return "Infinity";
		if(ench.equals(Enchantment.ARROW_KNOCKBACK)) return "Punch";
		if(ench.equals(Enchantment.BINDING_CURSE)) return "Curse of Binding";
		if(ench.equals(Enchantment.CHANNELING)) return "Channeling";
		if(ench.equals(Enchantment.DAMAGE_ALL)) return "Sharpness";
		if(ench.equals(Enchantment.DAMAGE_ARTHROPODS)) return "Bane of Arthropods";
		if(ench.equals(Enchantment.DAMAGE_UNDEAD)) return "Smite";
		if(ench.equals(Enchantment.DEPTH_STRIDER)) return "Depth Strider";
		if(ench.equals(Enchantment.DIG_SPEED)) return "Efficiency";
		if(ench.equals(Enchantment.PROTECTION_ENVIRONMENTAL )) return "Protection";
		if(ench.equals(Enchantment.PROTECTION_FIRE )) return "Fire Protection";
		if(ench.equals(Enchantment.PROTECTION_FALL )) return "Feather Falling";
		if(ench.equals(Enchantment.PROTECTION_EXPLOSIONS )) return "Blast Protection";
		if(ench.equals(Enchantment.PROTECTION_PROJECTILE )) return "Projectile Protection";
		if(ench.equals(Enchantment.OXYGEN )) return "Respiration";
		if(ench.equals(Enchantment.WATER_WORKER )) return "Aqua Affinity";
		if(ench.equals(Enchantment.THORNS )) return "Thorns";
		if(ench.equals(Enchantment.FIRE_ASPECT)) return "Fire Aspect";
		if(ench.equals(Enchantment.LOOT_BONUS_MOBS )) return "Looting";
		if(ench.equals(Enchantment.SILK_TOUCH )) return "Silk Touch";
		if(ench.equals(Enchantment.DURABILITY )) return "Unbreaking";
		if(ench.equals(Enchantment.LOOT_BONUS_BLOCKS )) return "Fortune";
		if(ench.equals(Enchantment.LUCK )) return "Luck";
		if(ench.equals(Enchantment.LURE )) return "Lure";
		if(ench.equals(Enchantment.LOYALTY)) return "Loyalty";
		if(ench.equals(Enchantment.MULTISHOT)) return "Multishot";
		if(ench.equals(Enchantment.MENDING)) return "Mending";
		if(ench.equals(Enchantment.PIERCING)) return "Piercing";
		if(ench.equals(Enchantment.QUICK_CHARGE)) return "Quick Charge";
		if(ench.equals(Enchantment.RIPTIDE)) return "Riptide";
		if(ench.equals(Enchantment.SWEEPING_EDGE)) return "Sweeping Edge";
		if(ench.equals(Enchantment.VANISHING_CURSE)) return "Curse of Vanishing";
		if(ench.equals(Enchantment.FROST_WALKER)) return "Frost Walker";
		if(ench.equals(Enchantment.IMPALING)) return "Impaling";
		if(ench.equals(Enchantment.KNOCKBACK)) return "Knockback";
		if(ench.equals(Enchantment.SOUL_SPEED)) return "Soul Speed";
		return "";
	}
	
}
