package com.wiredlife.mcplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wiredlife.mcplugin.concurrent.UpdateResourcesThread;
import com.wiredlife.mcplugin.listener.OnHitSendPokeListener;
import com.wiredlife.mcplugin.listener.OnJoinUpdateResourcesListener;

public class PluginInitializer extends JavaPlugin {

	@Override
	public void onEnable() {
		// TODO Insert logic to be performed when the plugin is enabled

		getServer().getPluginManager().registerEvents(new OnJoinUpdateResourcesListener(), this);
		getServer().getPluginManager().registerEvents(new OnHitSendPokeListener(), this);

		Thread updateResourcesThread = new Thread(new UpdateResourcesThread());
		updateResourcesThread.start();
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled
	}

}
