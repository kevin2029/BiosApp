package com.example.filmapp;

import android.os.AsyncTask;
import android.util.Log;

import com.example.filmapp.Domain.Film;
import com.example.filmapp.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmList extends AsyncTask<String, Void, List<Film>> {
    private static final String TAG = FilmList.class.getSimpleName();
    private FilmListListener filmListListener;

    public FilmList(FilmListListener filmListListener) {
        this.filmListListener = filmListListener;
    }

    @Override
    protected List<Film> doInBackground(String... urls) {
        Log.i(TAG, "In doInBackground met urls: " + urls);

        List<Film> films = new ArrayList<>();

        if (urls != null && urls.length > 0) {
            String url = urls[0];
            Log.i(TAG, "param url: " + url);
            String response = NetworkUtils.doSendRequestToAPI(url, null);

            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray results = jsonResponse.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject jsonObject = results.getJSONObject(i);


                    String picture = "https://image.tmdb.org/t/p/w500/" + jsonObject.getString("poster_path");

                    Film film = new Film(
                            jsonObject.getString("title"),
                            jsonObject.getString("overview"),
                            jsonObject.getString("release_date"),
                            picture,
                            jsonObject.getString("vote_average"),
                            jsonObject.getInt("id"),
                            jsonObject.getInt("vote_count"),
                            jsonObject.getDouble("vote_average")
                    );
                    films.add(film);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return films;
        }
        return films;
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "In onPreExecute");
    }
    @Override
    protected void onPostExecute(List<Film> films) {
        Log.i(TAG, "In onPostExecute");
        filmListListener.processResult(films);
    }

    public interface FilmListListener{
        void processResult(List<Film> films);
    }
}
