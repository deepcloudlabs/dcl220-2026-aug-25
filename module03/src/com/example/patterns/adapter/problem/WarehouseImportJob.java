package com.example.patterns.adapter.problem;

import com.example.patterns.adapter.model.Address;
import com.example.patterns.adapter.vendor.NaviCorpClient;
import com.example.patterns.adapter.vendor.NaviCorpException;
import com.example.patterns.adapter.vendor.NaviLocation;

import java.util.List;

/**
 * PROBLEM: a third copy of the vendor translation. This one forgot to lower-case the country
 * code, which the vendor rejects - so every warehouse silently fails to import.
 */
public class WarehouseImportJob {

    private final NaviCorpClient navi =
            new NaviCorpClient(System.getenv().getOrDefault("NAVI_KEY", "demo-key"));

    /** Geocodes the warehouse addresses and returns how many could be placed on the map. */
    public int importWarehouses(List<Address> warehouses) {
        int imported = 0;
        for (Address warehouse : warehouses) {
            String query = warehouse.street() + ", "
                         + warehouse.postalCode() + " " + warehouse.city();
            try {
                // forgot .toLowerCase(): the vendor rejects "TR" with error E101
                NaviLocation loc = navi.lookup(query, warehouse.countryCode());
                if (loc != null && loc.getMatchQuality() >= 0.7) {
                    imported++;
                }
            } catch (NaviCorpException e) {
                // "logged" and ignored - the failure never surfaces
            }
        }
        return imported;
    }
}
