package com.dhanya.android.earthquakereports;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
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


        // Format the magnitude to show 1 decimal place
        String magOutput = formatMagnitude(currentEarthquake.getMagnitude());



        // Populate the data into the template view using the data object
        magnitude_textView.setText(magOutput);
        //Set the proper background color on the magnitude circle.
        //Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude_textView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the background color for magnitude
        magnitudeCircle.setColor(magnitudeColor);

        // location_textView.setText(currentEarthquake.getLocation());
        String[] locationDetails = getLocationDetails(currentEarthquake.getLocation());
        location_textView.setText(locationDetails[ 0 ]);
        location_detail_textView.setText(locationDetails[ 1 ]);
        date_textView.setText(dateFormat);
        time_textView.setText(timeFormat);



        // Return the completed view to render on screen
        return convertView;
    }

    private int getMagnitudeColor( double magnitude ) {

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */

    private String formatMagnitude( double magnitude ) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedOutput = decimalFormat.format(magnitude);
        return formattedOutput;

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


