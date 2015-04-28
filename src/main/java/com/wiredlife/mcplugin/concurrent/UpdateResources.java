package com.wiredlife.mcplugin.concurrent;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.wiredlife.jsonformatjava.model.Data;
import com.wiredlife.mcplugin.controller.StorageController;

public class UpdateResources implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				update();
				Thread.sleep(10000);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void update() throws IOException {
		StorageController storageController = new StorageController();

		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			System.out.println("Updating resources for player " + player.getName());

			List<String> unloads = storageController.getUnloads(player.getName());
			for (String unload : unloads) {
				Data data = Data.fromJson(unload);
				storageController.updateResources(player, data);
			}
		}
	}

}
