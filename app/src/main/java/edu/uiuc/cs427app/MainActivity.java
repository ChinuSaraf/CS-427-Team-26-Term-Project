package edu.uiuc.cs427app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void onClickAddDetails(View view) {

        String name = ((EditText) findViewById(R.id.textName)).getText().toString();
        int flag=0;
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.city.provider/cities"), null, null, null, null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String currentName = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));
                System.out.println((currentName));
                System.out.println(name);
                if(currentName.equals(name))
                {
                    flag=1;
                    break;
                }
                cursor.moveToNext();
            }
        }
        // class to add values in the database
        if(flag==0)
        {
            ContentValues values = new ContentValues();

            // fetching text from user
            values.put(CityContentProvider.name, name);

            // inserting into database through content URI
            getContentResolver().insert(CityContentProvider.CONTENT_URI, values);

            // displaying a toast message
            Toast.makeText(getBaseContext(), name+" Inserted", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(), "City already added", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDeleteDetails(View view)
    {
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.city.provider/cities"), null, null, null, null);
        String name=((EditText) findViewById(R.id.textName)).getText().toString();
        int count = getContentResolver().delete(Uri.parse("content://com.demo.city.provider/cities"), "name=?", new String[]{name});
        if(count == 0)
        {
            Toast.makeText(getBaseContext(), "City not found!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(), name+" deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView resultView= (TextView) findViewById(R.id.res);

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.city.provider/cities"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(Math.max(cursor.getColumnIndex("id"), 0))+ "-"+ cursor.getString(Math.max(cursor.getColumnIndex("name"), 0)));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }
    }
}

