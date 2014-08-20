/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.activities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pools;

/**
 *
 * @author Super-Aapo
 */
public class ActivityGrid {
    public static int TILE_WIDTH = 30;
    public static int TILE_HEIGHT = 30;
    
    private Texture tileTex;
    
    private ActivityTile[][] tiles;
    
    public ActivityGrid() {
        tiles = new ActivityTile[20][30];
        for(int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[0].length; x++) {
                tiles[y][x] = new ActivityTile();
            }
        }
        
        tiles[3][3].take();
        
        Pixmap pm = new Pixmap(TILE_WIDTH, TILE_HEIGHT, Pixmap.Format.RGBA8888);
        pm.setColor(Color.WHITE);
        pm.drawLine(0, 0, pm.getWidth()-1, 0);
        pm.drawLine(0, 0, 0, pm.getHeight()-1);
        pm.drawLine(pm.getWidth()-1, 0, pm.getWidth()-1, pm.getHeight()-1);
        pm.drawLine(0, pm.getHeight()-1, pm.getWidth()-1, pm.getWidth()-1);
        tileTex = new Texture(pm);
    }
    
    public void draw(Batch batch) {
        for(int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[0].length; x++) {
                Color oldCol = batch.getColor();
                if(tiles[y][x].isTaken()) {
                    batch.setColor(Color.RED);
                }
                else {
                    batch.setColor(Color.WHITE);
                }
                
                batch.draw(tileTex, x*TILE_WIDTH, y*TILE_HEIGHT);
                batch.setColor(oldCol);
            }
        }
    }
}
