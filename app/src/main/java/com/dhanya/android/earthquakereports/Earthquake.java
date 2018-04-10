package com.dhanya.android.earthquakereports;

public class Earthquake {
    private double magnitude;
    private String location;
    private long timeInMilliSeconds;
    private String url;


    /* Constructs a new (@link object)
     * @param magnitude of the earthquake
     * @param location of the earthquake
     * @param date of the earthquake happened
     */
    public Earthquake( double magnitude, String location, long timeInMilliSeconds,String url ) {
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliSeconds = timeInMilliSeconds;
        this.url = url;
    }

    /**
     * Returns the magnitude of the earthquake
     *
     * @return magnitude
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * Returns the location of the earthquake
     */
    public String getLocation() {
        return location;
    }

    public long getTimeInMilliSeconds() {
        return timeInMilliSeconds;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
