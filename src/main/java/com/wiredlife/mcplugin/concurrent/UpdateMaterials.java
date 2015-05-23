package com.wiredlife.mcplugin.concurrent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.wiredlife.jsonformatjava.model.unload.Unload;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.controller.StorageController;

public class UpdateMaterials extends AbstractRunnable {

	private StorageController storageController;

	public UpdateMaterials() {
		this.storageController = new StorageController(Config.getValues().get("database"));
	}

	@Override
	public void doOperation() {
		System.out.println("Running UpdateMaterials...");

		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			List<Unload> unloads = this.storageController.getUnloads(player.getName());
			for (Unload unload : unloads) {
				this.storageController.updateMaterials(player, unload);
			}
		}
	}

}
