package com.mobilebutterfly.unitconverter;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by asolano on 11/27/15.
 */
public class TemperatureConverterTests {

    private static final Map<Double, Double> conversionTable = new HashMap<Double, Double>(){
        {
            put(0.0, 32.0);
            put(100.0, 212.0);
            put(-1.0, 30.20);
            put(-100.0, -148.0);
            put(32.0, 89.60);
            put(-40.0, -40.0);
            put(-273.0, -459.40);
        }
    };

    @Test
    public void testFahrenheitToCelsius(){
        for(double knownCelsius : conversionTable.keySet()){
            double knownFahrenheit = conversionTable.get(knownCelsius);

            double resultCelsius = TemperatureConverter.fahrenheitToCelsius(knownFahrenheit);

            double delta = Math.abs(resultCelsius - knownCelsius);
            String message = knownFahrenheit + "F -> " + knownCelsius +
                    "C but is " + resultCelsius + "C";

            assertTrue(message, delta < 0.0001);
        }
    }
}
