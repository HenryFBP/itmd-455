package me.henryfbp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import me.henryfbp.library.TemperatureSolver;


public class MainActivity extends AppCompatActivity {

    TemperatureSolver ts = new TemperatureSolver();
    SeekBar seekBar; //declare seekbar object
    TextView textView;
    //declare member variables for SeekBar
    int discrete = 0;
    int start = 50;
    int start_position = 50; //progress tracker
    int temp = 0;
    //declare objects for ViewStub
    ViewStub stub;
    Button button;
    ListView lv; //declare Listview object

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null && extras.containsKey("temperature")) {
            Integer temperature = (Integer) extras.get("temperature");
            Log.i("GETTIN INTENT", String.valueOf(temperature));
            start_position = temperature + start;
        } else {
            Log.i("GETTIN INTENT", "No 'temperature' from Intent's Extras. Must be the first time we're in this Activity.");
        }

        textView = findViewById(R.id.textview);
        textView.setText("0c");
        //set default view
        seekBar = findViewById(R.id.seekbar);

        stub = findViewById(R.id.viewStub);
        View inflated = stub.inflate();

        stub.setVisibility(View.INVISIBLE);

        button = findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TempPicker.class);

                startActivityForResult(i, 0);
            }
        });

        //create event handler for SeekBar
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getBaseContext(), String.format("%sf", String.valueOf(discrete)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // To convert progress passed as discrete (Fahrenheit) value
                temp = progress - start;
                discrete = ts.solve("celsius", "fahrenheit", new BigDecimal(temp)).intValue();
                textView.setText(String.format("%dc", temp));
            }
        });

        seekBar.setProgress(start_position);


    } //end onCreate method
}