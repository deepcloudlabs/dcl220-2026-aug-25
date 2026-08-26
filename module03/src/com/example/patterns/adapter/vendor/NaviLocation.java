package com.example.patterns.adapter.vendor;

/**
 * A location as returned by the (simulated) NaviCorp SDK. Coordinates are strings and the
 * match quality is a score between 0.0 and 1.0 - exactly the kind of interface an
 * application does not want to depend on directly.
 */
public final class NaviLocation {

    private final String lat;
    private final String lon;
    private final double matchQuality;

    public NaviLocation(String lat, String lon, double matchQuality) {
        this.lat = lat;
        this.lon = lon;
        this.matchQuality = matchQuality;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public double getMatchQuality() {
        return matchQuality;
    }

    @Override
    public String toString() {
        return "NaviLocation{lat='" + lat + "', lon='" + lon + "', matchQuality=" + matchQuality + '}';
    }
}
