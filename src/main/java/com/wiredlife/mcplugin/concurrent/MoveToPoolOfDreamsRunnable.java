package com.wiredlife.mcplugin.concurrent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.wiredlife.jsonformatjava.model.status.OnlineStatus;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.controller.StorageController;

public class MoveToPoolOfDreamsRunnable implements Runnable {

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				moveTo();
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private synchronized void moveTo() {
		StorageController storageController = new StorageController(Config.getValues().get("database"));

		Server server = Bukkit.getServer();
		for (Player player : server.getOnlinePlayers()) {
			OnlineStatus onlineStatus = storageController.getOnlineStatus(player.getName());
			if (onlineStatus != null && !onlineStatus.isHome()) {
				System.out.println("Moving player " + player.getName() + " to the Pool of Dreams");
				Location location = new Location(server.getWorld("DevTest"), -15, 61, -215);
				player.teleport(location);
			}
		}
	}

	public synchronized void interrupt() {
		Thread.currentThread().interrupt();
	}

}
