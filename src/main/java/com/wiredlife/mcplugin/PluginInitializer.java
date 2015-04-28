package com.wiredlife.mcplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wiredlife.mcplugin.concurrent.UpdateResources;
import com.wiredlife.mcplugin.listener.GiveItemsListener;

public class PluginInitializer extends JavaPlugin {

	@Override
	public void onEnable() {
		// TODO Insert logic to be performed when the plugin is enabled

		getServer().getPluginManager().registerEvents(new GiveItemsListener(), this);
		
		Thread updateResourcesThread = new Thread(new UpdateResources());
		updateResourcesThread.start();
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled
	}

}
