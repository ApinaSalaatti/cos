package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.gamejoltapi.GameJolt;

/**
 *
 * @author Merioksan Mikko
 */
public class GameJoltLoginWidget extends Table {
    private Skin skin;
    
    private TextField username;
    private TextField userToken;
    private CheckBox remember;
    
    private Label loginInfoLabel;
    private Label logoutInfoLabel;
    
    private TextButton login;
    private TextButton logout;
    
    private Table loginTable;
    private Table logoutTable;
    
    private boolean loggingIn = false;

    public GameJoltLoginWidget() {
        skin = GameApplication.getAssetManager().get("uiskin.json", Skin.class);
        
        loginTable = new Table();
        logoutTable = new Table();

        login = new TextButton("Login", skin, "default");
        login.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    GameApplication.getGameJolt().login(username.getText(), userToken.getText());
                    loggingIn = true;
                    loginInfoLabel.setText("Logging in, please wait...");
                }
            });
        
        logout = new TextButton("Logout", skin, "default");
        logout.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    GameApplication.getGameJolt().logout();
                    logout();
                }
            });
        
        loginInfoLabel = new Label("Login to GameJolt", skin, "default");
        logoutInfoLabel = new Label("", skin, "default");
        
        username = new TextField("", skin);
        userToken = new TextField("", skin);
        
        remember = new CheckBox("Remember me", skin);
        
        loginTable.add(loginInfoLabel).width(400f).colspan(2);
        loginTable.row();
        loginTable.add(username);
        loginTable.add(userToken);
        loginTable.row();
        loginTable.add(remember);
        loginTable.row();
        loginTable.add(login).colspan(2);
        loginTable.setSize(410, 150);
        loginTable.debug();
        
        logoutTable.add(logoutInfoLabel).width(400f).colspan(2);
        logoutTable.row();
        logoutTable.add(logout).colspan(2);
        logoutTable.setSize(410, 150);
        logoutTable.debug();
        
        FileHandle f = Gdx.files.local("gjapi-credentials.txt");
        if(f.exists()) {
            String[] contents = f.readString().split("\n");
            if(contents.length > 1) {
                String n = contents[0];
                String t = contents[1];

                username.setText(n);
                userToken.setText(t);
                remember.toggle();
            }
        }
        
        add(loginTable);
    }
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        if(loggingIn) {
            if(GameApplication.getGameJolt().getLoginStatus() == GameJolt.LoginStatus.LOGIN_OK) {
                login();
            }
            else if(GameApplication.getGameJolt().getLoginStatus() == GameJolt.LoginStatus.WRONG_CREDENTIALS) {
                loggingIn = false;
                loginInfoLabel.setText("Username or token wrong. Please try again.");
            }
            else if(GameApplication.getGameJolt().getLoginStatus() == GameJolt.LoginStatus.CONNECTION_ERROR) {
                loggingIn = false;
                loginInfoLabel.setText("Error connecting to GameJolt.");
            }
        }
    }
    
    private void login() {
        loggingIn = false;
        this.removeActor(loginTable);
        this.add(logoutTable);
        
        if(remember.isChecked()) {
            FileHandle f = Gdx.files.local("gjapi-credentials.txt");
            f.writeString(username.getText() + "\n", false);
            f.writeString(userToken.getText(), true);
        }
        logoutInfoLabel.setText("Logged in as " + username.getText());

        // TODO: Get achievements from GameJolt.
    }
    
    private void logout() {
        this.removeActor(logoutTable);
        this.addActor(loginTable);
        loginInfoLabel.setText("Login to GameJolt");
        
        // TODO: Get achievements from file or smthng.
    }
}
