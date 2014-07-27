package com.krishan;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.util.Pair;

/**
 * Created by KRISHAN on 06-Jul-14.
 */

class TypefaceManager {

    private static LruCache<String, Typeface> lruCache = new LruCache<String, Typeface>(10);  //Todo provide configuration for cache size

    public static Pair<Typeface, Boolean> getTypeface(Context context, String name) {
        Typeface typeface = lruCache.get(name);
        boolean fromCache=true;
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), name);
                fromCache=false;
            } catch (Exception e) {

            }

            if (typeface == null) {
                throw new NullPointerException("Resource named " + name + " not found in assets");
            } else
                lruCache.put(name, typeface);
        }
        return Pair.create(typeface,fromCache);
    }

}
