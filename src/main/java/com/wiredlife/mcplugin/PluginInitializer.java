package com.wiredlife.mcplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wiredlife.mcplugin.concurrent.MoveToPoolOfDreams;
import com.wiredlife.mcplugin.concurrent.RunnableContainer;
import com.wiredlife.mcplugin.concurrent.UpdateMaterials;
import com.wiredlife.mcplugin.config.Config;
import com.wiredlife.mcplugin.listener.OnHitSendPokeListener;
import com.wiredlife.mcplugin.listener.OnJoinUpdateResourcesListener;

public class PluginInitializer extends JavaPlugin {

	private Thread runnableContainerThread;

	private RunnableContainer runnableContainer;

	@Override
	public void onEnable() {
		// TODO Insert logic to be performed when the plugin is enabled

		Config.getValues().put("database", "../data/database.sqlite");

		getServer().getPluginManager().registerEvents(new OnJoinUpdateResourcesListener(), this);
		getServer().getPluginManager().registerEvents(new OnHitSendPokeListener(), this);

		this.runnableContainer = new RunnableContainer();
		this.runnableContainer.addRunnable(new UpdateMaterials());
		this.runnableContainer.addRunnable(new MoveToPoolOfDreams());

		this.runnableContainerThread = new Thread(this.runnableContainer);
		this.runnableContainerThread.start();
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled

		this.runnableContainer.interrupt();
		try {
			this.runnableContainerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
