package me.griffdragon.NewBaron.Functions;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.griffdragon.NewBaron.BaronCore;
import net.md_5.bungee.api.ChatColor;

public class PlayerFiles {
	public BaronCore plugin = (BaronCore) BaronCore.getPlugin(BaronCore.class);
	public FileConfiguration playerdata;
	public File players;

	public PlayerFiles(Player p) {
		if (!this.plugin.getDataFolder().exists()) {
			this.plugin.getDataFolder().mkdir();

		}
		this.players = new File(this.plugin.getDataFolder(), "Players/" + p.getUniqueId() + ".yml");
		if (!this.players.exists()) {
			try {
				this.players.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.GREEN + "This players file already exists, moving on. :(");
			}
		}
		this.playerdata = YamlConfiguration.loadConfiguration(this.players);

	}

	public FileConfiguration getPlayerFile() {
		return this.playerdata;
	}

	public void savePlayerFile() {
		try {
			this.playerdata.save(this.players);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't Save Players file. :(");
		}
	}

	public void reloadPlayers() {
		this.playerdata = YamlConfiguration.loadConfiguration(this.players);
	}

}