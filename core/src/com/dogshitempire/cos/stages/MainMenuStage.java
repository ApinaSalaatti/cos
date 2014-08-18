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
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.events.GameEvent;

/**
 *
 * @author Super-Aapo
 */
public class MainMenuStage extends GameStage {
    private Skin skin;
    
    public MainMenuStage() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        final TextButton button = new TextButton("Start the game, meow!", skin, "default");
        
        button.setWidth(300f);
        button.setHeight(50f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 150f, Gdx.graphics.getHeight()/2 - 25f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                GameApplication.getEventManager().queueEvent(new GameEvent(GameEvent.changeStageEvent, new HomeStage()));
            }
        });
        
        addActor(button);
    }
}
