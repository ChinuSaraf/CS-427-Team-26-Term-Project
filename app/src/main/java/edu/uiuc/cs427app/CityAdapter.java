package edu.uiuc.cs427app;

import static java.util.Objects.nonNull;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> cities;

    public CityAdapter(Context context, ArrayList cities) {
        super();
        this.context = context;
        this.cities = cities;
    }

    public int getCount() {
        // return the number of records
        return nonNull(cities) ? cities.size() : 0;
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.individual_city, parent, false);


        // get the reference of textView and button
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.cityName);
        Button btnAction = (Button) view.findViewById(R.id.deleteButton);

        // Set the title and button name
        String cityName = cities.get(position);
        txtSchoolTitle.setText(cityName);
        btnAction.setText("Delete City");

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = context.getContentResolver().delete(Uri.parse("content://com.demo.city.provider/cities"), CityContentProvider.cityName+"=? and " + CityContentProvider.userName+"=?", new String[]{cityName, "user1"});
                if(count != 0) {
                    if(context instanceof MainActivity) {
                        ((MainActivity)context).showCities();
                        Toast.makeText(context, cityName + " deleted!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}
