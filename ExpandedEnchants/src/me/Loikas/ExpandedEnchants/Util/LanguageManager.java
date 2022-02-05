package me.Loikas.ExpandedEnchants.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageManager
{
	public static LanguageManager instance;
	
	YamlConfiguration lang = new YamlConfiguration();
	
	public LanguageManager () {
		try { lang.load("plugins/ExpandedEnchants/lang.yml"); }
		catch (InvalidConfigurationException e) { e.printStackTrace(); lang = null;}
		catch(IOException e) {lang = null;};
	}
	
	public String GetTranslatedValue(String key) {
		if(lang == null) return "lang.yml file not set up correctly. Contact server administrator!";
		return lang.getString(key);
	}
	
	public List<String> GetTranslatedList(String key){
		if(lang == null) return new ArrayList<>();
		return lang.getStringList(key);
	}
}
