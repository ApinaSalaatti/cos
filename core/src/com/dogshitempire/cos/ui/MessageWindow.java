/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 *
 * @author Super-Aapo
 */
public class MessageWindow extends Table {
    private Skin skin;
    private Label label;
    private TextButton button;
    
    public MessageWindow(String text) {
        final MessageWindow window = this;
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.setSkin(skin);
        
        label = new Label(text, skin, "blackText");
        label.setWrap(true);
        button = new TextButton("OK", skin, "default");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                s.getSprite().getTexture().dispose();
                getStage().getActors().removeValue(window, true);
            }
        });
        
        add(label).width(500).pad(20);
        row();
        add(button).width(60);
        
        setX((Gdx.graphics.getWidth()) / 2);
        setY((Gdx.graphics.getHeight()) / 2);
        
        Pixmap pm = new Pixmap((int)getPrefWidth(), (int)getPrefHeight(), Format.RGBA8888);
        pm.setColor(Color.MAGENTA);
        pm.fill();
        Texture tex = new Texture(pm);
        Sprite spr = new Sprite(tex);
        s = new SpriteDrawable(spr);
        this.setBackground(s, false);
    }
    
    SpriteDrawable s;
    
    @Override
    public void draw(Batch batch, float alpha) {
        s.draw(batch, getX()-getPrefWidth()/2, getY()-getPrefHeight()/2, getPrefWidth(), getPrefHeight());
        super.draw(batch, alpha);
    }
}
