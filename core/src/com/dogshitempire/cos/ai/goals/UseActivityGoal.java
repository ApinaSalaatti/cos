package com.dogshitempire.cos.ai.goals;

import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.items.activities.Activity;

/**
 *
 * @author Merioksan Mikko
 */
public class UseActivityGoal extends Goal {
    private Activity activity;
    private float timer = 0f;
    
    public UseActivityGoal(CatBrain owner, Activity a) {
        super(owner, Goal.GOAL_USE_ACTIVITY);
     
        activity = a;
    }
    
    @Override
    public void activate() {
        setStatus(GoalStatus.ACTIVE);
        
        if(!activity.reserveSlot(getBrain().getCat())) {
            setStatus(GoalStatus.FAILED);
        }
    }
    
    @Override
    public GoalStatus update(float deltaSeconds) {
        activateIfInactive();
        
        timer += deltaSeconds;
        if(timer >= activity.getUseTime()) {
            activity.activate(getBrain().getCat());
            timer = 0;
            setStatus(GoalStatus.COMPLETED);
        }
        
        return getStatus();
    }
    
    @Override
    public void terminate() {
        
    }
}
