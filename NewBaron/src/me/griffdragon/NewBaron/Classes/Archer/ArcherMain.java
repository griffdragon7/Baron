package me.griffdragon.NewBaron.Classes.Archer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Classnames;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;

public class ArcherMain {

	private final ClassConfigFunctions config;
	private final BaronCore main;

	public ArcherMain(BaronCore main, ClassConfigFunctions config) {
		this.config = config;
		this.main = main;
	}

	public ItemStack classItem(Player p) {
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				"&a&lArcher &7- &8[&7" + config.getClassLevel(p, main.classString.get(Classnames.ARCHER))));

		return item;
	}

}
