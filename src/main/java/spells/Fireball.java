package spells;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import spells.base.SNSpell;

public class Fireball extends SNSpell {
	
	public Fireball() {
		setSpellName("Fireball");
		setSpellDesc("Cast a fireball at your enemy!");
		setSpellCost(1);
		setSpellIcon(Material.FIRE_CHARGE);
	}
	
	@Override
	public void cast(Player player) {
		org.bukkit.entity.Fireball fireball = player.launchProjectile(org.bukkit.entity.Fireball.class);
		fireball.setVelocity(player.getLocation().getDirection().multiply(2));
	}
}
