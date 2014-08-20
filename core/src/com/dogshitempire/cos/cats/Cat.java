/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dogshitempire.cos.GameApplication;

/**
 *
 * @author Super-Aapo
 */
public class Cat extends Actor {
    private int catID;
    public int getID() {
        return catID;
    }
    
    public static final int GENDER_FEMALE = 1;
    public static final int GENDER_MALE = 2;
    
    private String catName;
    private int gender;
    
    private Texture tex;
    private Texture selected;
    private boolean isSelected = false;
    
    private CatStats stats;
    private CatMover mover;
    private CatBrain brain;
    
    public Cat(int id) {
        catID = id;
        
        tex = GameApplication.getAssetManager().get("cat.png", Texture.class);
        Pixmap pm = new Pixmap(tex.getWidth(), tex.getHeight(), Format.RGBA8888);
        pm.setColor(Color.GREEN);
        pm.drawLine(0, 0, pm.getWidth()-1, 0);
        pm.drawLine(0, 0, 0, pm.getHeight()-1);
        pm.drawLine(pm.getWidth()-1, 0, pm.getWidth()-1, pm.getHeight()-1);
        pm.drawLine(0, pm.getHeight()-1, pm.getWidth()-1, pm.getWidth()-1);
        selected = new Texture(pm);
        
        stats = new CatStats();
        mover = new CatMover(this);
        brain = new CatBrain(this);
    }
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        stats.act(deltaSeconds);
        mover.act(deltaSeconds);
        brain.act(deltaSeconds);
        
        setBounds(getX(), getY(), tex.getWidth(), tex.getHeight());
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tex, getX(), getY());
        if(isSelected) {
            batch.draw(selected, getX(), getY());
        }
    }
    
    public void toggleSelected(boolean selected) {
        this.isSelected = selected;
    }

    public String getCatName() {
        return catName;
    }
    public void setCatName(String name) {
        this.catName = name;
    }
    
    public int getGender() {
        return gender;
    }
    public String getGenderLetter() {
        if(gender == GENDER_MALE) {
            return "M";
        }
        else if(gender == GENDER_FEMALE) {
            return "F";
        }
        
        return "?";
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    
    public CatMover getMover() {
        return mover;
    }
    
    public Texture getImage() {
        return tex;
    }
    
    public CatStats getStats() {
        return stats;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof Cat) {
            Cat c = (Cat)other;
            if(c.getID() == getID()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getID();
    }
}
