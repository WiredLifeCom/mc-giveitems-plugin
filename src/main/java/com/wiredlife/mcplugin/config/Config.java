package com.wiredlife.mcplugin.config;

import java.util.HashMap;
import java.util.Map;

public class Config {

	private static Map<String, String> values;

	static {
		if (values == null) {
			values = new HashMap<String, String>();
		}
	}

	public static Map<String, String> getValues() {
		return values;
	}

}