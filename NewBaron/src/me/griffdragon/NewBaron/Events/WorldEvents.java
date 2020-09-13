package me.griffdragon.NewBaron.Events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.griffdragon.NewBaron.BaronCore;

public class WorldEvents implements Listener {

	private final BaronCore main;

	public WorldEvents(BaronCore main) {
		this.main = main;
	}

	@EventHandler
	public void setMobLevel(EntitySpawnEvent e) {

		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();

			ent.setMetadata("1", new FixedMetadataValue(main, "level"));
		}

	}

}
