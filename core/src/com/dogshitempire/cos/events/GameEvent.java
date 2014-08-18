/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.events;

/**
 *
 * @author Super-Aapo
 */
public class GameEvent {
    /*
     * Event types
     */
    public static final int changeStageEvent = 1;
    public static final int catCreatedEvent = 2;
    public static final int activityCreatedEvent = 3;

    private int eventType;
    private Object eventData;

    public int getType() {
            return eventType;
    }

    public Object getData() {
            return eventData;
    }

    public GameEvent(int type, Object data) {
            eventType = type;
            eventData = data;
    }
}
