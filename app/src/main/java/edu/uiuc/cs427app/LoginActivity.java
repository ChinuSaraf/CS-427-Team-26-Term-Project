package edu.uiuc.cs427app;

import static java.util.Objects.isNull;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    String theme="";

    // function to create views and bind data to lists
    // Bundle is used to save & recover state information for the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // to define the layout for the activity's user interface
        // initializing all our variables.
        RadioGroup radioGroup = findViewById(R.id.idRGroup);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        // on below line we are setting on check change method for our radio group.
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.idRBLight:
                        theme="1";
                        break;
                    case R.id.idRBDark:
                        theme="2";
                        break;
                }
            }
        });
    }

    // function to be invoked in the activity when the button is clicked
    @Override
    public void onClick(View view) {
        Intent intent;
        EditText etUserName = findViewById(R.id.username);
        EditText etPassWord = findViewById(R.id.password);
        String typedUserName = etUserName.getText().toString().trim();
        String typedPassword = etPassWord.getText().toString().trim();
        if(isNull(typedPassword.trim()) || typedPassword.trim().equals("") || isNull(typedUserName.trim()) || typedUserName.trim().equals("")){
            Toast.makeText(getApplicationContext(),"Username, Password fields shouldn't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        switch (view.getId()) {
            case R.id.buttonSignUp:
                //1. Get an edited object of preferences
                SharedPreferences sharedPreferences = getSharedPreferences("SignUP",MODE_PRIVATE);
                Editor ed = sharedPreferences.edit();
                ed.putString(typedUserName,typedPassword);
                if(theme.equals(""))
                {
                    theme="1";
                }
                ed.putString(typedUserName+"_theme",theme);
                ed.commit();
                Toast.makeText(getApplicationContext(),"Save successfully",Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivity.class);
                // send the details of username to personalize the main page
                intent.putExtra("username", typedUserName);
                intent.putExtra("theme", theme);
                startActivity(intent);
                break;

            case R.id.buttonLogin:
                // retrieve the details of the logged in user
                SharedPreferences retrieving = getSharedPreferences("SignUP",MODE_PRIVATE);
                SharedPreferences sharedPreferencesLogin = getSharedPreferences("SignUP",MODE_PRIVATE);
                Editor edLogin = sharedPreferencesLogin.edit();
                // retrieve the theme preference of the logged in user,
                // and if the theme is not set, set it to the selected theme
                if(theme.equals(""))
                {
                    theme=retrieving.getString(typedUserName+"_theme", null);
                }
                edLogin.putString(typedUserName+"_theme",theme);
                edLogin.commit();
                // get the password for the logged in user and check
                // if the typed password is the same as the stored password
                if (retrieving.contains(typedUserName)){
                    String storedPassword = retrieving.getString(typedUserName,null);
                    if(storedPassword.equals(typedPassword)){
                        String storedTheme = retrieving.getString(typedUserName+"_theme",null);
                        intent = new Intent(this, MainActivity.class);
                        intent.putExtra("username", typedUserName);
                        intent.putExtra("theme", storedTheme);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Password incorrect",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Sign up first", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
