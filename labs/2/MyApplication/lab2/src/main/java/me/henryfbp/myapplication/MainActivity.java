package me.henryfbp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        //declare viewstub object
        stub = findViewById(R.id.viewStub);
        @SuppressWarnings("unused")
        View inflated = stub.inflate();
        stub.setVisibility(View.INVISIBLE);

        //ViewStub logic
        button = findViewById(R.id.button);
        //handle checkbox click event
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove objs from parent view to allow for child view objs
//                button.setVisibility(View.GONE);
//                seekBar.setVisibility(View.GONE);
//                textView.setVisibility(View.GONE);
//                stub.setVisibility(View.VISIBLE);
                Intent i = new Intent(v.getContext(), TempPicker.class);

                startActivityForResult(i, 0);
            }
        });

        //seekbar logic

        textView = findViewById(R.id.textview);
        textView.setText("0c");
        //set default view
        seekBar = findViewById(R.id.seekbar);
        seekBar.setProgress(start_position);

        //create event handler for SeekBar
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (temp == 0) //for initial view result
                    Toast.makeText(getBaseContext(), "32f", Toast.LENGTH_SHORT).show();
                else
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

    } //end onCreate method
}