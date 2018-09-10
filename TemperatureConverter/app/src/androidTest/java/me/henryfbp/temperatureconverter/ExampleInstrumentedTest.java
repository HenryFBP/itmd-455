package me.henryfbp.temperatureconverter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.math.BigDecimal;

import me.henryfbp.temperatureconverter.lib.TemperatureSolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("me.henryfbp.temperatureconverter", appContext.getPackageName());
    }

    @Test
    public void funWithMXParser() { //see https://stackoverflow.com/questions/4460631/java-how-to-implement-math-parsing

        String tag = new Object() {
        }.getClass().getEnclosingMethod().getName(); //current func name


        Double x = 1.0d;

        Function f = new Function("F(x) = (2 * x) + 3");

        Argument a = new Argument("x", x);

        Expression e = new Expression("F(x)", f, a);

        Log.i(tag, String.format("%s where x = %.2f is %.2f",
                f.getFunctionExpressionString(),
                x,
                e.calculate()));

        assertTrue(e.calculate() == ((2 * x) + 3));
    }

    @Test
    public void testTemperatureSolver() {
        String tag = new Object() {
        }.getClass().getEnclosingMethod().getName(); //current func name

        BigDecimal c = new BigDecimal(30d);

        TemperatureSolver ts = new TemperatureSolver();

        BigDecimal f = ts.solve("celsius", "fahrenheit", c);

        Log.i(tag, String.format("%.2fc = %.2ff", c.floatValue(), f.floatValue()));

        assertEquals(f.doubleValue(), (c.doubleValue() * (9d / 5d)) + 32d, 0.05d);
    }


}
