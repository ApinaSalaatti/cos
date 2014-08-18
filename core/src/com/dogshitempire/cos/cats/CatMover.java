/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Super-Aapo
 */
public class CatMover {
    public enum MoveMode { WANDER, GOTO, STAY };
    private MoveMode mode;
    
    private Cat cat;
    
    private Vector2 gotoTarget;
    
    private float speed;
    
    public CatMover(Cat cat) {
        this.cat = cat;
        
        gotoTarget = new Vector2();
        mode = MoveMode.WANDER;
        
        speed = (float)(Math.random() + 0.2f) * 120;
    }
    
    public void act(float deltaSeconds) {
        switch(mode) {
            case WANDER:
                wander(deltaSeconds);
                break;
            case GOTO:
                moveToTarget(deltaSeconds);
                break;
            case STAY:
                break;
        }
    }
    
    private void moveToTarget(float deltaSeconds) {
        speed = Math.abs(speed);
        
        float xDiff = gotoTarget.x - cat.getX();
        float yDiff = gotoTarget.y - cat.getY();
        
        Vector2 diff = new Vector2(xDiff, yDiff);
        diff.nor();
        diff.x *= speed;
        diff.y *= speed;
        
        cat.setX(cat.getX() + (diff.x * deltaSeconds));
        cat.setY(cat.getY() + (diff.y * deltaSeconds));
        
        if(gotoTarget.dst(cat.getX(), cat.getY()) <= 2f) {
            mode = MoveMode.STAY;
            cat.setPosition(gotoTarget.x, gotoTarget.y);
        }
    }
    
    private void wander(float deltaSeconds) {
        if(cat.getX() < 10 && speed < 0) {
            speed = -speed;
        }
        else if(cat.getX() > (Gdx.graphics.getWidth() - cat.getWidth() - 10) && speed > 0) {
            speed = -speed;
        }
        
        cat.setX(cat.getX() + (speed * deltaSeconds));
    }
    
    public void setTarget(float x, float y) {
        gotoTarget.x = x;
        gotoTarget.y = y;
        mode = MoveMode.GOTO;
    }
    
    public MoveMode getMode() {
        return mode;
    }
    public void setMode(MoveMode m) {
        mode = m;
    }
}
