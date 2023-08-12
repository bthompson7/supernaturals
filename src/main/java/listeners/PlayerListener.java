package listeners;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import player.SNPlayer;
import supernaturals.Supernaturals;

public class PlayerListener implements Listener {

	private static final int SPELL_COST = 10;

	/**
	 * TODO: wait until leveling is implemented, display level
	 * 
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {

	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());
		if (snPlayer != null) {
			snPlayer.updateUI();
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Block block = event.getClickedBlock();

		ItemStack item = player.getInventory().getItemInMainHand();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());

		if (item != null) {

			// player has wand
			if (item.getType() == Material.BLAZE_ROD && item.getItemMeta().getDisplayName().contains("Magic Wand")
					&& snPlayer.getCurrentMana() >= SPELL_COST) {
				Fireball fireball = player.launchProjectile(Fireball.class);
				fireball.setVelocity(player.getLocation().getDirection().multiply(2));

				// update mana
				snPlayer.setCurrentMana(snPlayer.getCurrentMana() - SPELL_COST);
				Supernaturals.players.put(player.getUniqueId(), snPlayer);
				snPlayer.getPlayer().sendMessage(ChatColor.RED + "-" + SPELL_COST + " Mana");
				snPlayer.updateUI();

				// click spell book
			} else if (item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().contains("Spell Book")) {
				event.setCancelled(true);
				player.openInventory(snPlayer.getInventory());
			}
		}
	}

	
	/**
	 * For when the player clicks an item in the custom iventory
	 * 
	 * @param event
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!Objects.equals(event.getView().getTitle(), "Custom Inventory")) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());

		event.setCancelled(true); // Prevent item moving/clicking in the inventory

		int slot = event.getSlot();
		if (event.getRawSlot() == 0) {
			player.sendMessage("You clicked the custom item!");
		}
	}

}
