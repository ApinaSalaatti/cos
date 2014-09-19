package com.dogshitempire.cos.ai.fsm;

import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyHealthState extends FSMState{
    
    public SatisfyHealthState() {
        super(FSMState.STATE_SATISFY_HEALTH);
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
        if(machine.brain.getPerceptions().health < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HEALTH))
            return getType();
        
        return FSMState.STATE_SATISFY_HUNGER;
    }
}
