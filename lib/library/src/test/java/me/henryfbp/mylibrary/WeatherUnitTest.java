package me.henryfbp.mylibrary;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.Forecast;
import com.github.fedy2.weather.data.Item;
import com.github.fedy2.weather.data.unit.DegreeUnit;

import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBException;

import static me.henryfbp.library.HLib.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WeatherUnitTest {

    @Test
    public void can_grab_weather() {


        SimpleDateFormat s = new SimpleDateFormat("MMM d, EEE");

        try {
            YahooWeatherService yws = new YahooWeatherService();
            YahooWeatherService.LimitDeclaration ld = yws.getForecastForLocation("Chicago, IL", DegreeUnit.FAHRENHEIT);

            try {
                for (Channel c : ld.all()) {

                    println(c.getDescription());

                    Item i = c.getItem();

                    for (Forecast f : i.getForecasts()) {
                        String str = (String.format("%s: %d - %d.",
                                s.format(f.getDate()), f.getHigh(), f.getLow()));

                        assertTrue(str.contains(((Integer) f.getHigh()).toString()));

                        println(str);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }


        assertEquals(true, true);

    }
}