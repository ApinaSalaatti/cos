package com.dogshitempire.cos.ai.fsm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.ai.CatBrain;

/**
 *
 * @author Merioksan Mikko
 */
public class FSMachine extends FSMState {
    Array<FSMState> states;
    private FSMState currentState;
    protected FSMState getCurrentState() {
        return currentState;
    }
    
    private FSMState defaultState;
    
    private FSMState globalState;
    public void setGlobalState(FSMState state) {
        globalState = state;
        globalState.setMachine(this);
    }
    
    protected CatBrain brain;
    public CatBrain getBrain() {
        return brain;
    }
    
    public FSMachine(int type, CatBrain brain) {
        super(type);
        
        this.brain = brain;
        
        states = new Array<FSMState>();
    }
    
    public void setDefaultState(FSMState state) {
        defaultState = state;
    }
    
    public void addState(FSMState state) {
        state.setMachine(this);
        if(states.size == 0) {
            defaultState = state;
        }
        states.add(state);
    }
    
    @Override
    public void update(float deltaseconds) {
        if(states.size <= 0) {
            return;
        }
        
        if(currentState == null)
            currentState = defaultState;
        if(currentState == null)
            return;
        
        int oldState = currentState.getType();
        int newState = 0;
        
        // First we let the global state update and check for state changes it might want to make
        if(globalState != null) {
            globalState.update(deltaseconds);
            newState = globalState.checkTransition();
            if(newState != FSMState.STATE_GLOBAL_CAT_STATE && newState != oldState) {
                changeState(newState);
            }
        }
        
        // Then let the current state handle updates and state changes
        // NOTE: if the global state made any state changes, they are already in effect
        currentState.update(deltaseconds);
        newState = currentState.checkTransition();
        
        if(oldState != newState) {
            changeState(newState);
        }
    }
    
    public void changeState(int state) {
        Gdx.app.log("STATEMACHINE", "CHANGING STATE TO " + state);
        if(currentState != null) currentState.exit();
        
        for(int i = 0; i < states.size; i++) {
            FSMState s = states.get(i);
            if(s.getType() == state) {
                currentState = s;
                currentState.enter();
                return;
            }
        }
        
        Gdx.app.log("STATEMACHINE", "INVALID STATE GIVEN");
        // Given state was invalid, fall back to default
        currentState = defaultState;
        currentState.enter();
    }
    
    @Override
    public void enter() {
        
    }
    
    @Override
    public void exit() {
        
    }
    
    @Override
    public int checkTransition() {
        return FSMState.STATE_BASE_MACHINE;
    }
    
    @Override
    public void debugDraw(Batch batch) {
        if(currentState != null) currentState.debugDraw(batch);
    }
}
