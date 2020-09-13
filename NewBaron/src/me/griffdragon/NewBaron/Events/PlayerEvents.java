package me.griffdragon.NewBaron.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.griffdragon.NewBaron.Functions.PlayerFiles;
import net.md_5.bungee.api.ChatColor;

public class PlayerEvents implements Listener {

	@EventHandler
	public void setupPlayerFiles(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		PlayerFiles file = new PlayerFiles(p);
		if (file.getPlayerFile() == null) {
			setUpPlayerFile(p);
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "New player, creating their file...");
		} else {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.GRAY + "Player already has a player file, proceeding normally...");
		}
	}

	public void setUpPlayerFile(Player p) {
		PlayerFiles file = new PlayerFiles(p);
		String uuid = p.getUniqueId().toString();
		file.getPlayerFile().set(uuid + ".Info.Active", "Archer");
		file.getPlayerFile().set(uuid + ".Classes.Archer.Level", 1);
		file.getPlayerFile().set(uuid + ".Classes.Archer.Exp", 0);
		file.getPlayerFile().set(uuid + ".Prof.Earth", 0);
		file.getPlayerFile().set(uuid + ".Prof.Water", 0);
		file.getPlayerFile().set(uuid + ".Prof.Air", 0);
		file.getPlayerFile().set(uuid + ".Prof.Fire", 0);
		file.getPlayerFile().set(uuid + ".Prof.Death", 0);

		file.savePlayerFile();

	}

}
