package com.example.filmapp.TMDBCommands.toevoegen;


import android.os.AsyncTask;

import com.example.filmapp.utils.NetworkUtils;

import org.json.JSONObject;

public class TmdbVoegFilmToeAanLijst extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... args) {
        String temp = "Failed";
        String listId = args[0];
        int movieId = Integer.parseInt(args[1]);

        try {
            String Jsonantwoord = NetworkUtils.doSendPostRequest(
                    "https://api.themoviedb.org/3/list/" + listId + "/add_item?api_key=" + NetworkUtils.API_Key + "&session_id=" + NetworkUtils.Session_ID,
                    "{\n" +
                            "  \"media_id\": " + movieId + "\n" +
                            "}");
            temp = new JSONObject(Jsonantwoord).getString("status_message");


        } catch (Exception e) {
        }

        return temp;
    }
}
