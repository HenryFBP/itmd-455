package me.henryfbp.library;

import android.graphics.Color;

import java.util.Map;
import java.util.Random;

public class HLib {


    public static void println(String arg)
    {
        System.out.println(arg);
    }

    public static void print(String args)
    {
        System.out.print(args);
    }

    public static Color randomColor() {
        Random r = new Random();
        return Color.valueOf(r.nextFloat(), r.nextFloat(), r.nextFloat());
    }


    /*
     * Adapted from https://stackoverflow.com/a/17544748/4262535.
     *
     * Mixes two colors together.
     */
    public static Color mixColors(Color c1, Color c2, Float percent) {

        if (percent < 0f) {
            throw new IllegalArgumentException(percent.toString() + " < 0!");
        }
        if (percent > 1f) {
            throw new IllegalArgumentException(percent.toString() + " > 1!");
        }

        float inv_percent = 1.0f - percent;

        float r = (c1.red() * percent +
                c2.red() * inv_percent);

        float g = (c1.green() * percent +
                c2.green() * inv_percent);

        float b = (c1.blue() * percent +
                c2.blue() * inv_percent);

        return Color.valueOf(r, g, b);
    }

    public static Color mixColors(int c1, int c2, float percent) {
        return mixColors(Color.valueOf(c1), Color.valueOf(c2), percent);
    }

    public static String apply_json_params(String str, Map<String, String> args){

        StringBuilder s = new StringBuilder(str);

        for (Map.Entry e : args.entrySet()) {
            String k = (String) e.getKey();
            String v = (String) e.getValue();

            s.append("&").append(k).append("=").append(v);
        }

        return s.toString();

    }


}
