package me.Loikas.ExpandedEnchants.Enchants;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import me.Loikas.ExpandedEnchants.CustomEnchantsManager;
import me.Loikas.ExpandedEnchants.EventsClass;

public class WideEnchantment extends Enchantment
{
	
	public final String name;
	public final int maxLvl;

	public WideEnchantment(String namespace, String name, int lvl)
	{
		super(NamespacedKey.minecraft(namespace));
		this.name = name;
		this.maxLvl = lvl;
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0)
	{
		if(EventsClass.functions.CheckAxeTypes(arg0)) return true;
		if(EventsClass.functions.CheckPickaxeTypes(arg0)) return true;
		if(EventsClass.functions.CheckShovelTypes(arg0)) return true;
		if(EventsClass.functions.CheckHoeTypes(arg0)) return true;
		if(arg0.getType().equals(Material.SHEARS)) return true;
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0)
	{
		if(arg0.equals(CustomEnchantsManager.LUMBERJACK)) return true;
		if(arg0.equals(CustomEnchantsManager.VEINMINE)) return true;
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
