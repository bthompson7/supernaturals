package spells;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import spell.base.SNSpell;

public class FireballSpell extends SNSpell {
	
	public FireballSpell() {
		setSpellName("Fireball");
		setSpellDesc("Cast a fireball at your enemy!");
		setSpellCost(1);
		setSpellIcon(Material.FIRE_CHARGE);
	}
	
	@Override
	public void cast(Player player) {
		Fireball fireball = player.launchProjectile(Fireball.class);
		fireball.setVelocity(player.getLocation().getDirection().multiply(2));
	}
}
