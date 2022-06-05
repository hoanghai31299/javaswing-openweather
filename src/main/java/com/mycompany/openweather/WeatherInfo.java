/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openweather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class WeatherInfo {
    private String status = "";
    private String statusDescription = "";
    private String statusIconId = "";
    
    private String temp = "";
    private String feelLike = "";
    
    private  String humidity ="";
    private  String tempMax ="";
    private  String tempMin ="";
    private  String timezone ="";

    private final String windSpeed;
    
    private long sunrise;
    private long sunset;
    public WeatherInfo(String status, String statusDescription, String statusIconId, String temp, String feelLike, String humidity, String tempMax, String tempMin, String timezone, String windSpeed, long sunrise, long sunset) {
        this.status = status;
        this.statusDescription = statusDescription;
        this.statusIconId = statusIconId;
        this.temp = temp;
        this.feelLike = feelLike;
        this.humidity = humidity;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.timezone = timezone;
        this.windSpeed = windSpeed;
        this.sunrise = sunrise * 1000;
        this.sunset = sunset * 1000;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public String getStatusIconUrl() {
        String endpoint = "http://openweathermap.org/img/wn/";
        return endpoint + statusIconId + "@2x.png";
    }

    public String getTemp() {
        return temp + "°C";
    }

    public String getFeelLike() {
        return feelLike + "°C";
    }
     public String getWindSpeed() {
        return windSpeed + "m/s";
    }

    public String getHumidity() {
        return humidity + "%";
    }

    public String getTempMax() {
        return tempMax + "°C";
    }

    public String getTempMin() {
        return tempMin + "°C";
    }

    public String getTimezone() {
        int UTCTimeInt = Integer.parseInt(this.timezone);
        int plus = UTCTimeInt / 3600;
        return "+" + plus;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" + "status=" + status + ", statusDescription=" + statusDescription + ", statusIconId=" + statusIconId + ", temp=" + temp + ", feelLike=" + feelLike + ", humidity=" + humidity + ", tempMax=" + tempMax + ", tempMin=" + tempMin + ", timezone=" + timezone + '}';
    }
    
    public String getCurrentTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fomarted = df.format(date).toString() + " (UCT" + this.getTimezone() + ")";
        return fomarted;
    }
    
    public String getSunrise() {
        Date date = new Date(this.sunrise);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String fomarted = df.format(date).toString();
        return fomarted;
    }
    public String getSunset() {
        Date date = new Date(this.sunset);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String fomarted = df.format(date).toString();
        return fomarted;
    }
}
