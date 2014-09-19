package com.dogshitempire.cos.processes;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

/**
 *
 * @author Merioksan Mikko
 */
public class ProcessManager {
    private Array<GameProcess> processes;
    
    public ProcessManager() {
        processes = new Array<GameProcess>();
    }
    
    public void update(float deltaSeconds) {
        Iterator<GameProcess> it = processes.iterator();
        
        while(it.hasNext()) {
            GameProcess p = it.next();
            p.update(deltaSeconds);
            
            if(p.succeeded()) {
                p.onSuccess();
                // On success we add the processes children to the list of processes
                processes.addAll(p.getChildren());
            }
            else if(p.aborted()) {
                p.onAbort();
            }
            
            // Finally, remove the process if it has aborted or succeeded.
            if(p.ended()) {
                it.remove();
            }
        }
    }
    
    public void addProcess(GameProcess p) {
        p.onStart();
        processes.add(p);
    }
}
