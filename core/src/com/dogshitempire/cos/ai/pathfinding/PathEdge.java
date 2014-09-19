package com.dogshitempire.cos.ai.pathfinding;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Merioksan Mikko
 */
public class PathEdge {
    public enum TraverseMethod { WALK, JUMP }
    
    private TraverseMethod method;
    private Vector2 start;
    private Vector2 end;
    
    public PathEdge(Vector2 s, Vector2 e, TraverseMethod  m) {
        start = s;
        end = e;
        method = m;
    }
    
    public Vector2 getStart() {
        return start;
    }
    public Vector2 getEnd() {
        return end;
    }
    public TraverseMethod getTraverseMethod() {
        return method;
    }
}
