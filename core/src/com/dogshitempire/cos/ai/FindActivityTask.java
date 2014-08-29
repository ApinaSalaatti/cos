package com.dogshitempire.cos.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.items.activities.Activity;
import com.dogshitempire.cos.stages.HomeStage;

/**
 *
 * @author Merioksan Mikko
 */
public class FindActivityTask extends Task {
    private Activity foundActivity;
    private int needToSatisfy;
    
    public FindActivityTask(Goal goal, int need) {
        super(goal);
        
        needToSatisfy = need;
    }
    
    public Activity getFoundActivity() {
        return foundActivity;
    }
    
    @Override
    public void update(float deltaSeconds) {
        if(foundActivity != null) {
            if(foundActivity.satisfiesNeed(needToSatisfy, goal.getBrain().getCat())) {
                return;
            }
        }
        
        HomeStage hs = (HomeStage)goal.getBrain().getCat().getStage();
        Array<Activity> activities = hs.getActivities();
        
        for(int i = 0; i < activities.size; i++) {
            Activity a = activities.get(i);
            if(a.satisfiesNeed(needToSatisfy, goal.getBrain().getCat())) {
                //Gdx.app.log("FAT", "ACTIVITY FOUND!");
                foundActivity = a;
                getDone();
                return;
            }
        }
        
        //Gdx.app.log("FFT", "ABORT!!");
        abort();
    }
    
    @Override
    public boolean done() {
        return (foundActivity != null && foundActivity.satisfiesNeed(needToSatisfy, goal.getBrain().getCat())) || done;
    }
}
