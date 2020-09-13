package me.griffdragon.NewBaron;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.griffdragon.NewBaron.Events.PlayerEvents;
import me.griffdragon.NewBaron.Events.WorldEvents;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;

public class BaronCore extends JavaPlugin implements Listener {

	ClassConfigFunctions files = new ClassConfigFunctions();

	PlayerEvents playerEvents = new PlayerEvents(files);
	WorldEvents worldEvents = new WorldEvents(this);

	public void onEnable() {
		// todo

		Bukkit.getServer().getPluginManager().registerEvents(playerEvents, this);
		Bukkit.getServer().getPluginManager().registerEvents(worldEvents, this);

	}

	public String archer = "Archer";

	public String cyromancer = "Cyromancer";

	public String geomancer = "Geomancer";

	public String pyromancer = "Pyromancer";

}
