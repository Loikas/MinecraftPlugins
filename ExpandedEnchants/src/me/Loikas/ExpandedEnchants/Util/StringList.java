package me.Loikas.ExpandedEnchants.Util;

import java.util.List;

public class StringList
{
	public String[] stringList = new String[0];
	public StringList(List<String> string) {
		String[] array = new String[string.size()];
		for(int i = 0; i < string.size(); i++) array[i] = string.get(i);
		stringList = array;
	}
	
	public StringList(String[] string) {
		stringList = string;
	}
}
