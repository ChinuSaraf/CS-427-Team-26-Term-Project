package edu.uiuc.cs427app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import edu.uiuc.cs427app.databinding.ActivityMainBinding;

import android.widget.Button;

import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText username;
    private EditText password;

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

                 // lead to the main page
                 intent = new Intent(this, MainActivity.class);
                
                 // send the details of username to personalize the main page
                 intent.putExtra("username", username_str);

                startActivity(intent);
                 break;
        }
    }
}

