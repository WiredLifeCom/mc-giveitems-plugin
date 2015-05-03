package com.wiredlife.mcplugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class OnHitSendPokeListener implements Listener {

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (e.getCause() == DamageCause.ENTITY_ATTACK) {
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
				e.setDamage(0);
				
				Player player = (Player) e.getEntity();
				Player damager = (Player) e.getDamager();

				System.out.println(damager.getName() + " is poking " + player.getName());
			}
		}
	}

}
