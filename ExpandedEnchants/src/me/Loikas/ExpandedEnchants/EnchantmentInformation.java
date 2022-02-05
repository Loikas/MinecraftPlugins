package me.Loikas.ExpandedEnchants;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Loikas.ExpandedEnchants.Util.DoubleItemStack;
import me.Loikas.ExpandedEnchants.Util.LanguageManager;
import me.Loikas.ExpandedEnchants.Util.StringList;

public class EnchantmentInformation
{
	public static DoubleItemStack[] tradeCosts = new DoubleItemStack[] {
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //antigravity
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.NETHERITE_INGOT, 8)), //assassin
			new DoubleItemStack(new ItemStack(Material.EMERALD, 32), new ItemStack(Material.DIAMOND, 16)), //autosmelt
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.WITHER_SKELETON_SKULL, 1)), //beheading
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //deflect
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //direct
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.END_CRYSTAL, 16)), //elementalprotection
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //owleyes
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.DIAMOND, 32)), //expboost
			new DoubleItemStack(new ItemStack(Material.EMERALD, 32), null), //feedingmodule
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //gourmand
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 5)), //healthboost
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //heavenslightness
			new DoubleItemStack(new ItemStack(Material.EMERALD, 32), new ItemStack(Material.DIAMOND, 32)), //icy
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //lavawalker
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //leaping
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.DIAMOND, 32)), //lifesteal
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.NETHERITE_SCRAP, 8)), //lumberjack
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.NETHERITE_INGOT, 4)), //oresight
			new DoubleItemStack(new ItemStack(Material.EMERALD, 32), null), //replanting
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), null), //shadowstep
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.DIAMOND, 48)), //soulbound
			new DoubleItemStack(new ItemStack(Material.EMERALD, 32), new ItemStack(Material.DIAMOND, 32)), //splitting
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.DIAMOND, 48)), //stepping
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.NETHERITE_SCRAP, 16)), //stonefists
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.NETHERITE_SCRAP, 8)), //thermalplating
			new DoubleItemStack(new ItemStack(Material.EMERALD, 32), new ItemStack(Material.FIREWORK_ROCKET, 32)), //thrusters
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.DIAMOND, 16)), //traveler
			new DoubleItemStack(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.NETHER_STAR, 1)), //unbreakable
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.NETHERITE_SCRAP, 8)), //veinmine
			new DoubleItemStack(new ItemStack(Material.EMERALD, 48), new ItemStack(Material.NETHERITE_SCRAP, 16)), //wide			
	};
	
	public static StringList[] descriptions = new StringList[] {
		new StringList(LanguageManager.instance.GetTranslatedList("antigravity-desc")), //new StringList(new String[] { "Make your arrows fly true", "Arrows won't be affected by gravity anymore" }), //antigravity
		new StringList(LanguageManager.instance.GetTranslatedList("assassin-desc")), //new StringList(new String[] { "Silently approach your foes.", "If you equip this nobody", "will be able to see you.", "Cancels when you deal damage to something!", "Length increases and", "cooldown decreases per level."}), //assassin
		new StringList(LanguageManager.instance.GetTranslatedList("autosmelt-desc")), //new StringList(new String[] { "Automatically smelt all ores you mine", "Compatible with fortune and direct" }), //autosmelt
		new StringList(LanguageManager.instance.GetTranslatedList("beheading-desc")), //new StringList(new String[] { "Higher chance to drop mob heads", "Each level adds + 5% chance"}), // beheading
		new StringList(LanguageManager.instance.GetTranslatedList("deflect-desc")), //new StringList(new String[] { "Deflect damage every 30 seconds", "Takes an emerald every time", "Each level lowers the cooldown by 1 second"}), //deflect
		new StringList(LanguageManager.instance.GetTranslatedList("direct-desc")), //new StringList(new String[] { "Teleport broken blocks or killed mob items", "directly into your inventory" }), //direct
		new StringList(LanguageManager.instance.GetTranslatedList("disarming-desc")), //new StringList(new String[] { "Has a small chance to drop the enemy player's", "main hand weapon or one of their armor pieces.", "Cooldown of 30 seconds"}), //disarming
		new StringList(LanguageManager.instance.GetTranslatedList("disruption-desc")), //new StringList(new String[] { "Deal more damage to mobs that can teleport", "and disable their ability to teleport.", "These mobs are endermen and shulkers" }), //disruption
		new StringList(LanguageManager.instance.GetTranslatedList("elementalprotection-desc")),//elementalprotection
		new StringList(LanguageManager.instance.GetTranslatedList("eyesofowl-desc")), //new StringList(new String[] { "No more potions of night vision", "see everything" }), //owleyes
		new StringList(LanguageManager.instance.GetTranslatedList("experienceboost-desc")), //new StringList(new String[] { "Chance to double the xp you get", "each level increases the chance by 15%"}), // expboost
		new StringList(LanguageManager.instance.GetTranslatedList("feedingmodule-desc")), //new StringList(new String[] { "Automatically eat foods for you", "takes the left most food in your inventory.", "Compatible with gourmand" }), //feedingmodule
		new StringList(LanguageManager.instance.GetTranslatedList("gourmand-desc")), //new StringList(new String[] { "Food becomes more delicious and", "restores more food points when you eat it.", "Compatible with feeding module" }), //gourmand
		new StringList(LanguageManager.instance.GetTranslatedList("healthboost-desc")), //new StringList(new String[] { "Add one heart for each level"}), //healthboost
		new StringList(LanguageManager.instance.GetTranslatedList("heavenslightness-desc")), //new StringList(new String[] { "Permanent slow falling", "never have fall damage again" }), //heavenslightness
		new StringList(LanguageManager.instance.GetTranslatedList("icy-desc")), //new StringList(new String[] { "Add a slowness effect to every", "entity you hit. Cooldown of 10 seconds.", "Makes players unable to jump", "Level increases the slowness level" }), //icy
		new StringList(LanguageManager.instance.GetTranslatedList("lavawalker-desc")), //new StringList(new String[] { "Places a block of basalt", "under you every time you walk", "over lava" }), //lavawalker
		new StringList(LanguageManager.instance.GetTranslatedList("leaping-desc")), //new StringList(new String[] { "Always have a jump boost effect"}), //leaping
		new StringList(LanguageManager.instance.GetTranslatedList("lifesteal-desc")), //new StringList(new String[] { "Steal some of the hearts your enemy had", "Half a heart per level"}), //lifesteal
		new StringList(LanguageManager.instance.GetTranslatedList("lumberjack-desc")), //new StringList(new String[] { "Break the whole tree when you break one block", "Each level decreases the food cost"}),//lumberjack
		new StringList(LanguageManager.instance.GetTranslatedList("oresight-desc")), //new StringList(new String[] { "Allows you to see ores through walls.","Levels change the ores you see.", "IE: Lvl 3 is only gold, nothing else.", "Level:", "1: Coal", "2: Iron and Copper", "3: Gold", "4: Lapis and Redstone", "5: Diamond and Emerald", "6: Ancient debris"}),//oresight
		new StringList(LanguageManager.instance.GetTranslatedList("replanting-desc")), //new StringList(new String[] { "Whenever you right click a full grown crop", "it automatically replants it" }),//replanting
		new StringList(LanguageManager.instance.GetTranslatedList("shadowstep-desc")), //new StringList(new String[] { "Makes it harder for mobs to hear you", "Decreases follow distance" }),//shadowstep
		new StringList(LanguageManager.instance.GetTranslatedList("soulbound-desc")), //new StringList(new String[] { "Preserve your item on death.", "You will keep all items with soulbound", "after you die.", }), //soulbound
		new StringList(LanguageManager.instance.GetTranslatedList("splitting-desc")), //new StringList(new String[] { "Shoots arrows to all nearby", "mobs when you hit a mob." }), //splitting
		new StringList(LanguageManager.instance.GetTranslatedList("stepping-desc")), //new StringList(new String[] { "Makes it easier to walk up", "single blocks" }),//stepping
		new StringList(LanguageManager.instance.GetTranslatedList("stonefists-desc")), //new StringList(new String[] { "Increase your attack damage significantly"}), //stonefists
		new StringList(LanguageManager.instance.GetTranslatedList("thermalplating-desc")), //new StringList(new String[] { "Get permanent fire resistance", "BUT ONLY IF EVERY ARMOR PIECE", "HAS THE ENCHANT" }), //thermalplating
		new StringList(LanguageManager.instance.GetTranslatedList("thrusters-desc")), //new StringList(new String[] { "Shift to boost forward when", "gliding with your elytra."}), //thrusters
		new StringList(LanguageManager.instance.GetTranslatedList("traveler-desc")), //new StringList(new String[] { "Get a permanent speed boost"}),//traveler
		new StringList(LanguageManager.instance.GetTranslatedList("unbreakable-desc")), //new StringList(new String[] { "Makes a tool truely unbreakable"}), //unbreakable
		new StringList(LanguageManager.instance.GetTranslatedList("veinmine-desc")), //new StringList(new String[] { "When you mine one ore", "it mines all" , "Each level decreases the food cost"}),//veinmine
		new StringList(LanguageManager.instance.GetTranslatedList("wide-desc")), //new StringList(new String[] { "Mine 3x3 to make your life easier", "Also works with right clicking on", "hoes, axes and shovels" }), //wide
	};
	
	public static StringList[] enchantableOn = new StringList[] {
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("bows").trim() }), //antigravity
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("boots").trim()}), //assassin
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("pickaxes").trim()}), //autosmelt
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("axes").trim()}), //beheading
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("chestplates").trim()}), //deflect
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("pickaxes").trim(), "- " + LanguageManager.instance.GetTranslatedValue("axes").trim(), "- " + LanguageManager.instance.GetTranslatedValue("swords").trim(), "- " + LanguageManager.instance.GetTranslatedValue("hoes").trim(), "- " + LanguageManager.instance.GetTranslatedValue("shears").trim(), "- " + LanguageManager.instance.GetTranslatedValue("shovels").trim(), "- " + LanguageManager.instance.GetTranslatedValue("tridents").trim(), "- " + LanguageManager.instance.GetTranslatedValue("crossbows").trim(), "- " + LanguageManager.instance.GetTranslatedValue("bows").trim()}),//direct
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("fishingrods").trim()}),//disarming
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("swords").trim(), "- " + LanguageManager.instance.GetTranslatedValue("axes").trim()}),//disruption
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("chestplates").trim()}), //elementalprotection
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("helmets").trim()}), //owleyes
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("chestplates").trim()}), //expboost
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("helmets").trim()}),//feedingmodule
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("helmets").trim()}),//gourmand
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("chestplates").trim()}), //healthboost
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("chestplates").trim()}),//heavenslightness
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("swords").trim(), "- " + LanguageManager.instance.GetTranslatedValue("axes").trim()}),//icy
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("boots").trim() }),//lavawalker
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("boots").trim()}),//leaping
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("swords").trim()}),//lifesteal
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("axes").trim()}),//lumberjack
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("helmets").trim()}),//oresight
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("hoes").trim()}),//replanting
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("boots").trim()}),//shadowstep
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("all-enchantable-items").trim()}), //soulbound
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("bows").trim()}), //splitting
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("boots").trim()}),//stepping
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("chestplates").trim(), "- " + LanguageManager.instance.GetTranslatedValue("elytras").trim()}), //stonefists
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("helmets").trim(), "- " + LanguageManager.instance.GetTranslatedValue("chestplates").trim(), "- " + LanguageManager.instance.GetTranslatedValue("leggings").trim(), "- " + LanguageManager.instance.GetTranslatedValue("boots").trim()}), //thermalplating
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("elytras").trim()}), //thrusters
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("leggings").trim()}), //traveler
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("all-items-durability").trim()}), //unbreakable
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("pickaxes").trim()}), //veinmine
		new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("axes").trim(), "- " + LanguageManager.instance.GetTranslatedValue("pickaxes").trim(), "- " + LanguageManager.instance.GetTranslatedValue("shovels").trim(), "- " + LanguageManager.instance.GetTranslatedValue("hoes").trim(), "- " + LanguageManager.instance.GetTranslatedValue("shears").trim()}),//wide
	};
	
	public static StringList[] conflictsWith = new StringList[] {
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim() }), //antigravity
			new StringList(new String[] { "- Shadow Step"}), //autosmelt
			new StringList(new String[] { "- Silk Touch"}), //autosmelt
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}), //beheading
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}), //deflect
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}), // direct
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}), // disarming
			new StringList(new String[] { "- Sharpness", "- Smite", "- Bane of Arthropods"}), // disruption			
			new StringList(new String[] { "- Thermal Plating"}), //elementalprotection
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}), //owleyes
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//expboost
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//feedingmodule
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//gourmand
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//healthboost
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//heavenslightness
			new StringList(new String[] { "- Fire Aspect"}),//icy
			new StringList(new String[] { "- Depth Strider", "- Frost Walker" }), //lavawalker
			new StringList(new String[] { "- Stepping"}), //leaping
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//lifesteal
			new StringList(new String[] { "- Wide"}),//lumberjack
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//oresight
			new StringList(new String[] { "- Wide"}),//replanting
			new StringList(new String[] { "- Assassin"}),//shadowstep
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//soulbound
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//splitting
			new StringList(new String[] { "- Leaping"}),//stepping
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//stonefists
			new StringList(new String[] { "- Elemental Protection"}),//thermalplating
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//thrusters
			new StringList(new String[] { "- " + LanguageManager.instance.GetTranslatedValue("nothing").trim()}),//traveler
			new StringList(new String[] { "- Infinity"}),//unbreakable
			new StringList(new String[] { "- Wide"}), //veinmine
			new StringList(new String[] { "- Lumberjack", "- Veinmine"}), //wide
		};
}
