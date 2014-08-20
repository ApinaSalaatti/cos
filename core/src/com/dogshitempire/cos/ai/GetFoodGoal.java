/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.dogshitempire.cos.cats.CatBrain;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Super-Aapo
 */
public class GetFoodGoal extends Goal {
    
    public GetFoodGoal(CatBrain brain) {
        super(brain);
        
        FindFoodTask fft = new FindFoodTask(this);
        GoToPositionTask gtpt = new GoToPositionTask(this);
        //EatFoodTask eft = new EatFoodTask(this);
        CompleteActivityTask ct = new CompleteActivityTask(this, 2f, CatStats.NEED_HUNGER);
        fft.setNextTask(gtpt);
        gtpt.setNextTask(ct);
        
        this.setTask(fft);
    }
}
