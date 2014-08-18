package com.dogshitempire.cos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dogshitempire.cos.events.EventManager;
import com.dogshitempire.cos.events.GameEvent;
import com.dogshitempire.cos.events.GameEventListener;
import com.dogshitempire.cos.stages.GameStage;
import com.dogshitempire.cos.stages.MainMenuStage;

public class GameApplication extends ApplicationAdapter implements GameEventListener {
    private GameStage currentStage;
    
    private static EventManager eventManager;
    public static EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public void create () {
        eventManager = new EventManager();
        eventManager.registerListener(GameEvent.changeStageEvent, this);
        
        changeStage(new MainMenuStage());
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaSeconds = Gdx.graphics.getDeltaTime();
        
        // Updating
        eventManager.update(deltaSeconds);
        currentStage.act(deltaSeconds);
        
        // Rendering
        currentStage.draw();
    }

    public void changeStage(GameStage newStage) {
        if(currentStage != null) {
            currentStage.onEnd();
            currentStage.clear();
            currentStage.dispose();
        }

        Gdx.input.setInputProcessor(newStage);
        currentStage = newStage;
        currentStage.onStart();
    }
    
    @Override
    public void receiveEvent(GameEvent event) {
        if(event.getType() == GameEvent.changeStageEvent) {
            GameStage newStage = (GameStage)event.getData();
            changeStage(newStage);
        }
    }
}
