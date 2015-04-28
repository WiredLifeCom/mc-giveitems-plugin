package com.wiredlife.mcplugin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.wiredlife.jsonformatjava.dba.SQLiteDBA;
import com.wiredlife.jsonformatjava.model.Data;
import com.wiredlife.jsonformatjava.model.Inventory;

public class StorageController {

	private SQLiteDBA dba;

	private Map<String, Material> materialMappings;

	public StorageController() {
		this.dba = new SQLiteDBA("../data/database.db");

		this.materialMappings = new HashMap<String, Material>();
		this.materialMappings.put("Dirt", Material.DIRT);
		this.materialMappings.put("Stone", Material.STONE);
		this.materialMappings.put("DiamondPickaxe", Material.DIAMOND_PICKAXE);
		this.materialMappings.put("WoodenAxe", Material.WOOD_AXE);
	}

	public List<String> getUnloads(String username) {
		return this.dba.getUnloads(username);
	}

	public void deleteUnloads(String username) {
		this.dba.deleteUnloads(username);
	}

	public void updateResources(Player player, Data data) {
		// Get the data inventory
		Inventory inventory = data.getUser().getInventory();

		// The player's inventory
		PlayerInventory playerInventory = player.getInventory();

		// Loop through every resource and item, and add these to the player
		// inventory
		for (String resource : inventory.getResources()) {
			ItemStack itemStack = new ItemStack(getMaterialMappings().get(resource), 1);
			playerInventory.addItem(itemStack);
		}
		for (String item : inventory.getItems()) {
			ItemStack itemStack = new ItemStack(getMaterialMappings().get(item), 1);
			playerInventory.addItem(itemStack);
		}
	}

	public Map<String, Material> getMaterialMappings() {
		return this.materialMappings;
	}

}
