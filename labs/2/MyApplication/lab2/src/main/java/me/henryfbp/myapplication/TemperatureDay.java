package me.henryfbp.myapplication;

import com.github.fedy2.weather.data.Forecast;

/***
 * A single temperature forecast for a single day.
 *
 * Meant to be used with a ListAdapter.
 */
public class TemperatureDay {

    private Forecast forecast;

    public TemperatureDay(Forecast f) {
        this.forecast = f;
    }

    @Override
    public String toString() {
        return "pupcakes: dog-flavored goodness";
    }
}
