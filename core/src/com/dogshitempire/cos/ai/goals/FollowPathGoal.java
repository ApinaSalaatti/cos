package com.dogshitempire.cos.ai.goals;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.pathfinding.PathEdge;

/**
 *
 * @author Merioksan Mikko
 */
public class FollowPathGoal extends CompositeGoal {
    private Array<PathEdge> path;
    
    public FollowPathGoal(CatBrain owner, Array<PathEdge> path) {
        super(owner, Goal.GOAL_FOLLOW_PATH);
        
        this.path = path;
    }
    
    @Override
    public void activate() {
        setStatus(GoalStatus.ACTIVE);
        
        PathEdge edge = path.removeIndex(0);
        
        switch(edge.getTraverseMethod()) {
            case WALK:
                addSubgoal(new TraverseEdgeGoal(getBrain(), edge, path.size == 0));
                break;
            case JUMP:
                //addSubgoal(new JumpGoal(getBrain(), edge));
                break;
        }
    }
    
    @Override
    public GoalStatus update(float deltaSeconds) {
        activateIfInactive();
        
        setStatus(processSubgoals(deltaSeconds));
        if(getStatus() == GoalStatus.COMPLETED && path.size > 0) {
            activate();
        }
        
        return getStatus();
    }
    
    @Override
    public void terminate() {
        
    }
}
