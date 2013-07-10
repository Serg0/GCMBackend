package com.nadolinskyi.serhii.gcmbackend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by serg0 on 7/10/13.
 */

@Entity
public class CustomMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public java.lang.Long id;

    public java.lang.String name;
    public java.lang.String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public java.lang.Long timestamp;

}
