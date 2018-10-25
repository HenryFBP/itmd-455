package me.henryfbp.parser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    public static final String DATA_SOURCE = "http://www.papademas.net:81/cd_catalog3.xml";
    XMLGettersSetters data;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundTask().execute();
    }

    private void saxParser() {

        try {
            /* Create a new instance of the SAX parser */
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();

            // URL of the XML
            URL url = new URL(DATA_SOURCE);

            /* Create the Handler to handle each of the XML tags */
            XMLHandler myXMLHandler = new XMLHandler();
            xmlR.setContentHandler(myXMLHandler);
            xmlR.parse(new InputSource(url.openStream()));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void viewSaxData() {
        /* Get the view of the layout within the main layout, so that we can
         * add TextViews.
         */
        View layout = findViewById(R.id.layout);
        ViewGroup viewGroupLayout = (ViewGroup) layout;

        /*Create TextView Arrays to add the retrieved data to */

        TextView title[];
        TextView artist[];
        TextView country[];
        TextView company[];
        TextView price[];
        TextView year[];

        data = XMLHandler.data;

        /* Make the TextView array length the size of the arraylist */
        title = new TextView[data.getTitle().size()];
        artist = new TextView[data.getArtist().size()];
        country = new TextView[data.getCountry().size()];
        company = new TextView[data.getCompany().size()];
        price = new TextView[data.getPrice().size()];
        year = new TextView[data.getYear().size()];


        /**
         * Run a for loop to set All the TextViews with text until
         * the given size of the arraylist is reached.
         **/
        for (int i = 0; i < data.getTitle().size(); i++) {

            title[i] = new TextView(this);
            title[i].setText(String.format("Title = %s", data.getTitle().get(i)));

            artist[i] = new TextView(this);
            artist[i].setText(String.format("Artist = %s", data.getArtist().get(i)));

            country[i] = new TextView(this);
            country[i].setText(String.format("Country = %s", data.getCountry().get(i)));

            company[i] = new TextView(this);
            company[i].setText(String.format("Company = %s", data.getCompany().get(i)));

            price[i] = new TextView(this);
            price[i].setText(String.format("Price = %s", data.getPrice().get(i)));

            year[i] = new TextView(this);
            year[i].setText(String.format("Year = %s", data.getYear().get(i)));

            viewGroupLayout.addView(title[i]);
            viewGroupLayout.addView(artist[i]);
            viewGroupLayout.addView(country[i]);
            viewGroupLayout.addView(company[i]);
            viewGroupLayout.addView(price[i]);
            viewGroupLayout.addView(year[i]);
        }

        setContentView(layout);
    }

    public class BackgroundTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewSaxData();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                synchronized (this) {
                    saxParser();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

