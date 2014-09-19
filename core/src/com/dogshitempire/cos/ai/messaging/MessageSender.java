package com.dogshitempire.cos.ai.messaging;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

/**
 * MessageSender handles sending messages between cats' brains!
 * @author Merioksan Mikko
 */
public class MessageSender {
    private Array<AIMessage> messagesToSend;
    
    public MessageSender() {
        messagesToSend = new Array<AIMessage>();
    }
    
    public void update(float deltaSeconds) {
        Iterator<AIMessage> it = messagesToSend.iterator();
        while(it.hasNext()) {
            AIMessage m = it.next();
            m.delay -= deltaSeconds;
            if(m.delay <= 0) {
                
            }
        }
    }
}
