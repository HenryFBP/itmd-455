package me.henryfbp.parser;

import android.util.Log;

import java.util.ArrayList;


public class XMLGettersSetters {
    /*This class contains all getter and setter methods to set and retrieve data.*/


    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> artist = new ArrayList<>();
    private ArrayList<String> country = new ArrayList<>();
    private ArrayList<String> company = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();
    private ArrayList<String> year = new ArrayList<>();
    private ArrayList<String> cd = new ArrayList<>();

    public ArrayList<String> getCompany() {
        return company;
    }

    public void addCompany(String company) {
        this.company.add(company);
        Log.i("This is the company:", company);
    }

    public ArrayList<String> getPrice() {
        return price;
    }

    public void addPrice(String price) {
        this.price.add(price);
        Log.i("This is the price:", price);
    }

    public ArrayList<String> getYear() {
        return year;
    }

    public void addYear(String year) {
        this.year.add(year);
        Log.i("This is the year:", year);
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void addTitle(String title) {
        this.title.add(title);
        Log.i("This is the title:", title);
    }

    public ArrayList<String> getArtist() {
        return artist;
    }

    public void addArtist(String artist) {
        this.artist.add(artist);
        Log.i("This is the artist:", artist);
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public void addCountry(String country) {
        this.country.add(country);
        Log.i("This is the country:", country);
    }

    public ArrayList<String> getCd() {
        return cd;
    }

    public void addCd(String cd) {
        Log.i("Sold out?: ", cd);
        this.cd.add(cd);
    }
}