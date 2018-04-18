package com.dhanya.android.earthquakereports;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.util.Log;

import java.util.List;


/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */


public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String url;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */

    public EarthquakeLoader( Context context, String  url ) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"Starting the onStartLoading().........");
        onForceLoad();
    }

    /**
     * This is on a background thread.
     */

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG,"Running backgroung thread in loadInBackground()........");
        // Don't perform the request if there are no URLs, or the first URL is null.
        if ( url == null ) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.

        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(url);
        return earthquakes;
    }
}
