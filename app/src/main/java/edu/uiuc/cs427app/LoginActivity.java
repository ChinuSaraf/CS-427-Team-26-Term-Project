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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText username;
    private EditText password;
    private Hashtable<String, String> accountDictionary;
    private SharedPreferences preferences;
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

