package com.example.filmapp.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.filmapp.Domain.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListTask extends AsyncTask<String, Void, List<String>> {
    private static final String TAG = ListTask.class.getSimpleName();
    private FilmListListener filmListListener;

    public ListTask(FilmListListener filmListListener) {
        this.filmListListener = filmListListener;
    }

    @Override
    protected List<String> doInBackground(String... urls) {
        Log.i(TAG, "In doInBackground met urls: " + urls);

        if (urls != null && urls.length > 1) {
            String url = urls[0];
            Log.i(TAG, "param url: " + url);
            String results = NetworkUtils.doSendRequestToAPI(url, urls[1]);
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "In onPreExecute");
    }
    @Override
    protected void onPostExecute(List<String> strings) {
        Log.i(TAG, "In onPostExecute");
        filmListListener.processResult(strings);
    }

    public interface FilmListListener{
        void processResult(List<String> strings);
    }

}
