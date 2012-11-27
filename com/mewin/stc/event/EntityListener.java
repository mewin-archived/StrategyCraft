package com.mewin.stc.event;

import com.mewin.stc.StCPlugin;
import com.mewin.stc.game.Unit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class EntityListener implements Listener {

    private StCPlugin plugin;
    
    public EntityListener(StCPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onEntityTarget(EntityTargetEvent e)
    {
        if (Unit.isUnitEntity(e.getEntity()) && e.getReason() != TargetReason.CUSTOM)
        {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e)
    {
        if (Unit.isUnitEntity(e.getEntity()))
        {
            Unit.unitFromEntity(e.getEntity()).despawn();
        }
    }
}
