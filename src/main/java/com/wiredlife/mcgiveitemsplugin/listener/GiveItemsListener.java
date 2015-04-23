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
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player = evt.getPlayer(); // The player who joined
        System.out.println("User joined: " + player.getName());
        PlayerInventory inventory = player.getInventory(); // The player's inventory
        ItemStack itemstack = new ItemStack(Material.DIRT, 2); // A small stack of dirt
     
        inventory.addItem(itemstack); // Adds a stack of dirt to the player's inventory
    }
	
}
