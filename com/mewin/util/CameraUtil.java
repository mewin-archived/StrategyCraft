package com.mewin.util;

import com.mewin.stc.game.Unit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public final class CameraUtil {
    
    
    public static Location getFocusLocation(Player player)
    {
        List<Block> blocks = player.getLastTwoTargetBlocks(null, 50);
        
        if (blocks.size() < 2 || blocks.get(1).getType() == Material.AIR)
        {
            return null;
        }
        else
        {
            return blocks.get(0).getLocation().add(0.5D, 0D, 0.5D);
        }
    }
    
    public static List<Unit> getFocusedUnits(Player player)
    {
        Set<Unit> nearUnits = Unit.getNearUnits(player.getLocation(), 50D);
        List<Unit> units = new ArrayList<Unit>();
        
        for (Unit unit : nearUnits)
        {
            if (player.getLocation().getDirection().angle(unit.getEntity().getLocation().toVector()) < 2f)
            {
                units.add(unit);
            }
        }
        
        return units;
    }
}
