/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.gamejoltapi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Merioksa
 */
public class GameJolt {
    private int gameID = -1;
    private String privateKey = "";
    
    private String username;
    private String userToken;
    
    public GameJolt(int gameID, String privateKey) {
        this.gameID = gameID;
        this.privateKey = privateKey;
        username = "";
        userToken = "";
    }
    
    public boolean verifyUser(String name, String token) {
        
        return true;
    }
    
    public boolean userVerified() {
        return username != "" && userToken != "";
    }
    
    public boolean login(String name, String token) {
        String url = "http://gamejolt.com/api/game/v1/users/auth/?";
        url += "game_id=" + gameID;
        url += "&username=" + name;
        url += "&user_token=" + token;
        
        return true;
    }
    
    public static final int TROPHIES_ACHIEVED = 1;
    public static final int TROPHIES_NOT_ACHIEVED = 2;
    public static final int TROPHIES_ALL = 3;
    public String getTrophies(int achieved) {
        String res = "";
        
        String url = "http://gamejolt.com/api/game/v1/trophies/?format=json";
        url += "&game_id=" + gameID;
        url += "&username=" + username;
        url += "&user_token=" + userToken;
        
        if(achieved == TROPHIES_ACHIEVED)
            url += "&achieved=true";
        else if(achieved == TROPHIES_NOT_ACHIEVED)
            url += "&achieved=false";
        
        url += "&signature=" + createSignature(url);
        
        return res;
    }
    
    private String createSignature(String url) {
        return MD5(url + privateKey);
    }
    
    public String MD5(String input) {
        String res = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(input.getBytes());
            byte[] md5 = md.digest();
            String tmp = "";
            for(int i = 0; i <md5.length; i++) {
                tmp = Integer.toHexString(0xFF & md5[i]);
                if(tmp.length() == 1)
                    res += "0" + tmp;
                else
                    res += tmp;
            }
        } catch(NoSuchAlgorithmException e) {
            
        }
        
        return res;
    }
}
