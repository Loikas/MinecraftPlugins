package me.Loikas.ExpandedEnchants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import me.Loikas.ExpandedEnchants.Enchants.*;

public class CustomEnchantsManager
{
	public static final Enchantment ASSASSIN = new AssassinEnchantment("ee_assassin", "Assassin", 3);
	public static final Enchantment NOBREAKABLE = new UnbreakableEnchantment("ee_unbreakable", "Unbreakable", 1);
	public static final Enchantment DIRECT = new DirectEnchantment("ee_direct", "Direct", 1);
	public static final Enchantment AUTOSMELT = new AutoSmeltEnchantment("ee_autosmelt", "Autosmelt", 1);
	public static final Enchantment ANTIGRAVITY = new AntiGravityEnchantment("ee_antigravity", "Anti-Gravity", 1);
	public static final Enchantment BEHEADING = new BeheadingEnchantment("ee_beheading", "Beheading", 5);
	public static final Enchantment EXP_BOOST = new ExpBoostEnchantment("ee_expboost", "Experience Boost", 5);
	public static final Enchantment LAVA_WALKER = new LavaWalkerEnchantment("ee_lavawalker", "Lava Walker", 1);
	public static final Enchantment TRAVELER = new TravelerEnchantment("ee_traveler", "Traveler", 3);
	public static final Enchantment HEALTHBOOST = new HealthBoostEnchantment("ee_healthboost", "Health Boost", 5);
	public static final Enchantment STONEFISTS = new StoneFistsEnchantment("ee_stonefists", "Stone Fists", 3);
	public static final Enchantment LEAPING = new LeapingEnchantment("ee_leaping", "Leaping", 3);
	public static final Enchantment OWLEYES = new OwlEyesEnchantment("ee_owleyes", "Eyes of Owl", 1);
	public static final Enchantment HEAVENSLIGHTNESS = new HeavensLightnessEnchantment("ee_heavenslightness", "Heaven's Lightness", 1);
	public static final Enchantment THERMALPLATING = new ThermalPlatingEnchantment("ee_thermalplating", "Thermal Plating", 1);
	public static final Enchantment LIFESTEAL = new LifestealEnchantment("ee_lifesteal", "Lifesteal", 3);
	public static final Enchantment ICY = new IcyEnchantment("ee_icy", "Icy", 5);
	public static final Enchantment DEFLECT = new DeflectEnchantment("ee_deflect", "Deflect", 4);
	public static final Enchantment LUMBERJACK = new LumberjackEnchantment("ee_lumberjack", "Lumberjack", 5);
	public static final Enchantment VEINMINE = new VeinmineEnchantment("ee_veinmine", "Vein Miner", 5);
	public static final Enchantment WIDE = new WideEnchantment("ee_wide", "Wide", 1);
	public static final Enchantment FEEDINGMODULE = new FeedingModuleEnchantment("ee_feedingmodule", "Feeding Module", 1);
	public static final Enchantment STEPPING = new SteppingEnchantment("ee_stepping", "Stepping", 1);
	public static final Enchantment GOURMAND = new GourmandEnchantment("ee_gourmand", "Gourmand", 2);
	public static final Enchantment SHADOWSTEP = new ShadowStepEnchantment("ee_shadowstep", "Shadow Step", 5);
	public static final Enchantment SOULBOUND = new SoulboundEnchantment("ee_soulbound", "Soulbound", 1);
	public static final Enchantment REPLANTING = new ReplantingEnchantment("ee_replanting", "Replanting", 1);
	public static final Enchantment ELEMENTALPROTECTION = new ElementalProtectionEnchantment("ee_elemental", "Elemental Protection", 3);
	public static final Enchantment DISARMING = new DisarmingEnchantment("ee_disarming", "Disarming", 4);
	public static final Enchantment DISRUPTION = new DisruptionEnchantment("ee_disruption", "Disruption", 3);
	
	
	public static ArrayList<Enchantment> custom_enchants = new ArrayList<Enchantment>();
	
	public static int GetIndex(Enchantment ench) {
		for(int i = 0; i < custom_enchants.size(); i++) if(custom_enchants.get(i).equals(ench)) return i;
		return -1;
	}
	
	public static void Register() 
	{		
		boolean[] registeredEnchs = new boolean[custom_enchants.size()];
		for(int i = 0; i < custom_enchants.size(); i++) {
			if(Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(custom_enchants.get(i))) {
				registeredEnchs[i] = true;
			}
		}
		
		for(int i = 0; i < custom_enchants.size(); i++) {
			if(!registeredEnchs[i]) {
				RegisterEnchantment(custom_enchants.get(i));
			}
		}
			
	}
	
	@SuppressWarnings("deprecation")
	public static void RegisterEnchantment(Enchantment enchantment) 
	{
		boolean registered = true;
		try 
		{
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} 
		catch (Exception e)
		{
			registered = false;
			e.printStackTrace();
		}
		if(registered) Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "ExpandedEnchants" + ChatColor.WHITE + ": '"+ enchantment.getName() + "' was succesfully registered!");
			
	}

	 
}
