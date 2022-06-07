/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.openweather;

import com.mycompany.openweather.CitiesInfo.Cities;

/**
 *
 * @author Admin
 */
public class Openweather {

    public static void main(String[] args) {
//        WeatherData data = new WeatherData();
//        data.getWeather("16.06778", "108.22083");
        Home home = new Home();
        home.pack();
        home.setLocationRelativeTo(null);
        home.show();
        home.load();
    }
}
