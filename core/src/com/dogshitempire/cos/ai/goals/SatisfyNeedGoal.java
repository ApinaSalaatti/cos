package com.dogshitempire.cos.ai.goals;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.pathfinding.PathEdge;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.items.activities.Activity;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyNeedGoal extends CompositeGoal {
    private int need;
    
    public SatisfyNeedGoal(CatBrain owner, int need) {
        super(owner, needToGoalType(need));
        
        this.need = need;
    }
    
    @Override
    public void activate() {
        setStatus(GoalStatus.ACTIVE);
        
        Activity a = getBrain().getPerceptions().getClosestActivity(need);
        
        if(a != null) {
            Array<PathEdge> path = getBrain().getPathfinder().findPathToActivity(a);
            addSubgoal(new UseActivityGoal(getBrain(), a));
            addSubgoal(new FollowPathGoal(getBrain(), path));
        }
        else {
            setStatus(GoalStatus.FAILED);
        }
    }

    @Override
    public GoalStatus update(float deltaSeconds) {
        activateIfInactive();
        
        setStatus(processSubgoals(deltaSeconds));
        return getStatus();
    }
    
    @Override
    public void terminate() {
        setStatus(GoalStatus.FAILED);
    }
    
    public static int needToGoalType(int need) {
        switch(need) {
            case CatStats.NEED_CLEANLINESS:
                return Goal.GOAL_SATISFY_CLEANLINESS;
            case CatStats.NEED_HAPPINESS:
                return Goal.GOAL_SATISFY_HAPPINESS;
            case CatStats.NEED_HEALTH:
                return Goal.GOAL_SATISFY_HEALTH;
            default: // an incorrect need falls back to hunger. I dunno. I guess I'm a bit hungry right now.
                return Goal.GOAL_SATISFY_HUNGER;
        }
    }
}
