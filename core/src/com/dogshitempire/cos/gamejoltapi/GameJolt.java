/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.gamejoltapi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.dogshitempire.cos.utilities.Debugging;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Merioksa
 */
public class GameJolt {
    public enum LoginStatus { NO_LOGIN, WRONG_CREDENTIALS, CONNECTION_ERROR, LOGIN_OK };
    private LoginStatus loginStatus = LoginStatus.NO_LOGIN;
    
    // Set these before use. :)
    private int gameID = 37205;
    private String privateKey = "ab865be72568c79b229fd9a745d9fc27";
    
    // These are set when the user is verified
    private String username;
    private String userToken;
    
    public GameJolt() {
        this.gameID = gameID;
        this.privateKey = privateKey;
        username = "";
        userToken = "";
    }
    
    public boolean userVerified() {
        return !username.equals("") && !userToken.equals("") && loginStatus == LoginStatus.LOGIN_OK;
    }
    
    public LoginStatus getLoginStatus() {
        return loginStatus;
    }
    
    public void login(String name, String token) {
        String url = "http://gamejolt.com/api/game/v1/users/auth/?";
        url += "game_id=" + gameID;
        url += "&username=" + name;
        url += "&user_token=" + token;
        
        url = getUrlWithSignature(url);
        HttpRequest request = new HttpRequest(HttpMethods.GET);
        request.setUrl(url);
        Gdx.net.sendHttpRequest(request, new UserLoginResponseListener(this));
        
        username = name;
        userToken = token;
    }
    
    protected void loginSuccess() {
        loginStatus = LoginStatus.LOGIN_OK;
    }
    protected void loginFailed() {
        loginStatus = LoginStatus.WRONG_CREDENTIALS;
    }
    protected void loginConnectionFailed() {
        loginStatus = LoginStatus.CONNECTION_ERROR;
    }
    
    public void logout() {
        loginStatus = LoginStatus.NO_LOGIN;
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
    
    private String getUrlWithSignature(String url) {
        return url + "&signature=" + createSignature(url);
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
    
    public void update(float deltaSeconds) {
        
    }
}
