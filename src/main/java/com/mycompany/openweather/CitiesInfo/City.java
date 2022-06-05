/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openweather.CitiesInfo;

/**
 *
 * @author Admin
 */
public class City {
    private String name;
    private String lat;
    private String lng;
    private String state;
    public City( String name, String lat, String lng,String state) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.state = state;
    }

    public String getState() {
        return state;
    }


    public City() {
    }

    @Override
    public String toString() {
        return "CityInfo{" + "name=" + name + ", lat=" + lat + ", lng=" + lng + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    
    public String getCityString() {
        return name + "," + state;
    }
    
}
