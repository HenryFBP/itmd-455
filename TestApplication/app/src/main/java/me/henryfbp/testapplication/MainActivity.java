package me.henryfbp.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button button;
    LinearLayout linearLayout;

    public void appendThing(View v) {
        Log.i("test", "button clicked!");

        EditText text = new EditText(this);
        text.setText("I am an even testier string!");

        text.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        linearLayout.addView(text);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        linearLayout = findViewById(R.id.linearLayout);

    }
}
