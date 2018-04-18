package com.dhanya.android.earthquakereports;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String url;



    public EarthquakeLoader( Context context, String  url ) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        onForceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if ( url == null ) {
            return null;
        }

        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(url);
        return earthquakes;
    }
}
