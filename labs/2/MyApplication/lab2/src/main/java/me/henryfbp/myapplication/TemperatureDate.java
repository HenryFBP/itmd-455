package me.henryfbp.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.henryfbp.library.TemperatureSolver;

public class TemperatureDate implements Serializable{

    public static final TemperatureSolver solver = new TemperatureSolver();

    public Double temperature;
    public Date date;

    public TemperatureDate(Date date, Double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public static List<TemperatureDate> fromJSONList(JSONArray j) throws JSONException {
        List<TemperatureDate> ret = new ArrayList<>();

        for (Integer i = 0; i < j.length(); i++) {
            JSONObject o = j.getJSONObject(i);

            Date date = new Date((long) o.getInt("dt") * 1000);
            Double temperature = ((double) o.getJSONObject("main").getInt("temp"));
            temperature = solver.solve("kelvin", "celsius", new BigDecimal(temperature)).doubleValue();

            ret.add(new TemperatureDate(date, temperature));
        }

        return ret;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2fc", new SimpleDateFormat("EEE, MMM d h:mm a").format(date), temperature);
    }
}
