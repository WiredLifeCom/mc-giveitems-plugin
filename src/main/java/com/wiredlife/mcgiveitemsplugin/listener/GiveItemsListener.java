package com.wiredlife.mcgiveitemsplugin.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class GiveItemsListener implements Listener {
	
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Map<String, Material> map = new HashMap<String, Material>();
        map.put("Dirt", Material.DIRT);
        map.put("Stone", Material.STONE);
    	
    	Player player = evt.getPlayer(); // The player who joined
        System.out.println("User joined: " + player.getName());
        PlayerInventory inventory = player.getInventory(); // The player's inventory
        ItemStack itemStack = new ItemStack(map.get("Dirt"), 2); // A small stack of dirt
     
        inventory.addItem(itemStack); // Adds a stack of dirt to the player's inventory
    }
	
}
