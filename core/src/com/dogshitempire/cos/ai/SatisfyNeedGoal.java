package com.dogshitempire.cos.ai;

import com.dogshitempire.cos.cats.CatBrain;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class SatisfyNeedGoal extends Goal {
    private int need;
    private float tolerance;
    
    public SatisfyNeedGoal(CatBrain brain, int need, float tolerance) {
        super(brain);
        
        this.need = need;
        this.tolerance = tolerance;
        
        FindActivityTask fat = new FindActivityTask(this, need);
        //FindFoodTask fft = new FindFoodTask(this);
        GoToPositionTask gtpt = new GoToPositionTask(this);
        //EatFoodTask eft = new EatFoodTask(this);
        CompleteActivityTask ct = new CompleteActivityTask(this, 2f, need);
        fat.setNextTask(gtpt);
        gtpt.setNextTask(ct);
        
        this.setTask(fat);
    }
    
    @Override
    public boolean aborted() {
        boolean needSatisfied = false;
        CatStats stats = getBrain().getCat().getStats();
        
        switch(need) {
            case CatStats.NEED_CLEANLINESS:
                if(stats.getCleanliness() > tolerance) needSatisfied = true;
                break;
            case CatStats.NEED_HAPPINESS:
                if(stats.getHappiness() > tolerance) needSatisfied = true;
                break;
            case CatStats.NEED_HEALTH:
                if(stats.getHealth() > tolerance) needSatisfied = true;
                break;
            case CatStats.NEED_HUNGER:
                if(stats.getHunger() > tolerance) needSatisfied = true;
                break;
        }
        
        return aborted || needSatisfied;
    }
}
