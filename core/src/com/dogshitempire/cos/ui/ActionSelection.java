/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.activities.Activity;

/**
 *
 * @author Super-Aapo
 */
public class ActionSelection extends Table {
    private Skin skin;
    
    public TextButton[] selections;
    
    public ActionSelection(final Activity target) {
        final ActionSelection as = this;
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        Array<String> actions = target.getPossibleActions();
        
        selections = new TextButton[actions.size];
        for(int i = 0; i < selections.length; i++) {
            final TextButton b = new TextButton(actions.get(i), skin, "default");
            selections[i] = b;
            b.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    target.receiveAction(b.getText().toString());
                    as.getStage().getActors().removeValue(as, true);
                }
            });
            
            add(selections[i]).width(100);
            row();
        }
    }
}
