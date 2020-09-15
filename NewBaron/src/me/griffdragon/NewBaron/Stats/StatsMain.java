package me.griffdragon.NewBaron.Stats;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Classes.Archer.ArcherMain;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import me.griffdragon.NewBaron.Items.CritDamage;
import me.griffdragon.NewBaron.Items.CritRate;
import me.griffdragon.NewBaron.Items.Defence;
import me.griffdragon.NewBaron.Items.Health;
import me.griffdragon.NewBaron.Items.Luck;
import me.griffdragon.NewBaron.Items.MagicDamage;
import me.griffdragon.NewBaron.Items.PhysicalDamage;
import me.griffdragon.NewBaron.Items.Speed;
import net.md_5.bungee.api.ChatColor;

public class StatsMain implements Listener {

	private final BaronCore main;

	private final ArcherMain archer;

	private final ClassConfigFunctions files;

	private final CritDamage critDamage;
	private final CritRate critRate;
	private final Health health;
	private final Luck luck;
	private final MagicDamage magicDamage;
	private final PhysicalDamage physicalDamage;
	private final Speed speed;
	private final Defence defence;

	public static HashMap<Player, Integer> Health = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Defence = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Luck = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Speed = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> CritRate = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> CritDamage = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> PhysicalDamage = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> MagicDamage = new HashMap<Player, Integer>();

	public StatsMain(Defence df, CritDamage cd, CritRate cr, Health hp, Luck lc, MagicDamage md, PhysicalDamage pd,
			Speed sp, BaronCore main, ArcherMain archer, ClassConfigFunctions files) {
		this.main = main;
		this.archer = archer;
		this.files = files;

		this.defence = df;
		this.critDamage = cd;
		this.critRate = cr;
		this.health = hp;
		this.luck = lc;
		this.magicDamage = md;
		this.physicalDamage = pd;
		this.speed = sp;
	}

	public BossBar b(Player p) {

		float maxHP = StatsMain.Health.get(p);
		float health = (float) p.getHealth();
		float proportion = health / 20;
		float finalHP = maxHP * proportion;
		BossBar b = Bukkit.createBossBar(
				ChatColor.translateAlternateColorCodes('&', "&7Health: &a" + (int) finalHP + "&c \u2764"),
				BarColor.GREEN, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);
		b.setProgress(proportion);
		return b;
	}

	public HashMap<Player, BossBar> bars = new HashMap<Player, BossBar>();

	@EventHandler
	public void updateHealth(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();

			if (e.getEntity() instanceof Player) {
				new BukkitRunnable() {

					public void run() {
						double maxHP = StatsMain.Health.get(p);
						double health = p.getHealth();
						double proportion = health / 20;
						double finalHP = maxHP * proportion;
						bars.get(p).setTitle(ChatColor.translateAlternateColorCodes('&',
								"&7Health: &a" + (int) finalHP + "&c \u2764"));
						bars.get(p).setProgress(proportion);

					}
				}.runTaskLater(main, 1L);

			}
		}
	}

	@EventHandler
	public void onclick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();

		new BukkitRunnable() {

			@Override
			public void run() {

				StatsMain.Health.put(p, getHealth(p));
				StatsMain.Defence.put(p, getDefence(p));
				StatsMain.Luck.put(p, getLuck(p));
				StatsMain.Speed.put(p, getSpeed(p));
				StatsMain.CritRate.put(p, getCritRate(p));
				StatsMain.CritDamage.put(p, getCritDamage(p));
				StatsMain.PhysicalDamage.put(p, getPhysicalDamage(p));
				StatsMain.MagicDamage.put(p, getMagicDamage(p));
				double maxHP = StatsMain.Health.get(p);
				double health = p.getHealth();
				double proportion = health / 20;
				double finalHP = maxHP * proportion;
				bars.get(p).setTitle(
						ChatColor.translateAlternateColorCodes('&', "&7Health: &a" + (int) finalHP + "&c \u2764"));
				bars.get(p).setProgress(proportion);

			}
		}.runTaskLater(main, 1L);

	}

	@EventHandler
	public void updateBar(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();

			new BukkitRunnable() {

				@Override
				public void run() {
					double maxHP = StatsMain.Health.get(p);
					double health = p.getHealth();
					double proportion = health / 20;
					double finalHP = maxHP * proportion;
					bars.get(p).setTitle(
							ChatColor.translateAlternateColorCodes('&', "&7Health: &a" + (int) finalHP + "&c \u2764"));
					bars.get(p).setProgress(proportion);

				}
			}.runTaskLater(main, 1L);

		}

	}

	@EventHandler
	public void playerLeaveEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		StatsMain.Health.remove(p);
		StatsMain.Defence.remove(p);
		StatsMain.Speed.remove(p);
		StatsMain.Luck.remove(p);
		StatsMain.CritRate.remove(p);
		StatsMain.CritDamage.remove(p);
		StatsMain.PhysicalDamage.remove(p);
		StatsMain.MagicDamage.remove(p);

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		// adds a player to a stat when they join

		StatsMain.Health.put(p, getHealth(p));
		StatsMain.Defence.put(p, getDefence(p));
		StatsMain.Luck.put(p, getLuck(p));
		StatsMain.Speed.put(p, getSpeed(p));
		StatsMain.CritRate.put(p, getCritRate(p));
		StatsMain.CritDamage.put(p, getCritDamage(p));
		StatsMain.PhysicalDamage.put(p, getPhysicalDamage(p));
		StatsMain.MagicDamage.put(p, getMagicDamage(p));

		BossBar bar = b(p);
		bar.addPlayer(p);
		bars.put(p, bar);
	}

	public int getHealth(Player p) {
		return getBaseHealth(p) + health.tallyStat(p);
	}

	public int getCritRate(Player p) {
		return getBaseCritRate(p) + critRate.tallyStat(p);
	}

	public int getCritDamage(Player p) {
		return getBaseCritDamage(p) + critDamage.tallyStat(p);
	}

	public int getPhysicalDamage(Player p) {
		return getBasePhysicalDamage(p) + physicalDamage.tallyStat(p);
	}

	public int getMagicDamage(Player p) {
		return getBaseMagicDamage(p) + magicDamage.tallyStat(p);
	}

	public int getSpeed(Player p) {
		return getBaseSpeed(p) + speed.tallyStat(p);
	}

	public int getLuck(Player p) {
		return getBaseLuck(p) + luck.tallyStat(p);
	}

	public int getDefence(Player p) {
		return getBaseDefence(p) + defence.tallyStat(p);
	}

	public int getBaseHealth(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.Health);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.HealthModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

	public int getBaseCritDamage(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.CritDamage);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.CritDamageModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

	public int getBaseCritRate(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.CritRate);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.CritRateModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

	public int getBasePhysicalDamage(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.PhysicalDamage);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.PhysicalModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

	public int getBaseMagicDamage(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.MagicDamage);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.MagicModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

	public int getBaseLuck(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.Luck);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.LuckModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

	public int getBaseSpeed(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.Speed);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.speedModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

	public int getBaseDefence(Player p) {
		HashMap<String, Integer> baseStat = new HashMap<String, Integer>();
		baseStat.put(main.archer, archer.Defence);

		HashMap<String, Integer> statModifier = new HashMap<String, Integer>();
		statModifier.put(main.archer, archer.DefenceModifier);

		return statModifier.get(files.getClass(p)) * files.getClassLevel(p, files.getClass(p))
				+ baseStat.get(files.getClass(p));
	}

}
