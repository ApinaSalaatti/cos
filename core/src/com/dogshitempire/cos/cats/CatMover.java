/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dogshitempire.cos.items.ItemGrid;
import com.dogshitempire.cos.items.ItemTile;

/**
 *
 * @author Super-Aapo
 */
public class CatMover {
    private Cat cat;
    
    private ItemGrid grid;
    private Vector2 gotoTarget;
    
    private boolean aboutToJump;
    private float jumpPower = 2000;
    private boolean grounded;
    private Vector2 movement;
    private float speed;
    
    public CatMover(Cat cat) {
        this.cat = cat;
        
        gotoTarget = new Vector2();
        movement = new Vector2();
        speed = (float)(Math.random() + 0.2f) * 120;
    }
    
    public void setGrid(ItemGrid grid) {
        this.grid = grid;
    }
    
    public void act(float deltaSeconds) {
        // Check the position just below the center of the cat
        Vector2 pos = grid.getGridPosition(cat.getX()+cat.getWidth()/2, cat.getY()-1);
        if(pos.x < 0 || pos.x >= grid.getTiles()[0].length || pos.y < 0 || pos.y >= grid.getTiles().length) {
            // WE ARE BEYOND THE GRID OH GOD!!
        }
        else if(!grid.getTiles()[(int)pos.y][(int)pos.x].isSolid(ItemTile.TileSide.TOP)) {
            grounded = false;
        }
        else {
            grounded = true;
        }
        
        moveToTarget(deltaSeconds);
    }
    
    public void jump() {
        aboutToJump = true;
    }
    
    private void moveToTarget(float deltaSeconds) {
        speed = Math.abs(speed);
        
        float xDiff = gotoTarget.x - cat.getX();
        xDiff = xDiff / Math.abs(xDiff);
        float gravity = 0;
        
        if(grounded && movement.y <= 0) {
            movement.y = 0;
        }
        else {
            gravity = 100;
        }
        
        if(aboutToJump) {
            if(grounded) {
                Skill j = cat.getStats().getSkill("Jumping");
                if(j != null) movement.y = jumpPower * j.getLevel();
            }
            aboutToJump = false;
        }
        
        movement.x = xDiff * speed;
        movement.y -= gravity;
        
        cat.setX(cat.getX() + (movement.x * deltaSeconds));
        cat.setY(cat.getY() + (movement.y * deltaSeconds));
    }
    
    public void setTarget(float x, float y) {
        gotoTarget.x = x;
        gotoTarget.y = y;
    }
}
