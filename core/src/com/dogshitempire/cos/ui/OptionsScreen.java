package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Json;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.GameOptions;

/**
 *
 * @author Merioksan Mikko
 */
public class OptionsScreen extends Table {
    
    public OptionsScreen() {
        FileHandle f = Gdx.files.local("settings.cfg");
        if(f.exists()) {
            
        }
    }
    
    public void saveOptions() {
        FileHandle f = Gdx.files.local("settings.cfg");
        Json j = new Json();
        j.toJson(GameApplication.getOptions(), GameOptions.class);
    }
}
