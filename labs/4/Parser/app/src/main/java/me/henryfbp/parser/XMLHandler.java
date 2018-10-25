package me.henryfbp.parser;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
    public static XMLGettersSetters data = null;
    String elementValue = null;
    Boolean elementOn = false;

    public static XMLGettersSetters getXMLData() {
        return data;
    }


    public static void setXMLData(XMLGettersSetters data) {
        XMLHandler.data = data;
    }

    /**
     * This will be called when the tags of the XML starts.
     **/
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        elementOn = true;

        if (localName.equals("CATALOG")) {
            data = new XMLGettersSetters();
        } else if (localName.equals("CD")) {
            try {
                data.addCd(attributes.getValue("attr"));
            } catch (Exception e) {
                Log.i("err on handler   ", e.getMessage());
            }
        }
    }

    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        elementOn = false;

        /**
         * Sets the values after retrieving the values from the XML tags
         * */
        if (localName.equalsIgnoreCase("title")) {
            data.addTitle(elementValue);
        } else if (localName.equalsIgnoreCase("artist")) {
            data.addArtist(elementValue);
        } else if (localName.equalsIgnoreCase("country")) {
            data.addCountry(elementValue);
        } else if (localName.equalsIgnoreCase("company")) {
            data.addCompany(elementValue);
        } else if (localName.equalsIgnoreCase("price")) {
            data.addPrice(elementValue);
        } else if (localName.equalsIgnoreCase("year")) {
            data.addYear(elementValue);
        }
    }

    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (elementOn) {
            elementValue = new String(ch, start, length);
            elementOn = false;
        }

    }
}
