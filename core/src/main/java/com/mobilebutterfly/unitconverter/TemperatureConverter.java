package com.mobilebutterfly.unitconverter;

/**
 * Created by asolano on 11/27/15.
 */
public class TemperatureConverter {
    private static final double ABSOLUTE_ZERO_F = -459.67d;
    private static final String ERROR_MESSAGE_BELOW_ZERO_FMT =
            "Invalid temperature: %.2f%c below absolute zero";

    private TemperatureConverter(){}

    public static double fahrenheitToCelsius(double f) {
        if(f < ABSOLUTE_ZERO_F){
            String message = String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, f, 'F','F');

            throw new InvalidTemperatureException(message);
        }

        return (f - 32) / 1.8d;
    }
}
