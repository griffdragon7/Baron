package me.griffdragon.NewBaron.Stats;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Classes.Archer.ArcherMain;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import me.griffdragon.NewBaron.Functions.PlayerFiles;
import me.griffdragon.NewBaron.Items.CritDamage;
import me.griffdragon.NewBaron.Items.CritRate;
import me.griffdragon.NewBaron.Items.Defence;
import me.griffdragon.NewBaron.Items.Health;
import me.griffdragon.NewBaron.Items.Luck;
import me.griffdragon.NewBaron.Items.MagicDamage;
import me.griffdragon.NewBaron.Items.PhysicalDamage;
import me.griffdragon.NewBaron.Items.Speed;

public class StatsMain {

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

	public void updateStats(Player p) {
		PlayerFiles file = new PlayerFiles(p);

		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.Health", getHealth(p));
		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.PhysicalDamage", getPhysicalDamage(p));
		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.MagicDamage", getMagicDamage(p));
		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.Defence", getDefence(p));
		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.Luck", getLuck(p));
		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.Speed", getSpeed(p));
		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.CritRate", getCritRate(p));
		file.getPlayerFile().set(p.getUniqueId().toString() + ".Stats.CritDamage", getCritDamage(p));

		file.savePlayerFile();
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
