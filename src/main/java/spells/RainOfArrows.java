package spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import spells.base.SNSpell;

import java.util.List;


public class RainOfArrows extends SNSpell {
    private Vector direction;
    private float ARROW_SPREAD = 12F;
    private float ARROW_SPEED = 0.6F;
    private int DISTANCE = 45;
    private int SPELL_COST = 35;

    public RainOfArrows(){
        setSpellName("Rain of Arrows");
        setSpellDesc("Rain arrows on the head of your enemy!");
        setSpellCost(SPELL_COST);
        setSpellIcon(Material.ARROW);
        direction = new Vector();
    }

    @Override
    public void cast(Player player) {
        Location cursorLocation = getCursorLocation(player);
        direction.setX(-cursorLocation.getX());
        direction.setY(-cursorLocation.getY());
        direction.setY(-cursorLocation.getZ());
        List<Location> nearbyBlocks = getLocationOfNearbyBlocks(player, DISTANCE);

        for(Location location : nearbyBlocks){
            player.getWorld().spawnArrow(location, direction, ARROW_SPEED, ARROW_SPREAD);
        }
    }
}
