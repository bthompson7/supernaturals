package spells;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class FireballSpell extends SNSpell {
	
	public FireballSpell() {
		setSpellName("FireBall");
		setSpellDesc("Cast a fireball at your enemy!");
		setSpellCost(5);
		setSpellIcon(Material.FIRE_CHARGE);
	}
	
	@Override
	public void cast(Player player) {
		Fireball fireball = player.launchProjectile(Fireball.class);
		fireball.setVelocity(player.getLocation().getDirection().multiply(2));
	}
}
