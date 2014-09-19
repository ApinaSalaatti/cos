package com.dogshitempire.cos.research;

import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.events.GameEvent;
import com.dogshitempire.cos.items.Item;

/**
 *
 * @author Merioksan Mikko
 */
public class UnlockItemEffect implements ResearchEffect {
    private Item item;
    
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public void execute() {
        GameApplication.getEventManager().queueEvent(new GameEvent(GameEvent.itemUnlockedEvent, item));
    }
}
