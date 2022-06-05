/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openweather.CitiesInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Admin
 */
public class Cities {

    private ArrayList<City> cities;

    public Cities() {
        try {
            StringBuilder result = new StringBuilder();
            File jsonFile = new File("vn.json");
            Scanner scanner = new Scanner(jsonFile, "UTF-8");
            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }
            //close scanner
            scanner.close();
            String dataString = String.valueOf(result);
            this.cities = parseDataStringToCitiesArr(dataString);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /*
    example
     {
    "city": "Ho Chi Minh City", 
    "lat": "10.8167", 
    "lng": "106.6333", 
    "country": "Vietnam", 
    "iso2": "VN", 
    "admin_name": "Hồ Chí Minh", 
    "capital": "admin", 
    "population": "13312000", 
    "population_proper": "7431000"
  }, 
     */
    private ArrayList<City> parseDataStringToCitiesArr(String dataString) {
        try {
            JSONParser jsonParse = new JSONParser();
            JSONArray arr = (JSONArray) jsonParse.parse(dataString);
            ArrayList<City> result = new ArrayList<City>();
            for (int i = 0; i < arr.size(); i++) {
                JSONObject cityObj = (JSONObject) arr.get(i);

                String name = (String) cityObj.get("city");
                String lat = (String) cityObj.get("lat");
                String lng = (String) cityObj.get("lng");
                String state = (String) cityObj.get("admin_name");

                result.add(new City(name, lat, lng, state));
            }
            return result;
        } catch (ParseException e) {
            throw new RuntimeException("FAIL TO PARSE DATA TO JSON, DATA" + dataString);
        }
    }

    public ArrayList<City> getCites() {
        return cities;
    }

    public City getCityByName(String name) {
        for (int i = 0; i < cities.size(); i++) {
            City c = cities.get(i);
            if (c.getCityString().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void print() {
        System.out.println(cities.toString());
    }

}
