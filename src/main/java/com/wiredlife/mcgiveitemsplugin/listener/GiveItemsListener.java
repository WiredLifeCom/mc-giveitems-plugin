package com.wiredlife.mcgiveitemsplugin.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class GiveItemsListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// The player who joined
		Player player = event.getPlayer();

		System.out.println("User joined: " + player.getName());

		// The player's inventory
		PlayerInventory inventory = player.getInventory();

		// A small stack of dirt
		ItemStack itemStack = new ItemStack(Material.DIRT, 2);

		// Adds a stack of dirt to the player's inventory
		inventory.addItem(itemStack);
	}

}
