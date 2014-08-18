/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.activities.Activity;
import com.dogshitempire.cos.activities.Bowl;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.events.GameEvent;
import com.dogshitempire.cos.factories.CatFactory;
import com.dogshitempire.cos.ui.ActionSelection;
import com.dogshitempire.cos.ui.CatSummary;
import com.dogshitempire.cos.ui.MessageWindow;

/**
 *
 * @author Super-Aapo
 */
public class HomeStage extends GameStage {
    private Skin skin;
    
    private CatFactory catFactory;
    private Array<Cat> cats;
    
    private Array<Activity> activities;
    
    private Vector2 stageCoords = new Vector2();
    private Cat hoveredCat;
    private Cat selectedCat;
    private CatSummary catSummary;
    
    private Label infoLabel;
    
    public HomeStage() {
        catFactory = new CatFactory();
        cats = new Array<Cat>();
        activities = new Array<Activity>();
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        catSummary = new CatSummary();
        catSummary.setPosition(150, 150);
        addActor(catSummary);
        
        Bowl bowl = new Bowl();
        bowl.setX(500);
        bowl.setY(500);
        addActivity(bowl);
        
        infoLabel = new Label("", skin, "blackText");
    }
    
    public void addCat() {
        Cat cat = catFactory.createCat();
        cat.setX(6);
        cat.setY(6);
        addActor(cat);
        cats.add(cat);
        
        addActor(new MessageWindow("Oh hey, you found a new cat! That's cool!"));
        GameApplication.getEventManager().queueEvent(new GameEvent(GameEvent.catCreatedEvent, cat));
    }
    
    public void addActivity(Activity a) {
        activities.add(a);
        addActor(a);
        GameApplication.getEventManager().queueEvent(new GameEvent(GameEvent.activityCreatedEvent, a));
    }
    public Array<Activity> getActivities() {
        return activities;
    }
    
    private float catSpawnTimer = 100f;
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        catSpawnTimer += deltaSeconds;
        if(catSpawnTimer >= 100f) {
            addCat();
            catSpawnTimer = 0f;
        }
    }
    
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        stageCoords = screenToStageCoordinates(stageCoords.set(screenX, screenY));
        Actor target = hit(stageCoords.x, stageCoords.y, false);
        if(target != null) {
            if(target instanceof Cat) {
                hoveredCat = (Cat)target;
                if(selectedCat == null) {
                    catSummary.setCat(hoveredCat);
                }
            }
            else {
                hoveredCat = null;
                if(selectedCat == null) {
                    catSummary.clearCat();
                }
                
                if(target instanceof Activity) {
                    infoLabel.setText(((Activity)target).getInfo());
                    infoLabel.setX(stageCoords.x);
                    infoLabel.setY(stageCoords.y);
                    addActor(infoLabel);
                }
                else {
                    getActors().removeValue(infoLabel, true);
                }
            }
        }
        else {
            hoveredCat = null;
            if(selectedCat == null) {
                catSummary.clearCat();
            }
            
            getActors().removeValue(infoLabel, true);
        }
        
        return super.mouseMoved(screenX, screenY);
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(super.touchDown(screenX, screenY, pointer, button)) {
            return true;
        }
        
        
        if(button == 1 && selectedCat != null) {
            selectedCat.toggleSelected(false);
            selectedCat = null;
            catSummary.clearCat();
        }
        
        stageCoords = screenToStageCoordinates(stageCoords.set(screenX, screenY));
        Actor target = hit(stageCoords.x, stageCoords.y, false);
        if(target != null) {
            return handleClick(stageCoords.x, stageCoords.y, target);
        }
        
        return false;
    }
    
    private boolean handleClick(float x, float y, Actor target) {
        if(target instanceof Cat) {
            if(selectedCat != null) {
                selectedCat.toggleSelected(false);
            }
            selectedCat = (Cat)target;
            selectedCat.toggleSelected(true);
            catSummary.setCat(selectedCat);
            return true;
        }
        else if(target instanceof Activity) {
            Activity a = (Activity)target;
            ActionSelection as = new ActionSelection(a);
            as.setX(x);
            as.setY(y);
            addActor(as);
        }
        
        return false;
    }
}
