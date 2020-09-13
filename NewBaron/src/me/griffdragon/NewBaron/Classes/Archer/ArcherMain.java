package me.griffdragon.NewBaron.Classes.Archer;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;

public class ArcherMain {

	private final ClassConfigFunctions config;

	private final BaronCore main;

	public ArcherMain(BaronCore main, ClassConfigFunctions config) {
		this.config = config;
		this.main = main;
	}

	// Base Stats

	public int PhysicalDamage = 120;

	public int MagicDamage = 0;

	public int Health = 320;

	public int Defence = 10;

	public int Speed = 70;

	public int CritDamage = 30;

	public int CritRate = 5;

	public int Luck = 0;

	// Leveling Modifiers

	public int PhysicalModifier = 15;

	public int MagicModifier = 0;

	public int HealthModifier = 130;

	public int DefenceModifier = 0;

	public int speedModifier = 0;

	public int CritDamageModifier = 1;

	public int CritRateModifier = 0;

	public int LuckModifier = 0;

	public ItemStack classItem(Player p) {
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				"&a&lArcher &7- &8[&7" + config.getClassLevel(p, main.archer) + "&8]"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7///CLASS LORE"));
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);

		return item;
	}

}
