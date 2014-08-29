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
    
    private FindActivityTask find;
    private GoToPositionTask move;
    private CompleteActivityTask complete;
    
    public SatisfyNeedGoal(CatBrain brain, int need, float tolerance) {
        super(brain);
        
        this.need = need;
        this.tolerance = tolerance;
        
        find = new FindActivityTask(this, need);
        //FindFoodTask fft = new FindFoodTask(this);
        move = new GoToPositionTask(this);
        //EatFoodTask eft = new EatFoodTask(this);
        complete = new CompleteActivityTask(this, 2f, need);
        find.setNextTask(move);
        move.setNextTask(complete);
        
        //this.setTask(fat);
    }
    
    @Override
    public void update(float deltaSeconds) {
        super.update(deltaSeconds);
        
        if(find.done()) {
            move.setPosition(find.getFoundActivity().getX(), find.getFoundActivity().getY());
            if(move.done()) {
                complete.setActivity(find.getFoundActivity());
                if(complete.done()) {
                    getDone();
                }
                else {
                    complete.update(deltaSeconds);
                    if(complete.aborted()) {
                        abort();
                    }
                }
            }
            else {
                move.update(deltaSeconds);
            }
        }
        else {
            find.update(deltaSeconds);
            if(find.aborted()) {
                abort();
            }
        }
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
