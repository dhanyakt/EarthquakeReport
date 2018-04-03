package com.dhanya.android.earthquakereports;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context  The current context. Used to inflate the layout file.
     * @param earthquakes A List of Earthquake objects to display in a list
     */


    public EarthquakeAdapter( Activity context, ArrayList<Earthquake> earthquakes ) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView( int position,  View convertView,  ViewGroup parent ) {

        // Get the data item for this position
        Earthquake currentEarthquake = ( Earthquake ) getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if ( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

            // Lookup view for data population
            TextView magnitude_textView = ( TextView ) convertView.findViewById(R.id.magnitude_txt);
            TextView location_textView = ( TextView ) convertView.findViewById(R.id.location_txt);
            TextView date_textView = ( TextView ) convertView.findViewById(R.id.date_txt);

            // Populate the data into the template view using the data object
            magnitude_textView.setText(currentEarthquake.getMagnitude());
            location_textView.setText(currentEarthquake.getLocation());
            date_textView.setText(currentEarthquake.getDate());

            // Return the completed view to render on screen
            return convertView;
        }


    }


