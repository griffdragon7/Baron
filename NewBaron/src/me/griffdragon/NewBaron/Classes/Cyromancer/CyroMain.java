package me.griffdragon.NewBaron.Classes.Cyromancer;

import java.util.ArrayList;

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
import me.griffdragon.NewBaron.Classes.Archer.SkillTwo;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import net.md_5.bungee.api.ChatColor;

public class CyroMain implements Listener {

	private final BaronCore main;
	private final ClassConfigFunctions config;

	public CyroMain(BaronCore main, ClassConfigFunctions config) {
		this.main = main;
		this.config = config;
	}

	// cooldowns for skills
	public ArrayList<Player> primaryCooldown = new ArrayList<Player>();
	public ArrayList<Player> skill1Cooldown = new ArrayList<Player>();
	public ArrayList<Player> skill2Cooldown = new ArrayList<Player>();
	public ArrayList<Player> skill3Cooldown = new ArrayList<Player>();

	// cooldown times (in seconds)
	public double primaryCooldownTime = .5;
	public double skill1CooldownTime = 10;
	public double skill2CooldownTime = 20;
	public double skill3CooldownTime = 30;

	// metadata for arrows

	public static String primaryMetadata = "archerPrimary";
	public static String skillOneMetadata = "archerOne";
	public static String skillTwoMetadata = "archerTwo";
	public static String skillThreeMetadata = "archerThree";

	// skill multipliers
	public double primaryMultiplier = 1;
	public double skill1Multiplier = 3;
	public double skill2Multiplier = 1;
	public double skill3Multiplier = 1;
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
			if (config.getClass(p).equalsIgnoreCase(main.cryomancer)) {
				if (e.getPlayer().getInventory().getHeldItemSlot() == 0) {
					if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
						if (!primaryCooldown.contains(p)) {
							primaryCooldown.add(p);
							new Primary(main, p);
							new BukkitRunnable() {

								public void run() {
									primaryCooldown.remove(p);

								}
							}.runTaskLater(main, (long) (primaryCooldownTime * 20));
						}
					}
				} else if (p.getInventory().getHeldItemSlot() == 1) {
					if (!skill1Cooldown.contains(p)) {
						skill1Cooldown.add(p);

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
						p.getInventory().setItem(2, skillTwoDisc());
						new BukkitRunnable() {

							public void run() {
								p.getInventory().setItem(2, skillTwo());
								skill2Cooldown.remove(p);

							}
						}.runTaskLater(main, (long) skill2CooldownTime * 20);
					}
				} else if (p.getInventory().getHeldItemSlot() == 3) {
					if (!skill3Cooldown.contains(p)) {
						skill3Cooldown.add(p);

						p.getInventory().setItem(3, skillThreeDisc());
						new BukkitRunnable() {

							public void run() {
								p.getInventory().setItem(3, skillThree());
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
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aChilling Wind &7(Right Click)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7A strong wind carries you in the "));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7direction you are looking dealing damage"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7to and freezing everything in its path."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e10 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillOneDisc() {
		ItemStack i = new ItemStack(Material.MUSIC_DISC_CHIRP);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cChilling Wind &7(Cooldown)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7A strong wind carries you in the "));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7direction you are looking dealing damage"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7to and freezing everything in its path."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e10 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillTwo() {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aFrozen Vortex &7(Right Click)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Summons a mighty cyclone that brings"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7all nearby enemies to its core."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e30 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillTwoDisc() {
		ItemStack i = new ItemStack(Material.MUSIC_DISC_CHIRP);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cFrozen Vortex &7(Cooldown)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Summons a mighty cyclone that brings"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7all nearby enemies to its core."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e30 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillThree() {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWinter's Blast &7(Right Click)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Damage all nearby enemies proprtionate"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7to your magic damage and  how frozen"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7the enemy is."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e25 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

	public ItemStack skillThreeDisc() {
		ItemStack i = new ItemStack(Material.MUSIC_DISC_CHIRP);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cWinter's Blast &7(Cooldown)"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Damage all nearby enemies proprtionate"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7to your magic damage and  how frozen"));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7the enemy is."));
		lore.add(ChatColor.translateAlternateColorCodes('&', " &a* &7Cooldown: &e25 Seconds"));

		im.setLore(lore);

		i.setItemMeta(im);

		return i;
	}

}
