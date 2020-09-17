package me.griffdragon.NewBaron.Classes.Archer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import me.griffdragon.NewBaron.BaronCore;

public class SkillTwo {
 
	public SkillTwo(BaronCore main, Player p) {
		Location loc = p.getLocation();
		loc.add(0, 1.5, 0);
		BlockData data = Bukkit.createBlockData(Material.ANVIL);
		FallingBlock block = p.getWorld().spawnFallingBlock(loc, data);
		Arrow arrow = p.getWorld().spawnArrow(loc, loc.getDirection(), 3, 1);
		arrow.setShooter(p);
		arrow.setKnockbackStrength(0);
		arrow.addPassenger(block);
		p.getInventory().setHeldItemSlot(0);
		p.playSound(loc, Sound.ENTITY_ARROW_SHOOT, 2, 2);
		arrow.setMetadata(ArcherMain.skillTwoMetadata, new FixedMetadataValue(main, "arrowData"));
	}

}
