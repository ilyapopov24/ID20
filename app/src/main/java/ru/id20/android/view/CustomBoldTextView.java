package ru.id20.android.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by Dre on 26.09.2014.
 */
public class CustomBoldTextView extends TextView {
    public CustomBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont();
    }

    public CustomBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    public CustomBoldTextView(Context context) {
        super(context);
        setCustomFont();
    }

    public void setCustomFont() {
        Typeface mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        setTypeface(mTypeface , Typeface.NORMAL);
    }
}