package com.dogshitempire.cos.ai.goals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.messaging.AIMessage;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Goal {
    // Goal types
    public static final int GOAL_CHOOSER = 0;
    public static final int GOAL_WANDER = 1;
    public static final int GOAL_SATISFY_HUNGER = 2;
    public static final int GOAL_SATISFY_CLEANLINESS = 3;
    public static final int GOAL_SATISFY_HAPPINESS = 4;
    public static final int GOAL_SATISFY_HEALTH = 5;
    public static final int GOAL_FOLLOW_PATH = 6;
    public static final int GOAL_TRAVERSE_EDGE = 7;
    public static final int GOAL_JUMP = 8;
    public static final int GOAL_USE_ACTIVITY = 9;
    
    public enum GoalStatus { INACTIVE, ACTIVE, COMPLETED, FAILED };
    private int type;
    private GoalStatus status;
    private CatBrain brain;
    public CatBrain getBrain() {
        return brain;
    }
    
    public Goal(CatBrain owner, int type) {
        this.type = type;
        brain = owner;
        status = GoalStatus.INACTIVE;
    }
    
    public abstract void activate();
    public abstract GoalStatus update(float deltaSeconds);
    public abstract void terminate();
    
    public void addSubgoal(Goal g) {
        // A basic goal has no subgoals, do nothing
    }
    
    public int getType() {
        return type;
    }
    public boolean isInactive() {
        return status == GoalStatus.INACTIVE;
    }
    public boolean isActive() {
        return status == GoalStatus.ACTIVE;
    }
    public boolean isCompleted() {
        return status == GoalStatus.COMPLETED;
    }
    public boolean hasFailed() {
        return status == GoalStatus.FAILED;
    }
    
    public void setStatus(GoalStatus s) {
        status = s;
    }
    public GoalStatus getStatus() {
        return status;
    }
    
    public void activateIfInactive() {
        if(isInactive()) {
            activate();
        }
    }
    public void activateIfFailed() {
        if(hasFailed()) {
            activate();
        }
    }
    
    public void debugDraw(Batch batch) {
        
    }
    
    public boolean handleMessage(AIMessage message) {
        return false;
    }
}
