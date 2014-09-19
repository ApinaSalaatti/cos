package com.dogshitempire.cos.ai.goals;

import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.items.activities.Activity;

/**
 *
 * @author Merioksan Mikko
 */
public class UseActivityGoal extends Goal {
    private Activity activity;
    
    public UseActivityGoal(CatBrain owner, Activity a) {
        super(owner, Goal.GOAL_USE_ACTIVITY);
     
        activity = a;
    }
    
    @Override
    public void activate() {
        
    }
    
    @Override
    public GoalStatus update(float deltaSeconds) {
        return getStatus();
    }
    
    @Override
    public void terminate() {
        
    }
}
