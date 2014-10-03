package com.dogshitempire.cos.ai.goals.evaluators;

import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.goals.Goal;
import com.dogshitempire.cos.ai.goals.GoalEvaluator;
import com.dogshitempire.cos.ai.goals.SatisfyNeedGoal;
import com.dogshitempire.cos.items.activities.Activity;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyNeedGoalEvaluator extends GoalEvaluator {
    private int need;
    
    public SatisfyNeedGoalEvaluator(CatBrain brain, int need) {
        super(brain);
        this.need = need;
    }
    
    @Override
    public Goal giveGoal() {
        return new SatisfyNeedGoal(getBrain(), need);
    }
    
    @Override
    public float calculateDesirability() {
        if(!getBrain().getPerceptions().canSatisfyNeed(need)) {
            return 0;
        }
        
        float threshold = 0.5f;
        float n = getBrain().getPerceptions().getNeed(need);
        float w = getBrain().getWeight(need);
        Activity a = getBrain().getPerceptions().getClosestActivity(need);
        
        if(a != null) {
            return (threshold - n) * w;
        }
        
        return 0;
    }
}
