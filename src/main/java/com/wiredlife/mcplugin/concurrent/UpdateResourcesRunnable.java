package com.wiredlife.mcplugin.concurrent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.wiredlife.jsonformatjava.model.unload.Unload;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.controller.StorageController;

public class UpdateResourcesRunnable implements Runnable {
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				update();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private synchronized void update() {
		StorageController storageController = new StorageController(Config.getValues().get("database"));

		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			List<Unload> unloads = storageController.getUnloads(player.getName());
			for (Unload unload : unloads) {
				storageController.updateMaterials(player, unload);
			}
		}
	}

	public synchronized void interrupt() {
		Thread.currentThread().interrupt();
	}

}
