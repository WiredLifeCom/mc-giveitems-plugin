package com.wiredlife.mcplugin.concurrent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.wiredlife.jsonformatjava.model.status.OnlineStatus;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.controller.StorageController;

public class MoveToPoolOfDreams extends AbstractRunnable {

	private StorageController storageController;

	public MoveToPoolOfDreams() {
		this.storageController = new StorageController(Config.getValues().get("database"));
	}

	@Override
	public void doOperation() {
		System.out.println("Running MoveToPoolOfDreams...");

		Server server = Bukkit.getServer();
		for (Player player : server.getOnlinePlayers()) {
			OnlineStatus onlineStatus = this.storageController.getOnlineStatus(player.getName());
			if (onlineStatus != null && !onlineStatus.isHome()) {
				System.out.println("Moving player " + player.getName() + " to the Pool of Dreams");
				Location location = new Location(server.getWorld("DevTest"), -15, 61, -215);
				player.teleport(location);
			}
		}
	}

}
