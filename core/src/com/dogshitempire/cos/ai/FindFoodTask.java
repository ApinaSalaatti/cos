/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.activities.Activity;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.stages.HomeStage;

/**
 *
 * @author Super-Aapo
 */
public class FindFoodTask extends Task {
    private Activity foundActivity;
    
    public FindFoodTask(Goal goal) {
        super(goal);
    }
    
    @Override
    public void onDone() {
        //Gdx.app.log("FFT", "GUESS WERE DONE!! " + getNextTask().toString());
        if(getNextTask() instanceof GoToPositionTask) {
            //Gdx.app.log("FFT", "SETTING POS!!");
            GoToPositionTask gtpt = (GoToPositionTask)getNextTask();
            gtpt.setTarget(foundActivity);
        }
    }
    
    @Override
    public void update(float deltaSeconds) {
        HomeStage hs = (HomeStage)goal.getCat().getStage();
        Array<Activity> activities = hs.getActivities();
        
        for(int i = 0; i < activities.size; i++) {
            Activity a = activities.get(i);
            if(a.satisfiesNeed(CatStats.NEED_HUNGER)) {
                //Gdx.app.log("FFT", "FOOD FOUND!");
                foundActivity = a;
                getDone();
                return;
            }
        }
        
        //Gdx.app.log("FFT", "ABORT!!");
        abort();
    }
}
