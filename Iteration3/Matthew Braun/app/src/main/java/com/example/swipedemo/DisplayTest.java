package com.example.swipedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DisplayTest extends AppCompatActivity {
    private static final String LOG_TAG = "ListRest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_test);

        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=name&key=AIzaSyDBk5Rvb1Pqk4K-Z4e1ZONfiCoBmKyxR58");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            StringBuilder jsonRes = new StringBuilder();

            while((read = in.read(buff)) != -1){
                jsonRes.append(buff, 0, read);
            }
            Toast.makeText(this, jsonRes.toString(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, "Error connecting to API", Toast.LENGTH_SHORT).show();
        }
    }
}
