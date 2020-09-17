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
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Mobs.BasicMobs;
import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class DamageSystem implements Listener {

	private final ClassConfigFunctions files;
	private final BasicMobs mobs;
	private final BaronCore main;

	public DamageSystem(ClassConfigFunctions files, BaronCore main, BasicMobs mobs) {
		this.files = files;
		this.main = main;
		this.mobs = mobs;
	}

	public void sendActionbar(Player p, String s) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		//DEFAULT MINECRAFT ATTACK
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				if (e.getCause() == DamageCause.ENTITY_ATTACK) {
					Player p = (Player) e.getDamager();

					double proportion = determineProportion(p, ent);
					// multiply the proportion by the real amount of hp to get actual damage
					double finaldamage = proportion * ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

					e.setDamage(finaldamage);

				}
			}
		}
	}

	@EventHandler
	public void magicDamageMessage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			//SENDING DAMAGE MESSAGES
			if (e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				// get a proprtion for the enemies hp
				new BukkitRunnable() {

					@Override
					public void run() {
						double fakeHPProp = ent.getHealth() / ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
						// multiply proportion by enemyHP to get fake hp left

						double damageProportion = e.getDamage()
								/ ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

						double damage = damageProportion * determineEnemyHp(ent);

						int hpNumber = (int) (fakeHPProp * determineEnemyHp(ent));

						sendActionbar(p, ChatColor.translateAlternateColorCodes('&', "&7Damage Dealt: &c" + damage
								+ " &8 | &7Mob Health: &6" + hpNumber + "/" + (int) determineEnemyHp(ent)));
					}
				}.runTaskLater(main, 1);

			}
			//ARROW DAMAGE MANAGER
			if (e.getDamager() instanceof Arrow) {
				Arrow arrow = (Arrow) e.getDamager();
				if (arrow.getShooter() instanceof Player) {
					Player p = (Player) arrow.getShooter();
					new BukkitRunnable() {

						@Override
						public void run() {
							double fakeHPProp = ent.getHealth()
									/ ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
							// multiply proportion by enemyHP to get fake hp left

							double damageProportion = e.getDamage()
									/ ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

							double damage = damageProportion * determineEnemyHp(ent);

							int hpNumber = (int) (fakeHPProp * determineEnemyHp(ent));

							sendActionbar(p, ChatColor.translateAlternateColorCodes('&', "&7Damage Dealt: &c" + (int) damage
									+ " &8 | &7Mob Health: &6" + hpNumber + "/" + (int) determineEnemyHp(ent)));
						}
					}.runTaskLater(main, 1);
				}
			}
		}
	}
	
	@EventHandler
	public void removeArrows(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Arrow) {
			e.getEntity().remove();
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
