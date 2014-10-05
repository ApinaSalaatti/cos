/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.gamejoltapi;

/**
 *
 * @author Merioksa
 */
public class GameJolt {
    public static int GAME_ID = -1;
    
    private String username;
    private String userToken;
    
    public GameJolt() {
        username = "";
        userToken = "";
    }
    
    public boolean verifyUser(String name, String token) {
        
        return true;
    }
    
    public boolean userVerified() {
        return username != "" && userToken != "";
    }
}
