package com.wiredlife.mcplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wiredlife.mcplugin.concurrent.UpdateMaterialsRunnable;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.listener.OnHitSendPokeListener;
import com.wiredlife.mcplugin.listener.OnJoinUpdateResourcesListener;

public class PluginInitializer extends JavaPlugin {

	Thread updateMaterialsThread;

	UpdateMaterialsRunnable updateMaterialsRunnable;

	@Override
	public void onEnable() {
		// TODO Insert logic to be performed when the plugin is enabled

		Config.getValues().put("database", "../data/database.sqlite");

		getServer().getPluginManager().registerEvents(new OnJoinUpdateResourcesListener(), this);
		getServer().getPluginManager().registerEvents(new OnHitSendPokeListener(), this);

		this.updateMaterialsRunnable = new UpdateMaterialsRunnable();

		this.updateMaterialsThread = new Thread(this.updateMaterialsRunnable);
		this.updateMaterialsThread.start();
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled

		this.updateMaterialsRunnable.interrupt();
		try {
			this.updateMaterialsThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
