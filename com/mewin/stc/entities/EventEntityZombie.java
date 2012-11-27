package com.mewin.stc.entities;

import net.minecraft.server.EntityZombie;
import net.minecraft.server.World;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class EventEntityZombie extends EntityZombie {
    public EventEntityZombie(World world)
    {
        super(world);
        this.setTarget(vehicle);
    }
}
