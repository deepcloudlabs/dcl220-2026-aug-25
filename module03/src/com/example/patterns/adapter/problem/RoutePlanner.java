package com.example.patterns.adapter.problem;

import com.example.patterns.adapter.model.Address;
import com.example.patterns.adapter.model.GeoPoint;
import com.example.patterns.adapter.model.Route;
import com.example.patterns.adapter.model.UnroutableAddressException;
import com.example.patterns.adapter.vendor.NaviCorpClient;
import com.example.patterns.adapter.vendor.NaviCorpException;
import com.example.patterns.adapter.vendor.NaviLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: the route planner was rewritten around the vendor SDK. String formatting, number
 * parsing, quality thresholds and vendor error handling are all mixed into the business logic,
 * and the same block is copied - with variations - into other clients.
 */
public class RoutePlanner {

    private final NaviCorpClient navi =
            new NaviCorpClient(System.getenv().getOrDefault("NAVI_KEY", "demo-key"));

    public Route plan(List<Address> stops) {
        List<GeoPoint> points = new ArrayList<>();
        for (Address stop : stops) {
            String query = stop.street() + ", "
                         + stop.postalCode() + " " + stop.city();
            String country = stop.countryCode().toLowerCase();
            try {
                NaviLocation loc = navi.lookup(query, country);
                if (loc == null || loc.getMatchQuality() < 0.7) {
                    throw new UnroutableAddressException(stop);
                }
                points.add(new GeoPoint(Double.parseDouble(loc.getLat()),
                                        Double.parseDouble(loc.getLon())));
            } catch (NaviCorpException e) {
                throw new UnroutableAddressException(stop, e);
            }
        }
        return optimise(points);
    }

    private Route optimise(List<GeoPoint> points) {
        return Route.nearestNeighbour(points);
    }
}
