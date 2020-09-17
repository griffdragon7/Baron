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

public class SkillOne extends Skills {

	public SkillOne(BaronCore main, ArcherMain archer, Player p) {
		super(main);

		Location loc = p.getLocation();
		loc.add(0, 1.5, 0);
		Arrow arrow = p.getWorld().spawnArrow(loc, loc.getDirection(), 4, 1);
		arrow.setShooter(p);
		arrow.setKnockbackStrength(4);
		p.playSound(loc, Sound.ENTITY_BLAZE_SHOOT, 2, .7F);
		p.getInventory().setHeldItemSlot(0);
		arrow.setMetadata(ArcherMain.skillOneMetadata, new FixedMetadataValue(main, "arrowData"));
		new BukkitRunnable() {

			public void run() {
				if (!arrow.isDead()) {
					loc.getWorld().spawnParticle(Particle.FLAME, arrow.getLocation(), 3, 0, 0, 0, 0);
				} else {
					this.cancel();
				}

			}
		}.runTaskTimer(main, 0, 1);
	}

}
