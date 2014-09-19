package com.dogshitempire.cos.ai.goals;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.ai.CatBrain;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class CompositeGoal extends Goal {
    Array<Goal> subgoals;
    
    public CompositeGoal(CatBrain owner, int type) {
        super(owner, type);
        
        subgoals = new Array<Goal>();
    }
    
    public GoalStatus processSubgoals(float deltaSeconds) {
        // Remove all completed and failed goals
        while(subgoals.size > 0 && (subgoals.first().isCompleted() || subgoals.first().hasFailed())) {
            subgoals.first().terminate();
            subgoals.removeIndex(0);
        }
        
        if(subgoals.size > 0) {
            GoalStatus status = subgoals.first().update(deltaSeconds);
            if(status == GoalStatus.COMPLETED && subgoals.size > 1) {
                return GoalStatus.ACTIVE;
            }
            
            return status;
        }
        else {
            // All subgoals completed, whee!
            return GoalStatus.COMPLETED;
        }
    }
    
    /**
     * Adds a goal at the front of the subgoal stack
     * @param g 
     */
    @Override
    public void addSubgoal(Goal g) {
        subgoals.insert(0, g);
    }
    
    public void removeSubgoals() {
        while(subgoals.size > 0) {
            Goal g = subgoals.removeIndex(0);
            g.terminate();
        }
        
        subgoals.clear();
    }
}
