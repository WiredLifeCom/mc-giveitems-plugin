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
import com.wiredlife.jsonformatjava.model.status.OnlineStatus;
import com.wiredlife.jsonformatjava.model.unload.Unload;

public class StorageController {

	private static Map<String, Material> materialMappings;

	private DAO dao;

	public StorageController(String database) {
		if (materialMappings == null) {
			materialMappings = new HashMap<String, Material>();
			materialMappings.put("Dirt", Material.DIRT);
			materialMappings.put("Stone", Material.STONE);
			materialMappings.put("Wood", Material.WOOD);
			// materialMappings.put("DiamondPickaxe", Material.DIAMOND_PICKAXE);
			// materialMappings.put("WoodenAxe", Material.WOOD_AXE);
		}

		try {
			this.dao = new DAO(database);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	public void addOnlineStatus(OnlineStatus onlineStatus) {
		try {
			this.dao.addOnlineStatus(onlineStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public OnlineStatus getOnlineStatus(String username) {
		try {
			return this.dao.getOnlineStatus(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Unload> getUnloads(String username) {
		try {
			return this.dao.getUnloads(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteUnloads(String username) {
		try {
			this.dao.deleteUnloads(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUnload(Unload unload) {
		try {
			this.dao.deleteUnload(unload);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized void updateMaterials(Player player, Unload unload) {
		System.out.println("Updating resources for player " + player.getName());

		// Get the materials
		List<String> materials = unload.getMaterials();

		System.out.println("Adding " + materials.toString() + " to player " + player.getName());

		// The player's inventory
		PlayerInventory playerInventory = player.getInventory();

		// Loop through every material and add these to the player inventory
		for (String material : materials) {
			ItemStack itemStack = new ItemStack(getMaterialMappings().get(material), 1);
			playerInventory.addItem(itemStack);
		}

		try {
			this.dao.deleteUnload(unload);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Material> getMaterialMappings() {
		return StorageController.materialMappings;
	}

}
