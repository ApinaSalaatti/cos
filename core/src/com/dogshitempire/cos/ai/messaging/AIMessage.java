package com.dogshitempire.cos.ai.messaging;

/**
 * AIMessages can be send between cats or withing the same cat's brain to share information. Wicked!
 * @author Merioksan Mikko
 */
public class AIMessage {
    public enum MessageType { PATH_FOUND, NO_PATH_FOUND }
    
    public int recipient;
    public float delay;
    public MessageType type;
    public Object additionalData;
    
    public AIMessage(int recipientID, float sendDelay, MessageType t, Object data) {
        recipient = recipientID;
        delay = sendDelay;
        type = t;
        additionalData = data;
    }
}
