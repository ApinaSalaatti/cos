/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dogshitempire.cos.activities.Activity;

/**
 *
 * @author Super-Aapo
 */
public class CompleteActivityTask extends Task {
    private Activity activity;
    private int needToSatisfy;
    
    private float activityTimer = 0f;
    private float activityInterval = 2f;
    
    public CompleteActivityTask(Goal goal, float speed) {
        this(goal, speed, -1);
    }
    
    /**
     * 
     * @param goal
     * @param speed how many seconds should pass between each time progress is added on the activity
     * @param need the need we want to satisfy. If -1 this Task accepts any activity
     */
    public CompleteActivityTask(Goal goal, float speed, int need) {
        super(goal);
        
        activityInterval = speed;
        needToSatisfy = need;
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
        if(activity.satisfiesNeed(needToSatisfy)) {
            if(!activity.reserveSlot(goal.getBrain().getCat())) {
                abort();
            }
        }
        else {
            abort();
        }
    }
    
    @Override
    public void update(float deltaSeconds) {
        activityTimer += deltaSeconds;
        if(activityTimer >= activityInterval) {
            activityTimer = 0;
            if(activity.addProgress(goal.getBrain().getCat())) {
                getDone();
            }
        }
    }
}
