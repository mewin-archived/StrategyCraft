package com.mewin.stc.event;

import com.mewin.stc.StCPlugin;
import com.mewin.util.CameraUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class PlayerListener implements Listener {
    private StCPlugin plugin;
    
    public PlayerListener(StCPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        plugin.addAttachment(e.getPlayer());
    }
    
    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent e)
    {
        if (plugin.hasAttachment(e.getPlayer()))
        {
            e.setVelocity(e.getVelocity().setY(0));
        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        if (e.getAction() == Action.RIGHT_CLICK_AIR)
        {
            Location clickedLoc = CameraUtil.getFocusLocation(e.getPlayer());
            
            if (clickedLoc != null)
            {
                plugin.getListener().onRightClick(e.getPlayer(), clickedLoc);
            }
        }
        else if (e.getAction() == Action.LEFT_CLICK_AIR)
        {
            Location clickedLoc = CameraUtil.getFocusLocation(e.getPlayer());
            
            if (clickedLoc != null)
            {
                plugin.getListener().onLeftClick(e.getPlayer(), clickedLoc);
            }
        }
    }
}
