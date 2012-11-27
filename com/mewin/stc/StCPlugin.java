package com.mewin.stc;


import com.mewin.stc.event.EntityListener;
import com.mewin.stc.event.GameListener;
import com.mewin.stc.event.PlayerListener;
import com.mewin.util.CameraAttachment;
import com.mewin.util.CameraAttachmentUpdater;
import com.mewin.util.GameUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class StCPlugin extends JavaPlugin {
    private Map<Player, CameraAttachment> attachments;
    private GameListener gameListener;
    private PlayerListener playerListener;
    private EntityListener entityListener;
    
    public StCPlugin()
    {
        super();
        attachments = new HashMap<Player, CameraAttachment>();
    }
    
    @Override
    public void onEnable()
    {
        GameUtil.registerPlugin(this);
        getServer().getScheduler().scheduleAsyncRepeatingTask(this, new CameraAttachmentUpdater(attachments), 60L, 10L);
        gameListener = new GameListener(this);
        entityListener = new EntityListener(this);
        playerListener = new PlayerListener(this);
        getServer().getPluginManager().registerEvents(entityListener, this);
        getServer().getPluginManager().registerEvents(playerListener, this);
    }
    
    public CameraAttachment addAttachment(Player player)
    {
        getLogger().log(Level.INFO, "Adding attachment to player {0]", player.getName());
        return attachments.put(player, new CameraAttachment(player));
    }
    
    public boolean hasAttachment(Player player)
    {
        return attachments.containsKey(player);
    }
    
    public boolean removeAttachment(Player player)
    {
        if (!hasAttachment(player))
        {
            return false;
        }
        else
        {
            attachments.remove(player);
            return true;
        }
    }
    
    public CameraAttachment getAttachment(Player player)
    {
        return this.attachments.get(player);
    }
    
    public GameListener getListener()
    {
        return this.gameListener;
    }

    @Override
    public void onDisable()
    {
        
    }
}
