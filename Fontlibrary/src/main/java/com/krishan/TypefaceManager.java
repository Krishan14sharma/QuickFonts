package com.krishan;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Created by KRISHAN on 06-Jul-14.
 */

class TypefaceManager {

    private static LruCache<String, Typeface> lruCache = new LruCache<String, Typeface>(10);  //Todo provide configuration for cache size

    public static Typeface getTypeface(Context context, String name) {
        Typeface typeface = lruCache.get(name);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception e) {

            }

            if (typeface == null) {
                throw new NullPointerException("Resource named " + name + " not found in assets");
            } else
                lruCache.put(name, typeface);
        }
        return typeface;
    }

}
