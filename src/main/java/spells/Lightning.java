package spells;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import spells.base.SNSpell;

public class Lightning extends SNSpell {

	public Lightning() {
		setSpellName("Lightning");
		setSpellDesc("Strike down your foes!");
		setSpellCost(2);
		setSpellIcon(Material.LIGHTNING_ROD);
		setLevelRequirement(2);
	}

	@Override
	public void cast(Player player) {
        player.getWorld().strikeLightning(getCursorLocation(player));
	}
	
	

}
