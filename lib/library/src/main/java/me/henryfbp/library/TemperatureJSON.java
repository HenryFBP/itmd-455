package me.henryfbp.library;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class TemperatureJSON {

    /*
    OpenWeatherMap API Key.

    I am aware embedding API keys is insecure, and to that, I say:
    You wouldn't download a cloud, would you?
    */
    private static final String API_KEY = "b2d4abd7657a8072e535576cfd13b3e6"; // ...would you?

    private static final String URL = "https://samples.openweathermap.org/data/2.5/forecast";

    public static String form_week_request(String location) {
        return form_request(ImmutableMap.of("q", location));
    }

    public static String form_request(Map<String, String> args) {

        String s = URL + "?appid=" + API_KEY;

        s = HLib.apply_json_params(s, args);

        return s;
    }

}
