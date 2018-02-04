package com.diversityhack.placespace.aggieeye;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Srujan on 6/15/2017.
 */

public class Font {

    static Context context;

    public static void setAllTextView(ViewGroup parent,Context ctx) {
        context = ctx;
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                setAllTextView((ViewGroup) child,ctx);
            } else if (child instanceof TextView) {
                ((TextView) child).setTypeface(getFont());
            }
        }
    }

    public static Typeface getFont() {
        return Typeface.createFromAsset(context.getAssets(), "avenir.otf");
    }
}
