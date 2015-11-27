package com.mobilebutterfly.unitconverter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by asolano on 11/27/15.
 */
public class EditNumber extends EditText {
    private double number;

    public EditNumber(Context context) {
        super(context);
    }

    public EditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void clear() {

    }

    public double getNumber() {
        return number;
    }
}
