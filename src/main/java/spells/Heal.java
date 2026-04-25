package spells;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import spells.base.SNSpell;

public class Heal extends SNSpell {

    public Heal(){
        setSpellName("Heal");
        setSpellDesc("Heal yourself!");
        setSpellCost(15);
        setSpellIcon(Material.GOLDEN_APPLE);
        setLevelRequirement(1);
    }
    @Override
    public void cast(Player player){
        double MAX_HEALTH = 20.0;
        double healthDiff = Math.round(MAX_HEALTH - player.getHealth());

        double HEALTH_GAIN_AMOUNT = 3.0;
        if(player.getHealth() < MAX_HEALTH && healthDiff >= HEALTH_GAIN_AMOUNT){
            player.setHealth(player.getHealth() + HEALTH_GAIN_AMOUNT);
            player.sendMessage("You gained " + HEALTH_GAIN_AMOUNT +  " heart(s)!");
        }else if(healthDiff <= HEALTH_GAIN_AMOUNT && healthDiff > 0){
            player.setHealth(player.getHealth() + healthDiff);
            player.sendMessage("You gained " + healthDiff +  " heart(s)!");
        }else{
            player.sendMessage("You're at max health!");
        }


    }
}
