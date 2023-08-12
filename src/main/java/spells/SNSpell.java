package spells;

import org.bukkit.Material;
import org.bukkit.entity.Player;

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
