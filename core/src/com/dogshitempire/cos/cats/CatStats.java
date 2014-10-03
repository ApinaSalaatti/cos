/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Super-Aapo
 */
public class CatStats {
    public static final float MAX_NEED_VALUE = 100f;
    
    public static final int NEED_HEALTH = 1;
    public static final int NEED_HAPPINESS = 2;
    public static final int NEED_CLEANLINESS = 3;
    public static final int NEED_HUNGER = 4;
    
    private float health;
    private float happiness;
    private float cleanliness;
    private float hunger;
    
    private Array<Interest> interests;
    
    private Array<Skill> skills;
    
    public CatStats() {
        interests = new Array<Interest>();
        skills = new Array<Skill>();
    }
    
    public void act(float deltaSeconds) {
        health -= deltaSeconds;
        happiness -= deltaSeconds;
        cleanliness -= deltaSeconds;
        hunger -= deltaSeconds;
    }
    
    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        if(health > 100) {
            health = 100;
        }
        if(health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public float getHappiness() {
        return happiness;
    }

    public void setHappiness(float happiness) {
        if(happiness > 100) {
            happiness = 100;
        }
        if(happiness < 0) {
            happiness = 0;
        }
        this.happiness = happiness;
    }

    public float getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(float cleanliness) {
        if(cleanliness > 100) {
            cleanliness = 100;
        }
        if(cleanliness < 0) {
            cleanliness = 0;
        }
        this.cleanliness = cleanliness;
    }

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        if(hunger > 100) {
            hunger = 100;
        }
        if(hunger < 0) {
            hunger = 0;
        }
        this.hunger = hunger;
    }
    
    /**
     * Returns the average status of the cat
     * @return 
     */
    public float getStatus() {
        return (health + happiness + cleanliness + hunger) / 4;
    }

    public Array<Interest> getInterests() {
        return interests;
    }
    public void addInterest(Interest interest) {
        // Check we don't already have the same interest
        for(int i = 0; i < interests.size; i++) {
            if(interests.get(i).getName().equals(interest.getName())) {
                return;
            }
        }
        
        interests.add(interest);
    }
    public Interest getInterest(String name) {
        for(int i = 0; i < interests.size; i++) {
            if(interests.get(i).getName().equals(name)) {
                return interests.get(i);
            }
        }
        
        return null;
    }
    public boolean removeInterest(Interest interest) {
        return interests.removeValue(interest, true);
    }

    public Array<Skill> getSkills() {
        return skills;
    }
    public void addSkill(Skill skill) {
        // Check we don't already have the same skill
        for(int i = 0; i < skills.size; i++) {
            if(skills.get(i).getName().equals(skill.getName())) {
                return;
            }
        }
        
        skills.add(skill);
    }
    public Skill getSkill(String skill) {
        for(int i = 0; i < skills.size; i++) {
            if(skills.get(i).getName().equals(skill)) {
                return skills.get(i);
            }
        }
        return null;
    }
    public boolean removeSkill(Skill skill) {
        return skills.removeValue(skill, true);
    }
    public boolean removeSkill(String skill) {
        Skill s = getSkill(skill);
        return removeSkill(s);
    }
    
    public void debugDraw(Batch batch) {
        
    }
}
