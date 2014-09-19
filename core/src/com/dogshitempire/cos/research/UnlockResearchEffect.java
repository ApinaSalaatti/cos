package com.dogshitempire.cos.research;

import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.events.GameEvent;

/**
 *
 * @author Merioksan Mikko
 */
public class UnlockResearchEffect implements ResearchEffect {
    private String research;
    
    public void setResearch(String research) {
        this.research = research;
    }
    
    @Override
    public void execute() {
        GameApplication.getEventManager().queueEvent(new GameEvent(GameEvent.researchUnlockedEvent, research));
    }
}
