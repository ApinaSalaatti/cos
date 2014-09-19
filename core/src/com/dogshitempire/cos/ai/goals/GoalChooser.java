package com.dogshitempire.cos.ai.goals;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.ai.CatBrain;

/**
 *
 * @author Merioksan Mikko
 */
public class GoalChooser extends CompositeGoal {
    private Array<GoalEvaluator> evaluators;
    
    public GoalChooser(CatBrain owner) {
        super(owner, Goal.GOAL_CHOOSER);
        
        evaluators = new Array<GoalEvaluator>();
    }
    
    @Override
    public void activate() {
        float best = -1;
        GoalEvaluator bestEvaluator = null;
        
        for(int i = 0; i < evaluators.size; i++) {
            float d = evaluators.get(i).calculateDesirability();
            if(d > best) {
                best = d;
                bestEvaluator = evaluators.get(i);
            }
        }
        
        if(bestEvaluator == null) {
            throw new RuntimeException("GoalEvaluators failed, brainfreeze!");
        }
        else {
            addSubgoal(bestEvaluator.giveGoal());
        }
        
        setStatus(GoalStatus.ACTIVE);
    }
    
    @Override
    public void terminate() {
        
    }
    
    @Override
    public GoalStatus update(float deltaSeconds) {
        activateIfInactive();
        
        GoalStatus status = processSubgoals(deltaSeconds);
        
        if(status == GoalStatus.COMPLETED || status == GoalStatus.FAILED) {
            setStatus(GoalStatus.INACTIVE);
        }
        
        return getStatus();
    }
}
