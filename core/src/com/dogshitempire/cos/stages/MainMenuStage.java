/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Super-Aapo
 */
public class MainMenuStage extends GameStage {
    private Skin skin;
    
    public MainMenuStage() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        final TextButton button = new TextButton("Click me", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                button.setText("You clicked the button");
            }
        });
        
        addActor(button);
        
        Gdx.input.setInputProcessor(this);
    }
}
