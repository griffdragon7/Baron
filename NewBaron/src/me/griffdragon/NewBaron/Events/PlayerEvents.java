package me.griffdragon.NewBaron.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.griffdragon.NewBaron.Functions.ClassConfigFunctions;
import me.griffdragon.NewBaron.Functions.PlayerFiles;
import net.md_5.bungee.api.ChatColor;

public class PlayerEvents implements Listener {

	private final ClassConfigFunctions files;

	public PlayerEvents(ClassConfigFunctions files) {
		this.files = files;
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
