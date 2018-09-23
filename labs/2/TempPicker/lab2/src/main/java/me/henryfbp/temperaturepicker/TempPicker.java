package me.henryfbp.temperaturepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.henryfbp.library.TemperatureJSON;

public class TempPicker extends Activity {

    ListView listView;

    ArrayAdapter listAdapter;

    List<TemperatureDate> wkTemps = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        RequestQueue mRequestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        String url = TemperatureJSON.form_week_request("Chicago");

        Log.i("JSON BRO", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("JSON BRO", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("list");

                    List<TemperatureDate> temps = TemperatureDate.fromJSONList(array);

                    wkTemps.addAll(temps);

                    synchronized (listAdapter) {
                        listAdapter.notifyDataSetChanged();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("JSON BRO", error.toString());
                error.printStackTrace();
            }
        });

        mRequestQueue.add(stringRequest); //add the request


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_temp_picker);

        listAdapter = new ArrayAdapter(this, R.layout.simplerow, wkTemps); // create arrayAdapter
        listView = findViewById(R.id.listView);

        listView.setAdapter(listAdapter); // Populate listView with arrayAdapter's content

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Someone clicks a single item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TemperatureDate item = (TemperatureDate) parent.getItemAtPosition(position);

                Log.i("U KLIK ME?", item.toString());

                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                i.putExtra("temperature", item); // pass temperature

                startActivity(i); //start previous activity
            }
        });
    }


}
