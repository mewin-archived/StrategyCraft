package com.mewin.stc.state;

import com.mewin.stc.game.Unit;
import com.mewin.stc.game.UnitType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class EmptyState extends State {

    @Override
    public void onRightClick(Player player, Location loc) {
        Unit.spawnUnit(loc, UnitType.ZOMBIE, player);
    }

    @Override
    public ItemStack[] getItems(Player player) {
        return new ItemStack[0];
    }

    @Override
    public void update(Player player) {
        
    }
    
}
