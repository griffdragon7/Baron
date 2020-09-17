package me.griffdragon.NewBaron.Classes.Bard;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;

import me.griffdragon.NewBaron.BaronCore;

public class Primary {

	
	
	public Primary(Player p, Action action, BaronCore main) {
		Location loc = p.getLocation();
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			ThrownPotion potion = (ThrownPotion) loc.getWorld().spawnEntity(loc.add(0, 1.5, 0),
					EntityType.SPLASH_POTION);
			potion.setVelocity(loc.getDirection().multiply(1.1));
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			PotionMeta itemMeta = (PotionMeta) item.getItemMeta();
			itemMeta.setColor(Color.GREEN);
			item.setItemMeta(itemMeta);
			potion.setItem(item);
			potion.setMetadata(BardMain.healPotionData, new FixedMetadataValue(main, "potions"));

		} else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
			ThrownPotion potion = (ThrownPotion) loc.getWorld().spawnEntity(loc.add(0, 1.5, 0),
					EntityType.SPLASH_POTION);
			potion.setVelocity(loc.getDirection().multiply(1.1));
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			PotionMeta itemMeta = (PotionMeta) item.getItemMeta();
			itemMeta.setColor(Color.RED);

			item.setItemMeta(itemMeta);
			potion.setItem(item);
			potion.setMetadata(BardMain.damagePotionData, new FixedMetadataValue(main, "potions"));
		}

	}

}
