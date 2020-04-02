package com.example.filmapp;
import com.example.filmapp.utils.NetworkUtils;
public class FLijstInfo extends ExtraInfo {
    public FLijstInfo(String mLijstId, String mLijstnaam, String mLijstBeschrijving) {
        super(mLijstId, mLijstnaam, mLijstBeschrijving);
    }

    @Override
    public String getQueryUrl() {
        return null;
    }


    public String GetLijstID() {
        return "https://api.themoviedb.org/3/list/" + mLijstId + "?api_key=" + NetworkUtils.API_Key;
    }

}
