package player;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;

import spells.Fireball;
import spells.Heal;
import spells.Lightning;
import spells.PoisonMist;
import spells.base.SNSpell;
import supernaturals.Supernaturals;

/**
 * Class that represents a supernaturals player
 *
 * TODO: ORM here?
 */
public class SNPlayer {

	private int currentMana;
	private int maxMana;
	private int currentLevel = 1;
	private String playerName;
	private UUID uuid;
	private int currentSpellNumber;
	private String currentSpellName;
	private Inventory spellInventory;
	private Map<Integer, SNSpell> spellList = new HashMap<Integer, SNSpell>();


	/**
	 * 
	 * @param p the bukkit player
	 */
	public SNPlayer(Player p) {
		this.playerName = p.getName();
		this.uuid = p.getUniqueId();
		this.maxMana = 500;
		this.currentMana = 450;
		this.currentSpellNumber = 0;
		populateSpellList();
		createSpellInventory();
	}
	
	private void createSpellInventory() {
		spellInventory = Bukkit.createInventory(null, 9, "Spell List");

		for (Map.Entry<Integer, SNSpell> entry : getSpellList().entrySet()) {
			int key = entry.getKey();
			SNSpell spell = entry.getValue();
			ItemStack spellIcon = new ItemStack(spell.getSpellIcon());
			ItemMeta spellIconMeta = spellIcon.getItemMeta();
			spellIconMeta.setDisplayName(spell.getSpellName() + ", " + spell.getSpellDesc());
			spellIcon.setItemMeta(spellIconMeta);
			spellInventory.setItem(key, spellIcon);
		}
	}
	
	private void populateSpellList() {
		spellList.put(0, new Fireball());
		spellList.put(1, new Lightning());
		spellList.put(2, new PoisonMist());
		spellList.put(3, new Heal());

	}
	
	public Map<Integer, SNSpell> getSpellList() {
		return spellList;
	}
	public int getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}

	public String getName() {
		return playerName;
	}

	public void setName(String name) {
		this.playerName = name;
	}

	public static SNPlayer getPlayer(Player player) {
		return Supernaturals.players.get(player.getUniqueId());
	}

	public Player getPlayer() {
		return Bukkit.getPlayerExact(playerName);
	}

	public Boolean isOnline() {
		return getPlayer() != null;
	}

	public Boolean isOffline() {
		return !(isOnline());
	}
	
	/**
	 * 
	 * Updates the players UI with info about current and max mana, and spells
	 * 
	 */
	public void updateUI() {
		Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
		if(scoreboard.getObjective("playerInfo") == null) {
			scoreboard.registerNewObjective("playerInfo", Criteria.DUMMY, "");
		}
		
		Objective playerInfo = scoreboard.getObjective("playerInfo");
		playerInfo.setDisplaySlot(DisplaySlot.SIDEBAR);
		playerInfo.setDisplayName("Mage - " +  ChatColor.GOLD + getCurrentSpellName());
		Score currMana = playerInfo.getScore(ChatColor.BLUE + "Current Mana: ");
		currMana.setScore(getCurrentMana());
		
		Score maxMana = playerInfo.getScore(ChatColor.BLUE + "Max Mana: ");
		maxMana.setScore(getMaxMana());
		Score currentLevel = playerInfo.getScore(ChatColor.BLUE + "Level: ");
		currentLevel.setScore(getCurrentLevel());

		getPlayer().setScoreboard(scoreboard);
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

	public int getCurrentSpellNumber() {
		return currentSpellNumber;
	}

	public void setCurrentSpellNumber(int currentSpellNumber) {
		this.currentSpellNumber = currentSpellNumber;
	}

	public Inventory getInventory() {
		return spellInventory;
	}

	public void setInventory(Inventory inventory) {
		this.spellInventory = inventory;
	}

	public String getCurrentSpellName() {
		return currentSpellName;
	}

	public void setCurrentSpellName(String currentSpellName) {
		this.currentSpellName = currentSpellName;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}


}
