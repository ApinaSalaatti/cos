/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatBrain;
import com.dogshitempire.cos.cats.CatMover;

/**
 *
 * @author Super-Aapo
 */
public class WanderGoal extends Goal {
    
    public WanderGoal(CatBrain brain) {
        super(brain);
    }
    
    @Override
    public void update(float deltaSeconds) {
        Cat cat = getBrain().getCat();
        
        if(cat.getY() > 6) {
            if(cat.getMover().getMode() != CatMover.MoveMode.GOTO) {
                cat.getMover().setTarget(cat.getX() + (float)Math.random() * 30 - 60, 6);
            }
        }
        else {
            cat.getMover().setMode(CatMover.MoveMode.WANDER);
        }
    }
    
}
