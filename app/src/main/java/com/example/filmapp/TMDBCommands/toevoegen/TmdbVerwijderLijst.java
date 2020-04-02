package com.example.filmapp.TMDBCommands.toevoegen;

import android.os.AsyncTask;

import com.example.filmapp.utils.NetworkUtils;

import org.json.JSONObject;

public class TmdbVerwijderLijst extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {
        String temp = "Naught";
        try {
            String jsonResponeString = NetworkUtils.doSendRequestToAPI("https://api.themoviedb.org/3/list/" + strings[0] + "?api_key=" + NetworkUtils.API_Key + "&session_id=" + NetworkUtils.Session_ID, "DELETE");
            JSONObject Jsonantwoord = new JSONObject(jsonResponeString);
            temp = Jsonantwoord.getString("status_message");
            return temp;
        }

        catch (Exception e){}
        return temp;
    }
}
