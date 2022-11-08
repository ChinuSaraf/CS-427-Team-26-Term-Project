package edu.uiuc.cs427app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WebView webView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        String cityName = getIntent().getStringExtra("cityName");
        String username = getIntent().getStringExtra("username");
        this.setTitle("Team #26 - "+username);
        // Fetch Lat and Long
        TextView city = findViewById(R.id.city_name);
        TextView lat = findViewById(R.id.latitude);
        TextView lng = findViewById(R.id.longitude);
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + cityName + "&key=AIzaSyD6X8BsJNNnvHPjiLsdOGsDfIdZ6FdqzXg";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = new JSONObject(String.valueOf(response.getJSONArray("results").get(0)));
                    JSONObject location = new JSONObject(String.valueOf(new JSONObject(String.valueOf(result.get("geometry"))).get("location")));
                    Double latitude = (Double) location.get("lat");
                    Double longitude = (Double) location.get("lng");
                    String formattedCityName = String.valueOf(result.get("formatted_address"));
                    lat.setText(" Latitude: " + latitude);
                    lng.setText(" Longitude: " + longitude);
                    city.setText("City Name: "+ formattedCityName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lat.setText("Unable to Fetcg Data ");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

        // Maps
        webView = (WebView) findViewById(R.id.city_map);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com/maps/place/"+cityName);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
}