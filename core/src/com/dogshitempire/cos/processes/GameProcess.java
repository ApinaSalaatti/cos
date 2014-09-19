package com.dogshitempire.cos.processes;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class GameProcess {
    private final Array<GameProcess> children;
    
    private boolean aborted;
    private boolean succeeded;
    
    public GameProcess() {
        children = new Array<GameProcess>();
    }
    
    public void addChild(GameProcess c) {
        children.add(c);
    }
    public Array<GameProcess> getChildren() {
        return children;
    }
    
    public void update(float deltaSeconds) {
        
    }
    
    public void onStart() {
        
    }
    
    public void abort() {
        aborted = true;
    }
    public boolean aborted() {
        return aborted;
    }
    
    public void succeed() {
        succeeded = true;
    }
    public boolean succeeded() {
        return succeeded;
    }
    
    public boolean ended() {
        return aborted || succeeded;
    }
    
    public void onAbort() {
        
    }
    
    public void onSuccess() {
        
    }
}
