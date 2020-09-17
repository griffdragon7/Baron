package me.griffdragon.NewBaron.Functions;

import java.util.Random;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Classes.Archer.ArcherMain;
import me.griffdragon.NewBaron.Mobs.BasicMobs;
import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class DamageSystem implements Listener {

	private final ClassConfigFunctions files;
	private final BasicMobs mobs;

	public DamageSystem(ClassConfigFunctions files, BasicMobs mobs) {
		this.files = files;
		this.mobs = mobs;
	}

	public void sendActionbar(Player p, String s) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				if (e.getCause() == DamageCause.ENTITY_ATTACK) {
					Player p = (Player) e.getDamager();
					String color = "&c";

					double proportion = determineProportion(p, ent);
					// multiply the proportion by the real amount of hp to get actual damage
					double finaldamage = proportion * ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

					e.setDamage(finaldamage);
					// get a proprtion for the enemies hp
					double fakeHPProp = ent.getHealth() / ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
					// multiply proportion by enemyHP to get fake hp left
					int hpNumber = (int) ((fakeHPProp * determineEnemyHp(ent)) - determineDamage(p, ent));
					sendActionbar(p,
							ChatColor.translateAlternateColorCodes('&',
									"&7Damage Dealt: " + color + determineDamage(p, ent) + " &8 | &7Mob Health: &6"
											+ hpNumber + "/" + (int) determineEnemyHp(ent)));

				}
			}
		}
	}

	@EventHandler
	public void magicDamageMessage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				if (e.getCause() == DamageCause.CUSTOM) {
					// get a proprtion for the enemies hp
					double fakeHPProp = ent.getHealth() / ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
					// multiply proportion by enemyHP to get fake hp left
					int hpNumber = (int) ((fakeHPProp * determineEnemyHp(ent)));

					double damageProportion = e.getDamage() / ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

					double damage = damageProportion * determineEnemyHp(ent);

					sendActionbar(p, ChatColor.translateAlternateColorCodes('&', "&7Damage Dealt: &c" + damage
							+ " &8 | &7Mob Health: &6" + hpNumber + "/" + (int) determineEnemyHp(ent)));
				}
			}
		}
	}

	@EventHandler
	public void playerArrowDamages(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Arrow) {

				Arrow arrow = (Arrow) e.getDamager();
				if (arrow.getShooter() instanceof Player) {
					Player p = (Player) arrow.getShooter();
					if (arrow.hasMetadata(ArcherMain.primaryMetadata)) {

						String color = "&c";
						double proportion = determineProportion(p, ent);
						// multiply the proportion by the real amount of hp to get actual damage
						double finaldamage = proportion * ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

						e.setDamage(finaldamage);
						// get a proprtion for the enemies hp
						double fakeHPProp = ent.getHealth() / ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
						// multiply proportion by enemyHP to get fake hp left
						int hpNumber = (int) ((fakeHPProp * determineEnemyHp(ent)) - determineDamage(p, ent));
						sendActionbar(p,
								ChatColor.translateAlternateColorCodes('&',
										"&7Damage Dealt: " + color + determineDamage(p, ent) + " &8 | &7Mob Health: &6"
												+ hpNumber + "/" + (int) determineEnemyHp(ent)));
					}
					if (arrow.hasMetadata(ArcherMain.skillOneMetadata)) {

					}
				}
			}
		}

	}

	public double determineProportion(Player p, Entity ent) {

		return determineDamage(p, ent) / determineEnemyHp(ent);
	}

	public double determineDamage(Player p, Entity ent) {
		Random rand = new Random();
		double damage = 0;
		double critDamage = (double) StatsMain.CritDamage.get(p) / 100;
		if (BaronCore.magicalClasses.contains(files.getClass(p))) {

			if (rand.nextInt(100) < StatsMain.CritRate.get(p)) {
				damage = StatsMain.MagicDamage.get(p) + (StatsMain.MagicDamage.get(p) * critDamage);

			} else {

				damage = StatsMain.MagicDamage.get(p);
			}
		} else if (BaronCore.physicalClasses.contains(files.getClass(p))) {
			if (rand.nextInt(100) < StatsMain.CritRate.get(p)) {
				damage = StatsMain.PhysicalDamage.get(p) + (StatsMain.PhysicalDamage.get(p) * critDamage);

			} else {

				damage = StatsMain.PhysicalDamage.get(p);
			}
		}
		// getting proportion for enemy hp left
		return damage;
	}

	public double determineEnemyHp(Entity ent) {

		double enemyHp = 20;

		if (ent.hasMetadata("1")) {
			enemyHp = mobs.level1Health;
		}
		if (ent.hasMetadata("2")) {
			enemyHp = mobs.level2Health;
		}
		if (ent.hasMetadata("3")) {
			enemyHp = mobs.level3Health;
		}
		if (ent.hasMetadata("4")) {
			enemyHp = mobs.level4Health;
		}
		if (ent.hasMetadata("5")) {
			enemyHp = mobs.level5Health;
		}
		if (ent.hasMetadata("6")) {
			enemyHp = mobs.level6Health;
		}
		if (ent.hasMetadata("7")) {
			enemyHp = mobs.level7Health;
		}
		if (ent.hasMetadata("8")) {
			enemyHp = mobs.level8Health;
		}
		if (ent.hasMetadata("9")) {
			enemyHp = mobs.level9Health;
		}
		// getting proportion for enemy hp left
		return enemyHp;
	}

}
