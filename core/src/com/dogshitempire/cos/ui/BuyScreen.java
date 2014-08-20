/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Pools;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.activities.Activity;
import com.dogshitempire.cos.activities.Bowl;
import com.dogshitempire.cos.stages.HomeStage;

/**
 *
 * @author Super-Aapo
 */
public class BuyScreen extends Table {
    private Skin skin;
    
    private Activity objectBeingBought;
    
    // The stage this screen is part of. We know it must be a HomeStage
    private HomeStage homeStage;
    
    public BuyScreen() {
        final BuyScreen bs = this;
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        Button button = new Button(skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor target) {
                objectBeingBought = new Bowl();
            }
        });
        
        button.add(new Image(GameApplication.getAssetManager().get("bowl.png", Texture.class)));
        add(button);
        
        this.setFillParent(true);
    }
    
    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        
        if(!(s instanceof HomeStage)) {
            closeScreen();
        }
        
        s.setKeyboardFocus(this);
        homeStage = (HomeStage)s;
    }
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        if(objectBeingBought != null) {
            Vector2 vec = Pools.obtain(Vector2.class);
            vec.set(Gdx.input.getX(), Gdx.input.getY());
            vec = getStage().screenToStageCoordinates(vec);
            
            objectBeingBought.setX(vec.x);
            objectBeingBought.setY(vec.y);
        }
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor a = super.hit(x, y, touchable);
        if(a != null && a != this) {
            return a;
        }
        
        if(objectBeingBought != null) {
            return this;
        }
        else {
            return null;
        }
    }
    
    @Override
    public boolean fire(Event event) {
        if(super.fire(event)) {
            return true;
        }
        
        if(event instanceof InputEvent) {
            InputEvent ie = (InputEvent)event;
            if(ie.getType() == Type.touchDown) {
                handleTouch(ie);
            }
            if(ie.getType() == Type.keyDown) {
                handleKeyDown(ie);
            }
        }
        
        return false;
    }
    
    private void handleKeyDown(InputEvent event) {
        if(event.getKeyCode() == Keys.B) {
            closeScreen();
            event.handle();
        }
    }
    
    private void handleTouch(InputEvent event) {
        if(event.getButton() == 0) {
            if(objectBeingBought != null) {
                homeStage.addActor(objectBeingBought);
                homeStage.addActivity(objectBeingBought);
                objectBeingBought = null;
                event.handle();
            }
        }
        else if(event.getButton() == 1) {
            objectBeingBought = null;
        }
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        homeStage.getActivityGrid().draw(batch);
        if(objectBeingBought != null) {
            objectBeingBought.draw(batch, alpha);
        }
        
        super.draw(batch, alpha);
    }
    
    public void closeScreen() {
        getStage().setKeyboardFocus(null);
        getStage().getActors().removeValue(this, true);
        this.clear();
    }
}
