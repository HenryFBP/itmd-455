package me.henryfbp.library;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Forecast;
import com.github.fedy2.weather.data.unit.DegreeUnit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

public class TemperatureGetter {

    private YahooWeatherService service;

    public TemperatureGetter() throws JAXBException {
        service = new YahooWeatherService();
    }

    public List<Forecast> getThisWeekForecast(String location) throws IOException, JAXBException {
        SimpleDateFormat s = new SimpleDateFormat("MMM d, EEE");

        YahooWeatherService.LimitDeclaration ld = service.getForecastForLocation(location, DegreeUnit.FAHRENHEIT);

        return ld.all().get(0).getItem().getForecasts();
    }

    public List<Double> getWeek(Location l) {
        Date today = Date.from(Instant.now());

        return null;
    }
}
