package listeners;

import org.bukkit.Effect;
import org.bukkit.entity.LingeringPotion;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WorldListener implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof ThrownPotion) {
            ThrownPotion potion = (ThrownPotion) event.getEntity();
            PotionEffect poisonEffect = new PotionEffect(PotionEffectType.POISON, 150, 1); // 100 ticks, level 1
            potion.getWorld().getNearbyEntities(potion.getLocation(), 2.0, 2.0, 2.0)
                    .stream()
                    .filter(entity -> entity instanceof LivingEntity)
                    .forEach(livingEntity -> ((LivingEntity) livingEntity).addPotionEffect(poisonEffect));
        }
    }

}
