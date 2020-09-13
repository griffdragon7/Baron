package me.griffdragon.NewBaron.Menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class ClassSelector implements Listener {

	public Inventory i(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 27, "Choose a class");

		return inv;

	}

}
