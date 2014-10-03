package com.dogshitempire.cos.achievements;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Achievement {
    private final AchievementManager manager;
    
    private final String name;
    public String getName() {
        return name;
    }
    
    public Achievement(AchievementManager manager, String name) {
        this.manager = manager;
        this.name = name;
    }
    
    public abstract boolean check();
}
