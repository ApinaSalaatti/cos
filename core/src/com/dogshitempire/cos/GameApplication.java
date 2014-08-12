package com.dogshitempire.cos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dogshitempire.cos.stages.GameStage;
import com.dogshitempire.cos.stages.MainMenuStage;

public class GameApplication extends ApplicationAdapter {
	private GameStage currentStage;
	
	@Override
	public void create () {
            changeStage(new MainMenuStage());
	}

	@Override
	public void render () {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            
            currentStage.act(Gdx.graphics.getDeltaTime());
            currentStage.draw();
	}
        
        public void changeStage(GameStage newStage) {
            if(currentStage != null) {
                currentStage.onEnd();
                currentStage.clear();
                currentStage.dispose();
            }
            
            currentStage = newStage;
            currentStage.onStart();
        }
}
