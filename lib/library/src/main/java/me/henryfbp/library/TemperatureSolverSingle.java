package me.henryfbp.library;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.math.BigDecimal;

/***
 * Turn one temperature into another one.
 */
public class TemperatureSolverSingle {

    public String from;
    public String to;
    public Function f;

    public TemperatureSolverSingle(String from, String to, String func) {
        this.from = from;
        this.to = to;
        this.f = new Function(func);
    }

    /**
     * Default constructor, defaults to celsius->fahrenheit.
     */
    public TemperatureSolverSingle() {
        this.f = new Function("F(x) = ((9/5) * x) + 32");
        this.from = "celsius";
        this.to = "fahrenheit";
    }

    public BigDecimal solve(BigDecimal temp) {
        return BigDecimal.valueOf(new Expression("F(x)", this.f,
                new Argument("x", temp.doubleValue())).calculate());

    }
}
