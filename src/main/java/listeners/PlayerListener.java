package listeners;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import player.SNPlayer;
import spells.base.SNSpell;
import supernaturals.Supernaturals;

public class PlayerListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());

		if (snPlayer == null) {
			return;
		}

		event.setFormat("<" + player.getDisplayName() + ChatColor.BLUE  + " [Level " + snPlayer.getCurrentLevel() + "] " + ChatColor.WHITE + "> " + event.getMessage());
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
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());

		if (snPlayer == null) {
			return;
		}

		snPlayer.save(snPlayer.getUuid(), snPlayer);
	}

	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerExpChange(PlayerExpChangeEvent event){

		Player player = event.getPlayer();
		int expAmount = event.getAmount();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());
		int currentExperience = snPlayer.getExperience();
		int currentLevel = snPlayer.getCurrentLevel();
		snPlayer.setExperience(currentExperience + expAmount);

		int nextLevelExperience = snPlayer.getLevels().get(currentLevel + 1);

		if(snPlayer.getExperience() >= nextLevelExperience){
			snPlayer.setCurrentLevel(currentLevel + 1);
			snPlayer.sendMessage(ChatColor.GOLD + "You are now level " + snPlayer.getCurrentLevel());

			SNSpell spell = snPlayer.getSpells().get(snPlayer.getCurrentLevel());

			if(spell != null){
				snPlayer.getUnlockedSpells().put(snPlayer.getUnlockedSpells().size(), spell);
				snPlayer.sendMessage(ChatColor.GOLD + "You have unlocked " + spell.getSpellName() + " !");
				snPlayer.updateSpellInventory();
			}

		}
		snPlayer.updateUI();
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		SNPlayer snPlayer = Supernaturals.players.get(player.getUniqueId());


		if (snPlayer == null) {
			return;
		}

		if (item.getType() == Material.BLAZE_ROD && Objects.requireNonNull(item.getItemMeta()).getDisplayName().contains("Magic Wand")) {
			SNSpell spell = snPlayer.getUnlockedSpells().get(snPlayer.getCurrentSpellNumber());

			if (snPlayer.getCurrentMana() >= spell.getSpellCost()) {
				spell.cast(player);
				snPlayer.setCurrentMana(snPlayer.getCurrentMana() - spell.getSpellCost());
				Supernaturals.players.put(player.getUniqueId(), snPlayer);
				snPlayer.getPlayer().sendMessage(ChatColor.RED + "-" + spell.getSpellCost() + " Mana");
				snPlayer.updateUI();
			}

		} else if (item.getType() == Material.BOOK && Objects.requireNonNull(item.getItemMeta()).getDisplayName().contains("Spell Book")) {
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

		if(snPlayer == null){
			return;
		}

		int slot = event.getSlot();

		if (slot < snPlayer.getUnlockedSpells().size()) {
			String currentSpellName = snPlayer.getUnlockedSpells().get(slot).getSpellName();
			snPlayer.sendMessage(ChatColor.GOLD + currentSpellName + " Selected ");
			snPlayer.setCurrentSpellNumber(slot);
			snPlayer.setCurrentSpellName(currentSpellName);
			snPlayer.updateUI();
			snPlayer.save(snPlayer.getUuid(), snPlayer);
		}

	}

}
