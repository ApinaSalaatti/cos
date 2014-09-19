package com.dogshitempire.cos.items.furniture;

import com.badlogic.gdx.graphics.Texture;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.items.Item;
import com.dogshitempire.cos.items.ItemTile;

/**
 *
 * @author Merioksan Mikko
 */
public class Chair extends Item {

    public Chair(int id) {
        super(id);
        
        super.init(4, 8, Item.Place.FLOOR);
        
        getTile(0,3).setSolid(ItemTile.TileSide.TOP, true);
        getTile(1,3).setSolid(ItemTile.TileSide.TOP, true);
        getTile(2,3).setSolid(ItemTile.TileSide.TOP, true);
        getTile(3,3).setSolid(ItemTile.TileSide.TOP, true);
        
        getTile(0,0).take();
        getTile(0,1).take();
        getTile(0,2).take();
        getTile(0,3).take();
        getTile(0,4).take();
        getTile(0,5).take();
        getTile(0,6).take();
        getTile(0,7).take();
        
        getTile(3,0).take();
        getTile(3,1).take();
        getTile(3,2).take();
        getTile(3,3).take();
        
        setTexture(GameApplication.getAssetManager().get("chair.png", Texture.class));
    }
    
    @Override
    public String getInfo() {
        return "A chair";
    }
}
