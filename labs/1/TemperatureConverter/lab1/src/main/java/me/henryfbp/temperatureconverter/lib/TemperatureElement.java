package me.henryfbp.temperatureconverter.lib;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;

import me.henryfbp.temperatureconverter.R;

public class TemperatureElement extends LinearLayout {

    public TemperatureUnit unit;
    public EditText editText;
    public TextWatcher textWatcher;
    public TextView textView;

    public TemperatureElement(Context context, TemperatureUnit t) {
        super(context);

        this.unit = t;

        //inflate all layout contents from single_temperature.xml
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.single_temperature, null);

        this.addView(v); //add inflated view

        this.editText = v.findViewById(R.id.editTextTemperatureUnit);
        this.setTemp(new BigDecimal("-1"));

        this.textView = v.findViewById(R.id.textViewTemperatureUnit);
        this.textView.setText(this.unit.unit);

    }

    public BigDecimal getTemp() {
        try {
            return new BigDecimal(this.editText.getText().toString());

        } catch (NumberFormatException nfe) {
            return new BigDecimal(0);
        }
    }

    public void setTemp(BigDecimal t) {
        this.editText.setText(t.toPlainString());
    }

}
