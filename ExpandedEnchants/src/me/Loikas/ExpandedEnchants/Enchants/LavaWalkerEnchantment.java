package me.Loikas.ExpandedEnchants.Enchants;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import me.Loikas.ExpandedEnchants.Util.Functions;

public class LavaWalkerEnchantment extends Enchantment
{
	
	public final String name;
	public final int maxLvl;

	public LavaWalkerEnchantment(String namespace, String name, int lvl)
	{
		super(NamespacedKey.minecraft(namespace));
		this.name = name;
		this.maxLvl = lvl;
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0)
	{
		if(Functions.CheckBootsTypes(arg0)) return true;
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0)
	{
		if(arg0.equals(Enchantment.DEPTH_STRIDER)) return true;
		if(arg0.equals(Enchantment.FROST_WALKER)) return true;
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget()
	{
		return null;
	}

	@Override
	public int getMaxLevel()
	{
		return maxLvl;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public int getStartLevel()
	{
		return 0;
	}

	@Override
	public boolean isCursed()
	{
		return false;
	}

	@Override
	public boolean isTreasure()
	{
		return false;
	}
	
	
	
}
