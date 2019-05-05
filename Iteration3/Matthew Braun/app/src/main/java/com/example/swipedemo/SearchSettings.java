package com.example.swipedemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SearchSettings extends AppCompatActivity {
    private int minD = 5;
    private int maxD = 100;
    private String minDistance = String.format(Locale.US, "%d Miles", minD);
    private TextView milesText;
    private SeekBar distanceBar;

    private CheckBox [] checkBoxArr;
    private String errorMessage = "Please select at least one type of food.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_settings);

        milesText = (TextView) findViewById(R.id.miles_description);
        distanceBar = (SeekBar) findViewById(R.id.distance_bar);
        Button matchButton = (Button) findViewById(R.id.match_me_button);

        milesText.setText(minDistance);
        distanceBar.setMax(maxD);
        distanceBar.setProgress(0);

        checkBoxArr = new CheckBox[]{(CheckBox) findViewById(R.id.food_choice_one),
                                    (CheckBox) findViewById(R.id.food_choice_two),
                                    (CheckBox) findViewById(R.id.food_choice_three),
                                    (CheckBox) findViewById(R.id.food_choice_four),
                                    (CheckBox) findViewById(R.id.food_choice_five),
                                    (CheckBox) findViewById(R.id.food_choice_six),
                                    (CheckBox) findViewById(R.id.food_choice_seven),
                                    (CheckBox) findViewById(R.id.food_choice_eight),
                                    (CheckBox) findViewById(R.id.food_choice_nine),
                                    (CheckBox) findViewById(R.id.food_choice_ten),
                                    (CheckBox) findViewById(R.id.food_choice_eleven),
                                    (CheckBox) findViewById(R.id.food_choice_twelve) };

        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minDistance = String.format(Locale.US, "%d Miles", progress + 5);
                milesText.setText(minDistance);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        matchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptContinue();
            }
        });
    }

    private void attemptContinue() {
        boolean checked = false;
        for(int i = 0; i < checkBoxArr.length; i++) {
            if(checkBoxArr[i].isChecked()) {
                checked = true;
                break;
            }
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, DisplayRestaurants.class);
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Double longitude = location.getLongitude();
            Double latitude = location.getLatitude();
            String longit = Double.toString(longitude);
            String lat = Double.toString(latitude);
            intent.putExtra("long", longit);
            intent.putExtra("lat", lat);
            startActivity(intent);
        }


        /*
        if(checked){
            Uri gmmIntentUri = Uri.parse("google.navigation:q=a+16458+Bolsa+Chica+Street,+Huntington+Beach,+California+92649");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        */



        /*          OPENS MAPS ~ USE THIS FOR DEMO
        if(checked){
            Uri gmmIntentUri = Uri.parse("google.navigation:q=a+800+N+State+College+Blvd,+Fullerton,+California&mode=d");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        */



        /*
        if(checked) {
            // Move to next Activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
        */
    }
}
