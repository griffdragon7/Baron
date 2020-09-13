package me.griffdragon.NewBaron.Functions;

import org.bukkit.entity.Player;

public class ClassConfigFunctions {

	public int getClassLevel(Player p, String Class) {
		//returns the players level for given class
		PlayerFiles file = new PlayerFiles(p);

		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Classes." + Class + ".Level");
	}

	public void setUpPlayerFile(Player p) {
		
		//code to run when player first joins server!
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Info.Active", "Archer");
		file.getPlayerFile().set(uuid + ".Classes.Archer.Level", 1);
		file.getPlayerFile().set(uuid + ".Classes.Archer.Exp", 0);
		file.getPlayerFile().set(uuid + ".Prof.Earth", 0);
		file.getPlayerFile().set(uuid + ".Prof.Water", 0);
		file.getPlayerFile().set(uuid + ".Prof.Air", 0);
		file.getPlayerFile().set(uuid + ".Prof.Fire", 0);
		file.getPlayerFile().set(uuid + ".Prof.Death", 0);

		file.savePlayerFile();

	}
	
	public void addClass(Player p, String classname) {
		
		//adds a class to a players collection
		
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Level", 1);
		file.getPlayerFile().set(uuid + ".Classes." + classname + ".Exp", 0);
		
		file.savePlayerFile();
		
	}
	
	public void setActiveClass(Player p, String classname) {
		//sets the active class of a player
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Info.Active" + classname, classname);
		
		file.savePlayerFile();
		
		
	}
	
}
