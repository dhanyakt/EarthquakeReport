package com.dhanya.android.earthquakereports;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String LOG_TAG = MainActivity.class.getName();
    /** TextView that is displayed when the list is empty */
    private TextView emptyStateTextView;
    private ListView listView;
    private ProgressBar circularProgressBar;
    /**
     * Adapter for the list of earthquakes
     */
    private EarthquakeAdapter earthquakeAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        Log.i(LOG_TAG,"onCreate() is called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = ( ListView ) findViewById(R.id.list);
        circularProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        /** TextView that is displayed when the list is empty */
        emptyStateTextView = (TextView) findViewById(R.id.empty_view);


        // Checking whether there is a active internet connection
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        /*
         If there is internet fetch the earthquake data by calling loader else set empty
         textView to No Internet Connection
          */

        if(activeNetwork!= null && activeNetwork.isConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            Log.i(LOG_TAG, "Loader callback initLoader()....");
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, MainActivity.this);

        } else {
            circularProgressBar.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.no_internet_connection);

        }

        // Create a new adapter that takes an empty list of earthquakes as input
        earthquakeAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        listView.setEmptyView(emptyStateTextView);
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

    @Override
    public Loader<List<Earthquake>> onCreateLoader( int i, Bundle bundle ) {
        Log.i(LOG_TAG, "Calling the method onCreateLoader()......");
        return new EarthquakeLoader(MainActivity.this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished( Loader<List<Earthquake>> loader, List<Earthquake> earthquakes ) {
        Log.i(LOG_TAG,"Results from the method onLoadFinished().......");

        circularProgressBar.setVisibility(View.GONE);
        emptyStateTextView.setText(R.string.empty_text_view);

        // Clear the adapter of previous earthquake data
        earthquakeAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if ( earthquakes != null && !earthquakes.isEmpty() ) {
           earthquakeAdapter.addAll(earthquakes);
        }


    }

    @Override
    public void onLoaderReset( Loader<List<Earthquake>> loader ) {
        Log.i(LOG_TAG,"Resetting the loader by onLoaderReset().......");
        earthquakeAdapter.clear();
    }

}
