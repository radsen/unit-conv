package com.mobilebutterfly.unitconverter;

import android.app.ActionBar;
import android.support.v7.widget.LinearLayoutCompat;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.test.ViewAsserts.assertLeftAligned;
import static android.test.ViewAsserts.assertOnScreen;
import static android.test.ViewAsserts.assertRightAligned;

/**
 * Created by asolano on 11/26/15.
 */
public class TemperatureConverterActivityTests extends
        ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    private TemperatureConverterActivity activity;

    private EditNumber celsiusInput;
    private EditNumber fahrenheitInput;
    private TextView celsiusLabel;
    private TextView fahrenheitLabel;

    public TemperatureConverterActivityTests(){
        this("TemperatureConverterActivityTests");
    }

    public TemperatureConverterActivityTests(String name){
        super(TemperatureConverterActivity.class);
        setName(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

        celsiusInput = (EditNumber) activity.findViewById(R.id.et_temp_converter_celsius);
        fahrenheitInput = (EditNumber) activity.findViewById(R.id.et_temp_converter_fahrenheit);

        celsiusLabel = (TextView) activity.findViewById(R.id.tv_temp_converter_celsius);
        fahrenheitLabel = (TextView) activity.findViewById(R.id.tv_temp_converter_fahrenheit);
    }

    public final void testHasInputFields(){
        assertNotNull(celsiusInput);
        assertNotNull(fahrenheitInput);
    }

    public void testFieldsShouldStartEmpty(){
        assertEquals("", celsiusInput.getText().toString());
        assertEquals("", fahrenheitInput.getText().toString());
    }

    public void testFieldsOnScreen(){
        View originView = activity.getWindow().getDecorView();

        assertOnScreen(originView, celsiusInput);
        assertOnScreen(originView, fahrenheitInput);
    }

    public void testAlignment(){
        assertLeftAligned(celsiusLabel, celsiusInput);
        assertLeftAligned(fahrenheitLabel, fahrenheitInput);
        assertLeftAligned(celsiusInput, fahrenheitInput);
        assertRightAligned(celsiusInput, fahrenheitInput);
    }

    public void testCelsiusInputScreenCoversEntireScreen(){

        ViewGroup.LayoutParams lp;

        int expected = ViewGroup.LayoutParams.MATCH_PARENT;
        lp = celsiusInput.getLayoutParams();

        assertEquals("Celsius input layout width is not MATCH_PARENT", expected, lp.width);
    }

    public void testFahrenheitInputScreenCoversEntireScreen(){
        ViewGroup.LayoutParams lp;

        int expected = ViewGroup.LayoutParams.MATCH_PARENT;
        lp = fahrenheitInput.getLayoutParams();

        assertEquals("Fahrenheit input layout width is not MATCH_PARENT", expected, lp.width);
    }

    public void testFontSizes(){
        float pixelSize = getFloatPixelSize(R.dimen.label_text_size);

        assertEquals(pixelSize, celsiusLabel.getTextSize());
        assertEquals(pixelSize, fahrenheitLabel.getTextSize());
    }

    public void testCelsiusInputMargins(){
        LinearLayout.LayoutParams lp =
                (LinearLayout.LayoutParams) celsiusInput.getLayoutParams();

        assertEquals(getInPixelSize(R.dimen.margin), lp.leftMargin);
        assertEquals(getInPixelSize(R.dimen.margin), lp.rightMargin);
    }

    public void testFahrenheitInputMargins(){
        LinearLayout.LayoutParams lp =
                (LinearLayout.LayoutParams) fahrenheitInput.getLayoutParams();

        assertEquals(getInPixelSize(R.dimen.margin), lp.leftMargin);
        assertEquals(getInPixelSize(R.dimen.margin), lp.rightMargin);
    }

    public void testCelsiusInputJustification(){
        int expectedGravity = Gravity.END | Gravity.CENTER_VERTICAL;
        int currentGravity = celsiusInput.getGravity();
        String errorMessage  = String.format("Expected 0x%02x but was 0x%02x", expectedGravity, currentGravity);
        assertEquals(errorMessage, expectedGravity, currentGravity);
    }

    public void testFahrenheitInputJustification(){
        int expectedGravity = Gravity.END | Gravity.CENTER_VERTICAL;
        int currentGravity = fahrenheitInput.getGravity();
        String errorMessage  = String.format("Expected 0x%02x but was 0x%02x",
                expectedGravity, currentGravity);
        assertEquals(errorMessage, expectedGravity, currentGravity);
    }

    public void testVirtualKeyboardSpaceReserved(){
        int expected = getInPixelSize(R.dimen.keyboard_space);
        int actual = fahrenheitInput.getBottom();
        String errorMessage = "Space not reserved, expected " + expected + " actual " + actual;
        assertTrue(errorMessage, actual <= expected);
    }

    @UiThreadTest
    public void testFahrenheitToCelsiusConversion(){
        celsiusInput.clear();
        fahrenheitInput.clear();
        fahrenheitInput.requestFocus();
        fahrenheitInput.setText("32.5");
        celsiusInput.requestFocus();
        double f = 32.5;
        double expectedCelsius = TemperatureConverter.fahrenheitToCelsius(f);
        double actualCelsius = celsiusInput.getNumber();
        double delta = Math.abs(expectedCelsius - actualCelsius);
        String message = "" + f + "F -> " + expectedCelsius + "C but was "
                + actualCelsius + "C (delta " + delta + ")";
        assertTrue(message, delta < 0.005);
    }

    private int getInPixelSize(int dimensionResourceId) {
        return (int) getFloatPixelSize(dimensionResourceId);
    }

    private float getFloatPixelSize(int dimensionResourceId) {
        return activity.getResources().getDimensionPixelSize(dimensionResourceId);
    }
}
