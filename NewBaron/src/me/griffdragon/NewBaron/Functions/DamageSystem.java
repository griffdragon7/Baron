package me.griffdragon.NewBaron.Functions;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Mobs.BasicMobs;
import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class DamageSystem implements Listener {

	private final ClassConfigFunctions files;
	private final BasicMobs mobs;

	public DamageSystem(ClassConfigFunctions files, BasicMobs mobs) {
		this.files = files;
		this.mobs = mobs;
	}

	public void sendActionbar(Player p, String s) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				if (e.getCause() == DamageCause.ENTITY_ATTACK) {
					Player p = (Player) e.getDamager();
					double damage = 0;
					double enemyHp = 20;
					if (BaronCore.magicalClasses.contains(files.getClass(p))) {
						damage = StatsMain.MagicDamage.get(p);
					} else if (BaronCore.physicalClasses.contains(files.getClass(p))) {
						damage = StatsMain.PhysicalDamage.get(p);
					}
					if (e.getEntity().hasMetadata("1")) {
						enemyHp = mobs.level1Health;
					}
					// getting proportion for enemy hp left
					double proportion = damage / enemyHp;
					// multiply the proportion by the real amount of hp to get actual damage
					double finaldamage = proportion * ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

					e.setDamage(finaldamage);
					// get a proprtion for the enemies hp
					double fakeHPProp = ent.getHealth() / ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
					// multiply proportion by enemyHP to get fake hp left
					int hpNumber = (int) ((fakeHPProp * enemyHp) - damage);
					sendActionbar(p, ChatColor.translateAlternateColorCodes('&',
							"&7Damage Dealt: &c" + damage + " &8 | &7Mob Health: &6" + hpNumber + "/" + (int) enemyHp));

				}
			}
		}
	}

}
