package me.griffdragon.NewBaron.Items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import me.griffdragon.NewBaron.BaronCore;
import net.md_5.bungee.api.ChatColor;

public class Health {

	private final BaronCore main;
	private final ItemGenerator items;

	public Health(BaronCore main, ItemGenerator items) {
		this.main = main;
		this.items = items;
	}

	public int tallyStat(Player p) {
		int amount = 0;
		try {
			if (p.getInventory().getHelmet() != null) {
				ItemMeta im = p.getInventory().getHelmet().getItemMeta();
				amount = amount + getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getChestplate() != null) {
				ItemMeta im = p.getInventory().getChestplate().getItemMeta();
				amount = amount + getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getLeggings() != null) {
				ItemMeta im = p.getInventory().getLeggings().getItemMeta();
				amount = amount + getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getBoots() != null) {
				ItemMeta im = p.getInventory().getBoots().getItemMeta();
				amount = amount + getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getItem(0).getType() != Material.GREEN_STAINED_GLASS_PANE) {
				ItemMeta im = p.getInventory().getItem(0).getItemMeta();
				amount = amount + getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		return amount;

	}

	public int getAmount(ItemMeta im, Player p) {
		int amount = 0;
		for (int x = 0; x < im.getLore().size(); x++) {
			try {
				if (ChatColor.translateAlternateColorCodes('&', " * &7Health: &a").equalsIgnoreCase(
						im.getLore().get(x).substring(2, ChatColor.stripColor(items.healthLore).length() + 6))) {
					if (im.getLore().get(x).substring(im.getLore().get(x).length() - 1).equalsIgnoreCase("%")) {
						double y = Integer.parseInt(im.getLore().get(x).substring(
								im.getLore().get(x).lastIndexOf("a") + 1, im.getLore().get(x).lastIndexOf("%")));
						y = y / 100;
						amount = amount + (int) (main.stats.getBaseHealth(p) * (y));
					} else {
						amount = amount + Integer
								.parseInt(im.getLore().get(x).substring(im.getLore().get(x).lastIndexOf("a") + 1));

					}
				}
			} catch (StringIndexOutOfBoundsException e) {

			}
		}
		return amount;
	}

}
