package io.github.arthurhoch.purevalue.geo;

/**
 * Utility class for GeoCoordinate validation and parsing.
 */
public final class GeoCoordinateValidator {

    private GeoCoordinateValidator() {
        // Prevent instantiation of utility class.
    }

    /**
     * Checks if the given latitude and longitude are within valid ranges.
     * @param latitude must be between -90 and +90.
     * @param longitude must be between -180 and +180.
     * @return true if both values are within valid ranges.
     */
    public static boolean isValid(double latitude, double longitude) {
        return latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180;
    }

    /**
     * Parses a string in the format "latitude,longitude" and returns an array of two doubles.
     * @param value the input string.
     * @return a double array: [latitude, longitude].
     * @throws IllegalArgumentException if the input is null, empty, not properly formatted, or contains invalid numbers.
     */
    public static double[] parse(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("GeoCoordinate string cannot be null or empty");
        }
        String[] parts = value.trim().split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("GeoCoordinate must be in the format 'latitude,longitude'");
        }
        try {
            double lat = Double.parseDouble(parts[0].trim());
            double lon = Double.parseDouble(parts[1].trim());
            return new double[]{lat, lon};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("GeoCoordinate contains invalid numbers: " + value, e);
        }
    }

    /**
     * Formats latitude and longitude into a canonical string "latitude,longitude".
     * @param latitude the latitude.
     * @param longitude the longitude.
     * @return a formatted string.
     */
    public static String format(double latitude, double longitude) {
        return latitude + "," + longitude;
    }
}
