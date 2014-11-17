package ru.id20.android.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Dre on 26.09.2014.
 */
public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);
        setCustomFont();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont();
    }

    public void setCustomFont() {
        Typeface mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Light.ttf");
        setTypeface(mTypeface , Typeface.NORMAL);
    }
}
