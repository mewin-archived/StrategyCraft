package com.mewin.stc.state;

import com.mewin.stc.game.Unit;
import com.mewin.util.CameraUtil;
import com.mewin.util.GameUtil;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public abstract class State
{
    public static final State EMPTY = new EmptyState();
    public static final State UNIT = new UnitSelectedState();
    
    public void onLeftClick(Player player, Location loc)
    {
        List<Unit> units = CameraUtil.getFocusedUnits(player);
        
        if (units.size() > 0)
        {
            GameUtil.setSelectedUnits(player, units);
        }
        else
        {
            GameUtil.getPlugin().getListener().setPlayerState(player, EMPTY);
        }
    }
    
    public abstract void onRightClick(Player player, Location loc);
    
    public abstract ItemStack[] getItems(Player player);
    
    public abstract void update(Player player);
}
