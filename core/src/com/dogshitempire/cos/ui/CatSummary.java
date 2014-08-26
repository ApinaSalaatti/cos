/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.dogshitempire.cos.cats.Cat;

/**
 *
 * @author Super-Aapo
 */
public class CatSummary extends Table {
    private Cat cat;
    
    private Skin skin;
    
    private Label catName;
    private Label catHealth;
    private Label catHappiness;
    private Label catCleanliness;
    private Label catHunger;
    
    private TextButton button;
    
    public CatSummary() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        catName = new Label("", skin, "blackText");
        catHealth = new Label("", skin, "blackText");
        catHappiness = new Label("", skin, "blackText");
        catCleanliness = new Label("", skin, "blackText");
        catHunger = new Label("", skin, "blackText");
        
        catName.setFontScale(1.5f);
        add(catName).width(200).height(30).colspan(2);
        row();
        add(new Label("Health:", skin, "blackText")).width(100);
        add(catHealth).width(100);
        row();
        add(new Label("Happiness", skin, "blackText")).width(100);
        add(catHappiness).width(100);
        row();
        add(new Label("Cleanliness", skin, "blackText")).width(100);
        add(catCleanliness).width(100);
        row();
        add(new Label("Hunger", skin, "blackText")).width(100);
        add(catHunger).width(100);
        row();
        
        button = new TextButton("More info", skin, "default");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CatScreen screen = new CatScreen(cat);
                getStage().addActor(screen);
            }
        });
        button.setDisabled(true);
        
        add(button);
    }
    
    public void setCat(Cat cat) {
        if(cat == null) {
            return;
        }
        
        this.cat = cat;
        button.setDisabled(false);
    }
    public void clearCat() {
        this.cat = null;
        button.setDisabled(true);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if(cat != null) {
            catName.setText(cat.getCatName() + " (" + cat.getGenderLetter() + ")");
            catHealth.setText("" + (int)cat.getStats().getHealth());
            catHappiness.setText("" + (int)cat.getStats().getHappiness());
            catCleanliness.setText("" + (int)cat.getStats().getCleanliness());
            catHunger.setText("" + (int)cat.getStats().getHunger());
        }
        else {
            catName.setText("");
            catHealth.setText("");
            catHappiness.setText("");
            catCleanliness.setText("");
            catHunger.setText("");
        }
        
        super.draw(batch, alpha);
    }
}
