package me.griffdragon.NewBaron.Events;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

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
			Location spawn = ent.getLocation().getWorld().getSpawnLocation();
			Location mobLocation = e.getLocation();
			new BukkitRunnable() {

				@Override
				public void run() {
					if (spawn.distance(mobLocation) < 50) {
						ent.setMetadata("1", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 1");
					} else if (spawn.distance(mobLocation) < 100 && spawn.distance(mobLocation) > 50) {
						ent.setMetadata("2", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 2");
					} else if (spawn.distance(mobLocation) < 200 && spawn.distance(mobLocation) > 100) {
						ent.setMetadata("3", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 3");
					} else if (spawn.distance(mobLocation) < 300 && spawn.distance(mobLocation) > 200) {
						ent.setMetadata("4", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 4");
					} else if (spawn.distance(mobLocation) < 400 && spawn.distance(mobLocation) > 300) {
						ent.setMetadata("5", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 5");
					} else if (spawn.distance(mobLocation) < 500 && spawn.distance(mobLocation) > 400) {
						ent.setMetadata("6", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 6");
					} else if (spawn.distance(mobLocation) < 600 && spawn.distance(mobLocation) > 500) {
						ent.setMetadata("7", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 7");
					} else if (spawn.distance(mobLocation) < 700 && spawn.distance(mobLocation) > 600) {
						ent.setMetadata("8", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 8");
					} else if (spawn.distance(mobLocation) > 700) {
						ent.setMetadata("9", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 9");
					}

				}
			}.runTaskLater(main, 1l);

		}

	}

}
