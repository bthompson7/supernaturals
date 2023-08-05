package listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

import player.SNPlayer;
import supernaturals.Supernaturals;

public class PlayerListener implements Listener {

	private static final int SPELL_COST = 10;
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {

	}

	@EventHandler
	public void onPlayerLogin(final PlayerLoginEvent event) {
		Supernaturals.plugin.getLogger().info("Player login event: " + event.getPlayer().getName());
		Player player = event.getPlayer();

	}

	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {

	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());

		// Check if the player is holding the custom wand item
		if (item != null && item.getType() == Material.BLAZE_ROD && item.getItemMeta().getDisplayName().contains("Mage Wand")
				&& snPlayer.getCurrentMana() >= SPELL_COST) {
			Fireball fireball = player.launchProjectile(Fireball.class);
			fireball.setVelocity(player.getLocation().getDirection().multiply(2));

			// update mana
			snPlayer.setCurrentMana(snPlayer.getCurrentMana() - SPELL_COST);
			Supernaturals.players.put(player.getUniqueId(), snPlayer);
			snPlayer.getPlayer().sendMessage("You have " + snPlayer.getCurrentMana() + " Mana");


		}
	}

}
