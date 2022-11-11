package edu.uiuc.cs427app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.RequiresApi;
import android.os.Build;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherActivity extends AppCompatActivity {

    // create variables from API
    TextView tv_get_cityName;
    TextView tv_date_and_time, tv_temp, tv_weather_type, tv_humidity, tv_wind_condition;
    String cityName;

    // onCreate, match to xml file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        String cityName = getIntent().getStringExtra("cityName");
        String username = getIntent().getStringExtra("username");
        this.setTitle("Team #26 - "+username);
        Toast.makeText(getBaseContext(), cityName, Toast.LENGTH_LONG).show();
        tv_get_cityName = findViewById(R.id.tv_get_cityName);
        tv_date_and_time = findViewById(R.id.tv_date_and_time);
        tv_temp = findViewById(R.id.tv_temp);
        tv_weather_type = findViewById(R.id.tv_weather_type);
        tv_humidity = findViewById(R.id.tv_humidity);
        tv_wind_condition = findViewById(R.id.tv_wind_condition);

        cityName = getIntent().getStringExtra("cityName");

        // set the cityName in the frontend
        tv_get_cityName.setText(cityName);
        if (tv_get_cityName != null){
            // call the fetchWeather
            fetchWeather();
        }
    }

    public void fetchWeather() {

        // API url
        String apiKey = "ff23032afc5f79d1097236636db71215";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=" + apiKey + "&units=imperial";

        // define the API call with key and implement the request to UI format
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject main_ob = response.getJSONObject("main");
                    JSONArray jsonArray = response.getJSONArray("weather");
                    JSONObject object = jsonArray.getJSONObject(0);
                    String temp = main_ob.getString("temp");
                    String weatherType = object.getString("main");
                    String humidity = main_ob.getString("humidity");

                    JSONObject aboutWind = response.getJSONObject("wind");
                    String windSpeed = aboutWind.getString("speed");


                    if(weatherType.equals("Thunderstorm")) {
                        tv_weather_type.setText("Weather: " + "Thunderstorm");
                    }
                    else if(weatherType.equals("Drizzle") || weatherType.equals("Rain")) {
                        tv_weather_type.setText("Weather: " + "Raining");
                    }
                    else if(weatherType.equals("Snow")) {
                        tv_weather_type.setText("Weather: " + "Snow");
                    }
                    else if(weatherType.equals("Mist") || weatherType.equals("Smoke") || weatherType.equals("Haze") || weatherType.equals("Dust")
                            || weatherType.equals("Fog") || weatherType.equals("Sand") || weatherType.equals("Ash") || weatherType.equals("Squall")
                            || weatherType.equals("Tornado")) {
                        tv_weather_type.setText("Weather: " + "Mist");
                    }
                    else if(weatherType.equals("Clear")) {
                        tv_weather_type.setText("Weather: " + "Clear");
                    }
                    else if(weatherType.equals("Clouds")) {
                        tv_weather_type.setText("Weather: " + "Cloudy");
                    }

                    // get the local time
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm a");
                    String formattedDate = myDateObj.format(myFormatObj);
                    tv_date_and_time.setText(formattedDate);

                    // get the temp value
                    double temInt = Double.parseDouble(temp);
                    int i = (int) Math.round(temInt);
                    tv_temp.setText("Temperature: " + String.valueOf(i)+"°F");

                    // get the windspeed
                    tv_wind_condition.setText("Wind Condition: " + windSpeed+" mph");

                    // get the humidity
                    tv_humidity.setText("Humidity: " + humidity+"%");



                }catch (JSONException e){
                    e.getStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            // handle the error
            public void onErrorResponse(VolleyError error) {
//                tv.setText("error ");
                System.out.println(error);
            }
        });

        // send the request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
//
    }

}