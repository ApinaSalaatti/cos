/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.lady;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dogshitempire.cos.GameApplication;

/**
 *
 * @author Super-Aapo
 */
public class Lady extends Actor {
    private Texture tex;
    
    public Lady() {
        tex = GameApplication.getAssetManager().get("lady.png", Texture.class);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(tex, getX(), getY());
    }
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        setBounds(getX(), getY(), tex.getWidth(), tex.getHeight());
    }
}
