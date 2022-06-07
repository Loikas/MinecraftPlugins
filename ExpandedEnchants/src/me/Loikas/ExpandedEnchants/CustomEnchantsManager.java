package me.Loikas.ExpandedEnchants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import me.Loikas.ExpandedEnchants.Enchants.*;
import me.Loikas.ExpandedEnchants.Util.LanguageManager;

public class CustomEnchantsManager
{
	public static final Enchantment ANTIGRAVITY = new AntiGravityEnchantment("ee_antigravity", LanguageManager.instance.GetTranslatedValue("antigravity-name"), 1);
	public static final Enchantment ASSASSIN = new AssassinEnchantment("ee_assassin", LanguageManager.instance.GetTranslatedValue("assassin-name"), 3);
	public static final Enchantment AUTOSMELT = new AutoSmeltEnchantment("ee_autosmelt", LanguageManager.instance.GetTranslatedValue("autosmelt-name"), 1);
	public static final Enchantment BEHEADING = new BeheadingEnchantment("ee_beheading", LanguageManager.instance.GetTranslatedValue("beheading-name"), 5);
	public static final Enchantment DEFLECT = new DeflectEnchantment("ee_deflect", LanguageManager.instance.GetTranslatedValue("deflect-name"), 4);
	public static final Enchantment DIRECT = new DirectEnchantment("ee_direct", LanguageManager.instance.GetTranslatedValue("direct-name"), 1);
	public static final Enchantment DISARMING = new DisarmingEnchantment("ee_disarming", LanguageManager.instance.GetTranslatedValue("disarming-name"), 4);
	public static final Enchantment DISRUPTION = new DisruptionEnchantment("ee_disruption", LanguageManager.instance.GetTranslatedValue("disruption-name"), 3);
	public static final Enchantment ELEMENTALPROTECTION = new ElementalProtectionEnchantment("ee_elemental", LanguageManager.instance.GetTranslatedValue("elementalprotection-name"), 3);
	public static final Enchantment OWLEYES = new OwlEyesEnchantment("ee_owleyes", LanguageManager.instance.GetTranslatedValue("eyesofowl-name"), 1);
	public static final Enchantment EXP_BOOST = new ExpBoostEnchantment("ee_expboost", LanguageManager.instance.GetTranslatedValue("experienceboost-name"), 5);
	public static final Enchantment FEEDINGMODULE = new FeedingModuleEnchantment("ee_feedingmodule", LanguageManager.instance.GetTranslatedValue("feedingmodule-name"), 1);
	public static final Enchantment FLAMINGFALL = new FlamingFallEnchantment("ee_flamingfall", LanguageManager.instance.GetTranslatedValue("flamingfall-name"), 1);
	public static final Enchantment GOURMAND = new GourmandEnchantment("ee_gourmand", LanguageManager.instance.GetTranslatedValue("gourmand-name"), 2);
	public static final Enchantment HEALTHBOOST = new HealthBoostEnchantment("ee_healthboost", LanguageManager.instance.GetTranslatedValue("healthboost-name"), 5);
	public static final Enchantment HEAVENSLIGHTNESS = new HeavensLightnessEnchantment("ee_heavenslightness", LanguageManager.instance.GetTranslatedValue("heavenslightness-name"), 1);
	public static final Enchantment ICY = new IcyEnchantment("ee_icy", LanguageManager.instance.GetTranslatedValue("icy-name"), 5);
	public static final Enchantment LAVA_WALKER = new LavaWalkerEnchantment("ee_lavawalker", LanguageManager.instance.GetTranslatedValue("lavawalker-name"), 1);
	public static final Enchantment LEAPING = new LeapingEnchantment("ee_leaping", LanguageManager.instance.GetTranslatedValue("leaping-name"), 3);
	public static final Enchantment LIFESTEAL = new LifestealEnchantment("ee_lifesteal", LanguageManager.instance.GetTranslatedValue("lifesteal-name"), 3);
	public static final Enchantment LUMBERJACK = new LumberjackEnchantment("ee_lumberjack", LanguageManager.instance.GetTranslatedValue("lumberjack-name"), 5);
	public static final Enchantment ORESIGHT = new OresightEnchantment("ee_oresight", LanguageManager.instance.GetTranslatedValue("oresight-name"), 6);
	public static final Enchantment REPLANTING = new ReplantingEnchantment("ee_replanting", LanguageManager.instance.GetTranslatedValue("replanting-name"), 1);
	public static final Enchantment SHADOWSTEP = new ShadowStepEnchantment("ee_shadowstep", LanguageManager.instance.GetTranslatedValue("shadowstep-name"), 5);
	public static final Enchantment SOULBOUND = new SoulboundEnchantment("ee_soulbound", LanguageManager.instance.GetTranslatedValue("soulbound-name"), 1);
	public static final Enchantment SPLITTING = new SplittingEnchantment("ee_splitting", LanguageManager.instance.GetTranslatedValue("splitting-name"), 3);
	public static final Enchantment STEPPING = new SteppingEnchantment("ee_stepping", LanguageManager.instance.GetTranslatedValue("stepping-name"), 1);
	public static final Enchantment STONEFISTS = new StoneFistsEnchantment("ee_stonefists", LanguageManager.instance.GetTranslatedValue("stonefists-name"), 3);
	public static final Enchantment THERMALPLATING = new ThermalPlatingEnchantment("ee_thermalplating", LanguageManager.instance.GetTranslatedValue("thermalplating-name"), 1);
	public static final Enchantment THRUSTERS = new ThrustersEnchantment("ee_thrusters", LanguageManager.instance.GetTranslatedValue("thrusters-name"), 2);
	public static final Enchantment TRAVELER = new TravelerEnchantment("ee_traveler", LanguageManager.instance.GetTranslatedValue("traveler-name"), 3);
	public static final Enchantment NOBREAKABLE = new UnbreakableEnchantment("ee_unbreakable", LanguageManager.instance.GetTranslatedValue("unbreakable-name"), 1);
	public static final Enchantment VEINMINE = new VeinmineEnchantment("ee_veinmine", LanguageManager.instance.GetTranslatedValue("veinmine-name"), 5);
	public static final Enchantment WIDE = new WideEnchantment("ee_wide", LanguageManager.instance.GetTranslatedValue("wide-name"), 1);
	
	public static ArrayList<Enchantment> custom_enchants = new ArrayList<Enchantment>();
	
	
	@SuppressWarnings("deprecation")
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
				registeredEnchs[i] = RegisterEnchantment(custom_enchants.get(i));
			}
		}
		boolean allRegistered = true;
		for(boolean reg : registeredEnchs) if(reg == false) allRegistered = false;
		if(allRegistered) Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "ExpandedEnchants" + ChatColor.WHITE + ": All enchants were succesfully registered!");
		else {
			for(int i = 0 ; i < registeredEnchs.length; i++) if(registeredEnchs[i] == false) Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "ExpandedEnchants" + ChatColor.RED + ": Couldn't register '" + custom_enchants.get(i).getName() + "'!");
		}
	}

	public static boolean RegisterEnchantment(Enchantment enchantment) 
	{
		boolean registered = true;
		try 
		{
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
			return registered;
		} 
		catch (Exception e)
		{
			registered = false;
			e.printStackTrace();
			return registered;
		}
			
	}

	 
}
