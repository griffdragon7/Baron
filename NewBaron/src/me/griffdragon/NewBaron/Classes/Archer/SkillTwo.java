package me.griffdragon.NewBaron.Classes.Archer;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Skills;

public class SkillTwo extends Skills {

	public SkillTwo(BaronCore main, Player p) {
		super(main);
		Location loc = p.getLocation();
		loc.add(0, 1.5, 0);

		Arrow arrow = p.getWorld().spawnArrow(loc, loc.getDirection(), 4, 1);
		arrow.setShooter(p);
		arrow.setKnockbackStrength(0);
		p.getInventory().setHeldItemSlot(0);
		p.playSound(loc, Sound.ENTITY_IRON_GOLEM_DAMAGE, 2, .1F);
		arrow.setMetadata(ArcherMain.skillTwoMetadata, new FixedMetadataValue(main, "arrowData"));
		new BukkitRunnable() {

			public void run() {
				if (!arrow.isDead()) {
					loc.getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 3, 0, 0, 0, 0, new Particle.DustOptions(Color.WHITE, 1F));
				} else {
					this.cancel();
				}

			}
		}.runTaskTimer(main, 0, 1);
	}

}
