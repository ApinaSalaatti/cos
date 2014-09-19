package com.dogshitempire.cos.ai.fsm;

import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyHungerState extends FSMState{
    
    public SatisfyHungerState() {
        super(FSMState.STATE_SATISFY_HUNGER);
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
        if(machine.brain.getPerceptions().hunger < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HUNGER))
            return getType();
        
        return FSMState.STATE_SATISFY_HAPPINESS;
    }
}
