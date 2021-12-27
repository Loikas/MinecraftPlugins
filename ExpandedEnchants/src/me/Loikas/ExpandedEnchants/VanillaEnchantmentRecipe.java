package me.Loikas.ExpandedEnchants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

public class VanillaEnchantmentRecipe
{	
	public Enchantment ench;
	public int amount;
	public Material mat;
	public EnchantmentTarget enchTarget;
	
	VanillaEnchantmentRecipe(Enchantment _ench, int _amount, Material _mat, EnchantmentTarget _enchTarget)
	{
		ench = _ench;
		amount = _amount;
		mat = _mat;
		enchTarget = _enchTarget;
	}
}
