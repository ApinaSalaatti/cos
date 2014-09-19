package com.dogshitempire.cos.processes;

/**
 * A cool process that does nothing but chills, thus delaying the execution of it's child processes!
 * @author Merioksan Mikko
 */
public class DelayProcess extends GameProcess {
    private final float amount;
    private float timer;
    
    /**
     * 
     * @param delayAmount time to delay in seconds
     */
    public DelayProcess(float delayAmount) {
        amount = delayAmount;
        timer = 0;
    }
    
    @Override
    public void update(float deltaSeconds) {
        timer += deltaSeconds;
        if(timer >= amount) {
            succeed();
        }
    }
    
    // Getters for displaying purposes or something
    public float getAmount() {
        return amount;
    }
    public float getElapsed() {
        return timer;
    }
}
