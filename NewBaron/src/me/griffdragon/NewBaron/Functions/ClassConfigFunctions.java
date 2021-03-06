package me.griffdragon.NewBaron.Functions;

import org.bukkit.entity.Player;

import me.griffdragon.NewBaron.BaronCore;

public class ClassConfigFunctions {

	private final BaronCore main;

	public ClassConfigFunctions(BaronCore main) {
		this.main = main;
	}

	public int getClassLevel(Player p, String Class) {
		// returns the players level for given class
		PlayerFiles file = new PlayerFiles(p);

		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Classes." + Class + ".Level");
	}

	public void setUpPlayerFile(Player p) {

		// code to run when player first joins server!
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Info.Active", "Archer");
		file.getPlayerFile().set(uuid + ".Classes.Archer.Level", 1);
		file.getPlayerFile().set(uuid + ".Classes.Archer.Exp", 0);
		file.getPlayerFile().set(uuid + ".Prof.Bow", 0);
		file.getPlayerFile().set(uuid + ".Prof.Magic", 0);
		file.getPlayerFile().set(uuid + ".Prof.Sword", 0);
		file.getPlayerFile().set(uuid + ".Prof.Shield", 0);
		file.getPlayerFile().set(uuid + ".Prof.Healer", 0);

		file.savePlayerFile();

	}

	public int getBowProficiency(Player p) {
		PlayerFiles file = new PlayerFiles(p);

		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Prof.Bow");

	}

	public int getMagicProficiency(Player p) {
		PlayerFiles file = new PlayerFiles(p);

		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Prof.Magic");

	}

	public int getSwordProficiency(Player p) {
		PlayerFiles file = new PlayerFiles(p);

		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Prof.Sword");

	}

	public int getShieldProficiency(Player p) {
		PlayerFiles file = new PlayerFiles(p);

		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Prof.Shield");

	}

	public int getHealerProficiency(Player p) {
		PlayerFiles file = new PlayerFiles(p);

		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Prof.Healer");

	}

	public void addClass(Player p, String classname) {

		// adds a class to a players collection

		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Level", 1);
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Exp", 0);

		file.savePlayerFile();

	}

	public String getClass(Player p) {
		// returns the players active class
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().get(uuid + ".Info.Active");

		return file.getPlayerFile().getString(uuid + ".Info.Active");

	}

	public boolean hasClass(Player p, String classname) {
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		if (file.getPlayerFile().get(uuid + ".Classes." + classname + ".Level") == null) {
			return false;
		} else {
			return true;
		}

	}

	public void setActiveClass(Player p, String classname) {
		// sets the active class of a player
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Info.Active", classname);

		file.savePlayerFile();

	}

	public int getLevel(Player p, String classname) {

		// returns the level of specified class

		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		return file.getPlayerFile().getInt(uuid + ".Classes." + classname + ".Level");
	}

	public void setLevel(Player p, String classname, int level) {

		// sets the level of a class
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Level", level);
		file.savePlayerFile();
	}

	public int getExp(Player p, String classname) {

		// returns the exp of a class
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		return file.getPlayerFile().getInt(uuid + ".Classes." + classname + ".Exp");
	}

	public void addExp(Player p, String classname, int amount) {
		// adds exp to a class
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Exp", getExp(p, classname) + amount);
		file.savePlayerFile();
		if (getExp(p, classname) >= expToLevel(p)) {
			levelUp(p, classname);
		}
		scaleExp(p);

	}

	public void removeExp(Player p, String classname, int amount) {
		// removes exp from a class
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Exp", getExp(p, classname) - amount);
		file.savePlayerFile();
		if (getExp(p, classname) >= expToLevel(p)) {
			levelUp(p, classname);
		}
		scaleExp(p);
	}

	public void setExp(Player p, String classname, int amount) {
		// sets the exp for a class
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Exp", amount);
		file.savePlayerFile();

		if (getExp(p, classname) >= expToLevel(p)) {
			levelUp(p, classname);
		}
		scaleExp(p);
	}

	public void levelUp(Player p, String classname) {
		setLevel(p, classname, getLevel(p, classname) + 1);
		setExp(p, classname, 0);

		main.stats.updateStats(p);
		main.stats.updateHealthBar(p);

		scaleExp(p);
	}

	public void scaleExp(Player p) {
		// scales the EXP bar for a player's active class

		p.setLevel(getClassLevel(p, getClass(p)));
		p.setExp((float) (getExp(p, getClass(p)) / expToLevel(p)));

	}

	public double expToLevel(Player p) {
		return (125 * (Math.pow(getClassLevel(p, getClass(p)), 3.1)));
	}

}
