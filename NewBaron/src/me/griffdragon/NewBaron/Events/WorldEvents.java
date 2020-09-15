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
	
	int level1 = 100;
	int level2 = 200;
	int level3 = 300;
	int level4 = 400;
	int level5 = 500;
	int level6 = 600;
	int level7 = 700;
	int level8 = 800;
	int level9 = 900;
	
	@EventHandler
	public void setMobLevel(EntitySpawnEvent e) {

		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			Location spawn = ent.getLocation().getWorld().getSpawnLocation();
			Location mobLocation = e.getLocation();
			new BukkitRunnable() {

				@Override
				public void run() {
					if (spawn.distance(mobLocation) < level1) {
						ent.setMetadata("1", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 1");
					} else if (spawn.distance(mobLocation) < level2 && spawn.distance(mobLocation) > level1) {
						ent.setMetadata("2", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 2");
					} else if (spawn.distance(mobLocation) < level3 && spawn.distance(mobLocation) > level2) {
						ent.setMetadata("3", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 3");
					} else if (spawn.distance(mobLocation) < level4 && spawn.distance(mobLocation) > level3) {
						ent.setMetadata("4", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 4");
					} else if (spawn.distance(mobLocation) < level5 && spawn.distance(mobLocation) > level4) {
						ent.setMetadata("5", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 5");
					} else if (spawn.distance(mobLocation) < level6 && spawn.distance(mobLocation) > level5) {
						ent.setMetadata("6", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 6");
					} else if (spawn.distance(mobLocation) < level7 && spawn.distance(mobLocation) > level6) {
						ent.setMetadata("7", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 7");
					} else if (spawn.distance(mobLocation) < level8 && spawn.distance(mobLocation) > level7) {
						ent.setMetadata("8", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 8");
					} else if (spawn.distance(mobLocation) > level8) {
						ent.setMetadata("9", new FixedMetadataValue(main, "level"));
						ent.setCustomNameVisible(true);
						ent.setCustomName("Level 9");
					}

				}
			}.runTaskLater(main, 1l);

		}

	}

}
