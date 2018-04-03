package com.dhanya.android.earthquakereports;

public class Earthquake {
    private String magnitude;
    private String location;
    private String date;


    /* Constructs a new (@link object)
     * @param magnitude of the earthquake
     * @param location of the earthquake
     * @param date of the earthquake happened
     */
    public Earthquake( String magnitude, String location, String date ) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
    }

    /** Returns the magnitude of the earthquake
     *
     * @return magnitude
     */
    public String getMagnitude() {
        return magnitude;
    }

    /** Returns the location of the earthquake
     */
    public String getLocation() {
        return location;
    }

    /** Returns the date of the earthquake happened
     *
     * @return date
     */

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
