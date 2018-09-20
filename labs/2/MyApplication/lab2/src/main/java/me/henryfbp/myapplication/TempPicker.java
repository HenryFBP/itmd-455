package me.henryfbp.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class TempPicker extends Activity {

    ListView listView;

    List<Integer> wkTemps = Arrays.asList(1, -10, 0, 30, 10);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_temp_picker);

        ListAdapter listAdapter = new ArrayAdapter(this, R.layout.simplerow, wkTemps); // create arrayAdapter
        listView = findViewById(R.id.listView);

        listView.setAdapter(listAdapter); // Populate listView with arrayAdapter's content

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Someone clicks a single item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer item = (Integer) parent.getItemAtPosition(position);

                Log.i("U KLIK ME?", item.toString());

                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                i.putExtra("temperature", item); // pass temperature

                startActivity(i); //start previous activity
            }
        });
    }


}
