package com.dogshitempire.cos.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.items.activities.Activity;
import com.dogshitempire.cos.stages.HomeStage;

/**
 *
 * @author Merioksan Mikko
 */
public class PerceptionCenter {
    private CatBrain brain;
    
    // All the cat's need normalized to be in the range of 0 to 1
    public float cleanliness;
    public float happiness;
    public float health;
    public float hunger;
    
    private Array<Activity> cleanlinessActivities;
    private Array<Activity> happinessActivities;
    private Array<Activity> healthActivities;
    private Array<Activity> hungerActivities;
    
    public PerceptionCenter(CatBrain brain) {
       this.brain = brain;
       
       cleanlinessActivities = new Array<Activity>();
       happinessActivities = new Array<Activity>();
       healthActivities = new Array<Activity>();
       hungerActivities = new Array<Activity>();
    }
    
    public float getNeed(int need) {
        switch(need) {
            case CatStats.NEED_CLEANLINESS:
                return cleanliness;
            case CatStats.NEED_HAPPINESS:
                return happiness;
            case CatStats.NEED_HEALTH:
                return health;
            case CatStats.NEED_HUNGER:
                return hunger;
            default:
                return 0;
        }
    }
    
    public boolean canSatisfyNeed(int need) {
        switch(need) {
            case CatStats.NEED_CLEANLINESS:
                return cleanlinessActivities.size > 0;
            case CatStats.NEED_HAPPINESS:
                return happinessActivities.size > 0;
            case CatStats.NEED_HEALTH:
                return healthActivities.size > 0;
            case CatStats.NEED_HUNGER:
                return hungerActivities.size > 0;
            default:
                return false;
        }
    }
    
    public Activity getClosestActivity(int need) {
        Array<Activity> a = null;
        switch(need) {
            case CatStats.NEED_CLEANLINESS:
                a = cleanlinessActivities; break;
            case CatStats.NEED_HAPPINESS:
                a = happinessActivities; break;
            case CatStats.NEED_HEALTH:
                a = healthActivities; break;
            case CatStats.NEED_HUNGER:
                a = hungerActivities; break;
            default:
                return null;
        }
        
        if(a.size > 0) {
            Vector2 pos = Pools.obtain(Vector2.class);
            pos.set(brain.getCat().getX(), brain.getCat().getY());
            Activity closest = a.get(0);
            float closestDst = pos.dst2(closest.getX(), closest.getY());
            
            for(Activity ac : a) {
                float dst = pos.dst2(ac.getX(), ac.getY());
                if(dst < closestDst) {
                    closest = ac;
                    closestDst = dst;
                }
            }
            
            Pools.free(pos);
            return closest;
        }
        
        return null;
    }
    
    public void update(float deltaSeconds) {
        cleanliness = brain.getCat().getStats().getCleanliness() / CatStats.MAX_NEED_VALUE;
        happiness = brain.getCat().getStats().getHappiness() / CatStats.MAX_NEED_VALUE;
        health = brain.getCat().getStats().getHealth() / CatStats.MAX_NEED_VALUE;
        hunger = brain.getCat().getStats().getHunger() / CatStats.MAX_NEED_VALUE;
        
        scanActivities();
    }
    
    private void scanActivities() {
        // TODO: maybe change this so it only scans from a certain radius?
        // Then the cat could have a "search activities" state where it walks around looking for them
        
        cleanlinessActivities.clear();
        happinessActivities.clear();
        healthActivities.clear();
        hungerActivities.clear();
        
        Array<Activity> as = ((HomeStage)brain.getCat().getStage()).getActivities();
        for(Activity a : as) {
            if(a.satisfiesNeed(CatStats.NEED_CLEANLINESS, brain.getCat()))
                cleanlinessActivities.add(a);
            if(a.satisfiesNeed(CatStats.NEED_HAPPINESS, brain.getCat()))
                happinessActivities.add(a);
            if(a.satisfiesNeed(CatStats.NEED_HEALTH, brain.getCat()))
                healthActivities.add(a);
            if(a.satisfiesNeed(CatStats.NEED_HUNGER, brain.getCat()))
                hungerActivities.add(a);
        }
    }
}
