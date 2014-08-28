/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Pools;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.items.activities.Activity;
import com.dogshitempire.cos.items.ItemGrid;
import com.dogshitempire.cos.items.ItemTile;
import com.dogshitempire.cos.items.activities.Bowl;
import com.dogshitempire.cos.items.activities.Toy;
import com.dogshitempire.cos.cats.Interest;
import com.dogshitempire.cos.items.Item;
import com.dogshitempire.cos.items.furniture.Chair;
import com.dogshitempire.cos.stages.HomeStage;

/**
 *
 * @author Super-Aapo
 */
public class BuyScreen extends Table {
    private Skin skin;
    private Texture tileTex;
    
    private Item objectBeingBought;
    
    // The stage this screen is part of. We know it must be a HomeStage
    private HomeStage homeStage;
    
    public BuyScreen() {
        final BuyScreen bs = this;
        
        skin = GameApplication.getAssetManager().get("uiskin.json", Skin.class);
        
        Button button = new Button(skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor target) {
                objectBeingBought = new Bowl();
            }
        });
        button.add(new Image(GameApplication.getAssetManager().get("bowl.png", Texture.class)));
        add(button);
        
        Button button2 = new Button(skin);
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor target) {
                objectBeingBought = new Toy(
                        2, 2,
                        Activity.Place.FLOOR,
                        GameApplication.getAssetManager().get("toy.png", Texture.class),
                        new Interest("Butts")
                );
            }
        });
        button2.add(new Image(GameApplication.getAssetManager().get("toy.png", Texture.class)));
        add(button2);
        
        Button button3 = new Button(skin);
        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor target) {
                objectBeingBought = new Chair();
            }
        });
        button3.add(new Image(GameApplication.getAssetManager().get("chair.png", Texture.class)));
        add(button3).size(32, 32);
        
        this.setFillParent(true);
        
        Pixmap pm = new Pixmap(ItemGrid.TILE_WIDTH, ItemGrid.TILE_HEIGHT, Pixmap.Format.RGBA8888);
        pm.setColor(Color.WHITE);
        pm.drawLine(0, 0, pm.getWidth()-1, 0);
        pm.drawLine(0, 0, 0, pm.getHeight()-1);
        pm.drawLine(pm.getWidth()-1, 0, pm.getWidth()-1, pm.getHeight()-1);
        pm.drawLine(0, pm.getHeight()-1, pm.getWidth()-1, pm.getWidth()-1);
        tileTex = new Texture(pm);
    }
    
    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        
        if(!(s instanceof HomeStage)) {
            closeScreen();
        }
        
        s.setKeyboardFocus(this);
        homeStage = (HomeStage)s;
    }
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        if(objectBeingBought != null) {
            Vector2 vec = Pools.obtain(Vector2.class);
            vec.set(Gdx.input.getX(), Gdx.input.getY());
            vec = getStage().screenToStageCoordinates(vec);
            
            objectBeingBought.setX(vec.x);
            objectBeingBought.setY(vec.y);
            
            homeStage.getItemGrid().snapToTile(objectBeingBought, ItemTile.TileSide.BOTTOM);
            
            objectBeingBought.act(deltaSeconds);
            
            Pools.free(vec);
        }
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor a = super.hit(x, y, touchable);
        if(a != null && a != this) {
            return a;
        }
        
        if(objectBeingBought != null) {
            return this;
        }
        else {
            return null;
        }
    }
    
    @Override
    public boolean fire(Event event) {
        if(super.fire(event)) {
            return true;
        }
        
        if(event instanceof InputEvent) {
            InputEvent ie = (InputEvent)event;
            if(ie.getType() == Type.touchDown) {
                handleTouch(ie);
            }
            if(ie.getType() == Type.keyDown) {
                handleKeyDown(ie);
            }
        }
        
        return false;
    }
    
    private void handleKeyDown(InputEvent event) {
        if(event.getKeyCode() == Keys.B) {
            closeScreen();
            event.handle();
        }
    }
    
    private void handleTouch(InputEvent event) {
        if(event.getButton() == 0) {
            if(objectBeingBought != null && homeStage.getItemGrid().canPlace(objectBeingBought)) {
                homeStage.getItemGrid().place(objectBeingBought);
                homeStage.addActor(objectBeingBought);
                if(objectBeingBought instanceof Activity) homeStage.addActivity((Activity)objectBeingBought);
                objectBeingBought = null;
                event.handle();
            }
        }
        else if(event.getButton() == 1) {
            objectBeingBought = null;
        }
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        drawGrid(batch);
        if(objectBeingBought != null) {
            objectBeingBought.draw(batch, alpha);
        }
        
        super.draw(batch, alpha);
    }
    
    public void drawGrid(Batch batch) {
        ItemTile[][] tiles = homeStage.getItemGrid().getTiles();
        int[][] occupied = new int[0][0];
        if(objectBeingBought != null) {
            occupied = homeStage.getItemGrid().getOccopiedTiles(objectBeingBought);
            
            // Reserving tiles is for drawing purposes only
            for(int i = 0; i < occupied.length; i++) {
                tiles[occupied[i][1]][occupied[i][0]].reserve();
            }
        }
        
        Color oldCol = batch.getColor();
        for(int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[0].length; x++) {
                if(tiles[y][x].isReserved()) {
                    if(tiles[y][x].isTaken()) {
                        batch.setColor(Color.RED);
                    }
                    else {
                        batch.setColor(Color.GREEN);
                    }
                }
                else {
                    batch.setColor(Color.WHITE);
                }
                
                batch.draw(tileTex, x*ItemGrid.TILE_WIDTH, y*ItemGrid.TILE_HEIGHT);
            }
        }
        batch.setColor(oldCol);
        
        // Release the reserved tiles
        for(int i = 0; i < occupied.length; i++) {
            int x = occupied[i][0];
            int y = occupied[i][1];
            tiles[y][x].unreserve();
        }
    }
    
    public void closeScreen() {
        getStage().setKeyboardFocus(null);
        getStage().getActors().removeValue(this, true);
        this.clear();
    }
}
