package com.dogshitempire.cos.actors;

import com.badlogic.gdx.utils.IntMap;

/**
 *
 * @author Merioksan Mikko
 */
public class GameActorManager {
    private IntMap<GameActor> actors;
    
    public GameActorManager() {
        actors = new IntMap<GameActor>();
    }
    
    public void addActor(GameActor a) {
        actors.put(a.getID(), a);
    }
    public GameActor getActor(int id) {
        return actors.get(id);
    }
    public GameActor removeActor(int id) {
        return actors.remove(id);
    }
}
