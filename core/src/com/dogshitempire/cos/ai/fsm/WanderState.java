package com.dogshitempire.cos.ai.fsm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;
import static java.nio.file.Files.move;

/**
 *
 * @author Merioksan Mikko
 */
public class WanderState extends FSMState {
    private Vector2 target;
    
    private float jumpInterval;
    private float jumpTimer = 0;
    
    public WanderState() {
        super(FSMState.STATE_WANDER);
        
        target = new Vector2();
        float x = 10 + (float)Math.random()*Gdx.graphics.getWidth();
        float y = 30;
        target.set(x, y);
        
        jumpInterval = 10 + (float)Math.random() * 5;
    }
    
    @Override
    public void enter() {
        
    }
    
    @Override
    public void exit() {
        
    }
    
    @Override
    public void update(float deltaSeconds) {
        Cat cat = machine.brain.getCat();
        
        jumpTimer += deltaSeconds;
        if(jumpTimer >= jumpInterval) {
            machine.brain.getCat().getMover().jump();
            jumpTimer = 0; 
            jumpInterval = 10 + (float)Math.random() * 5;
        }
        
        if(target.dst(cat.getX(), cat.getY()) <= 15) {
            float x = 10 + (float)Math.random()*(Gdx.graphics.getWidth()-20);
            float y = 30;
            target.set(x, y);
        }
        else {
            cat.getMover().setTarget(target.x, target.y);
        }
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
        debugLabel.setText("WANDERING TO " + target.toString());
        debugLabel.draw(batch, 1f);
    }
}
