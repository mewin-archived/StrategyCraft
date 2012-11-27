package com.mewin.util;

import java.util.Map;
import org.bukkit.entity.Player;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class CameraAttachmentUpdater implements Runnable {
    private Map<Player, CameraAttachment> attachments;
    public CameraAttachmentUpdater(Map<Player, CameraAttachment> attachments)
    {
        this.attachments = attachments;
    }
    
    @Override
    public void run()
    {
        for(CameraAttachment att : this.attachments.values())
        {
            att.update();
        }
    }
    
}
