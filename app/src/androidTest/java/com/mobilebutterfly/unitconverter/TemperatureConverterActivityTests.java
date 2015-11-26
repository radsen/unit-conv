package com.mobilebutterfly.unitconverter;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

/**
 * Created by asolano on 11/26/15.
 */
public class TemperatureConverterActivityTests extends ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    private TemperatureConverterActivity activity;
    private EditText celsiusInput;
    private EditText fahrenheitInput;

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

        celsiusInput = (EditText) activity.findViewById(R.id.et_temp_converter_celsius);
        fahrenheitInput = (EditText) activity.findViewById(R.id.et_temp_converter_fahrenheit);
    }

    public final void testHasInputFields(){
        assertNotNull(celsiusInput);
        assertNotNull(fahrenheitInput);
    }
}
