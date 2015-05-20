package com.wiredlife.mcplugin.listener;

import java.io.IOException;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wiredlife.jsonformatjava.model.unload.Unload;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.controller.StorageController;

public class OnJoinUpdateResourcesListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
		StorageController storageController = new StorageController(Config.getDatabase());

		// The player who joined
		Player player = event.getPlayer();

		// Loop through all the local files
		List<Unload> unloads = storageController.getUnloads(player.getName());

		for (Unload unload : unloads) {
			storageController.updateResources(player, unload);
		}
		// storageController.deleteUnloads(player.getName());
	}
}