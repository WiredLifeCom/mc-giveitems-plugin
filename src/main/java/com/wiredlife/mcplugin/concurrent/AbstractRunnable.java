package com.wiredlife.mcplugin.concurrent;

public abstract class AbstractRunnable implements RunnableOperation {

	public synchronized void interrupt() {
		Thread.currentThread().interrupt();
	}

}
