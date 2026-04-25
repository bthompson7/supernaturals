package spells;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import spells.base.SNSpell;

public class PoisonMist extends SNSpell {
    public PoisonMist(){
        setSpellName("Poison Mist");
        setSpellDesc("Cast a mist to poison your enemies!");
        setSpellCost(20);
        setSpellIcon(Material.POISONOUS_POTATO);
        setLevelRequirement(3);
    }

    @Override
    public void cast(Player player){
        player.launchProjectile(ThrownPotion.class);
    }
}
