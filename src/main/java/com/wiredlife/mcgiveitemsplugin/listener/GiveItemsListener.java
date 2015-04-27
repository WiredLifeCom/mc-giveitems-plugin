package com.wiredlife.mcgiveitemsplugin.listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.wiredlife.jsonformatjava.model.Data;
import com.wiredlife.jsonformatjava.model.Inventory;

public class GiveItemsListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Map<String, Material> mappings = new HashMap<String, Material>();
        mappings.put("Dirt", Material.DIRT);
        mappings.put("Stone", Material.STONE);
        mappings.put("DiamondPickaxe", Material.DIAMOND_PICKAXE);
        mappings.put("WoodenAxe", Material.WOOD_AXE);
        
		// The player who joined
		Player player = event.getPlayer();
		System.out.println("User joined: " + player.getName());
		
		//System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
    	
    	String json = new String(Files.readAllBytes(Paths.get(String.format("../data/%s/2015-04-21T13-04-54.000+02-00.json", player.getName()))));
    	
    	Data data = Data.fromJson(json);
    	Inventory dataInventory = data.getUser().getInventory();
    	
    	Map<String, Integer> resources = new HashMap<String, Integer>();
    	for (String resource : dataInventory.getResources()) {
    		if (resources.containsKey(resource)) {
    			resources.put(resource, resources.get(resource) + 1);
    		}
    		else {
    			resources.put(resource, 1);
    		}
    	}
    	
    	Map<String, Integer> items = new HashMap<String, Integer>();
    	for (String item : dataInventory.getItems()) {
    		if (items.containsKey(item)){
    			items.put(item, items.get(item) + 1);
    		} else {
    			items.put(item, 1);
    		}
    	}

		// The player's inventory
		PlayerInventory inventory = player.getInventory();

		for (String key : resources.keySet()) {
			ItemStack itemStack = new ItemStack(mappings.get(key), resources.get(key));
			inventory.addItem(itemStack);
		}
		
		for (String key : items.keySet()) {
			ItemStack itemStack = new ItemStack(mappings.get(key), items.get(key));
			inventory.addItem(itemStack);
		}
		
//		// A small stack of dirt
//		ItemStack itemStack = new ItemStack(mappings.get("Dirt"), 2);
//
//		// Adds a stack of dirt to the player's inventory
//		inventory.addItem(itemStack);
    }
}