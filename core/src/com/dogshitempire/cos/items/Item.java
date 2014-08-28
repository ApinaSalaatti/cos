package com.dogshitempire.cos.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Item extends Actor {
    public enum Place { FLOOR, WALL, CEILING, ANY };
    private Place place;
    
    private int widthInTiles;
    private int heightInTiles;
    
    private ItemTile[][] grid;
    
    protected Texture tex;
    
    /**
     * 
     * @param width width in tiles
     * @param height height in tiles
     * @param place 
     */
    public Item(int width, int height, Place place) {
        this.widthInTiles = width;
        this.heightInTiles = height;
        
        this.place = place;
        grid = new ItemTile[height][width];
        for(int y = 0; y < grid.length; y++) {
            for(int x = 0; x < grid[0].length; x++) {
                grid[y][x] = new ItemTile();
            }
        }
        
        setBounds(getX(), getY(), width*ItemGrid.TILE_WIDTH-6, height*ItemGrid.TILE_HEIGHT-6);
    }
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        setBounds(getX(), getY(), widthInTiles*ItemGrid.TILE_WIDTH-6, heightInTiles*ItemGrid.TILE_HEIGHT-6);
    }
    
    public ItemTile getTile(int x, int y) {
        return grid[y][x];
    }
    public int getWidthInTiles() {
        return widthInTiles;
    }
    public int getHeightInTiles() {
        return heightInTiles;
    }
    
    public Place getPlace() {
        return place;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(tex, getX(), getY());
    }

    public abstract String getInfo();
}
