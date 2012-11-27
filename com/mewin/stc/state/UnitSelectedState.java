package com.mewin.stc.state;

import com.mewin.stc.game.Unit;
import com.mewin.stc.game.UnitType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class UnitSelectedState extends State {
    private static Map<Player, List<Unit>> selectedUnits= new HashMap<Player, List<Unit>>();
    
    @Override
    public void onRightClick(Player player, Location loc)
    {
        for (Unit u : getUnits(player))
        {
            u.move(loc);
        }
    }

    @Override
    public ItemStack[] getItems(Player player)
    {
        List<Unit> units = getUnits(player);
        Map<UnitType, Integer> unitCount = new HashMap<UnitType, Integer>();
        
        for (Unit unit : units)
        {
            int curVal = 0;
            
            if (unitCount.containsKey(unit.getType()))
            {
                curVal = unitCount.get(unit.getType());
            }
            
            unitCount.put(unit.getType(), ++curVal);
        }
        
        ItemStack[] items = new ItemStack[9];
        
        Iterator<Entry<UnitType, Integer>> itr = unitCount.entrySet().iterator();
        for (int i = 0; i < 9; i++)
        {
            if (itr.hasNext())
            {
                Entry<UnitType, Integer> e = itr.next();
                items[i] = getMaterialForUnit(e.getKey()).toItemStack(e.getValue());
            }
            else
            {
                break;
            }
        }
        
        return items;
    }

    @Override
    public void update(Player player) {
        
    }
    
    private static List<Unit> getUnits(Player player)
    {
        if (selectedUnits.containsKey(player))
        {
            return selectedUnits.get(player);
        }
        else
        {
            return selectedUnits.put(player, new ArrayList<Unit>());
        }
    }
    
    public static void setUnits(Player player, List<Unit> units)
    {
        selectedUnits.put(player, units);
    }
    
    private MaterialData getMaterialForUnit(UnitType unit)
    {
        switch(unit)
        {
            case ZOMBIE:
                return new MaterialData(Material.SKULL_ITEM, (byte) 2);
            case SKELETON:
                return new MaterialData(Material.SKULL_ITEM, (byte) 0);
        }
        
        return null;
    }
}
