package edu.uiuc.cs427app;

import android.accounts.AccountManagerCallback;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.navigation.ui.AppBarConfiguration;

import edu.uiuc.cs427app.databinding.ActivityMainBinding;

import android.widget.Button;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.accounts.AccountManager;
import 	android.accounts.Account;
import android.widget.EditText;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
   // private ActivityMainBinding binding;
    private EditText username;
    private EditText password;
    private Hashtable<String, String> accountDictionary;
    private SharedPreferences preferences;
    private EditText etUserName;
    private EditText etPassWord;

    private RadioGroup radioGroup;
    private TextView themeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.username);
        etPassWord = findViewById(R.id.password);

        // initializing all our variables.
        radioGroup = findViewById(R.id.idRGroup);
        themeTV = findViewById(R.id.idtvTheme);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);


        // on below line we are setting on check change method for our radio group.
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // on radio button check change
                switch (checkedId) {
                    case R.id.idRBLight:
                        // on below line we are checking the radio button with id.
                        // on below line we are setting the text to text view as light mode.
                        themeTV.setText("Light Theme");
                        // on below line we are changing the theme to light mode.
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case R.id.idRBDark:
                        // this method is called when dark radio button is selected
                        // on below line we are setting dark theme text to our text view.
                        themeTV.setText("Dark Theme");
                        // on below line we are changing the theme to dark mode.
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        Intent intent;


        switch (view.getId()) {
            case R.id.buttonSignUp:

                // update the storage for username and password
                // lead to the main page
                //1. Get an edited object of preferences
                SharedPreferences sharedPreferences = getSharedPreferences("SignUP",MODE_PRIVATE);
                Editor ed = sharedPreferences.edit();
                //2. Editor.putString; put string
                //2.1. Get the content of the input box
                String username = etUserName.getText().toString().trim();
                String password = etPassWord.getText().toString().trim();
                //2.2. Put our data
                ed.putString(username,password);
//                ed.putString("name",username);
//                ed.putString("pass",password);
                //3. Submit
                ed.commit();
                Toast.makeText(getApplicationContext(),"Save successfully",Toast.LENGTH_SHORT).show();
//                String test = sharedPreferences.getString(username,null);


//                Toast.makeText(getApplicationContext(),"Password is :"+test,Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivity.class);
                // send the details of username to personalize the main page
                intent.putExtra("username", username);

                startActivity(intent);


                break;

             case R.id.buttonLogin:

                 // check the correctness of username and password

//                 intent = new Intent(this, MainActivity.class);

                 // send the details of username to personalize the main page


                 String typedUserName = etUserName.getText().toString().trim();
                 String typedPassword = etPassWord.getText().toString().trim();
//                 intent.putExtra("username", typedUserName);

                 SharedPreferences retrieving = getSharedPreferences("SignUP",MODE_PRIVATE);
                 String retrievedPassword = retrieving.getString((typedUserName),null);

//                 Toast.makeText(getApplicationContext(),typedUserName,Toast.LENGTH_SHORT).show();
//                 Toast.makeText(getApplicationContext(),"Password is:"+retrievedPassword,Toast.LENGTH_SHORT).show();

                 if (retrieving.contains(typedUserName)){
                     String storedPassword = retrieving.getString(typedUserName,null);
                     if(storedPassword.equals(typedPassword)){
                         intent = new Intent(this, MainActivity.class);
                         intent.putExtra("username", typedUserName);
                         startActivity(intent);
                     }else{
                         Toast.makeText(getApplicationContext(),"Password incorrect",Toast.LENGTH_SHORT).show();
                     }

                 }else{
                     Toast.makeText(getApplicationContext(),"Sign up first",Toast.LENGTH_SHORT).show();
                 }
                 break;
        }
    }


}

