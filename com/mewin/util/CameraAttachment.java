package com.mewin.util;

import java.util.HashSet;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class CameraAttachment {
    private Player player;
    private int height_level = 80;
    
    public CameraAttachment(Player player)
    {
        this.player = player;
    }
    
    public void update()
    {
        this.player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(2000, -2), true);
        this.player.setAllowFlight(true);
        this.player.setFlying(true);
        Location loc = this.player.getLocation();
        
        if (loc.getY() < height_level || loc.getY() > height_level + 2)
        {
            loc.setY(height_level + 1);
            this.player.teleport(loc);
        }
    }
}
