package me.henryfbp.myapplication;

import com.github.fedy2.weather.data.Forecast;

import java.util.ArrayList;
import java.util.List;

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

    public static List<TemperatureDay> fromForecasts(List<Forecast> forecasts) {
        List<TemperatureDay> ret = new ArrayList<>();

        for (Forecast f : forecasts) {
            ret.add(new TemperatureDay(f));
        }

        return ret;
    }

    @Override
    public String toString() {
        return "pupcakes: dog-flavored goodness";
    }
}
