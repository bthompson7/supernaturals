package spells;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import spells.base.SNSpell;

public class Heal extends SNSpell {
    private double HEALTH_GAIN_AMOUNT = 3.0;
    private double MAX_HEALTH = 20.0;
    public Heal(){
        setSpellName("Heal");
        setSpellDesc("Heal yourself!");
        setSpellCost(15);
        setSpellIcon(Material.GOLDEN_APPLE);
    }
    @Override
    public void cast(Player player){
        double healthDiff = MAX_HEALTH - player.getHealth();
        System.out.println(healthDiff);

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
