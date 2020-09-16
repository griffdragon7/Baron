package me.griffdragon.NewBaron.Classes.Archer;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import me.griffdragon.NewBaron.BaronCore;

public class SkillOne {

	public SkillOne(BaronCore main, ArcherMain archer, Player p) {
		Location loc = p.getLocation();
		loc.add(0, 1.5, 0);
		Arrow arrow = p.getWorld().spawnArrow(loc, loc.getDirection(), 4, 1);
		arrow.setShooter(p);
		arrow.setKnockbackStrength(4);
		p.playSound(loc, Sound.ENTITY_ARROW_SHOOT, 2, 2);
		arrow.setMetadata(ArcherMain.skillOneMetadata, new FixedMetadataValue(main, "arrowData"));
	}

}
