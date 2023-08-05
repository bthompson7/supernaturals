package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import supernaturals.Supernaturals;

public class PlayerListener implements Listener {

	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {

	}
	
	@EventHandler
	public void onPlayerLogin(final PlayerLoginEvent event) {
		Supernaturals.plugin.getLogger().info("Player login event: " + event.getPlayer().getName());

	}
	
	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {		
		
	}

	
}
