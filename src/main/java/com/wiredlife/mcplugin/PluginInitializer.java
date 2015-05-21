package com.wiredlife.mcplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wiredlife.mcplugin.concurrent.UpdateResourcesRunnable;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.listener.OnHitSendPokeListener;
import com.wiredlife.mcplugin.listener.OnJoinUpdateResourcesListener;

public class PluginInitializer extends JavaPlugin {

	Thread updateResourcesThread;

	UpdateResourcesRunnable updateResourcesRunnable;

	@Override
	public void onEnable() {
		// TODO Insert logic to be performed when the plugin is enabled
		
		Config.getValues().put("database", "../data/database.sqlite");

		getServer().getPluginManager().registerEvents(new OnJoinUpdateResourcesListener(), this);
		getServer().getPluginManager().registerEvents(new OnHitSendPokeListener(), this);

		this.updateResourcesRunnable = new UpdateResourcesRunnable();

		this.updateResourcesThread = new Thread(this.updateResourcesRunnable);
		this.updateResourcesThread.start();
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled

		this.updateResourcesRunnable.terminate();
		try {
			this.updateResourcesThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
