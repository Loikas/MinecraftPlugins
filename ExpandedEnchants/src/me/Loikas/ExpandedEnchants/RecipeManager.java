package me.Loikas.ExpandedEnchants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import me.Loikas.ExpandedEnchants.Util.VanillaEnchantmentRecipe;


public class RecipeManager
{
	public VanillaEnchantmentRecipe[] recipes = new VanillaEnchantmentRecipe[] {
			new VanillaEnchantmentRecipe(Enchantment.WATER_WORKER, 2, Material.HEART_OF_THE_SEA, EnchantmentTarget.ARMOR_HEAD),
			new VanillaEnchantmentRecipe(Enchantment.DAMAGE_ARTHROPODS, 36, Material.FERMENTED_SPIDER_EYE, EnchantmentTarget.WEAPON),
			new VanillaEnchantmentRecipe(Enchantment.PROTECTION_EXPLOSIONS, 40, Material.TNT, EnchantmentTarget.ARMOR),
			new VanillaEnchantmentRecipe(Enchantment.CHANNELING, 8, Material.CREEPER_HEAD, EnchantmentTarget.TRIDENT),
			new VanillaEnchantmentRecipe(Enchantment.BINDING_CURSE, 40, Material.SLIME_BLOCK, EnchantmentTarget.WEARABLE),
			new VanillaEnchantmentRecipe(Enchantment.VANISHING_CURSE, 64, Material.COBWEB, EnchantmentTarget.VANISHABLE),
			new VanillaEnchantmentRecipe(Enchantment.DEPTH_STRIDER, 32, Material.PRISMARINE_SHARD, EnchantmentTarget.ARMOR_FEET),
			new VanillaEnchantmentRecipe(Enchantment.DIG_SPEED, 24, Material.REDSTONE_BLOCK, EnchantmentTarget.TOOL),
			new VanillaEnchantmentRecipe(Enchantment.PROTECTION_FALL, 64, Material.FEATHER, EnchantmentTarget.ARMOR_FEET),
			new VanillaEnchantmentRecipe(Enchantment.FIRE_ASPECT, 48, Material.BLAZE_POWDER, EnchantmentTarget.WEAPON),
			new VanillaEnchantmentRecipe(Enchantment.PROTECTION_FIRE, 24, Material.BLAZE_ROD, EnchantmentTarget.ARMOR),
			new VanillaEnchantmentRecipe(Enchantment.ARROW_FIRE, 48, Material.FLINT, EnchantmentTarget.BOW),
			new VanillaEnchantmentRecipe(Enchantment.LOOT_BONUS_BLOCKS, 24, Material.LAPIS_BLOCK, EnchantmentTarget.TOOL),
			new VanillaEnchantmentRecipe(Enchantment.FROST_WALKER, 32, Material.BLUE_ICE, EnchantmentTarget.ARMOR_FEET),
			new VanillaEnchantmentRecipe(Enchantment.IMPALING, 40, Material.PRISMARINE_CRYSTALS, EnchantmentTarget.TRIDENT),
			new VanillaEnchantmentRecipe(Enchantment.ARROW_INFINITE, 1, Material.NETHER_STAR, EnchantmentTarget.BOW),
			new VanillaEnchantmentRecipe(Enchantment.KNOCKBACK, 24, Material.PISTON, EnchantmentTarget.WEAPON),
			new VanillaEnchantmentRecipe(Enchantment.LOOT_BONUS_MOBS, 24, Material.GOLD_BLOCK, EnchantmentTarget.WEAPON),
			new VanillaEnchantmentRecipe(Enchantment.LOYALTY, 30, Material.LEAD, EnchantmentTarget.TRIDENT),
			new VanillaEnchantmentRecipe(Enchantment.LUCK, 24, Material.COPPER_BLOCK, EnchantmentTarget.FISHING_ROD),
			new VanillaEnchantmentRecipe(Enchantment.LURE, 24, Material.BONE_MEAL, EnchantmentTarget.FISHING_ROD),
			new VanillaEnchantmentRecipe(Enchantment.MENDING, 64, Material.GLOWSTONE, EnchantmentTarget.BREAKABLE),
			new VanillaEnchantmentRecipe(Enchantment.MULTISHOT, 64, Material.AMETHYST_SHARD, EnchantmentTarget.CROSSBOW),
			new VanillaEnchantmentRecipe(Enchantment.PIERCING, 64, Material.QUARTZ, EnchantmentTarget.CROSSBOW),
			new VanillaEnchantmentRecipe(Enchantment.ARROW_DAMAGE, 24, Material.QUARTZ_BRICKS, EnchantmentTarget.BOW),
			new VanillaEnchantmentRecipe(Enchantment.PROTECTION_PROJECTILE, 64, Material.ARROW, EnchantmentTarget.ARMOR),
			new VanillaEnchantmentRecipe(Enchantment.PROTECTION_ENVIRONMENTAL, 36, Material.IRON_BLOCK, EnchantmentTarget.ARMOR),
			new VanillaEnchantmentRecipe(Enchantment.ARROW_KNOCKBACK, 24, Material.STICKY_PISTON, EnchantmentTarget.BOW),
			new VanillaEnchantmentRecipe(Enchantment.QUICK_CHARGE, 24, Material.STRING, EnchantmentTarget.CROSSBOW),
			new VanillaEnchantmentRecipe(Enchantment.OXYGEN, 12, Material.NAUTILUS_SHELL, EnchantmentTarget.ARMOR_HEAD),
			new VanillaEnchantmentRecipe(Enchantment.RIPTIDE, 24, Material.PHANTOM_MEMBRANE, EnchantmentTarget.TRIDENT),
			new VanillaEnchantmentRecipe(Enchantment.DAMAGE_ALL, 24, Material.QUARTZ_BLOCK, EnchantmentTarget.WEAPON),
			new VanillaEnchantmentRecipe(Enchantment.SILK_TOUCH, 24, Material.EMERALD_BLOCK, EnchantmentTarget.TOOL),
			new VanillaEnchantmentRecipe(Enchantment.DAMAGE_UNDEAD, 64, Material.PODZOL, EnchantmentTarget.WEAPON),
			new VanillaEnchantmentRecipe(Enchantment.SOUL_SPEED, 64, Material.SOUL_SAND, EnchantmentTarget.ARMOR_FEET),
			new VanillaEnchantmentRecipe(Enchantment.SWEEPING_EDGE, 8, Material.NETHERITE_SCRAP, EnchantmentTarget.WEAPON),
			new VanillaEnchantmentRecipe(Enchantment.THORNS, 64, Material.POINTED_DRIPSTONE, EnchantmentTarget.ARMOR),
			new VanillaEnchantmentRecipe(Enchantment.DURABILITY, 40, Material.OBSIDIAN, EnchantmentTarget.BREAKABLE),
			new VanillaEnchantmentRecipe(Enchantment.SOUL_SPEED, 64, Material.SOUL_SOIL, EnchantmentTarget.ARMOR_FEET),
	};
	
	public Enchantment GetEnchantment(Material mat) {
		Enchantment enchant = null;
		for(VanillaEnchantmentRecipe recipe : recipes) {
			if(recipe.mat == mat) {
				enchant = recipe.ench;
			}
		}
		return enchant;
	}
	
	public VanillaEnchantmentRecipe GetRecipe(Enchantment ench) {
		VanillaEnchantmentRecipe r = null;
		for(VanillaEnchantmentRecipe recipe : recipes) {
			if(recipe.ench.equals(ench)) {
				r = recipe;
			}
		}
		return r;
	}
	
	public int GetAmount(Enchantment ench) {
		for(int i = 0; i < recipes.length; i++) {
			if(recipes[i].ench == ench) return recipes[i].amount;
		}
		return 0;
	}
	
	public Material GetMaterial(Enchantment ench) {
		for(int i = 0; i < recipes.length; i++) {
			if(recipes[i].ench == ench) return recipes[i].mat;
		}
		return null;
	}
	
	public EnchantmentTarget GetTarget(Enchantment ench) {
		for(int i = 0; i < recipes.length; i++) {
			if(recipes[i].ench == ench) return recipes[i].enchTarget;
		}
		return null;
	}
}


