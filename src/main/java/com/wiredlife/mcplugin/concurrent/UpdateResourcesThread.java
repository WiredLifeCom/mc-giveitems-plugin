package com.wiredlife.mcplugin.concurrent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.wiredlife.jsonformatjava.model.unload.Unload;
import com.wiredlife.mcplugin.controller.StorageController;

public class UpdateResourcesThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				update();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void update() {
		StorageController storageController = new StorageController();

		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			System.out.println("Updating resources for player " + player.getName());

			List<Unload> unloads = storageController.getUnloads(player.getName());
			System.out.println(unloads);
			for (Unload unload : unloads) {
				storageController.updateResources(player, unload);
			}
		}
	}

}
