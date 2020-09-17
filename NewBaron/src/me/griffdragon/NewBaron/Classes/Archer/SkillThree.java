package me.griffdragon.NewBaron.Classes.Archer;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.griffdragon.NewBaron.BaronCore;

public class SkillThree {

	public SkillThree(Player p, BaronCore main, ArcherMain archer) {
		p.setVelocity(new Vector(0, 2, 0));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 3));
		archer.archerUltimate.add(p);
		new BukkitRunnable() {

			public void run() {
				archer.archerUltimate.remove(p);

			}
		}.runTaskLater(main, 120L);
	}

}
