package me.henryfbp.temperatureconverter.lib;

public final class TemperatureLib {
    public static final double celsiusToFahrenheit(double c)
    {
        return (c * (9d / 5d)) + 32d;
    }

    public static final double fahrenheitToCelsius(double f)
    {
        return (f - 32d) * (5d / 9d);
    }
}
