/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.items;

/**
 *
 * @author Super-Aapo
 */
public class ItemTile {
    public enum TileSide { TOP, BOTTOM, LEFT, RIGHT };
    
    private boolean taken;
    private boolean reserved;
    private boolean solidTop;
    private boolean solidBottom;
    private boolean solidLeft;
    private boolean solidRight;
    
    public ItemTile() {
        solidTop = solidBottom = solidLeft = solidRight = false;
    }
    
    public boolean isTaken() {
        return taken;
    }
    public void take() {
        taken = true;
    }
    public void release() {
        taken = false;
    }
    
    public boolean isReserved() {
        return reserved;
    }
    public void reserve() {
        reserved = true;
    }
    public void unreserve() {
        reserved = false;
    }
    
    public void setSolid(TileSide side, boolean solid) {
        switch(side) {
            case TOP:
                solidTop = solid;
                break;
            case BOTTOM:
                solidBottom = solid;
                break;
            case LEFT:
                solidLeft = solid;
                break;
            case RIGHT:
                solidRight = solid;
                break;
        }
        
        // If a side of a tile is solid, it must also be taken.
        if(solid) take();
    }
    
    public boolean isSolid(TileSide side) {
        switch(side) {
            case TOP:
                return solidTop;
            case BOTTOM:
                return solidBottom;
            case LEFT:
                return solidLeft;
            case RIGHT:
                return solidRight;
        }
        
        return false;
    }
}
