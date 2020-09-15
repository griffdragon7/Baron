package me.griffdragon.NewBaron.Functions;

import java.util.Random;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
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
		Random rand = new Random();
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				if (e.getCause() == DamageCause.ENTITY_ATTACK) {
					Player p = (Player) e.getDamager();
					String color = "&c";
					double damage = 0;
					double enemyHp = 20;
					double critDamage = (double) StatsMain.CritDamage.get(p) / 100;
					if (BaronCore.magicalClasses.contains(files.getClass(p))) {

						if (rand.nextInt(100) < StatsMain.CritRate.get(p)) {
							damage = StatsMain.MagicDamage.get(p) + (StatsMain.MagicDamage.get(p) * critDamage);
							color = "&4";
						} else {

							damage = StatsMain.MagicDamage.get(p);
						}
					} else if (BaronCore.physicalClasses.contains(files.getClass(p))) {
						if (rand.nextInt(100) < StatsMain.CritRate.get(p)) {
							damage = StatsMain.PhysicalDamage.get(p) + (StatsMain.PhysicalDamage.get(p) * critDamage);
							color = "&4";

						} else {
							
							damage = StatsMain.PhysicalDamage.get(p);
						}
					}
					if (e.getEntity().hasMetadata("1")) {
						enemyHp = mobs.level1Health;
					}
					if (e.getEntity().hasMetadata("2")) {
						enemyHp = mobs.level2Health;
					}
					if (e.getEntity().hasMetadata("3")) {
						enemyHp = mobs.level3Health;
					}
					if (e.getEntity().hasMetadata("4")) {
						enemyHp = mobs.level4Health;
					}
					if (e.getEntity().hasMetadata("5")) {
						enemyHp = mobs.level5Health;
					}
					if (e.getEntity().hasMetadata("6")) {
						enemyHp = mobs.level6Health;
					}
					if (e.getEntity().hasMetadata("7")) {
						enemyHp = mobs.level7Health;
					}
					if (e.getEntity().hasMetadata("8")) {
						enemyHp = mobs.level8Health;
					}
					if (e.getEntity().hasMetadata("9")) {
						enemyHp = mobs.level9Health;
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
					sendActionbar(p, ChatColor.translateAlternateColorCodes('&', "&7Damage Dealt: " + color + damage
							+ " &8 | &7Mob Health: &6" + hpNumber + "/" + (int) enemyHp));

				}
			}
		}
	}

}
