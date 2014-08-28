/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Super-Aapo
 */
public class ItemGrid {
    public static int TILE_WIDTH = 30;
    public static int TILE_HEIGHT = 30;
    
    private ItemTile[][] tiles;
    
    public ItemGrid() {
        tiles = new ItemTile[20][30];
        for(int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[0].length; x++) {
                tiles[y][x] = new ItemTile();
                if(y == 0) {
                    tiles[y][x].setSolid(ItemTile.TileSide.TOP, true);
                }
            }
        }
    }
    
    /**
     * 
     * @param item
     * @return the tiles taken int x,y -pairs, eg array[0][0] is the x component and array[0][1] the y component of the first tile
     */
    public int[][] getOccopiedTiles(Item item) {
        Vector2 bl = getGridPosition(item.getX(), item.getY());
        Vector2 tr = getGridPosition(item.getRight(), item.getTop());
        
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
    
    public boolean canPlace(Item item) {
        Vector2 bl = getGridPosition(item.getX(), item.getY());
        Vector2 tr = getGridPosition(item.getRight(), item.getTop());
        
        if(bl.x < 0 || bl.x >= tiles[0].length-item.getWidthInTiles() || bl.y < 0 || bl.y >= tiles.length-item.getHeightInTiles()) {
            Gdx.app.log("IG", "TOO FAR bl: " + bl.y + " vs" + (tiles.length-item.getHeightInTiles()));
            return false;
        }
        if(tr.x < 0 || tr.x >= tiles[0].length || tr.y < 0 || tr.y >= tiles.length) {
            Gdx.app.log("IG", "TOO FAR tr: " + tr.y + " vs" + (tiles.length-item.getHeightInTiles()));
            return false;
        }
        
        for(int y = (int)bl.y; y <= (int)tr.y; y++) {
            for(int x = (int)bl.x; x <= (int)tr.x; x++) {
                if(tiles[y][x].isTaken()) {
                    Gdx.app.log("IG", "TAKEN");
                    return false;
                }
            }
        }
        
        Gdx.app.log("IG", "CAN STILL PLACE");
        // Make sure there's floor or ceiling present when needed
        switch(item.getPlace()) {
            case FLOOR:
                Gdx.app.log("IG", "SIDE IS SOLID: " + sideIsSolid(item, ItemTile.TileSide.BOTTOM));
                if(!sideIsSolid(item, ItemTile.TileSide.BOTTOM)) return false;
                break;
            case CEILING:
                if(!sideIsSolid(item, ItemTile.TileSide.TOP)) return false;
                break;
        }
        
        return true;
    }
    
    public void place(Item item) {
        Vector2 bl = getGridPosition(item.getX(), item.getY());
        Vector2 tr = getGridPosition(item.getRight(), item.getTop());
        
        if(bl.x < 0 || bl.x >= tiles[0].length-item.getWidthInTiles() || bl.y < 0 || bl.y >= tiles.length-item.getHeightInTiles()) {
            return;
        }
        if(tr.x < 0 || tr.x >= tiles[0].length || tr.y < 0 || tr.y >= tiles.length) {
            return;
        }
        
        int ix = (int)bl.x;
        int iy = (int)bl.y;
        
        for(int y = 0; y < item.getHeightInTiles(); y++) {
            for(int x = 0; x < item.getWidthInTiles(); x++) {
                tiles[iy+y][ix+x].setSolid(ItemTile.TileSide.TOP, item.getTile(x, y).isSolid(ItemTile.TileSide.TOP));
                tiles[iy+y][ix+x].setSolid(ItemTile.TileSide.BOTTOM, item.getTile(x, y).isSolid(ItemTile.TileSide.BOTTOM));
                tiles[iy+y][ix+x].setSolid(ItemTile.TileSide.LEFT, item.getTile(x, y).isSolid(ItemTile.TileSide.LEFT));
                tiles[iy+y][ix+x].setSolid(ItemTile.TileSide.RIGHT, item.getTile(x, y).isSolid(ItemTile.TileSide.RIGHT));
                if(item.getTile(x, y).isTaken()) {
                    tiles[iy+y][ix+x].take();
                }
            }
        }
        
        //for(int y = (int)bl.y; y <= (int)tr.y; y++) {
        //    for(int x = (int)bl.x; x <= (int)tr.x; x++) {
        //        tiles[y][x].take();
        //    }
        //}
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
    
    public ItemTile[][] getTiles() {
        return tiles;
    }
    
    public boolean sideIsSolid(Item item, ItemTile.TileSide side) {
        Vector2 from = null;
        Vector2 to = null;
        switch(side) {
            case TOP:
                from = getGridPosition(item.getX(), item.getTop());
                to = getGridPosition(item.getRight(), item.getTop());
                for(int y = (int)from.y; y <= (int)to.y; y++) {
                    for(int  x = (int)from.x; x <= (int)to.x; x++) {
                        if(!tiles[y+1][x].isSolid(ItemTile.TileSide.BOTTOM)) {
                            return false;
                        }
                    }
                }
                break;
            case BOTTOM:
                from = getGridPosition(item.getX(), item.getY());
                to = getGridPosition(item.getRight(), item.getY());
                for(int y = (int)from.y; y <= (int)to.y; y++) {
                    for(int  x = (int)from.x; x <= (int)to.x; x++) {
                        if(!tiles[y-1][x].isSolid(ItemTile.TileSide.TOP)) {
                            return false;
                        }
                    }
                }
                break;
            case LEFT:
                from = getGridPosition(item.getX(), item.getY());
                to = getGridPosition(item.getX(), item.getTop());
                for(int y = (int)from.y; y <= (int)to.y; y++) {
                    for(int  x = (int)from.x; x <= (int)to.x; x++) {
                        if(!tiles[y][x-1].isSolid(ItemTile.TileSide.RIGHT)) {
                            return false;
                        }
                    }
                }
                break;
            case RIGHT:
                from = getGridPosition(item.getRight(), item.getY());
                to = getGridPosition(item.getRight(), item.getTop());
                for(int y = (int)from.y; y <= (int)to.y; y++) {
                    for(int  x = (int)from.x; x <= (int)to.x; x++) {
                        if(!tiles[y][x+1].isSolid(ItemTile.TileSide.LEFT)) {
                            return false;
                        }
                    }
                }
                break;
        }
        
        return true;
    }
    
    public void snapToTile(Item item, ItemTile.TileSide side) {
        Vector2 pos;
        
        switch(side) {
            case TOP:
                pos = getGridPosition(item.getX(), item.getTop());
                item.setPosition(item.getX(), TILE_HEIGHT*(pos.y+1)-(item.getHeight()+1));
                break;
            case BOTTOM:
                pos = getGridPosition(item.getX(), item.getY());
                item.setPosition(item.getX(), TILE_HEIGHT*pos.y);
                break;
            case LEFT:
                pos = getGridPosition(item.getX(), item.getY());
                item.setPosition(TILE_WIDTH*pos.x, item.getY());
                break;
            case RIGHT:
                pos = getGridPosition(item.getRight(), item.getY());
                item.setPosition(TILE_WIDTH*(pos.x+1)-(item.getWidth()+1), item.getY());
                break;
        }
    }
}
