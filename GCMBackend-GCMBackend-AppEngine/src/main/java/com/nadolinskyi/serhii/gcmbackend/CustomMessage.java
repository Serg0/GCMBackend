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

    public java.lang.Long   latitude;
    public java.lang.Long   longitude;
    public java.lang.String indefiner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public String getIndefiner() {
        return indefiner;
    }

    public void setIndefiner(String indefiner) {
        this.indefiner = indefiner;
    }

}
