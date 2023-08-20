package spells;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import spells.base.SNSpell;

public class PoisonMist extends SNSpell {
    public PoisonMist(){
        setSpellName("Poison Mist");
        setSpellDesc("Cast a mist to poison your enemies!");
        setSpellCost(20);
        setSpellIcon(Material.POISONOUS_POTATO);
    }

    @Override
    public void cast(Player player){
        ThrownPotion potion = player.launchProjectile(ThrownPotion.class);
    }
}
