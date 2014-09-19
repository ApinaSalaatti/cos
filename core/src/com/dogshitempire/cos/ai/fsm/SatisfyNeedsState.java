package com.dogshitempire.cos.ai.fsm;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyNeedsState extends FSMachine {
    public SatisfyNeedsState(CatBrain brain) {
        super(FSMState.STATE_SATISFY_NEEDS, brain);
        
        this.addState(new SatisfyHungerState());
        this.addState(new SatisfyCleanlinessState());
        this.addState(new SatisfyHappinessState());
        this.addState(new SatisfyHealthState());
    }
    
    @Override
    public void enter() {

    }
    
    @Override
    public void exit() {
        
    }
    
    @Override
    public int checkTransition() {
        // If we should AND can satisfy a need, don't change state. Else start wandering.
        int ret = FSMState.STATE_WANDER;
        if(machine.brain.getPerceptions().cleanliness < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_CLEANLINESS))
            ret = getType();
        if(machine.brain.getPerceptions().happiness < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HAPPINESS))
            ret = getType();
        if(machine.brain.getPerceptions().health < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HEALTH))
            ret = getType();
        if(machine.brain.getPerceptions().hunger < 50f && machine.brain.getPerceptions().canSatisfyNeed(CatStats.NEED_HUNGER))
            ret = getType();
        
        return ret;
    }
    
    /*
     * DEBUGGING STUFF HERE
     */
    private Label debugLabel;
    @Override
    public void initDebug() {
        debugLabel = new Label("", debugSkin);
    }
    
    @Override
    public void debugDraw(Batch batch) {
        debugLabel.setPosition(machine.brain.getCat().getX(), machine.brain.getCat().getY());
        debugLabel.setText("SATISFYING NEED: " + this.getCurrentState().toString());
        debugLabel.draw(batch, 1f);
    }
}
