package me.henryfbp.temperatureconverter.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.henryfbp.temperatureconverter.R;

public class TemperatureElement extends LinearLayout {

    public TemperatureUnit temperatureUnit;
    public EditText editText;
    public TextView textView;

    public TemperatureElement(Context context, TemperatureUnit t) {
        super(context);

        this.temperatureUnit = t;

        //inflate all layout contents from single_temperature.xml
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.single_temperature, null);

        this.addView(v); //add inflated view

        this.editText = v.findViewById(R.id.editTextTemperatureUnit);
        this.editText.setText("-1");

        this.textView = v.findViewById(R.id.textViewTemperatureUnit);
        this.textView.setText(this.temperatureUnit.unit);

    }

}
