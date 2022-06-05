/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openweather;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.HashMap;
import java.util.Scanner;
import javax.sound.sampled.AudioFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Admin
 */
public class WeatherData {

    private String apiKey = "30717fdea84952bcd3b7c58978a8dfe9";
    JSONObject weatherData;
    private HttpURLConnection connection;

    public WeatherData() {
        this.weatherData = new JSONObject();
    }

    private String endpoint = "https://api.openweathermap.org/data/2.5/weather?units=metric";

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherInfo getWeather(String lat, String lon) throws RuntimeException{
        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?units=metric&lat=" + lat + "&lon=" + lon + "&appid=" + this.apiKey + "&lang=vi";
            System.out.println(urlString);
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            //Request setup;

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            if (status != 200) {
                throw new RuntimeException("HTTP response code" + status);
            } else {
                StringBuilder result = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "UTF-8");

                while (scanner.hasNext()) {
                    result.append(scanner.nextLine());
                }
                //close scanner
                scanner.close();
                String dataString = String.valueOf(result);
                WeatherInfo w =  parseJSONDataToWeather(dataString);
                System.out.println(w.toString());
                return w;
            }
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("FAIL TO GET DATA: " + e.getMessage());
        }
    }

    public WeatherInfo parseJSONDataToWeather(String dataString) {
        try {
        JSONParser jsonParse = new JSONParser();
        JSONObject data = (JSONObject) jsonParse.parse(dataString);
        JSONArray weatherArr = (JSONArray) data.get("weather");
        JSONObject wind = (JSONObject) data.get("wind");
        JSONObject sys = (JSONObject) data.get("sys");
        JSONObject weather = (JSONObject) weatherArr.get(0);
        JSONObject main = (JSONObject) data.get("main");
        String status = (String) weather.get("main");
        String statusDescription = (String) weather.get("description");
        String statusIconId = (String) weather.get("icon");

        String temp = (String) main.get("temp").toString();
        String feelLike = (String) main.get("feels_like").toString();

        String humidity = (String) main.get("humidity").toString();
        String tempMax = (String) main.get("temp_max").toString();
        String tempMin = (String) main.get("temp_min").toString();
        String timezone = (String) data.get("timezone").toString();
        String windSpeed = (String) wind.get("speed").toString();
        long sunrise = (long) sys.get("sunrise");
        long sunset = (long) sys.get("sunset");
        return new WeatherInfo(status, statusDescription, 
                statusIconId, temp, feelLike, humidity, tempMax,
                tempMin, timezone, windSpeed, sunrise, sunset);
        } catch (ParseException e) {
            System.out.println("ERROR" + e);
            throw new RuntimeException("FAIL TO PARSE DATA TO JSON, DATA" + dataString);
        }
       
    }
}


    /*
   
{
  "coord": {
    "lon": -122.08,
    "lat": 37.39
  },
  "weather": [
    {
      "id": 800,
      "main": "Clear",
      "description": "clear sky",
      "icon": "01d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 282.55,
    "feels_like": 281.86,
    "temp_min": 280.37,
    "temp_max": 284.26,
    "pressure": 1023,
    "humidity": 100
  },
  "visibility": 10000,
  "wind": {
    "speed": 1.5,
    "deg": 350
  },
  "clouds": {
    "all": 1
  },
  "dt": 1560350645,
  "sys": {
    "type": 1,
    "id": 5122,
    "message": 0.0139,
    "country": "US",
    "sunrise": 1560343627,
    "sunset": 1560396563
  },
  "timezone": -25200,
  "id": 420006353,
  "name": "Mountain View",
  "cod": 200
  }                         

*/