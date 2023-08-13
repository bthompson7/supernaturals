package listeners;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import player.SNPlayer;
import spells.base.SNSpell;
import supernaturals.Supernaturals;

public class PlayerListener implements Listener {

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

		if (snPlayer == null) {
			return;
		}

		snPlayer.updateUI();
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());

		if (item == null) {
			return;
		}

		if (item.getType() == Material.BLAZE_ROD && item.getItemMeta().getDisplayName().contains("Magic Wand")) {
			SNSpell spell = snPlayer.getSpellList().get(snPlayer.getCurrentSpell());

			if (snPlayer.getCurrentMana() >= spell.getSpellCost()) {
				spell.cast(player);
				snPlayer.setCurrentMana(snPlayer.getCurrentMana() - spell.getSpellCost());
				Supernaturals.players.put(player.getUniqueId(), snPlayer);
				snPlayer.getPlayer().sendMessage(ChatColor.RED + "-" + spell.getSpellCost() + " Mana");
				snPlayer.updateUI();
			}

		} else if (item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().contains("Spell Book")) {
			player.openInventory(snPlayer.getInventory());
		}
		
	}

	/**
	 * For when the player clicks an item in the custom inventory
	 * 
	 * @param event inventory click event
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!Objects.equals(event.getView().getTitle(), "Spell List")) {
			return;
		}

		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());
		int slot = event.getSlot();

		if (slot < snPlayer.getSpellList().size()) {
			snPlayer.sendMessage("Current spell set to " + snPlayer.getSpellList().get(slot).getSpellName());
			snPlayer.setCurrentSpell(slot);
		}

	}

}
