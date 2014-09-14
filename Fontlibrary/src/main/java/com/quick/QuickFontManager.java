package com.quick;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by KRISHAN on 06-Jul-14.
 */

public class QuickFontManager {

    private static int cacheSize=5;
    private static boolean debuggable=false;
    private static LruCache<String, Typeface> lruCache;


    private QuickFontManager(int cacheSize, boolean debuggable)
    {
        QuickFontManager.debuggable=debuggable;

        if(lruCache==null)
        {
            QuickFontManager.cacheSize=cacheSize;
            lruCache= new LruCache<String, Typeface>(cacheSize);

        }else
        {
            Log.e("QuickFonts","Cache already configured, use configuration before using typeface. Application class is a good contender.");
        }
    }

    public static void clearCache()
    {
        if(lruCache!=null)
        {
            lruCache.evictAll();
            lruCache=null;
        }
    }

    /**
     *
     * @param context
     * @param name
     * @return A pair containing required typeface and boolean value for whether it was fetched from cache.Boolean works only for debuggable mode.
     */
    public static Pair<Typeface, Boolean> getTypeface(Context context, String name) {

        if(lruCache==null)
        {
            lruCache= new LruCache<String, Typeface>(cacheSize);
        }

        Typeface typeface = lruCache.get(name);
        boolean fromCache=true;
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), name);
                fromCache=false;
            } catch (Exception e) {
                typeface=null;
            }

            if (typeface == null) {
                throw new NullPointerException("Resource named " + name + " not found in assets");
            } else
            {
                lruCache.put(name, typeface);
            }
        }
        if(!QuickFontManager.debuggable)
        {
            fromCache=true;  // User has not asked for debugging ,let's fool views
        }
        return Pair.create(typeface,fromCache);
    }




    public static class QuickFontBuilder
    {
        private int cacheSize;
        private boolean debuggable=false;
        public QuickFontBuilder()
        {

        }

        public QuickFontBuilder setCachesize(int cachesize)
        {
            this.cacheSize=cachesize;
            return this;
        }

        public QuickFontManager build()
        {
                return new QuickFontManager(this.cacheSize,this.debuggable);
        }

        public QuickFontBuilder setDebuggable(boolean debuggable) {
            this.debuggable = debuggable;
            return this;
        }

    }



}
