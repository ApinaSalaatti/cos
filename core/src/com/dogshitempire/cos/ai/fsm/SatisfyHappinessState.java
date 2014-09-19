package com.dogshitempire.cos.ai.fsm;

import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyHappinessState extends FSMState {
    
    public SatisfyHappinessState() {
        super(FSMState.STATE_SATISFY_HAPPINESS);
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
        if(machine.brain.getPerceptions().happiness < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HAPPINESS))
            return getType();
        
        return FSMState.STATE_SATISFY_CLEANLINESS;
    }
}
