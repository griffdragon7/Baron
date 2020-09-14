package me.griffdragon.NewBaron.Events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.griffdragon.NewBaron.BaronCore;
import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import me.griffdragon.NewBaron.Functions.PlayerFiles;
import me.griffdragon.NewBaron.Stats.StatsMain;
import net.md_5.bungee.api.ChatColor;

public class PlayerEvents implements Listener {

	private final ClassConfigFunctions files;
	private final StatsMain stats;
	private final BaronCore main;

	public PlayerEvents(ClassConfigFunctions files, StatsMain stats, BaronCore main) {
		this.files = files;
		this.stats = stats;
		this.main = main;
	}

	public BossBar b(Player p) {

		float maxHP = stats.getHealth(p);
		float health = (float) p.getHealth();
		float proportion = health / 20;
		float finalHP = maxHP * proportion;
		BossBar b = Bukkit.createBossBar(
				ChatColor.translateAlternateColorCodes('&', "&7Health: &a" + (int) finalHP + "&c \u2764"),
				BarColor.GREEN, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);
		b.setProgress(proportion);
		return b;
	}

	public HashMap<Player, BossBar> bars = new HashMap<Player, BossBar>();

	@EventHandler
	public void healthbar(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		BossBar bar = b(p);
		bar.addPlayer(p);
		bars.put(p, bar);
	}

	@EventHandler
	public void updateHealth(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();

			if (e.getEntity() instanceof Player) {
				new BukkitRunnable() {

					public void run() {
						double maxHP = stats.getHealth(p);
						double health = p.getHealth();
						double proportion = health / 20;
						double finalHP = maxHP * proportion;
						bars.get(p).setTitle(ChatColor.translateAlternateColorCodes('&',
								"&7Health: &a" + (int) finalHP + "&c \u2764"));
						bars.get(p).setProgress(proportion);

					}
				}.runTaskLater(main, 1L);

			}
		}
	}

	@EventHandler
	public void updateBar(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();

			new BukkitRunnable() {

				@Override
				public void run() {
					double maxHP = stats.getHealth(p);
					double health = p.getHealth();
					double proportion = health / 20;
					double finalHP = maxHP * proportion;
					bars.get(p).setTitle(
							ChatColor.translateAlternateColorCodes('&', "&7Health: &a" + (int) finalHP + "&c \u2764"));
					bars.get(p).setProgress(proportion);

				}
			}.runTaskLater(main, 1L);

		}

	}

	@EventHandler
	public void setupPlayerFiles(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		PlayerFiles file = new PlayerFiles(p);
		if (file.getPlayerFile().getString(p.getUniqueId().toString() + ".Info.Active") == null) {
			files.setUpPlayerFile(p);
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "New player, creating their file...");
		} else {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.GRAY + "Player already has a player file, proceeding normally...");
		}
	}

}
