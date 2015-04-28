package com.wiredlife.mcplugin.concurrent;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.wiredlife.jsonformatjava.model.Data;
import com.wiredlife.jsonformatjava.model.Inventory;
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
		
		for (Player player: Bukkit.getServer().getOnlinePlayers()) {
			System.out.println("Updating resources for player " + player.getName());
			
			List<String> files = storageController.getLocalFilesContents(player.getName()); 
			for (String file : files) {
				Data data = Data.fromJson(file);
				storageController.updateResources(player, data);
			}
		}
	}
	
}
