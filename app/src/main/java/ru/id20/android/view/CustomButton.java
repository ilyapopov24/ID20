package ru.id20.android.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Dre on 26.09.2014.
 */
public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
        setCustomFont();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont();
    }

    public void setCustomFont() {
        Typeface mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Light.ttf");
        setTypeface(mTypeface , Typeface.NORMAL);
    }
}