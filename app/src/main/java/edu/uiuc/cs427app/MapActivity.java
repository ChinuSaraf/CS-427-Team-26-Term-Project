package edu.uiuc.cs427app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        String cityName = getIntent().getStringExtra("cityName");
        Toast.makeText(getBaseContext(), cityName, Toast.LENGTH_LONG).show();
    }
}