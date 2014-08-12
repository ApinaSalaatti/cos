package com.dogshitempire.cos;

import com.badlogic.gdx.ApplicationAdapter;
import com.dogshitempire.cos.stages.GameStage;

public class GameApplication extends ApplicationAdapter {
	private GameStage currentStage;
	
	@Override
	public void create () {
            
	}

	@Override
	public void render () {
		
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
