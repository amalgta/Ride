package com.styx.mobile.ride.ui.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by amal.george on 18-11-2016.
 */
public class TypeFaceProvider {

    private static Hashtable<String, Typeface> sTypeFaces = new Hashtable<>(
    );

    public static Typeface getTypeFaceWithExt(Context context, String fileName) {
        Typeface tempTypeface = sTypeFaces.get(fileName);

        if (tempTypeface == null) {
            String fontPath = fileName;
            if (context.getAssets() != null)
                tempTypeface = Typeface.createFromAsset(context.getAssets(), fontPath);
            else
                tempTypeface = Typeface.DEFAULT;
            sTypeFaces.put(fileName, tempTypeface);
        }

        return tempTypeface;
    }
}