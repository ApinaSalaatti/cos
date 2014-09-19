package com.dogshitempire.cos.ai.pathfinding;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.actors.GameActor;
import com.dogshitempire.cos.ai.CatBrain;
import com.dogshitempire.cos.ai.pathfinding.PathEdge.TraverseMethod;
import com.dogshitempire.cos.items.activities.Activity;

/**
 *
 * @author Merioksan Mikko
 */
public class Pathfinder {
    private CatBrain brain;
    
    public Pathfinder(CatBrain brain) {
        this.brain = brain;
    }
    
    public Array<PathEdge> findPathToSatisfyNeed(int need) {
        Activity a = brain.getPerceptions().getClosestActivity(need);
        if(a == null) return null;
        
        return findPathToActivity(a);
    }
    
    public Array<PathEdge> findPathToActivity(Activity a) {
        return findPathToPosition(a.getActivationPosition().x, a.getActivationPosition().y);
    }
    
    public Array<PathEdge> findPathToObject(GameActor object) {
        return findPathToPosition(object.getX(), object.getY());
    }
    
    public Array<PathEdge> findPathToPosition(float x, float y) {
        Array<PathEdge> a = new Array<PathEdge>();
        a.add(new PathEdge(
                new Vector2(brain.getCat().getX(), brain.getCat().getY()),
                new Vector2(x, y),
                TraverseMethod.WALK
        ));
        
        return a;
    }
}
