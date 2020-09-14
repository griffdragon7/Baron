package me.griffdragon.NewBaron.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;

public class StatsMenu {

	private final ClassConfigFunctions files;

	public StatsMenu(ClassConfigFunctions files) {
		this.files = files;
	}

	public Inventory inv(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 27, p.getName() + "'s stats");
		if (p.isOnline()) {
			inv.setItem(4, skull(p));
			inv.setItem(11, health(p));
			inv.setItem(12, defence(p));
			inv.setItem(13, speed(p));
			inv.setItem(14, luck(p));
			inv.setItem(15, critrate(p));
			inv.setItem(21, critdamage(p));
			inv.setItem(22, physicaldamage(p));
			inv.setItem(23, magicdamage(p));

		}

		return inv;

	}

	public ItemStack skull(Player p) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta im = (SkullMeta) item.getItemMeta();
		im.setOwningPlayer(p);
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l" + p.getName()));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c »&7 Class: " + files.getClass(p)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c »&7 Level: " + files.getLevel(p, files.getClass(p))));
		lore.add(ChatColor.translateAlternateColorCodes('&',
				"&c »&7 EXP: " + files.getExp(p, files.getClass(p)) + "/" + files.expToLevel(p)));

		im.setLore(lore);

		item.setItemMeta(im);
		return item;
	}

	public ItemStack health(Player p) {
		ItemStack item = new ItemStack(Material.APPLE);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aHealth"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.Health.get(p)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Health is determined by your"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* level and armor. Health % stats"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* scale off of your class' base health"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* not total health."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

	public ItemStack defence(Player p) {
		ItemStack item = new ItemStack(Material.SHIELD);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aDefence"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.Defence.get(p)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Defence is a stat that reduces"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* overall damage taken from enemies."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

	public ItemStack luck(Player p) {
		ItemStack item = new ItemStack(Material.RABBIT_FOOT);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLuck"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.Luck.get(p)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Luck increases the chance of items"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* dropping, and dropping at a higher"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* rarity."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

	public ItemStack speed(Player p) {
		ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSpeed"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.Speed.get(p)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Speed increases the movement speed"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* of your player."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

	public ItemStack critdamage(Player p) {
		ItemStack item = new ItemStack(Material.QUARTZ);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aCritical Damage"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.CritDamage.get(p) + "%"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Critical damage increases the damage"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* dealt when a critical hit lands."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

	public ItemStack critrate(Player p) {
		ItemStack item = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aCritical Rate"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.CritRate.get(p) + "%"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Critical rate increases the chance of "));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* your attacks landing as a critical hit."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

	public ItemStack physicaldamage(Player p) {
		ItemStack item = new ItemStack(Material.IRON_SWORD);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aPhysical Damage"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.PhysicalDamage.get(p)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Physical damage increases the base damage"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* of physical classes and skills."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

	public ItemStack magicdamage(Player p) {
		ItemStack item = new ItemStack(Material.HEART_OF_THE_SEA);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aMagicDamage"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c» &7" + StatsMain.MagicDamage.get(p)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* Magic damage increases the base damage"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7* dealt by magical classes and skills."));

		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}

}
