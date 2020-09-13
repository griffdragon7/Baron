package me.griffdragon.NewBaron;

import java.util.HashMap;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BaronCore extends JavaPlugin implements Listener {

	public HashMap<Classnames, String> classString = new HashMap<Classnames, String>();

	public void onEnable() {
		classString.put(Classnames.ARCHER, "Archer");
	}

}
