package me.henryfbp.temperatureconverter.lib;

import org.mariuszgromada.math.mxparser.Expression;

public final class TemperatureLib {

    public static final String celsius_to_fahrenheit = "y = ((9/5) * x) + 32";

    public static double celsiusToFahrenheit(double x) {

        Expression e = new Expression(celsius_to_fahrenheit);

        e.setArgumentValue("x", x);

        return e.calculate();
    }

    public static double fahrenheitToCelsius(double f) {
        return (f - 32d) * (5d / 9d);
    }
}
