package me.griffdragon.NewBaron.Stats;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Classes.Archer.ArcherMain;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;

public class StatsMain {

	private final BaronCore main;

	private final ArcherMain archer;

	private final ClassConfigFunctions files;

	public StatsMain(BaronCore main, ArcherMain archer, ClassConfigFunctions files) {
		this.main = main;
		this.archer = archer;
		this.files = files;
	}

	public int getClassHealth(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.Health);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.HealthModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

}
