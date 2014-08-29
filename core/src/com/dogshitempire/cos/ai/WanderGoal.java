/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dogshitempire.cos.cats.CatBrain;

/**
 *
 * @author Super-Aapo
 */
public class WanderGoal extends Goal {
    public GoToPositionTask move;
    
    private float jumpInterval;
    private float jumpTimer = 0;
    
    public WanderGoal(CatBrain brain) {
        super(brain);
        
        float x = 10 + (float)Math.random()*Gdx.graphics.getWidth();
        float y = 30;
        move = new GoToPositionTask(this, new Vector2(x, y));
        
        jumpInterval = 10 + (float)Math.random() * 5;
    }
    
    @Override
    public void update(float deltaSeconds) {
        jumpTimer += deltaSeconds;
        if(jumpTimer >= jumpInterval) {
            getBrain().getCat().getMover().jump();
            jumpTimer = 0; 
            jumpInterval = 10 + (float)Math.random() * 5;
        }
        
        if(move.done()) {
            float x = 10 + (float)Math.random()*Gdx.graphics.getWidth();
            float y = 30;
            move.setPosition(x, y);
        }
        else {
            move.update(deltaSeconds);
        }
    }
    
}
