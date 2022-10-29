package edu.uiuc.cs427app;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.MotionEvent;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> citiesFromDB = new ArrayList<String>();
    private CityAdapter cityAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("username");
        String theme = getIntent().getStringExtra("theme");
        if(theme.equals("1"))
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        this.setTitle("Team #26 - "+username);
        showCities();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    //function to add a city name -- gets called when add city button is called
    public void onClickAddDetails(View view) {
        EditText city_input = findViewById(R.id.textName);
        String name = city_input.getText().toString();
        String userName = getIntent().getStringExtra("username");
        if(name.length() == 0){
            Toast.makeText(getBaseContext(), "Please enter a city name!", Toast.LENGTH_LONG).show();
            return;
        }
        if(nonNull(citiesFromDB) && citiesFromDB.contains(name)){
            Toast.makeText(getBaseContext(), name+" already added!", Toast.LENGTH_LONG).show();
        }
        else {
            ContentValues values = new ContentValues();
            // fetching text from user
            values.put(CityContentProvider.cityName, name);
            values.put(CityContentProvider.userName, userName);
            // inserting into database through content URI
            getContentResolver().insert(CityContentProvider.CONTENT_URI, values);
            // displaying a toast message
            Toast.makeText(getBaseContext(), name+" inserted!", Toast.LENGTH_LONG).show();
            city_input.setText(null);
        }
        //calling the showcities list function to show the list every time the user adds a city so that it looks dynamic
        showCities();
    }

    public void onClickLogout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //this function shows the list of cities that the user has added till now.
    public void showCities() {
        String userName = getIntent().getStringExtra("username");
        if(isNull(userName)){
            return;
        }
        listView = (ListView) findViewById(R.id.listCity);
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.city.provider/cities"),  null, CityContentProvider.userName+"=?", new String[]{userName}, null);
        citiesFromDB = new ArrayList<String>();
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                citiesFromDB.add(cursor.getString(Math.max(cursor.getColumnIndex(CityContentProvider.cityName), 0)));
                cursor.moveToNext();
            }
        }
        cityAdapter = new CityAdapter(MainActivity.this, citiesFromDB);
        listView.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();
    }
}

