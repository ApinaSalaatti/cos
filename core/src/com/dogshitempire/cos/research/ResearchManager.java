package com.dogshitempire.cos.research;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.events.GameEvent;
import com.dogshitempire.cos.events.GameEventListener;
import com.dogshitempire.cos.shopping.ShoppingManager;

/**
 *
 * @author Merioksan Mikko
 */
public class ResearchManager implements GameEventListener {
    private Array<ResearchItem> allResearch;
    
    public ResearchManager() {
        allResearch = new Array<ResearchItem>();
    }
    
    @Override
    public void receiveEvent(GameEvent e) {
        if(e.getType() == GameEvent.researchUnlockedEvent) {
            String r = (String)e.getData();
            for(ResearchItem ri : allResearch) {
                if(ri.getName().equals(r)) {
                    ri.makeAvailable();
                }
            }
        }
    }
}
