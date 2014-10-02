package com.dogshitempire.cos.ai.goals;

import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.pathfinding.PathEdge;

/**
 *
 * @author Merioksan Mikko
 */
public class TraverseEdgeGoal extends Goal {
    private PathEdge edge;
    private boolean lastEdge;
    
    public TraverseEdgeGoal(CatBrain owner, PathEdge edge, boolean lastEdge) {
        super(owner, Goal.GOAL_TRAVERSE_EDGE);
        
        this.edge = edge;
        this.lastEdge = lastEdge;
    }
    
    @Override
    public void activate() {
        setStatus(GoalStatus.ACTIVE);
        getBrain().getCat().getMover().setTarget(edge.getEnd().x, edge.getEnd().y);
    }
    
    @Override
    public GoalStatus update(float deltaSeconds) {
        activateIfInactive();
        
        if(getBrain().getCat().getMover().isAtPosition(edge.getEnd().x, edge.getEnd().y)) {
            setStatus(GoalStatus.COMPLETED);
            getBrain().getCat().getMover().stop();
        }
        
        return getStatus();
    }
    
    @Override
    public void terminate() {
        
    }
}
