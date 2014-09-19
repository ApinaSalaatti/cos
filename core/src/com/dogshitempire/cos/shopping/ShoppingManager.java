package com.dogshitempire.cos.shopping;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.events.GameEvent;
import com.dogshitempire.cos.events.GameEventListener;
import com.dogshitempire.cos.items.Item;

/**
 *
 * @author Merioksan Mikko
 */
public class ShoppingManager implements GameEventListener {
    private Array<Item> availableItems;
    
    public ShoppingManager() {
        availableItems = new Array<Item>();
    }
    
    public Array<Item> getItems() {
        return availableItems;
    }
    
    @Override
    public void receiveEvent(GameEvent e) {
        if(e.getType() == GameEvent.itemUnlockedEvent) {
            Item i = (Item)e.getData();
            if(!availableItems.contains(i, false)) {
                availableItems.add(i);
            }
        }
    }
}
