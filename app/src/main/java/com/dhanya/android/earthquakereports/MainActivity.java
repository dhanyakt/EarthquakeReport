package com.dhanya.android.earthquakereports;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Calling extractEarthquakes from QueryUtils for earthquake objects
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        // Create an {@link EarthquakeAdapter}, whose data source is a list of
        // {@link Earthquake}s. The adapter knows how to create list item views for each item
        // in the list.
        // Create the adapter to convert the array to views
        final EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this,earthquakes);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(earthquakeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> adapterView, View view, int position, long l ) {
                Earthquake currentEarthquake = ( Earthquake ) adapterView.getItemAtPosition(position);
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

    }
}
