package me.griffdragon.NewBaron.Functions;

import org.bukkit.entity.Player;

public class ClassConfigFunctions {

	public int getClassLevel(Player p, String Class) {
		PlayerFiles file = new PlayerFiles(p);
		
		return file.getPlayerFile().getInt(p.getUniqueId().toString() + ".Classes." + Class + ".Level");
	}

}
