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
    
    private String description;
    public String getDescription() {
        return description;
    }
    
    public Achievement(AchievementManager manager, String name, String description) {
        this.manager = manager;
        this.name = name;
        this.description = description;
    }
    
    public abstract boolean check();
}
