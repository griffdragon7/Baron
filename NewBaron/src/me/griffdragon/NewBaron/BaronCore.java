package me.griffdragon.NewBaron;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.griffdragon.NewBaron.Classes.Archer.ArcherMain;
import me.griffdragon.NewBaron.Classes.Cyromancer.CyroMain;
import me.griffdragon.NewBaron.Classes.Geomancer.GeoMain;
import me.griffdragon.NewBaron.Classes.Hunter.HunterMain;
import me.griffdragon.NewBaron.Classes.Pyromancer.PyroMain;
import me.griffdragon.NewBaron.Classes.Ranger.RangerMain;
import me.griffdragon.NewBaron.Events.PlayerEvents;
import me.griffdragon.NewBaron.Events.WorldEvents;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import me.griffdragon.NewBaron.Functions.DamageSystem;
import me.griffdragon.NewBaron.Functions.ExpSystem;
import me.griffdragon.NewBaron.Items.CritDamage;
import me.griffdragon.NewBaron.Items.CritRate;
import me.griffdragon.NewBaron.Items.Defence;
import me.griffdragon.NewBaron.Items.Health;
import me.griffdragon.NewBaron.Items.ItemGenerator;
import me.griffdragon.NewBaron.Items.Luck;
import me.griffdragon.NewBaron.Items.MagicDamage;
import me.griffdragon.NewBaron.Items.PhysicalDamage;
import me.griffdragon.NewBaron.Items.Speed;
import me.griffdragon.NewBaron.Menus.ClassSelector;
import me.griffdragon.NewBaron.Menus.StatsMenu;
import me.griffdragon.NewBaron.Mobs.BasicMobs;
import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;

public class BaronCore extends JavaPlugin implements Listener {

	// register items and functions/stats
	ClassConfigFunctions files = new ClassConfigFunctions(this);
	ItemGenerator items = new ItemGenerator();
	CritDamage cd = new CritDamage(items);
	CritRate cr = new CritRate(items);
	Health hp = new Health(this, items);
	Luck lc = new Luck(items);
	MagicDamage md = new MagicDamage(items);
	PhysicalDamage pd = new PhysicalDamage(items);
	Speed sp = new Speed(items);
	Defence df = new Defence(items);

	StatsMenu statsmenu = new StatsMenu(files);

	// register classes
	ArcherMain archerMain = new ArcherMain(this, files);
	HunterMain hunterMain = new HunterMain();
	RangerMain rangerMain = new RangerMain();

	PyroMain pyromancerMain = new PyroMain();
	CyroMain cryomancerMain = new CyroMain();
	GeoMain geomancerMain = new GeoMain();

	public StatsMain stats = new StatsMain(df, cd, cr, hp, lc, md, pd, sp, this, archerMain, files);

	BasicMobs basicmobs = new BasicMobs();

	// register events
	PlayerEvents playerEvents = new PlayerEvents(files, stats, this);
	WorldEvents worldEvents = new WorldEvents(this);
	ExpSystem expSystem = new ExpSystem(files);
	DamageSystem damageSystem = new DamageSystem(files, basicmobs);

	ClassSelector classSelector = new ClassSelector(files, geomancerMain, pyromancerMain, cryomancerMain, hunterMain,
			rangerMain, archerMain, this);

	public static ArrayList<String> physicalClasses = new ArrayList<>();
	public static ArrayList<String> magicalClasses = new ArrayList<>();

	public void onEnable() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			StatsMain.Health.put(p, stats.getHealth(p));
			StatsMain.Defence.put(p, stats.getDefence(p));
			StatsMain.Luck.put(p, stats.getLuck(p));
			StatsMain.Speed.put(p, stats.getSpeed(p));
			StatsMain.CritRate.put(p, stats.getCritRate(p));
			StatsMain.CritDamage.put(p, stats.getCritDamage(p));
			StatsMain.PhysicalDamage.put(p, stats.getPhysicalDamage(p));
			StatsMain.MagicDamage.put(p, stats.getMagicDamage(p));
		}

		physicalClasses.add("Archer");

		magicalClasses.add("Cryomancer");

		for (Player ps : Bukkit.getServer().getOnlinePlayers()) {
			BossBar bar = stats.b(ps);
			bar.addPlayer(ps);
			stats.bars.put(ps, bar);

		}
		items.putMaps();
		Bukkit.getServer().getPluginManager().registerEvents(playerEvents, this);
		Bukkit.getServer().getPluginManager().registerEvents(worldEvents, this);
		Bukkit.getServer().getPluginManager().registerEvents(damageSystem, this);
		Bukkit.getServer().getPluginManager().registerEvents(expSystem, this);
		Bukkit.getServer().getPluginManager().registerEvents(classSelector, this);
		Bukkit.getServer().getPluginManager().registerEvents(statsmenu, this);
		Bukkit.getServer().getPluginManager().registerEvents(stats, this);

	}

	public void onDisable() {
		for (Player ps : Bukkit.getServer().getOnlinePlayers()) {
			stats.bars.get(ps).removePlayer(ps);
		}
	}

	// Use these to access classes in config files

	public String archer = "Archer";

	public String hunter = "Hunter";

	public String ranger = "Ranger";

	public String cryomancer = "Cryomancer";

	public String geomancer = "Geomancer";

	public String pyromancer = "Pyromancer";

	public String warrior = "Warrior";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("stats")) {
				if (args.length == 1) {
					Player tp = Bukkit.getPlayer(args[0]);
					if (tp.isOnline()) {
						tp.openInventory(statsmenu.inv(tp));
					} else {
						p.sendMessage(ChatColor.RED + "That player is not online at the moment, sorry!");
					}
				} else {
					p.openInventory(statsmenu.inv(p));
				}

			}
			if (cmd.getName().equalsIgnoreCase("class")) {
				p.openInventory(classSelector.i(p));
			}
			if (cmd.getName().equalsIgnoreCase("baron")) {
				if (args.length == 5) {
					if (args[0].equalsIgnoreCase("item")) {
						try {
							// player name
							Player tp = Bukkit.getPlayer(args[1]);
							// type
							String s = String.valueOf(args[2]);
							// tier
							String b = String.valueOf(args[3]);
							int num = Integer.parseInt(args[4]);
							for (int x = 0; x < num; x++) {
								tp.getInventory().addItem(items.Armor(b, s));
							}
						} catch (NullPointerException n) {
							helpText(p);
							n.printStackTrace();
						}
					}
				}
				try {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("help")) {
							helpText(p);
						}
					}
				} catch (NullPointerException n) {
					helpText(p);
				}
			}
		}

		return true;
	}

	public void statsText(Player p) {

	}

	public void helpText(Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&8»&8&m            &a&lBaron Commands&8&m            &8«"));
		p.sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&7 - /baron item {name} {item type} {tier} {amount}"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7 - /stats [Player]"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&8»&8&m                                                 &8«"));
	}

}
