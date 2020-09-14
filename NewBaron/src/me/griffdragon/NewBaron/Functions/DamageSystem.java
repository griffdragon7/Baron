package me.griffdragon.NewBaron.Functions;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Mobs.BasicMobs;
import me.griffdragon.NewBaron.Stats.StatsMain;

public class DamageSystem implements Listener {

	private final ClassConfigFunctions files;
	private final BasicMobs mobs;

	public DamageSystem(ClassConfigFunctions files, BasicMobs mobs) {
		this.files = files;
		this.mobs = mobs;
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				int damage = 0;
				int enemyHp = 20;
				if (BaronCore.magicalClasses.contains(files.getClass(p))) {
					damage = StatsMain.MagicDamage.get(p);
				} else if (BaronCore.physicalClasses.contains(files.getClass(p))) {
					damage = StatsMain.PhysicalDamage.get(p);
				}
				if (e.getEntity().hasMetadata("1")) {
					enemyHp = mobs.level1Health;
				}

				double proportion = damage / enemyHp;
				double finaldamage = proportion * ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
				e.setDamage(finaldamage);

			}
		}
	}

}
