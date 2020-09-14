package me.griffdragon.NewBaron;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.griffdragon.NewBaron.Classes.Archer.ArcherMain;
import me.griffdragon.NewBaron.Events.PlayerEvents;
import me.griffdragon.NewBaron.Events.WorldEvents;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import me.griffdragon.NewBaron.Functions.DamageSystem;
import me.griffdragon.NewBaron.Functions.ExpSystem;
import me.griffdragon.NewBaron.Items.CritDamage;
import me.griffdragon.NewBaron.Items.CritRate;
import me.griffdragon.NewBaron.Items.Health;
import me.griffdragon.NewBaron.Items.Luck;
import me.griffdragon.NewBaron.Items.MagicDamage;
import me.griffdragon.NewBaron.Items.PhysicalDamage;
import me.griffdragon.NewBaron.Items.Speed;
import me.griffdragon.NewBaron.Stats.StatsMain;

public class BaronCore extends JavaPlugin implements Listener {

	ClassConfigFunctions files = new ClassConfigFunctions();

	CritDamage cd = new CritDamage();
	CritRate cr = new CritRate();
	Health hp = new Health(this);
	Luck lc = new Luck();
	MagicDamage md = new MagicDamage();
	PhysicalDamage pd = new PhysicalDamage();
	Speed sp = new Speed();

	ArcherMain archermain = new ArcherMain(this, files);

	public StatsMain stats = new StatsMain(cd, cr, hp, lc, md, pd, sp, this, archermain, files);

	PlayerEvents playerEvents = new PlayerEvents(files, stats, this);
	WorldEvents worldEvents = new WorldEvents(this);
	ExpSystem expSystem = new ExpSystem(files);
	DamageSystem damageSystem = new DamageSystem();

	public void onEnable() {
		// todo

		Bukkit.getServer().getPluginManager().registerEvents(playerEvents, this);
		Bukkit.getServer().getPluginManager().registerEvents(worldEvents, this);
		Bukkit.getServer().getPluginManager().registerEvents(damageSystem, this);
		Bukkit.getServer().getPluginManager().registerEvents(expSystem, this);

	}

	// Use these to access classes in config files

	public String archer = "Archer";

	public String cyromancer = "Cyromancer";

	public String geomancer = "Geomancer";

	public String pyromancer = "Pyromancer";

	public String warrior = "Warrior";

}
