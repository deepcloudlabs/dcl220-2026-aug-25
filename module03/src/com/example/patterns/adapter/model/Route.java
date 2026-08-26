package com.example.patterns.adapter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** An ordered sequence of stops with its total length. */
public record Route(List<GeoPoint> stops, double totalDistanceKm) {

    public Route {
        stops = List.copyOf(stops);
    }

    /** Orders the points with a simple nearest-neighbour heuristic, starting from the first one. */
    public static Route nearestNeighbour(List<GeoPoint> points) {
        if (points.isEmpty()) {
            return new Route(List.of(), 0.0);
        }
        List<GeoPoint> remaining = new ArrayList<>(points);
        List<GeoPoint> ordered = new ArrayList<>();
        GeoPoint current = remaining.remove(0);
        ordered.add(current);
        double total = 0.0;
        while (!remaining.isEmpty()) {
            GeoPoint nearest = remaining.get(0);
            double best = current.distanceKmTo(nearest);
            for (GeoPoint candidate : remaining) {
                double distance = current.distanceKmTo(candidate);
                if (distance < best) {
                    best = distance;
                    nearest = candidate;
                }
            }
            remaining.remove(nearest);
            ordered.add(nearest);
            total += best;
            current = nearest;
        }
        return new Route(ordered, total);
    }

    public String describe() {
        return stops.size() + " stops, " + String.format(Locale.ROOT, "%.1f", totalDistanceKm) + " km";
    }
}
