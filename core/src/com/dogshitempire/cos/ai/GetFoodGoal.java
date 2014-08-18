/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.dogshitempire.cos.cats.Cat;

/**
 *
 * @author Super-Aapo
 */
public class GetFoodGoal extends Goal {
    
    public GetFoodGoal(Cat cat) {
        super(cat);
        
        FindFoodTask fft = new FindFoodTask(this);
        GoToPositionTask gtpt = new GoToPositionTask(this);
        EatFoodTask eft = new EatFoodTask(this);
        fft.setNextTask(gtpt);
        gtpt.setNextTask(eft);
        
        this.setTask(fft);
    }
}
