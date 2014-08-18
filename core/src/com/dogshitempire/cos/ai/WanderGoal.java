/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatMover;

/**
 *
 * @author Super-Aapo
 */
public class WanderGoal extends Goal {
    
    public WanderGoal(Cat cat) {
        super(cat);
    }
    
    @Override
    public void update(float deltaSeconds) {
        if(getCat().getY() > 6) {
            if(getCat().getMover().getMode() != CatMover.MoveMode.GOTO) {
                getCat().getMover().setTarget(getCat().getX() + (float)Math.random() * 30 - 60, 6);
            }
        }
        else {
            getCat().getMover().setMode(CatMover.MoveMode.WANDER);
        }
    }
}
