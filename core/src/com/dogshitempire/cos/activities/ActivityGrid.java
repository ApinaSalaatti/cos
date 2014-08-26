/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Super-Aapo
 */
public class ActivityGrid {
    public static int TILE_WIDTH = 30;
    public static int TILE_HEIGHT = 30;
    
    private ActivityTile[][] tiles;
    
    public ActivityGrid() {
        tiles = new ActivityTile[20][30];
        for(int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[0].length; x++) {
                tiles[y][x] = new ActivityTile();
            }
        }
    }
    
    /**
     * 
     * @param activity
     * @return the tiles taken int x,y -pairs, eg array[0][0] is the x component and array[0][1] the y component of the first tile
     */
    public int[][] getOccopiedTiles(Activity activity) {
        Vector2 bl = getGridPosition(activity.getX(), activity.getY());
        Vector2 tr = getGridPosition(activity.getRight(), activity.getTop());
        
        int minX = Math.min(Math.max((int)bl.x, 0), tiles[0].length-1);
        int minY = Math.min(Math.max((int)bl.y, 0), tiles.length-1);
        int maxX = Math.min(Math.max((int)tr.x, 0), tiles[0].length-1);
        int maxY = Math.min(Math.max((int)tr.y, 0), tiles.length-1);
        
        int amount = (maxY - minY + 1) * (maxX - minX + 1);
        
        int[][] arr = new int[amount][2];
        int i = 0;
        
        for(int y = minY; y <= maxY; y++) {
            for(int x = minX; x <= maxX; x++) {
                arr[i][0] = x;
                arr[i][1] = y;
                i++;
            }
        }
        
        return arr;
    }
    
    public boolean canPlace(Activity activity) {
        Vector2 bl = getGridPosition(activity.getX(), activity.getY());
        Vector2 tr = getGridPosition(activity.getRight(), activity.getTop());
        
        if(bl.x < 0 || bl.x >= tiles[0].length || bl.y < 0 || bl.y >= tiles.length) {
            return false;
        }
        if(tr.x < 0 || tr.x >= tiles[0].length || tr.y < 0 || tr.y >= tiles.length) {
            return false;
        }
        
        for(int y = (int)bl.y; y <= (int)tr.y; y++) {
            for(int x = (int)bl.x; x <= (int)tr.x; x++) {
                if(tiles[y][x].isTaken()) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void place(Activity activity) {
        Vector2 bl = getGridPosition(activity.getX(), activity.getY());
        Vector2 tr = getGridPosition(activity.getRight(), activity.getTop());
        
        if(bl.x < 0 || bl.x >= tiles[0].length || bl.y < 0 || bl.y >= tiles.length) {
            return;
        }
        if(tr.x < 0 || tr.x >= tiles[0].length || tr.y < 0 || tr.y >= tiles.length) {
            return;
        }
        
        for(int y = (int)bl.y; y <= (int)tr.y; y++) {
            for(int x = (int)bl.x; x <= (int)tr.x; x++) {
                tiles[y][x].take();
            }
        }
    }
    
    public Vector2 getGridPosition(float x, float y) {
        Vector2 vec = new Vector2();
        x /= TILE_WIDTH;
        x = MathUtils.floor(x);
        y /= TILE_HEIGHT;
        y = MathUtils.floor(y);
        
        vec.set(x, y);
        
        return vec;
    }
    
    public ActivityTile[][] getTiles() {
        return tiles;
    }
    
    public void snapToTile(Activity activity, ActivityTile.TileSide side) {
        Vector2 pos = null;
        
        switch(side) {
            case TOP:
                pos = getGridPosition(activity.getX(), activity.getTop());
                activity.setPosition(activity.getX(), TILE_HEIGHT*pos.y);
                break;
            case BOTTOM:
                pos = getGridPosition(activity.getX(), activity.getY());
                activity.setPosition(activity.getX(), TILE_HEIGHT*pos.y);
                break;
            case LEFT:
                pos = getGridPosition(activity.getX(), activity.getY());
                activity.setPosition(TILE_WIDTH*pos.x, activity.getY());
                break;
            case RIGHT:
                pos = getGridPosition(activity.getRight(), activity.getY());
                activity.setPosition(TILE_WIDTH*pos.x, activity.getY());
                break;
        }
    }
}
