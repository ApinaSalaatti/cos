package com.dogshitempire.cos.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dogshitempire.cos.actors.GameActor;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Item extends GameActor {
    public enum Place { FLOOR, WALL, CEILING, ANY };
    private Place place;
    
    private int widthInTiles;
    private int heightInTiles;
    
    private ItemTile[][] grid;
    
    private Texture tex;
    public void setTexture(Texture tex) {
        this.tex = tex;
    }
    
    /**
     * 
     * @param id
     * @param width width in tiles
     * @param height height in tiles
     * @param place 
     */
    public Item(int id) {
        super(id);
    }
    
    /**
     * 
     * @param width width in tiles
     * @param height height in tiles
     * @param place 
     */
    public void init(int width, int height, Place place) {
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
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if(super.hit(x, y, touchable) != null) {
            Vector2 pos = getGridPosition(x, y);
            if(grid[(int)pos.y][(int)pos.x].isTaken()) {
                return this;
            }
        }
        
        return null;
    }
    
    private Vector2 getGridPosition(float x, float y) {
        Vector2 vec = new Vector2();
        x /= ItemGrid.TILE_WIDTH;
        x = MathUtils.floor(x);
        y /= ItemGrid.TILE_HEIGHT;
        y = MathUtils.floor(y);
        
        vec.set(x, y);
        
        return vec;
    }

    public abstract String getInfo();
}
