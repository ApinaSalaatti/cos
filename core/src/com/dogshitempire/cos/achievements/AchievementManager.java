package com.dogshitempire.cos.achievements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

/**
 *
 * @author Merioksan Mikko
 */
public class AchievementManager {
    private Array<Achievement> all;
    private Array<Achievement> achieved;
    private Array<Achievement> unachieved;
    
    private SpriteBatch batch;
    
    public AchievementManager() {
        all = new Array<Achievement>();
        achieved = new Array<Achievement>();
        unachieved = new Array<Achievement>();
        
        batch = new SpriteBatch();
    }
    
    public void update() {
        Iterator<Achievement> it = unachieved.iterator();
        while(it.hasNext()) {
            Achievement a = it.next();
            if(a.check()) {
                unlock(a);
                it.remove();
            }
        }
    }
    
    private void unlock(Achievement a) {
        achieved.add(a);
        // TODO: inform player about her cool new achievement
        // Send achievement data to GameJolt or some server or whatever
    }
    
    public void draw() {
        
    }
}
