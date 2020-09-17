package me.griffdragon.NewBaron.Classes.Archer;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;

public class ArcherMain implements Listener {

	private final ClassConfigFunctions config;

	private final BaronCore main;

	public ArcherMain(BaronCore main, ClassConfigFunctions config) {
		this.config = config;
		this.main = main;
	}

	// arraylist for ultimate
	public ArrayList<Player> archerUltimate = new ArrayList<Player>();

	// cooldowns for skills
	public ArrayList<Player> primaryCooldown = new ArrayList<Player>();
	public ArrayList<Player> skill1Cooldown = new ArrayList<Player>();
	public ArrayList<Player> skill2Cooldown = new ArrayList<Player>();
	public ArrayList<Player> skill3Cooldown = new ArrayList<Player>();

	// cooldown times (in seconds)
	public double primaryCooldownTime = .7;
	public double skill1CooldownTime = 10;
	public double skill2CooldownTime = 20;
	public double skill3CooldownTime = 30;

	// metadata for arrows

	public static String primaryMetadata = "archerPrimary";
	public static String skillOneMetadata = "archerOne";
	public static String skillTwoMetadata = "archerTwo";
	public static String skillThreeMetadata = "archerThree";

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

	@EventHandler
	public void archerSkills(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player p = e.getPlayer();
			if (config.getClass(p).equalsIgnoreCase(main.archer)) {
				if (e.getPlayer().getInventory().getHeldItemSlot() == 0) {
					if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BOW)) {
						if (!primaryCooldown.contains(p)) {
							primaryCooldown.add(p);
							new Primary(main, this, p);
							new BukkitRunnable() {

								public void run() {
									primaryCooldown.remove(p);

								}
							}.runTaskLater(main, (long) primaryCooldownTime * 20);
						}
					}
				} else if (p.getInventory().getHeldItemSlot() == 1) {
					if (!skill1Cooldown.contains(p)) {
						skill1Cooldown.add(p);
						new SkillOne(main, this, p);
						p.getInventory().setItem(1, skillOneDisc());
						new BukkitRunnable() {

							public void run() {
								p.getInventory().setItem(1, skillOne());
								skill1Cooldown.remove(p);

							}
						}.runTaskLater(main, (long) skill1CooldownTime * 20);
					}
				} else if (p.getInventory().getHeldItemSlot() == 2) {
					if (!skill2Cooldown.contains(p)) {
						skill2Cooldown.add(p);
						new SkillTwo(main, p);
						p.getInventory().setItem(1, skillTwoDisc());
						new BukkitRunnable() {

							public void run() {
								p.getInventory().setItem(1, skillTwo());
								skill2Cooldown.remove(p);

							}
						}.runTaskLater(main, (long) skill2CooldownTime * 20);
					}
				} else if (p.getInventory().getHeldItemSlot() == 3) {
					if (!skill3Cooldown.contains(p)) {
						skill3Cooldown.add(p);
						new SkillThree(p, main, this);
						p.getInventory().setItem(1, skillThreeDisc());
						new BukkitRunnable() {

							public void run() {
								p.getInventory().setItem(1, skillThree());
								skill3Cooldown.remove(p);

							}
						}.runTaskLater(main, (long) skill3CooldownTime * 20);
					}
				}
			}
		}
	}

	public ItemStack skillOne() {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aConcussive Shot &7(Right Click)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Shoots a strong shot that knocks"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7the target back with a powerful force."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e5 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillOneDisc() {
		ItemStack i = new ItemStack(Material.MUSIC_DISC_CHIRP);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cConcussive Shot &7(Cooldown)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Shoots a strong shot that knocks"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7the target back with a powerful force."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e5 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillTwo() {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aFull Metal Arrow &7(Right Click)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Launches a giant anvil from"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7your bow to deal more damage"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7proportionate to distance."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e10 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillTwoDisc() {
		ItemStack i = new ItemStack(Material.MUSIC_DISC_CHIRP);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cFull Metal Arrow &7(Cooldown)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Launches a giant anvil from"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7your bow to deal more damage"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7proportionate to distance."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e10 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillThree() {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aArcher's Glory &7(Right Click)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Take a huge leap into the air descending slowly"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7and multiplying shot rate by 5 for a short"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7period of time."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e25 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillThreeDisc() {
		ItemStack i = new ItemStack(Material.MUSIC_DISC_CHIRP);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cArcher's Glory &7(Cooldown)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Take a huge leap into the air descending slowly"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7and multiplying shot rate by 5 for a short"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7period of time."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e25 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

}
