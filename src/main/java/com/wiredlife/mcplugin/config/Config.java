package com.wiredlife.mcplugin.config;

public class Config {

	private static String database;

	public static String getDatabase() {
		return database;
	}

	public static void setDatabase(String database) {
		Config.database = database;
	}

}
