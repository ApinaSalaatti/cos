/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

/**
 *
 * @author Super-Aapo
 */
public class Skill {
    private String name;
    private int level;
    private int bonus;
    private int experience;
    
    public Skill(String name) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void setBonus(int b) {
        bonus = b;
    }
    public boolean isBuffed() {
        return bonus != 0;
    }
    
    public int getLevel() {
        return level + bonus;
    }
    
    public int getExperience() {
        return experience;
    }
    public boolean earnExperience(int xp) {
        experience += xp;
        if(experience >= getNeededExperience()) {
            experience -= getNeededExperience();
            level++;
            return true;
        }
        
        return false;
    }
    public int getNeededExperience() {
        return (level*level)*1000;
    }
}
