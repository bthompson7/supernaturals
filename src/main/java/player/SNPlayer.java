package player;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;

import spells.*;
import spells.base.SNSpell;
import supernaturals.Supernaturals;

/**
 * Class that represents a supernaturals player
 * TODO: ORM here?
 */
public class SNPlayer {

	private UUID uuid;
	private int currentMana;
	private int maxMana;
	private int currentLevel;
	private int experience;
	private String playerName;
	private int currentSpellNumber;
	private String currentSpellName;
	private Inventory spellInventory;

	private final Map<Integer, SNSpell> spells = new HashMap<>();
	private final Map<Integer, SNSpell> unlockedSpells = new HashMap<>();
	private final Map<Integer, Integer> levels = new HashMap<>();

	/**
	 * 
	 * @param p the Bukkit player
	 */
	public SNPlayer(Player p) {
		this.playerName = p.getName();
		this.uuid = p.getUniqueId();
		this.maxMana = 500;
		this.currentMana = 450;
		this.currentSpellNumber = 0;
		this.experience = 0;
		this.currentLevel = 1;

		// Unlocked by default
		unlockedSpells.put(0, new Fireball());
		unlockedSpells.put(1, new Heal());

		levels.put(1, 0);
		for(int level = 2; level < 20; level++){
			levels.put(level, (level * 100));
		}

		populateSpellList();
		createSpellInventory();
	}
	
	private void createSpellInventory() {
		spellInventory = Bukkit.createInventory(null, 9, "Spell List");

		for (Map.Entry<Integer, SNSpell> entry : getUnlockedSpells().entrySet()) {
			int key = entry.getKey();
			SNSpell spell = entry.getValue();
			ItemStack spellIcon = new ItemStack(spell.getSpellIcon());
			ItemMeta spellIconMeta = spellIcon.getItemMeta();

			if(spellIconMeta == null) {
				continue;
			}

			spellIconMeta.setDisplayName(spell.getSpellName() + ", " + spell.getSpellDesc());
			spellIcon.setItemMeta(spellIconMeta);
			spellInventory.setItem(key, spellIcon);

		}
	}

	public void updateSpellInventory(){
		for (Map.Entry<Integer, SNSpell> entry : getUnlockedSpells().entrySet()) {
			int key = entry.getKey();
			SNSpell spell = entry.getValue();
			ItemStack spellIcon = new ItemStack(spell.getSpellIcon());
			ItemMeta spellIconMeta = spellIcon.getItemMeta();

			if(spellIconMeta == null) {
				continue;
			}

			spellIconMeta.setDisplayName(spell.getSpellName() + ", " + spell.getSpellDesc());
			spellIcon.setItemMeta(spellIconMeta);
			spellInventory.setItem(key, spellIcon);
		}
	}
	
	private void populateSpellList() {
		spells.put(new Fireball().getLevelRequirement(), new Fireball());
		spells.put(new Lightning().getLevelRequirement(), new Lightning());
		spells.put(new PoisonMist().getLevelRequirement(), new PoisonMist());
		spells.put(new Heal().getLevelRequirement(), new Heal());
		spells.put(new RainOfArrows().getLevelRequirement(), new RainOfArrows());
	}

	public Map<Integer, SNSpell> getUnlockedSpells() {
		return unlockedSpells;
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
		Objective playerInfo = scoreboard.getObjective("playerInfo");

		if(scoreboard.getObjective("playerInfo") == null) {
			playerInfo = scoreboard.registerNewObjective("playerInfo", Criteria.DUMMY, "");
		}

		if(playerInfo == null){
			return;
		}

		playerInfo.setDisplaySlot(DisplaySlot.SIDEBAR);
		playerInfo.setDisplayName("Mage - " +  ChatColor.GOLD + getCurrentSpellName());
		Score currMana = playerInfo.getScore(ChatColor.BLUE + "Current Mana: ");
		currMana.setScore(getCurrentMana());
		
		Score maxMana = playerInfo.getScore(ChatColor.BLUE + "Max Mana: ");
		maxMana.setScore(getMaxMana());
		Score currentLevel = playerInfo.getScore(ChatColor.BLUE + "Level: ");
		currentLevel.setScore(getCurrentLevel());

		Score currentExp = playerInfo.getScore(ChatColor.BLUE + "Exp: ");
		currentExp.setScore(getExperience());

		getPlayer().setScoreboard(scoreboard);

		Supernaturals.plugin.getLogger().info("updated UI: " + this.getUuid() + " " + this.getPlayer().getName());

	}

	public void sendMessage(String message) {
		if (isOnline()) {
			getPlayer().sendMessage(message);
		}
	}
	
	public void save(UUID uuid, SNPlayer p) {
		Supernaturals.players.put(uuid, p);
		Supernaturals.plugin.getLogger().info("Saved Data For: " + uuid + " Player: " + p.getPlayer().getName());


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

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Map<Integer, Integer> getLevels() {
		return levels;
	}

	public Map<Integer, SNSpell> getSpells() {
		return spells;
	}



}
