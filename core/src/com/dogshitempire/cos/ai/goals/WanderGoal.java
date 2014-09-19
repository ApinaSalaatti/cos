package com.dogshitempire.cos.ai.goals;

import com.badlogic.gdx.math.Vector2;
import com.dogshitempire.cos.ai.CatBrain;

/**
 *
 * @author Merioksan Mikko
 */
public class WanderGoal extends Goal{
    private Vector2 target;
    
    public WanderGoal(CatBrain owner) {
        super(owner, Goal.GOAL_WANDER);
        
        target = new Vector2();
    }
    
    @Override
    public void activate() {
        setStatus(GoalStatus.ACTIVE);
        
        float x = 10 + (float)Math.random() * (getBrain().getCat().getHomeStage().getItemGrid().getWidth() - 50);
        float y = 30;
        
        getBrain().getCat().getMover().setTarget(x, y);
    }
    
    @Override
    public GoalStatus update(float deltaSeconds) {
        activateIfInactive();
        
        if(getBrain().getCat().getMover().isAtPosition(target.x, target.y)) {
            setStatus(GoalStatus.COMPLETED);
        }
        
        return getStatus();
    }
    
    @Override
    public void terminate() {
        
    }
}
