package me.griffdragon.NewBaron.Classes.Archer;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Skills;

public class Primary extends Skills {

	public Primary(BaronCore main, ArcherMain archer, Player p) {
		super(main);

		if (archer.archerUltimate.contains(p)) {
			new BukkitRunnable() {
				int x = 0;

				@Override
				public void run() {
					x++;
					if (x > 5) {
						this.cancel();
					}
					Location loc = p.getLocation();
					loc.add(0, 1.5, 0);
					Arrow arrow = p.getWorld().spawnArrow(loc, loc.getDirection(), 4, 0);
					arrow.setShooter(p);
					arrow.setKnockbackStrength(0);
					p.playSound(loc, Sound.ENTITY_ARROW_SHOOT, 2, 2);
					arrow.setMetadata(ArcherMain.primaryMetadata, new FixedMetadataValue(main, "arrowData"));
					loc.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, loc.subtract(0, 1, 0), 1, 1, 1, 1, .2F);
					loc.getWorld().spawnParticle(Particle.CLOUD, loc.subtract(0, 2, 0), 1, 0, 0, 0, 0);

				}
			}.runTaskTimer(main, 0, 2);
		} else {
			Location loc = p.getLocation();
			loc.add(0, 1.5, 0);
			Arrow arrow = p.getWorld().spawnArrow(loc, loc.getDirection(), 4, 0);
			arrow.setShooter(p);
			arrow.setKnockbackStrength(0);
			p.playSound(loc, Sound.ENTITY_ARROW_SHOOT, 2, 2);
			arrow.setMetadata(ArcherMain.primaryMetadata, new FixedMetadataValue(main, "arrowData"));
		}
	}

}
