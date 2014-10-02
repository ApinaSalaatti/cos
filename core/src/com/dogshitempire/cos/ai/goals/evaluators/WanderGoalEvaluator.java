package com.dogshitempire.cos.ai.goals.evaluators;

import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.goals.Goal;
import com.dogshitempire.cos.ai.goals.GoalEvaluator;
import com.dogshitempire.cos.ai.goals.WanderGoal;

/**
 *
 * @author Merioksan Mikko
 */
public class WanderGoalEvaluator extends GoalEvaluator {
    public WanderGoalEvaluator(CatBrain brain) {
        super(brain);
    }

    @Override
    public Goal giveGoal() {
        return new WanderGoal(getBrain());
    }
    
    @Override
    public float calculateDesirability() {
        return 0.05f;
    }
}
