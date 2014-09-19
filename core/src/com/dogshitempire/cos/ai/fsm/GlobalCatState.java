package com.dogshitempire.cos.ai.fsm;

import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class GlobalCatState extends FSMState {
    
    public GlobalCatState() {
        super(FSMState.STATE_GLOBAL_CAT_STATE);
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
        int ret = getType();
        if(machine.brain.getPerceptions().cleanliness < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_CLEANLINESS))
            ret = FSMState.STATE_SATISFY_NEEDS;
        if(machine.brain.getPerceptions().happiness < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HAPPINESS))
            ret = FSMState.STATE_SATISFY_NEEDS;
        if(machine.brain.getPerceptions().health < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HEALTH))
            ret = FSMState.STATE_SATISFY_NEEDS;
        if(machine.brain.getPerceptions().hunger < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HUNGER))
            ret = FSMState.STATE_SATISFY_NEEDS;
        
        
        return ret;
    }
}
