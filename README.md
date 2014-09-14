QuickFont
=======

Android library to apply quick custom typefaces directly from layouts.The library maintains a cache of least recently used typefaces.

Usage
-----

Here is an example of the use of this library.It contains commonly used widgets ,where you can apply typeface--

```java

   <com.quick.TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world"
        android:textColor="#2cc26b"
        android:textSize="22sp"
        app:quickfont="JennaSue.ttf" 
        />
