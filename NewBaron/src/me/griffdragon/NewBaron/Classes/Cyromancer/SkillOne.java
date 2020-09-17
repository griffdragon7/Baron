package me.griffdragon.NewBaron.Classes.Cyromancer;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Skills;
import me.griffdragon.NewBaron.Functions.DamageSystem;

public class SkillOne extends Skills {

	public SkillOne(BaronCore main, Player p, DamageSystem damageSystem, CyroMain cryo) {
		super(main);
		new BukkitRunnable() {

			int x = 0;

			public void run() {
				x++;
				Vector dir = p.getLocation().getDirection().normalize();
				p.setVelocity(dir);
				p.getLocation().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 20, .4, 0, .4, 0);
				p.getLocation().getWorld().spawnParticle(Particle.SPIT, p.getLocation(), 15, 1, 2, 1, 0);

				p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
				for (Entity es : p.getNearbyEntities(1, 1, 1)) {
					if (es != p) {
						if (es.getType() != EntityType.ARMOR_STAND) {
							if (es instanceof LivingEntity) {

								((LivingEntity) es)
										.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, true));
								double damage = damageSystem.determineProportion(p, es)
										* ((LivingEntity) es).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
								((LivingEntity) es).damage(damage * cryo.skill1Multiplier, p);
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, .5F);
							}
						}
					}
				}
				if (x > 10) {
					this.cancel();
				}

			}
		}.runTaskTimer(main, 0, 1);
	}

}
