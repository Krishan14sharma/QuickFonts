package com.krishan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import chnk.view.R;

public class TextView extends android.widget.TextView {
    private String quickfont;

    public TextView(Context context) {
        super(context);
        init(null, 0);
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextView, defStyle, 0);
        try {
            quickfont = a.getString(R.styleable.TextView_quickfont);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            a.recycle();
        }
        if(quickfont!=null&!isInEditMode())
        {
            Typeface typeface=TypefaceManager.getTypeface(getContext(),quickfont);
            if(typeface!=null)
            {
                setTypeface(typeface);
                Log.v("TAG",TypefaceManager.getTypeface(getContext(),quickfont).toString());
            }
        }

        // Note: This flag is required for proper typeface rendering
        setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

    }
}