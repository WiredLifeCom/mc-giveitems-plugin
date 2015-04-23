package com.wiredlife.mcgiveitemsplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wiredlife.mcgiveitemsplugin.listener.GiveItemsListener;

public class GiveItemsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // TODO Insert logic to be performed when the plugin is enabled
    	
    	getServer().getPluginManager().registerEvents(new GiveItemsListener(), this);
    }
 
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    }
	
}
