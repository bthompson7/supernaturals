package spells.base;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import supernaturals.Supernaturals;

/**
 * 
 * Spell template class
 * 
 */
public abstract class SNSpell {

	protected int spellManaCost = 10;
	protected String spellName = "Default Spell Name";
	protected String spellDesc = "Default Spell Description";
	protected Material spellIcon = Material.OAK_WOOD;
	
	public SNSpell() {
		
	}

	public void cast() {

	}
	
	public void cast(Player player) {

	}
	
	/**
	 * Gets the players cursor location
	 * 
	 * @param player
	 * @return
	 */
	public Location getCursorLocation(Player player) {
		BlockIterator blockIterator = new BlockIterator(player, 50);
		Block lastBlock = null;

		while (blockIterator.hasNext()) {
			lastBlock = blockIterator.next();
			if (!lastBlock.getType().isAir()) {
				break;
			}
		}
		
		return lastBlock.getLocation();

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
