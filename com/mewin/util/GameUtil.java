package com.mewin.util;

import com.mewin.stc.StCPlugin;
import com.mewin.stc.game.Unit;
import com.mewin.stc.state.State;
import com.mewin.stc.state.UnitSelectedState;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.entity.Player;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class GameUtil {
    private static StCPlugin plugin = null;
    
    public static void setSelectedUnits(Player player, List<Unit> units)
    {
        UnitSelectedState.setUnits(player, units);
        plugin.getListener().setPlayerState(player, State.UNIT);
    }
    
    public static void registerPlugin(StCPlugin plugin)
    {
        if (GameUtil.plugin == null)
        {
            GameUtil.plugin = plugin;
        }
        else
        {
            GameUtil.plugin.getLogger().log(Level.WARNING, "Trying to register plugin twice.");
        }
    }
    
    public static StCPlugin getPlugin()
    {
        return plugin;
    }
}
