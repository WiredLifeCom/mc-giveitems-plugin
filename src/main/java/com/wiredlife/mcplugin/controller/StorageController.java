package com.wiredlife.mcplugin.controller;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.wiredlife.jsonformatjava.dao.DAO;
import com.wiredlife.jsonformatjava.model.unload.Unload;

public class StorageController {

	private static Map<String, Material> materialMappings;

	private DAO dao;

	public StorageController(String database) {
		if (materialMappings == null) {
			materialMappings = new HashMap<String, Material>();
			materialMappings.put("Dirt", Material.DIRT);
			materialMappings.put("Stone", Material.STONE);
			materialMappings.put("DiamondPickaxe", Material.DIAMOND_PICKAXE);
			materialMappings.put("WoodenAxe", Material.WOOD_AXE);
		}

		try {
			this.dao = new DAO(database);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	public List<Unload> getUnloads(String username) {
		try {
			return this.dao.getUnloads(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void deleteUnloads(String username) {
		try {
			this.dao.deleteUnloads(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void updateResources(Player player, Unload unload) {
		// Get the materials
		List<String> materials = unload.getMaterials();

		// The player's inventory
		PlayerInventory playerInventory = player.getInventory();

		// Loop through every material and add these to the player inventory
		for (String material : materials) {
			ItemStack itemStack = new ItemStack(getMaterialMappings().get(material), 1);
			playerInventory.addItem(itemStack);
		}
	}

	public Map<String, Material> getMaterialMappings() {
		return StorageController.materialMappings;
	}

}
