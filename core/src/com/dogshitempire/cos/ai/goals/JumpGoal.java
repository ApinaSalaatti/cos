package com.dogshitempire.cos.ai.goals;

import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.pathfinding.PathEdge;

/**
 *
 * @author Merioksan Mikko
 */
public class JumpGoal extends Goal {
    private PathEdge edge;
    public JumpGoal(CatBrain brain, PathEdge edge) {
        super(brain, Goal.GOAL_JUMP);
        
        this.edge = edge;
    }

    @Override
    public void activate() {
        setStatus(GoalStatus.ACTIVE);
        getBrain().getCat().getMover().jump();
    }
    
    @Override
    public GoalStatus update(float deltaSeconds) {
        activateIfInactive();
        
        if(getBrain().getCat().getMover().isAtPosition(edge.getEnd().x, edge.getEnd().y)) {
            setStatus(GoalStatus.COMPLETED);
        }
        
        return getStatus();
    }
    
    @Override
    public void terminate() {
        
    }
}
