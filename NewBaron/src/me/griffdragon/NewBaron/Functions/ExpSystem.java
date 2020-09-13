package me.griffdragon.NewBaron.Functions;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class ExpSystem implements Listener {

	private final ClassConfigFunctions files;

	public ExpSystem(ClassConfigFunctions files) {
		this.files = files;

	}

	@EventHandler
	public void onKillEntity(EntityDeathEvent e) {
		e.setDroppedExp(0);
	}

	@EventHandler
	public void entityDamageToDeath(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (e.getEntity() instanceof LivingEntity) {
				LivingEntity ent = (LivingEntity) e.getEntity();
				if (ent.hasMetadata("1")) {
					if (ent.getHealth() <= 0) {
						files.addExp(p, files.getClass(p), 100);
					}
				}
			}
		}
	}

}
