package spells.base;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Spell template class
 * 
 */
@SuppressWarnings("unused")
public abstract class SNSpell {

	protected int spellManaCost = 10;
	protected String spellName = "Default Spell Name";
	protected String spellDesc = "Default Spell Description";
	protected Material spellIcon = Material.OAK_WOOD;
	protected int MAX_DISTANCE = 50;

	public SNSpell() {
		
	}

	public void cast() {

	}
	
	public void cast(Player player) {

	}
	
	/**
	 * Gets the players cursor location. Uses the default maximum distance of 50
	 * 
	 * @param player the player
	 * @return the location of the cursor
	 *
	 */
	public Location getCursorLocation(Player player) {
		BlockIterator blockIterator = new BlockIterator(player, MAX_DISTANCE);
		Block lastBlock = null;

		while (blockIterator.hasNext()) {
			lastBlock = blockIterator.next();
			if (!lastBlock.getType().isAir()) {
				break;
			}
		}
		return lastBlock.getLocation();
	}

	/**
	 * Gets the players cursor location.
	 *
	 * @param player the player
	 * @param maxDistance This is the maximum distance in blocks for the trace. Setting this value above 140 may lead to problems with unloaded chunks. A value of 0 indicates no limit
	 * @return
	 */
	public Location getCursorLocation(Player player, int maxDistance) {
		BlockIterator blockIterator = new BlockIterator(player, maxDistance);
		Block lastBlock = null;

		while (blockIterator.hasNext()) {
			lastBlock = blockIterator.next();
			if (!lastBlock.getType().isAir()) {
				break;
			}
		}
		return lastBlock.getLocation();
	}

	/**
	 * Gets the location of nearby blocks
	 *
	 * @param player the player
	 * @param distance This is the maximum distance in blocks for the trace. Setting this value above 140 may lead to problems with unloaded chunks. A value of 0 indicates no limit
	 * @return a list of locations
	 */
	public List<Location> getLocationOfNearbyBlocks(Player player, int distance){
		List<Location> blockLocations = new ArrayList<>();
		BlockIterator blockIterator = new BlockIterator(player, distance);
		Block lastBlock = null;

		while (blockIterator.hasNext()) {
			lastBlock = blockIterator.next();

			blockLocations.add((lastBlock.getLocation()));
		}
		return blockLocations;
	}
	
	public int getSpellCost() {
		return spellManaCost;
	}

	public void setSpellCost(int spellCost) {
		this.spellManaCost = spellCost;
	}

	public String getSpellDesc() {
		return spellDesc;
	}

	public void setSpellDesc(String spellDesc) {
		this.spellDesc = spellDesc;
	}

	public Material getSpellIcon() {
		return spellIcon;
	}

	public void setSpellIcon(Material spellIcon) {
		this.spellIcon = spellIcon;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}
	
}
