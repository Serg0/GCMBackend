package com.nadolinskyi.serhii.gcmbackend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by serg0 on 7/10/13.
 */
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public java.lang.Long id;

    public java.lang.String chatname;
    public java.lang.String chatmessage;
    public java.lang.Long chattimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatname() {
        return chatname;
    }

    public void setChatname(String chatname) {
        this.chatname = chatname;
    }

    public String getChatmessage() {
        return chatmessage;
    }

    public void setChatmessage(String chatmessage) {
        this.chatmessage = chatmessage;
    }

    public Long getChattimestamp() {
        return chattimestamp;
    }

    public void setChattimestamp(Long chattimestamp) {
        this.chattimestamp = chattimestamp;
    }
}