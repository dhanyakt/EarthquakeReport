package com.dhanya.android.earthquakereports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this,earthquakes);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(earthquakeAdapter);

    }
}
