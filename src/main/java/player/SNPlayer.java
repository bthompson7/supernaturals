package player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import supernaturals.Supernaturals;

/**
 * Class that represents a supernaturals player
 */
public class SNPlayer {

	private int currentMana;
	private int maxMana;
	private String name;
	private UUID uuid;

	public SNPlayer(Player p) {
		this.name = p.getName();
		this.setUuid(p.getUniqueId());
		this.maxMana = 500;
		this.currentMana = 500;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void updateGUI() {

	}

	public static SNPlayer getPlayer(Player player) {
		return Supernaturals.players.get(player.getUniqueId());
	}

	public Player getPlayer() {
		return Bukkit.getPlayerExact(name);
	}

	public Boolean isOnline() {
		return getPlayer() != null;
	}

	public Boolean isOffline() {
		return !(isOnline());
	}
	
	public void sendMessage(String message) {
		if (isOnline()) {
			getPlayer().sendMessage(message);
		}
	}
	
	public void save(UUID uuid, SNPlayer p) {
		Supernaturals.players.put(uuid, p);
	}
	
	public static List<SNPlayer> getOnlinePlayers() {
		ArrayList<SNPlayer> players = new ArrayList<SNPlayer>();
				
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(getPlayer(p) != null) {
				players.add(getPlayer(p));
			}
		}
				
		return players;
	}
	
	public int getMana() {
		return currentMana;
	}

	public void setMana(int mana) {
		this.currentMana = mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
