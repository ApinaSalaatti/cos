package com.dogshitempire.cos.shopping;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.events.GameEvent;
import com.dogshitempire.cos.events.GameEventListener;
import com.dogshitempire.cos.items.Item;
import com.dogshitempire.cos.stages.HomeStage;

/**
 *
 * @author Merioksan Mikko
 */
public class ShoppingManager implements GameEventListener {
    private HomeStage stage;
    
    private Array<Item> availableItems;
    
    public ShoppingManager(HomeStage hs) {
        stage = hs;
        availableItems = new Array<Item>();
        
        availableItems.add(stage.getGameObjectFactory().createActivity("bowl"));
        availableItems.add(stage.getGameObjectFactory().createActivity("toy"));
        availableItems.add(stage.getGameObjectFactory().createFurniture("chair"));
    }
    
    public Array<Item> getAvailableItems() {
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
