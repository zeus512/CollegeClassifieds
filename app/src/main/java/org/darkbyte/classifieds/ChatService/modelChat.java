package org.darkbyte.classifieds.ChatService;

/**
 * Created by parvesh_dhull on 4/2/17.
 */

public class modelChat {

    private String message;
    private String sender;

    public modelChat(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
