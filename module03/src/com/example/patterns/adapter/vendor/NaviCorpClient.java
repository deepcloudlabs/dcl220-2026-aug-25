package com.example.patterns.adapter.vendor;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Map;

/**
 * Simulated third-party geocoding SDK.
 *
 * <p>A real SDK would perform HTTP calls; this one answers from a small built-in table so
 * that the project runs offline. Its interface is deliberately awkward - a free-text query,
 * coordinates as strings, a match-quality score, a checked exception and a lower-case
 * country code - because that is what the Adapter pattern has to deal with.
 *
 * <p>The class is not final so that tests can subclass it in place of a mocking framework.
 */
public class NaviCorpClient {

    private static final Map<String, NaviLocation> KNOWN_PLACES = Map.of(
            "tr|istiklal caddesi 10, 34433 istanbul", new NaviLocation("41.0350", "28.9784", 0.96),
            "tr|bagdat caddesi 250, 34728 istanbul", new NaviLocation("40.9633", "29.0870", 0.93),
            "tr|ataturk bulvari 5, 06680 ankara", new NaviLocation("39.9208", "32.8541", 0.91),
            "de|unter den linden 1, 10117 berlin", new NaviLocation("52.5170", "13.3889", 0.97),
            "nl|dam 1, 1012 amsterdam", new NaviLocation("52.3731", "4.8926", 0.95),
            // an ambiguous address: the vendor returns something, but with low confidence
            "tr|old harbour road 7, 34000 istanbul", new NaviLocation("41.0100", "28.9500", 0.45));

    private final String apiKey;

    public NaviCorpClient(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("NaviCorp API key is required");
        }
        this.apiKey = apiKey;
    }

    /**
     * Returns the best match for a free-text query, or {@code null} when nothing was found.
     *
     * @param freeText            e.g. {@code "Istiklal Caddesi 10, 34433 Istanbul"}
     * @param isoCountryLowerCase two-letter country code, lower case (the SDK rejects anything else)
     * @throws NaviCorpException when the request is invalid or the service fails
     */
    public NaviLocation lookup(String freeText, String isoCountryLowerCase) throws NaviCorpException {
        if (freeText == null || freeText.isBlank()) {
            throw new NaviCorpException("E100: empty query");
        }
        if (isoCountryLowerCase == null || !isoCountryLowerCase.matches("[a-z]{2}")) {
            throw new NaviCorpException("E101: country must be a lower-case ISO 3166-1 alpha-2 code, got '"
                    + isoCountryLowerCase + "'");
        }
        return KNOWN_PLACES.get(isoCountryLowerCase + "|" + normalise(freeText));
    }

    public String apiKey() {
        return apiKey;
    }

    private static String normalise(String text) {
        String ascii = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return ascii.toLowerCase(Locale.ROOT).replaceAll("\\s+", " ").trim();
    }
}
