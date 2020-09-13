package me.griffdragon.NewBaron.Items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;

public class Health {

	private final StatsMain stats;

	public Health(StatsMain stats) {
		this.stats = stats;
	}

	public int tallyStat(Player p) {
		int amount = 0;
		try {
			if (p.getInventory().getHelmet() != null) {
				ItemMeta im = p.getInventory().getHelmet().getItemMeta();
				getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getChestplate() != null) {
				ItemMeta im = p.getInventory().getChestplate().getItemMeta();
				getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getLeggings() != null) {
				ItemMeta im = p.getInventory().getLeggings().getItemMeta();
				getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getBoots() != null) {
				ItemMeta im = p.getInventory().getBoots().getItemMeta();
				getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (p.getInventory().getItem(0).getType() != Material.GREEN_STAINED_GLASS_PANE) {
				ItemMeta im = p.getInventory().getItem(0).getItemMeta();
				getAmount(im, p);
			}
		} catch (NullPointerException e) {

		}
		return amount;

	}

	public int getAmount(ItemMeta im, Player p) {
		for (int x = 0; x < im.getLore().size(); x++) {
			try {
				if (ChatColor.translateAlternateColorCodes('&', " * &7Health: &a")
						.equalsIgnoreCase(p.getInventory().getHelmet().getItemMeta().getLore().get(x).substring(2,
								ChatColor.stripColor(ItemGenerator.healthLore).length() + 6))) {
					if (im.getLore().get(x).substring(im.getLore().get(x).length() - 1).equalsIgnoreCase("%")) {
						double y = Integer.parseInt(im.getLore().get(x).substring(
								im.getLore().get(x).lastIndexOf("a") + 1, im.getLore().get(x).lastIndexOf("%")));
						y = y / 100;
						return (int) (stats.getClassHealth(p) * (y));
					} else {
						return Integer
								.parseInt(im.getLore().get(x).substring(im.getLore().get(x).lastIndexOf("a") + 1));

					}
				}
			} catch (StringIndexOutOfBoundsException e) {

			}
		}
		return 0;
	}

}
