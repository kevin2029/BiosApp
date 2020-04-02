package com.example.filmapp;

public abstract class ExtraInfo {
    public String mLijstId;
    public String mLijstnaam;
    public String mLijstBeschrijving;




    public ExtraInfo(String mLijstId, String mLijstnaam, String mLijstBeschrijving) {
        this.mLijstId = mLijstId;
        this.mLijstnaam = mLijstnaam;
        this.mLijstBeschrijving = mLijstBeschrijving;


    }

    public abstract String getQueryUrl();

    public String getmLijstId() {
        return mLijstId;
    }

    public String getmLijstnaam() {
        return mLijstnaam;
    }

    public String getmLijstBeschrijving() {
        return mLijstBeschrijving;
    }
}
