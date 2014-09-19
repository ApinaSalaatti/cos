package com.dogshitempire.cos.ai.goals;

import com.dogshitempire.cos.ai.CatBrain;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class GoalEvaluator {
    private final CatBrain brain;
    public CatBrain getBrain() {
        return brain;
    }
    
    public GoalEvaluator(CatBrain brain) {
        this.brain = brain;
    }
    
    public abstract float calculateDesirability();
    public abstract Goal giveGoal();
}
