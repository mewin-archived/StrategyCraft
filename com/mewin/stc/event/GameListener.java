package com.mewin.stc.event;

import com.mewin.stc.StCPlugin;
import com.mewin.stc.state.State;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class GameListener {
    private StCPlugin plugin;
    private Map<Player, State> playerStates;
    
    public GameListener(StCPlugin plugin)
    {
        this.playerStates = new HashMap<Player, State>();
        this.plugin = plugin;
    }
    
    public void onRightClick(Player player, Location loc)
    {
        getPlayerState(player).onRightClick(player, loc);
    }
    
    public void onLeftClick(Player player, Location loc)
    {
        getPlayerState(player).onLeftClick(player, loc);
    }
    
    public State getPlayerState(Player player)
    {
        if (!this.playerStates.containsKey(player))
        {
            this.playerStates.put(player, State.EMPTY);
        }
        return this.playerStates.get(player);
    }
    
    public void setPlayerState(Player player, State state)
    {
        this.playerStates.put(player, state);
        player.getInventory().setContents(state.getItems(player));
    }
}
