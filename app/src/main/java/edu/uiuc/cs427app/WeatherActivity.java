package edu.uiuc.cs427app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        String cityName = getIntent().getStringExtra("cityName");
        Toast.makeText(getBaseContext(), cityName, Toast.LENGTH_LONG).show();
    }
}