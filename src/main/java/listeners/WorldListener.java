package listeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static java.util.Arrays.asList;
import static supernaturals.Supernaturals.plugin;

public class WorldListener implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {

        // For Spell: Poison Mist
        if (event.getEntity() instanceof ThrownPotion) {
            ThrownPotion potion = (ThrownPotion) event.getEntity();
            PotionEffect poisonEffect = new PotionEffect(PotionEffectType.POISON, 200, 1); // 100 ticks, level 1
            potion.getWorld().getNearbyEntities(potion.getLocation(), 2.0, 2.0, 2.0)
                    .stream()
                    .filter(entity -> entity instanceof LivingEntity)
                    .forEach(livingEntity -> ((LivingEntity) livingEntity).addPotionEffect(poisonEffect));
        }
    }


}
