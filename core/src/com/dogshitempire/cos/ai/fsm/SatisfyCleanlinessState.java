package com.dogshitempire.cos.ai.fsm;

import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyCleanlinessState extends FSMState {

    public SatisfyCleanlinessState() {
        super(FSMState.STATE_SATISFY_CLEANLINESS);
    }
    @Override
    public void update(float deltaSeconds) {
        
    }
    
    @Override
    public void enter() {
        
    }
    
    @Override
    public void exit() {
        
    }
    
    @Override
    public int checkTransition() {
        if(machine.brain.getPerceptions().cleanliness < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_CLEANLINESS))
            return getType();
        
        return FSMState.STATE_SATISFY_HEALTH;
    }
}
