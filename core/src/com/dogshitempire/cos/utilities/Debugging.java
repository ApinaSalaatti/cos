package com.dogshitempire.cos.utilities;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author Merioksan Mikko
 */
public class Debugging {
    public static boolean debugMode = true;
    
    public static void debugLog(String tag, String message) {
        if(debugMode) Gdx.app.log(tag, message);
    }
}
