package com.mobilebutterfly.unitconverter;

import android.test.AndroidTestCase;

/**
 * Created by radsen on 12/4/15.
 */
public class EditNumberTests extends AndroidTestCase {

    private EditNumber editNumber;

    public EditNumberTests(){
        this("EditNumberTests");
    }

    public EditNumberTests(String editNumberTests) {
        setName(editNumberTests);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        editNumber = new EditNumber(mContext);
        editNumber.setFocusable(true);
    }

    public void testClear(){
        String value = "123.45";
        editNumber.setText(value);
        editNumber.clear();
        assertEquals("", editNumber.getText().toString());
    }
}
