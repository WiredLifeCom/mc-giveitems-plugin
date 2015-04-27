package com.wiredlife.mcplugin.listener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		System.out.println(files);

		for (String file : files) {
			Data data = Data.fromJson(file);
			Inventory inventory = data.getUser().getInventory();

			// Get the resources and items from the local files. The key
			// represents the string representation of a resource/item and the
			// value is the amount of that resource/item
			Map<String, Integer> resources = storageController.getResources(inventory);
			Map<String, Integer> items = storageController.getItems(inventory);

			// The player's inventory
			PlayerInventory playerInventory = player.getInventory();

			// Loop through every resource and item, and add these to the player
			// inventory
			for (String key : resources.keySet()) {
				ItemStack itemStack = new ItemStack(storageController.getMaterialMappings().get(key), resources.get(key));
				playerInventory.addItem(itemStack);
			}
			for (String key : items.keySet()) {
				ItemStack itemStack = new ItemStack(storageController.getMaterialMappings().get(key), items.get(key));
				playerInventory.addItem(itemStack);
			}
		}
		// storageController.deleteLocalFiles(player.getName());
	}
}