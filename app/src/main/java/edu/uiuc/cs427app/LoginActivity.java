package edu.uiuc.cs427app;

import android.accounts.AccountManagerCallback;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import edu.uiuc.cs427app.databinding.ActivityMainBinding;

import android.widget.Button;

import android.widget.EditText;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText username;
    private EditText password;
    private Hashtable<String, String> accountDictionary;

    public class AllUserAccounts{
        AllUserAccounts(){
            Hashtable<String, String[]> accountDictionary;

            accountDictionary = new Hashtable<String, String[]>();
            String test = "test";
        }

        public Hashtable<String, String> getAccountDict(){
            return accountDictionary;
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        AllUserAccounts userAccounts = new AllUserAccounts();

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
        AllUserAccounts userAccounts = new AllUserAccounts();
        Hashtable<String, String> accountDict = userAccounts.getAccountDict();

//        String test = userAccounts.test();


        switch (view.getId()) {
            case R.id.buttonSignUp:

                // update the storage for username and password
                // TODO

                // lead to the main page

                if (!accountDict.containsKey(accountDict)){
                    accountDict.put(Username,Password);
                    intent = new Intent(this, MainActivity.class);
                    // send the details of username to personalize the main page
                    intent.putExtra("username", username_str);

                    startActivity(intent);
                }


                break;

             case R.id.buttonLogin:

                 // check the correctness of username and password
                 // TODO
                 if (accountDict.containsKey(accountDict)){
                     accountDict.put(Username,Password);
                     intent = new Intent(this, MainActivity.class);
                     // send the details of username to personalize the main page
                     intent.putExtra("username", username_str);

                     startActivity(intent);
                 }
                 // lead to the main page
//                 intent = new Intent(this, MainActivity.class);
//
//                 // send the details of username to personalize the main page
//                 intent.putExtra("username", username_str);
//
//                 startActivity(intent);

                 break;
        }
    }
}

