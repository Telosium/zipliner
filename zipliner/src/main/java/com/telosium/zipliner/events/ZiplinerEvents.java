package com.telosium.zipliner.events;

import com.telosium.zipliner.Zipliner;
import com.telosium.zipliner.utils.ZiplinerUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ZiplinerEvents implements Listener {

    @EventHandler
    public void onArrowLand(ProjectileHitEvent event) {
        if(event.getEntity().getShooter() instanceof Player && event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            Player player = (Player) event.getEntity().getShooter();
            if(arrow.getColor().equals(Color.BLACK) && arrow.getName().equals("ziplineBowArrow") && null != player) {
                Location playerLocation = player.getLocation();
                double playerX = playerLocation.getBlockX();
                double playerY = playerLocation.getBlockY();
                double playerZ = playerLocation.getBlockZ();

                if(event.getHitEntity() != null) {
                    Location targetLocation = event.getEntity().getLocation();
                    double targetX = targetLocation.getBlockX();
                    double targetY = targetLocation.getBlockY();
                    double targetZ = targetLocation.getBlockZ();

                    double distanceX = targetX - playerX;
                    double distanceY = targetY - playerY + 1;
                    double distanceZ = targetZ - playerZ;

                    Vector velocity = new Vector(distanceX/2, (distanceY/4 + 0.5), distanceZ/2);

                    player.setVelocity(velocity);
                    event.getHitEntity().setVelocity(velocity.multiply(-1));

                } else if (event.getHitBlock() != null) {
                    Location targetLocation = arrow.getLocation();
                    double targetX = targetLocation.getBlockX();
                    double targetY = targetLocation.getBlockY();
                    double targetZ = targetLocation.getBlockZ();

                    double distanceX = targetX - playerX;
                    double distanceY = targetY - playerY;
                    double distanceZ = targetZ - playerZ;

                    Vector velocity = new Vector(distanceX, (distanceY/4 + 0.5), distanceZ);

                    player.setVelocity(velocity);

                } else {
                    Zipliner.getInstance().getLogger().fine("Something went wrong while " + player.getName() +
                            " tried to shoot his Zipline bow");
                }
            }
        }
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if(!event.isCancelled() && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemHand = player.getInventory().getItemInMainHand();

            if(player.hasPermission(ZiplinerUtils.ZIPLINEBOW_USE_PERM) && ZiplinerUtils.isZiplineBow(itemHand)) {
                if(event.getProjectile() instanceof Arrow) {
                    Arrow arrow = (Arrow) event.getProjectile();
                    arrow.setColor(Color.BLACK);
                    arrow.setCustomName("ziplineBowArrow");
                }
            }
        }
    }
}
