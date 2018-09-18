package me.henryfbp.library;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/***
 * Solve any temperature.
 */
public class TemperatureSolver {

    public Map<ImmutableList, TemperatureSolverSingle> map = new HashMap<>();

    public TemperatureSolver() { //TODO: Use linear algebra to automatically fill-in missing formulas.
        this.addTemp(
                new TemperatureSolverSingle("celsius", "fahrenheit", "F(x) = ((9/5) * x) + 32"),
                new TemperatureSolverSingle("celsius", "kelvin", "F(x) = x + 273.15"),
                new TemperatureSolverSingle("fahrenheit", "kelvin", "F(x) = (x + 459.67) * (5/9)"),
                new TemperatureSolverSingle("fahrenheit", "celsius", "F(x) = (x - 32) * (5/9)"),
                new TemperatureSolverSingle("kelvin", "celsius", "F(x) = x - 273.15"),
                new TemperatureSolverSingle("kelvin", "fahrenheit", "F(x) = ((9/5) * x) - 459.67")
        );
    }

    public void addTemp(TemperatureSolverSingle... tsss) {
        for (TemperatureSolverSingle tss : tsss) {
            this.map.put(ImmutableList.of(tss.from, tss.to), tss);
        }
    }

    public BigDecimal solve(String from, String to, BigDecimal temp) {
        return this.map.get(ImmutableList.of(from, to)).solve(temp);
    }
}
