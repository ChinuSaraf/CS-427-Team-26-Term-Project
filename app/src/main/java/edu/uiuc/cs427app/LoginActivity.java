package edu.uiuc.cs427app;

import android.accounts.AccountManagerCallback;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.navigation.ui.AppBarConfiguration;

import edu.uiuc.cs427app.databinding.ActivityMainBinding;

import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.RelativeLayout;
import android.content.SharedPreferences.Editor;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText username;
    private EditText password;
    private Hashtable<String, String> accountDictionary;
    private  SharedPreferences preferences;
    private EditText etUserName;
    private EditText etPassWord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.username);
        etPassWord = findViewById(R.id.password);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        //Get the preference setting object: the first parameter is the table name, the second parameter is the permission
        preferences=this.getSharedPreferences("SignUP",MODE_PRIVATE);
        //Get the user name and password data stored in the preferences
        String usrName = preferences.getString("name", "");
        String usrPass = preferences.getString("pass", "");

        //Put the data we stored in the two input boxes
        etUserName.setText(usrName);
        etPassWord.setText(usrPass);

        //Set the click event of the signup button: save the entered data into the preferences
        buttonSignUp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Save the data to the preferences
                //1. Get an edited object of preferences
                Editor ed=preferences.edit();
                //2. Editor.putString; put string
                //2.1. Get the content of the input box
                String username = etUserName.getText().toString().trim();
                String password = etPassWord.getText().toString().trim();
                //2.2. Put our data
                ed.putString("name",username);
                ed.putString("pass",password);
                //3. Submit
                ed.commit();
                Toast.makeText(getApplicationContext(),"Save successfully",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent;

        // get the username and password on click
        // log for debugging
        String username_str = username.getText().toString();
        Log.e("LISTEN", "username:" + username_str);
        String password_str = password.getText().toString();
        Log.e("LISTEN", "password:" + password_str);
        String Username = username_str;
        String Password = password_str;

        switch (view.getId()) {
            case R.id.buttonSignUp:

                // update the storage for username and password
                // TODO

                // lead to the main page

                intent = new Intent(this, MainActivity.class);
                // send the details of username to personalize the main page
                intent.putExtra("username", username_str);

                startActivity(intent);


                break;

             case R.id.buttonLogin:

                 // check the correctness of username and password
                 // TODO
                 intent = new Intent(this, MainActivity.class);

                 // send the details of username to personalize the main page
                 intent.putExtra("username", username_str);

                 startActivity(intent);

                 break;
        }
    }


}

