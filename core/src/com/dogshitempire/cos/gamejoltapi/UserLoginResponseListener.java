package com.dogshitempire.cos.gamejoltapi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.dogshitempire.cos.utilities.Debugging;

/**
 *
 * @author Merioksan Mikko
 */
public class UserLoginResponseListener implements HttpResponseListener {
    private GameJolt gameJolt;
    
    public UserLoginResponseListener(GameJolt gj) {
        gameJolt = gj;
    }
    
    @Override
    public void handleHttpResponse(Net.HttpResponse response) {
        String r = response.getResultAsString();
        Debugging.debugLog("GAMEJOLT API USER LOGIN", "USER LOGIN REQUEST RESPONSE: " + r);
        
        String[] lines = r.split("\n");
        if(lines[0].trim().equals("success:\"true\"")) {
            gameJolt.loginSuccess();
            Debugging.debugLog("GAMEJOL API USER LOGIN", "LOGIN SUCCESS!");
        }
        else {
            gameJolt.loginFailed();
            Debugging.debugLog("GAMEJOLT API USER LOGIN", "LOGIN FAIL!");
        }
    }
    
    @Override
    public void cancelled() {
        Debugging.debugLog("GAMEJOLT API USER LOGIN", "LOGIN CONNECTION CANCELLED");
    }
    
    @Override
    public void failed(Throwable t) {
        Debugging.debugLog("GAMEJOLT API USER LOGIN", "LOGIN CONNECTION FAIL OR SOMETHING");
        gameJolt.loginConnectionFailed();
    }
}
