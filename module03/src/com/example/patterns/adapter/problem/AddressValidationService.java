package com.example.patterns.adapter.problem;

import com.example.patterns.adapter.model.Address;
import com.example.patterns.adapter.vendor.NaviCorpClient;
import com.example.patterns.adapter.vendor.NaviCorpException;
import com.example.patterns.adapter.vendor.NaviLocation;

/**
 * PROBLEM: a second copy of the vendor translation. It has already drifted from the one in
 * {@link RoutePlanner}: the quality threshold is 0.6 instead of 0.7, and the null check was
 * forgotten, so an unknown address produces a NullPointerException.
 */
public class AddressValidationService {

    private final NaviCorpClient navi =
            new NaviCorpClient(System.getenv().getOrDefault("NAVI_KEY", "demo-key"));

    /** Returns true when the address can be delivered to. */
    public boolean isDeliverable(Address address) {
        String query = address.street() + ", "
                     + address.postalCode() + " " + address.city();
        try {
            NaviLocation loc = navi.lookup(query, address.countryCode().toLowerCase());
            return loc.getMatchQuality() >= 0.6;   // no null check: NPE for unknown addresses
        } catch (NaviCorpException e) {
            return false;
        }
    }
}
