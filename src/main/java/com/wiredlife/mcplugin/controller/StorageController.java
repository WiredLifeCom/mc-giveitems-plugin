package com.wiredlife.mcplugin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.wiredlife.jsonformatjava.dba.unload.UnloadDBA;
import com.wiredlife.jsonformatjava.model.unload.Unload;

public class StorageController {

	private UnloadDBA dba;

	private Map<String, Material> materialMappings;

	public StorageController() {
		this.dba = new UnloadDBA("../data/database.db");

		this.materialMappings = new HashMap<String, Material>();
		this.materialMappings.put("Dirt", Material.DIRT);
		this.materialMappings.put("Stone", Material.STONE);
		this.materialMappings.put("DiamondPickaxe", Material.DIAMOND_PICKAXE);
		this.materialMappings.put("WoodenAxe", Material.WOOD_AXE);
	}

	public List<Unload> getUnloads(String username) {
		return this.dba.getUnloads(username);
	}

	public void deleteUnloads(String username) {
		this.dba.deleteUnloads(username);
	}

	public synchronized void updateResources(Player player, Unload unload) {
		// Get the materials
		List<String> materials = unload.getUser().getMaterials();

		// The player's inventory
		PlayerInventory playerInventory = player.getInventory();

		// Loop through every material and add these to the player inventory
		for (String material : materials) {
			ItemStack itemStack = new ItemStack(getMaterialMappings().get(material), 1);
			playerInventory.addItem(itemStack);
		}
	}

	public Map<String, Material> getMaterialMappings() {
		return this.materialMappings;
	}

}
