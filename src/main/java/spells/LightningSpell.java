package spells;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import spells.base.SNSpell;

public class LightningSpell extends SNSpell {

	public LightningSpell() {
		setSpellName("Lightning");
		setSpellDesc("Strike down your foes!");
		setSpellCost(5);
		setSpellIcon(Material.LIGHTNING_ROD);
	}

	@Override
	public void cast(Player player) {
        player.getWorld().strikeLightning(getCursorLocation(player));
	}
	
	

}
