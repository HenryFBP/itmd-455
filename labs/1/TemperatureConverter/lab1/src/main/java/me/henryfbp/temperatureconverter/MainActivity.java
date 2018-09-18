package me.henryfbp.temperatureconverter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import me.henryfbp.library.EditableTextWatcher;
import me.henryfbp.library.TemperatureSolver;
import me.henryfbp.temperatureconverter.lib.TemperatureElement;
import me.henryfbp.temperatureconverter.lib.TemperatureUnit;

import static me.henryfbp.library.HLib.mixColors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Map<String, TemperatureElement> tempelems = new HashMap<>();
        final TemperatureSolver ts = new TemperatureSolver();
        final Context context = this.getApplicationContext();
        final MainActivity mainActivity = this;

        System.out.println();

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        LinearLayout templist = this.findViewById(R.id.linearLayoutTemperatureList);


        tempelems.put("fahrenheit", new TemperatureElement(this.getApplicationContext(), new TemperatureUnit("f")));
        tempelems.put("celsius", new TemperatureElement(this.getApplicationContext(), new TemperatureUnit("c")));
        tempelems.put("kelvin", new TemperatureElement(this.getApplicationContext(), new TemperatureUnit("k")));

        // This will update the background color to match the temperature.
        tempelems.get("fahrenheit").editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                View v = mainActivity.findViewById(R.id.root);

                // 0 is blue,
                // 100 is red.
                Float percent = tempelems.get("fahrenheit").getTemp().floatValue() / 100;

                // Avoid IllegalArgExceptions.
                if (percent > 1f) {
                    percent = 1f;
                }
                if (percent < 0f) {
                    percent = 0f;
                }

                //Mix the two colors.
                Color c = mixColors(
                        mainActivity.getColor(R.color.warm),
                        mainActivity.getColor(R.color.cold),
                        percent
                );

                Log.i("bg_color", percent.toString() + "->" + c.toString());

                // Apply the two colors.
                v.setBackgroundColor(
                        c.toArgb()
                );
            }
        });

        //  For each String <---> TemperatureElement, add a listener.
        for (Map.Entry<String, TemperatureElement> entry : tempelems.entrySet()) {

            final String k = entry.getKey();
            final TemperatureElement v = entry.getValue();

            EditableTextWatcher tw = new EditableTextWatcher() {


                @Override
                public void beforeTextChange(CharSequence s, int start, int count, int after) {

                }

                @Override
                protected void onTextChange(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChange(Editable s) {
                    Log.i(("main_tempTextEdit_" + k), s.toString());

                    //After text changes, update ALL temperatures that are not the selected one.
                    for (Map.Entry<String, TemperatureElement> entry : tempelems.entrySet()) {
                        String key = entry.getKey();
                        TemperatureElement value = entry.getValue();

                        // If we're not looking at ourselves, solve it!
                        if (!key.equalsIgnoreCase(k)) {

                            try {

                                TemperatureElement otherElem = tempelems.get(key);

                                BigDecimal solution = ts.solve(k, key, v.getTemp());

                                //Do NOT trigger the other element's EditText TextWatcher
                                otherElem.editText.removeTextChangedListener(otherElem.textWatcher);

                                // Change the text.
                                otherElem.setTemp(solution.round(new MathContext(4)));

                                // Re-register the textChangedListener.
                                otherElem.editText.addTextChangedListener(otherElem.textWatcher);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };

            v.editText.addTextChangedListener(tw);
            v.textWatcher = tw;
            templist.addView(v);
        }


        FloatingActionButton fab = this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
