/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
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
    
    public boolean isAtPosition(float x, float y) {
        Vector2 v = Pools.obtain(Vector2.class);
        v.set(x, y);
        
        if(v.dst(cat.getX(), cat.getY()) < 2f) {
            Pools.free(v);
            return true;
        }
        
        Pools.free(v);
        return false;
    }
    
    public void act(float deltaSeconds) {
        //Gdx.app.log("CATMOVER", cat.getX() + " " + cat.getY());
        // Check the position just below the center of the cat
        Vector2 pos = grid.getGridPosition(cat.getX()+cat.getWidth()/2, cat.getY()-3);
        if(pos.x < 0 || pos.x >= grid.getTiles()[0].length || pos.y < 0 || pos.y >= grid.getTiles().length) {
            // WE ARE BEYOND THE GRID OH GOD!!
            //grounded = false;
        }
        else if(!grid.getTiles()[(int)pos.y][(int)pos.x].isSolid(ItemTile.TileSide.TOP)) {
            grounded = false;
        }
        else {
            grounded = true;
        }
        
        move(deltaSeconds);
        
        pos = grid.getGridPosition(cat.getX(), cat.getY());
        //Gdx.app.log("CATMOVER", pos.x + ", " + pos.y);
        if(pos.x < 0 || pos.x >= grid.getTiles()[0].length || pos.y < 0 || pos.y >= grid.getTiles().length) {
            // WE ARE BEYOND THE GRID AGAIN OH GOD!!
            //Gdx.app.log("CATMOVER", "WOAH " + pos.x + ", " + pos.y);
            return;
        }

        // If we are inside a solid object, let's move out
        if(grid.getTiles()[(int)pos.y][(int)pos.x].isSolid(ItemTile.TileSide.TOP)) {
            cat.setY((pos.y+1)*ItemGrid.TILE_HEIGHT);
        }
        if(grid.getTiles()[(int)pos.y][(int)pos.x].isSolid(ItemTile.TileSide.BOTTOM)) {
            cat.setY((pos.y-1)*ItemGrid.TILE_HEIGHT);
        }
        if(grid.getTiles()[(int)pos.y][(int)pos.x].isSolid(ItemTile.TileSide.LEFT)) {
            cat.setX((pos.x-1)*ItemGrid.TILE_WIDTH);
        }
        if(grid.getTiles()[(int)pos.y][(int)pos.x].isSolid(ItemTile.TileSide.RIGHT)) {
            cat.setX((pos.x+1)*ItemGrid.TILE_WIDTH);
        }
    }
    
    public void jump() {
        aboutToJump = true;
    }
    
    private void move(float deltaSeconds) {
        float xDiff = gotoTarget.x - cat.getX();
        
        //Gdx.app.log("CATMOVER", gotoTarget.x + " vs " + cat.getX());
        
        if(xDiff < 0) {
            xDiff = -1;
        }
        else {
            xDiff = 1;
        }
        
        float gravity = 0;
        
        if(grounded && movement.y <= 0) {
            movement.y = 0;
            //cat.setY(grid.getGridPosition(cat.getX(), cat.getY()).y*ItemGrid.TILE_HEIGHT);
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
    
    public void debugDraw(Batch batch) {
        
    }
}
