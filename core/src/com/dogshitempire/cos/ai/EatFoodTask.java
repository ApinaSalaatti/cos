/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dogshitempire.cos.activities.Activity;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Super-Aapo
 */
public class EatFoodTask extends Task {
    private Activity activity;
    
    private float eatTimer = 0f;
    private float eatInterval = 2f;
    
    public EatFoodTask(Goal goal) {
        super(goal);
    }
    
    public void setActivity(Activity a) {
        activity = a;
    }
    
    @Override
    public void setTarget(Actor a) {
        super.setTarget(a);
        
        if(a instanceof Activity) {
            activity = (Activity)a;
        }
        else {
            abort();
        }
    }
    
    @Override
    public void onBegin() {
        if(activity.satisfiesNeed(CatStats.NEED_HUNGER)) {
            if(!activity.reserveSlot(goal.getCat())) {
                abort();
            }
        }
        else {
            abort();
        }
    }
    
    @Override
    public void update(float deltaSeconds) {
        eatTimer += deltaSeconds;
        if(eatTimer >= eatInterval) {
            eatTimer = 0;
            if(activity.addProgress(goal.getCat())) {
                getDone();
            }
        }
    }
}
