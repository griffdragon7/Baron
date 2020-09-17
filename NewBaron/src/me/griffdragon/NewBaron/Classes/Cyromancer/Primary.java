package me.griffdragon.NewBaron.Classes.Cyromancer;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Stats.StatsMain;

public class Primary {

	public Primary(BaronCore main, Player p) {
		Random rand = new Random();

		p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 10, 10);

		new BukkitRunnable() {
			ArrayList<Entity> damage = new ArrayList<Entity>();
			double t = 0;
			Location loc = p.getLocation();
			Vector direction = loc.getDirection()
					.add(new Vector(0.0D + 0.0D * rand.nextDouble(), 0.0D + 0.0D * rand.nextDouble(),
							0.0D + 0.0D * rand.nextDouble())
									.subtract(new Vector(0.0D + 0.0D * rand.nextDouble(),
											0.0D + 0.0D * rand.nextDouble(), 0.0D + 0.0D * rand.nextDouble())));

			public void run() {
				// for (double t = 0; t < 8; t = t + 0.5) {
				t = t + .7;
				double x = direction.getX() * t;
				double y = direction.getY() * t + 1.5;
				double z = direction.getZ() * t;

				loc.add(x, y, z);

				loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0, 1,
						new Particle.DustOptions(Color.AQUA, 1));
				loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0, 1,
						new Particle.DustOptions(Color.WHITE, 2));
				loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0, 1,
						new Particle.DustOptions(Color.BLACK, 1));
				for (Entity es : loc.getWorld().getNearbyEntities(loc, .5, .5, .5)) {
					if (es instanceof LivingEntity) {
						if (es != p) {
							if (!damage.contains(es)) {
								if (es.getType() != EntityType.ARMOR_STAND) {
									damage.add(es);
									new BukkitRunnable() {

										public void run() {
											damage.remove(es);

										}
									}.runTaskLater(main, 2l);
									((LivingEntity) es).damage(StatsMain.MagicDamage.get(p), p);

									((LivingEntity) es).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0));
									p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
									loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 9, .5, .5, .5, 1,
											new Particle.DustOptions(Color.WHITE, 2));
									this.cancel();
								}
							}
						}
					}
				}
				loc.subtract(x, y, z);
				if (t > 16) {
					this.cancel();

				}

			}
		}.runTaskTimer(main, 0, 1);
	}

}
