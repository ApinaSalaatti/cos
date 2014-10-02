package com.dogshitempire.cos.ai.fsm;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dogshitempire.cos.GameApplication;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class FSMState {
    // The different states
    public static final int STATE_GLOBAL_CAT_STATE = -1;
    public static final int STATE_BASE_MACHINE = 0;
    public static final int STATE_WANDER = 1;
    public static final int STATE_SATISFY_NEEDS = 2;
    public static final int STATE_SATISFY_CLEANLINESS = 3;
    public static final int STATE_SATISFY_HAPPINESS = 4;
    public static final int STATE_SATISFY_HEALTH = 5;
    public static final int STATE_SATISFY_HUNGER = 6;
    
    private final int type;
    public int getType() {
        return type;
    }
    
    protected FSMachine machine;
    public void setMachine(FSMachine machine) {
        this.machine = machine;
    }
    
    public FSMState(int type) {
        this.type = type;
        
        debugSkin = GameApplication.getAssetManager().get("uiskin.json", Skin.class);
        
        if(GameApplication.debugMode) initDebug();
    }
    
    protected Skin debugSkin;
    
    public void initDebug() {
        
    }
    
    public void debugDraw(Batch batch) {
        
    }
    
    public abstract int checkTransition();
    public abstract void enter();
    public abstract void exit();
    public abstract void update(float deltaSeconds);
}
