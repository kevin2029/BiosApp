package com.example.filmapp.TMDBCommands.toevoegen;

import android.os.AsyncTask;

import com.example.filmapp.utils.NetworkUtils;


import org.json.JSONObject;

public class TmdbMaakLijstAan extends AsyncTask<String, String, String> {




    @Override
    protected String doInBackground(String... strings) {
        String Naam = "{\"name\": \"" + strings[0] + "\", ";
        String Beschrijving = "\"description\": \"" + strings[1] + "\", ";
        String Taal = "\"language\": \"" + strings[2] + "\"}";

        String temp = "";

        try {
            String jsonResponseString = NetworkUtils.doSendPostRequest(
                    "https://api.themoviedb.org/3/list?api_key=" + NetworkUtils.API_Key + "&session_id=" + NetworkUtils.Session_ID, Naam + Beschrijving + Taal);
            temp = new JSONObject(jsonResponseString).getString("status_message");
        } catch (Exception e) {}
       return temp;
    }



}
