package me.griffdragon.NewBaron.Classes.Archer;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Skills;

public class SkillThree extends Skills {

	public SkillThree(Player p, BaronCore main, ArcherMain archer) {
		super(main);
		p.setVelocity(new Vector(0, 2, 0));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 3));
		archer.archerUltimate.add(p);
		p.getInventory().setHeldItemSlot(0);
		p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SCREAM, 2, .1F);
		new BukkitRunnable() {

			public void run() {
				archer.archerUltimate.remove(p);

			}
		}.runTaskLater(main, 120L);
	}

}
