package com.mewin.stc.game;

import com.mewin.util.GameUtil;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.server.EntityCreature;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftCreature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class Unit {
    private static Set<Unit> allUnits = new HashSet<Unit>();
    private LivingEntity bukkitEntity;
    private Player owner;
    private UnitType type;
    
    private Unit(Location loc, UnitType type, Player owner)
    {
        switch(type)
        {
            case ZOMBIE:
                bukkitEntity = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
                break;
            case SKELETON:
                bukkitEntity = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
                break;
        }
        
        this.owner = owner;
        this.type = type;
        
        bukkitEntity.setMetadata("unit", new FixedMetadataValue(GameUtil.getPlugin(), true));
    }
    
    public static Unit spawnUnit(Location loc, UnitType type, Player owner)
    {
        Unit unit = new Unit(loc, type, owner);
        
        allUnits.add(unit);
        
        return unit;
    }
    
    public static Set<Unit> getUnits()
    {
        return new HashSet<Unit>(allUnits);
    }
    
    public LivingEntity getEntity()
    {
        return this.bukkitEntity;
    }
    
    public static Set<Unit> getNearUnits(Location loc, double dist)
    {
        HashSet<Unit> units = new HashSet<Unit>();
        for (Unit u : allUnits)
        {
            if (loc.getWorld() == u.getEntity().getWorld() && loc.distance(u.getEntity().getLocation()) <= dist)
            {
                units.add(u);
            }
        }
        
        return units;
    }
    
    public void despawn()
    {
        bukkitEntity.remove();
        allUnits.remove(this);
    }
    
    public UnitType getType()
    {
        return this.type;
    }
    
    public void update()
    {
        
    }
    
    public void move(Location loc)
    {
        EntityCreature ent = ((CraftCreature) this.bukkitEntity).getHandle();
        
        ent.setPathEntity(ent.world.a(ent, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 16.0F, true, false, false, true));
    }
    
    public static boolean isUnitEntity(Entity ent)
    {
        return ent.getMetadata("unit").size() > 0;
    }
    
    public static Unit unitFromEntity(Entity ent)
    {
        for (Unit u : allUnits)
        {
            if (u.getEntity() == ent)
            {
                return u;
            }
        }
        
        return null;
    }
}
