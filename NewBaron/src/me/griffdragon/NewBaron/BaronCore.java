package me.griffdragon.NewBaron;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
import me.griffdragon.NewBaron.Items.ItemGenerator;
import me.griffdragon.NewBaron.Items.Luck;
import me.griffdragon.NewBaron.Items.MagicDamage;
import me.griffdragon.NewBaron.Items.PhysicalDamage;
import me.griffdragon.NewBaron.Items.Speed;
import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;

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

	ItemGenerator items = new ItemGenerator();

	PlayerEvents playerEvents = new PlayerEvents(files, stats, this);
	WorldEvents worldEvents = new WorldEvents(this);
	ExpSystem expSystem = new ExpSystem(files);
	DamageSystem damageSystem = new DamageSystem();

	public void onEnable() {

		for (Player ps : Bukkit.getServer().getOnlinePlayers()) {
			BossBar bar = playerEvents.b(ps);
			bar.addPlayer(ps);
			playerEvents.bars.put(ps, bar);

		}

		Bukkit.getServer().getPluginManager().registerEvents(playerEvents, this);
		Bukkit.getServer().getPluginManager().registerEvents(worldEvents, this);
		Bukkit.getServer().getPluginManager().registerEvents(damageSystem, this);
		Bukkit.getServer().getPluginManager().registerEvents(expSystem, this);

	}

	public void onDisable() {
		for (Player ps : Bukkit.getServer().getOnlinePlayers()) {
			playerEvents.bars.get(ps).removePlayer(ps);
		}
	}

	// Use these to access classes in config files

	public String archer = "Archer";

	public String cyromancer = "Cyromancer";

	public String geomancer = "Geomancer";

	public String pyromancer = "Pyromancer";

	public String warrior = "Warrior";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
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

	public void helpText(Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&8»&8&m            &a&lBaron Commands&8&m            &8«"));
		p.sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&7 - /baron item {name} {item type} {tier} {amount}"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&8»&8&m                                                 &8«"));
	}

}
