package com.wiredlife.mcplugin.listener;

import java.io.IOException;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.wiredlife.jsonformatjava.model.Data;
import com.wiredlife.jsonformatjava.model.Inventory;
import com.wiredlife.mcplugin.controller.StorageController;

public class GiveItemsListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
		StorageController storageController = new StorageController();

		// The player who joined
		Player player = event.getPlayer();

		// Loop through all the local files
		List<String> files = storageController.getLocalFilesContents(player.getName());
		
		for (String file : files) {
			Data data = Data.fromJson(file);
			storageController.updateResources(player, data);
		}
		// storageController.deleteLocalFiles(player.getName());
	}
}