/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.Interest;
import com.dogshitempire.cos.cats.Skill;

/**
 *
 * @author Super-Aapo
 */
public class CatScreen extends Table {
    private Skin skin;
    
    private Cat cat;
    
    private Image catImage;
    private Label catName;
    private Label catHealth;
    private Label catHappiness;
    private Label catCleanliness;
    private Label catHunger;
    private Label[] catInterests;
    private Label[] catSkills;
    
    private TextButton closeButton;
    private TextButton allCatsButton;
    
    public CatScreen(Cat cat) {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.cat = cat;
        this.setFillParent(true);
        
        catImage = new Image(cat.getImage());
        catName = new Label("", skin, "blackText");
        catHealth = new Label("", skin, "blackText");
        catHappiness = new Label("", skin, "blackText");
        catCleanliness = new Label("", skin, "blackText");
        catHunger = new Label("", skin, "blackText");
        
        catName.setFontScale(1.5f);
        add(catName).width(400).colspan(2);
        add(catImage);
        row();
        add(new Label("Health", skin, "blackText")).width(300);
        add(catHealth).width(100);
        row();
        add(new Label("Happiness", skin, "blackText")).width(300);
        add(catHappiness).width(100);
        row();
        add(new Label("Cleanliness", skin, "blackText")).width(300);
        add(catCleanliness).width(100);
        row();
        add(new Label("Hunger", skin, "blackText")).width(300);
        add(catHunger).width(100);
        row();
        
        row();
        add(new Label("Interests:", skin, "blackText")).width(300);
        row();
        Array<Interest> interests = cat.getStats().getInterests();
        catInterests = new Label[interests.size];
        for(int i = 0; i < catInterests.length; i++) {
            catInterests[i] = new Label(interests.get(i).getName(), skin, "blackText");
            add(catInterests[i]).width(300);
            row();
        }
        
        row();
        add(new Label("Skills:", skin, "blackText")).width(300);
        row();
        Array<Skill> skills = cat.getStats().getSkills();
        catSkills = new Label[skills.size];
        for(int i = 0; i < catSkills.length; i++) {
            catSkills[i] = new Label(skills.get(i).getName() + " (lvl " + skills.get(i).getLevel() + ")", skin, "blackText");
            add(catSkills[i]).width(300);
            row();
        }
        
        closeButton = new TextButton("Close", skin, "default");
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                closeScreen();
            }
        });
        add(closeButton);
        
        allCatsButton = new TextButton("All cats", skin, "default");
        allCatsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.log("CatScreen", "All cats coming soon ^_____^");
            }
        });
        add(allCatsButton);
        
        this.debug();
    }
    
    public void closeScreen() {
        getStage().getActors().removeValue(this, true);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        catName.setText(cat.getCatName() + " (" + cat.getGenderLetter() + ")");
        catHealth.setText("" + (int)cat.getStats().getHealth());
        catHappiness.setText("" + (int)cat.getStats().getHappiness());
        catCleanliness.setText("" + (int)cat.getStats().getCleanliness());
        catHunger.setText("" + (int)cat.getStats().getHunger());
        
        super.draw(batch, alpha);
    }
}
