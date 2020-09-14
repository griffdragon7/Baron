package me.griffdragon.NewBaron.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Classes.Archer.ArcherMain;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import net.md_5.bungee.api.ChatColor;

public class ClassSelector implements Listener {

	private final ClassConfigFunctions files;
	private final ArcherMain archer;
	private final BaronCore main;

	public ClassSelector(ClassConfigFunctions files, ArcherMain archer, BaronCore main) {
		this.files = files;
		this.archer = archer;
		this.main = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			if (e.getClickedInventory() != null) {
				Player p = (Player) e.getWhoClicked();
				if (e.getView().getTitle() == "Choose a class") {
					e.setCancelled(true);
					if (e.getCurrentItem() != null) {
						if (e.getSlot() == 0) {
							if (files.hasClass(p, main.archer)) {
								files.setActiveClass(p, main.archer);
							} else {
								p.sendMessage(ChatColor.RED + "You have not unlocked this class yet!");
							}
						}
						if (e.getSlot() == 1) {
							if (files.hasClass(p, main.cryomancer)) {
								files.setActiveClass(p, main.cryomancer);
							} else {
								p.sendMessage(ChatColor.RED + "You have not unlocked this class yet!");
							}
						} else {
							
						}
					}
				}

			}
		}
	}

	public Inventory i(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 27, "Choose a class");

		inv.setItem(0,
				selectorItem("Archer", p, Material.BOW, "A ranged class focused mainly on",
						"single target burst damage, great for defeating", "bosses or other strong enemies.", "Bow",
						"Physical", archer.PhysicalDamage, archer.Defence, archer.Health, archer.Speed));
		inv.setItem(1,
				selectorItem("Pyromancer", p, Material.STICK, "A ranged class focused mainly on",
						"AOE damage, great for defeating", "large waves of enemies.", "Staff", "Magical",
						archer.PhysicalDamage, archer.Defence, archer.Health, archer.Speed));
		inv.setItem(10,
				selectorItem("Cryomancer", p, Material.STICK, "A ranged class focused mainly on",
						"AOE damage, great for defeating", "bosses or other strong enemies.", "Staff", "Magical",
						archer.PhysicalDamage, archer.Defence, archer.Health, archer.Speed));
		inv.setItem(19,
				selectorItem("Geomancer", p, Material.STICK, "A ranged class focused mainly on",
						"AOE damage, great for defeating", "bosses or other strong enemies.", "Staff", "Magical",
						archer.PhysicalDamage, archer.Defence, archer.Health, archer.Speed));

		return inv;

	}

	public ItemStack selectorItem(String className, Player p, Material mat, String description1, String description2,
			String description3, String weaponType, String damageType, int damage, int defence, int health, int speed) {
		Material newMat = Material.REDSTONE;
		String color = "&c";
		if (files.hasClass(p, className)) {
			color = "&a";
			newMat = mat;
		}
		ItemStack i = new ItemStack(newMat);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				"&a&l" + className + "&8 - &7[" + files.getLevel(p, className) + "]"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&aClass Info:"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&a * &7Class Level: &a" + files.getLevel(p, className)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&a * &7Class EXP: &b" + files.getExp(p, className)));
		lore.add(ChatColor.translateAlternateColorCodes('&',
				"&a * &7Class Unlocked: " + color + files.hasClass(p, className)));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&a * &7Damage Type: &f" + damageType));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c* &8&m                               &c *"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&dClass Stats:"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&d * &7Weapon Type: &f" + weaponType));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&d * &7Base Damage: &f" + damage));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&d * &7Base Defence: &f" + defence));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&d * &7Base Health: &f" + health));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&d * &7Base Speed: &f" + speed));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&c* &8&m                               &c *"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&6Class Description:"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&6 * " + description1));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&6 * " + description2));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&6 * " + description3));

		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

}
