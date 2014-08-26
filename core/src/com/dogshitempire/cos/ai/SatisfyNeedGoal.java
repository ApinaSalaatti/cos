package com.dogshitempire.cos.ai;

import com.dogshitempire.cos.cats.CatBrain;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyNeedGoal extends Goal {
    
    public SatisfyNeedGoal(CatBrain brain, int need) {
        super(brain);
        
        FindActivityTask fat = new FindActivityTask(this, need);
        //FindFoodTask fft = new FindFoodTask(this);
        GoToPositionTask gtpt = new GoToPositionTask(this);
        //EatFoodTask eft = new EatFoodTask(this);
        CompleteActivityTask ct = new CompleteActivityTask(this, 2f, need);
        fat.setNextTask(gtpt);
        gtpt.setNextTask(ct);
        
        this.setTask(fat);
    }
}
