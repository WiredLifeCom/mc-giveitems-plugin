package com.wiredlife.mcplugin.concurrent;

import java.util.ArrayList;
import java.util.List;

public class RunnableContainer extends AbstractRunnable implements Runnable {

	private List<AbstractRunnable> concurrents;

	public RunnableContainer() {
		this.concurrents = new ArrayList<AbstractRunnable>();
	}

	public void addRunnable(AbstractRunnable c) {
		this.concurrents.add(c);
	}

	public void deleteRunnable(AbstractRunnable c) {
		this.concurrents.remove(c);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			doOperation();
		}
	}

	@Override
	public void doOperation() {
		for (AbstractRunnable a : this.concurrents) {
			a.doOperation();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
