package com.dhanya.android.earthquakereports;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context     The current context. Used to inflate the layout file.
     * @param earthquakes A List of Earthquake objects to display in a list
     */


    public EarthquakeAdapter( Activity context, ArrayList<Earthquake> earthquakes ) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        // Get the data item for this position
        Earthquake currentEarthquake = ( Earthquake ) getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if ( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        // Lookup view for data population
        TextView magnitude_textView = ( TextView ) convertView.findViewById(R.id.magnitude_txt);
        TextView location_textView = ( TextView ) convertView.findViewById(R.id.location_txt);
        TextView location_detail_textView = ( TextView ) convertView.findViewById(R.id.location_detail_txt);
        TextView date_textView = ( TextView ) convertView.findViewById(R.id.date_txt);
        TextView time_textView = ( TextView ) convertView.findViewById(R.id.time_txt);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliSeconds());

        // Format the date string (i.e. "Mar 3, 1984")
        String dateFormat = formatDate(dateObject);
        // Format the time string (i.e. "4:30PM")
        String timeFormat = formatTime(dateObject);

        // Populate the data into the template view using the data object
        magnitude_textView.setText(currentEarthquake.getMagnitude());
        // location_textView.setText(currentEarthquake.getLocation());
        String[] locationDetails = getLocationDetails(currentEarthquake.getLocation());
        location_textView.setText(locationDetails[ 0 ]);
        location_detail_textView.setText(locationDetails[ 1 ]);
        date_textView.setText(dateFormat);
        time_textView.setText(timeFormat);

        // Return the completed view to render on screen
        return convertView;
    }

    private String[] getLocationDetails( String location ) {
        String[] locationDetails = new String[ 2 ];
        int firstDetail = location.toLowerCase().indexOf("of");
        if ( firstDetail > 0 ) {
            // first part of location detail + 2 to include "of" in first string
            locationDetails[ 0 ] = location.substring(0, firstDetail + 2);
            // second part of location detail
            locationDetails[ 1 ] = location.substring(firstDetail + 2, location.length());
        }
        else {
            // first part of location detail - default value to be displayed
            locationDetails[ 0 ] = getContext().getString(R.string.near_the);
            // second part of location detail
            locationDetails[ 1 ] = location;
        }


        return locationDetails;
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */

    private String formatTime( Date dateObject ) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate( Date dateObject ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormat.format(dateObject);
    }


}


