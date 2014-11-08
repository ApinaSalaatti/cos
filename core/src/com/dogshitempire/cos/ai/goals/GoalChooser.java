package com.dogshitempire.cos.ai.goals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.goals.evaluators.SatisfyNeedGoalEvaluator;
import com.dogshitempire.cos.ai.goals.evaluators.WanderGoalEvaluator;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.utilities.Debugging;

/**
 *
 * @author Merioksan Mikko
 */
public class GoalChooser extends CompositeGoal {
    private Array<GoalEvaluator> evaluators;
    
    public GoalChooser(CatBrain owner) {
        super(owner, Goal.GOAL_CHOOSER);
        
        evaluators = new Array<GoalEvaluator>();
        
        evaluators.add(new WanderGoalEvaluator(owner));
        evaluators.add(new SatisfyNeedGoalEvaluator(owner, CatStats.NEED_CLEANLINESS));
        evaluators.add(new SatisfyNeedGoalEvaluator(owner, CatStats.NEED_HAPPINESS));
        evaluators.add(new SatisfyNeedGoalEvaluator(owner, CatStats.NEED_HEALTH));
        evaluators.add(new SatisfyNeedGoalEvaluator(owner, CatStats.NEED_HUNGER));
    }
    
    @Override
    public void activate() {
        Debugging.debugLog("GoalChooser", "Figuring out new goal");
        
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
            Debugging.debugLog("GoalChooser", "New goal found:" + getCurrentGoal());
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
            Debugging.debugLog("GoalChooser", "Goal completed");
            setStatus(GoalStatus.INACTIVE);
        }
        
        return getStatus();
    }
}
