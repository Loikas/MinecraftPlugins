package me.Loikas.ExpandedEnchants.Util;

import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;

public class DoubleItemStack
{
	public ItemStack item1;
	public ItemStack item2;
	
	public DoubleItemStack(ItemStack _item1, ItemStack _item2) {
		item1 = _item1;
		item2 = _item2;
	}
	
	public ArrayList<ItemStack> getItems() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(item1);
		items.add(item2);
		return items;
	}
}
