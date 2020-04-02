package com.example.filmapp.utils;
//test

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetListIdTask extends AsyncTask<String, Void, List<Integer>> {
    private static final String TAG = GetListIdTask.class.getSimpleName();
    private FilmListListener filmListListener;

    public GetListIdTask(FilmListListener filmListListener) {
        this.filmListListener = filmListListener;
    }

    @Override
    protected List<Integer> doInBackground(String... urls) {
        Log.i(TAG, "In doInBackground met urls: " + urls);

        ArrayList<Integer> ids = new ArrayList<>();

        if (urls != null && urls.length > 0) {
            String url = urls[0];
            Log.i(TAG, "param url: " + url);
            String response = NetworkUtils.doSendRequestToAPI(url, null);
            System.out.println(response);
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray results = jsonResponse.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject jsonObject = results.getJSONObject(i);
                    ids.add(jsonObject.getInt("id"));
                }
                return ids;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ids;
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "In onPreExecute");
    }

    @Override
    protected void onPostExecute(List<Integer> strings) {
        Log.i(TAG, "In onPostExecute");
        filmListListener.processResultInts(strings);
    }

    public interface FilmListListener {
        void processResultInts(List<Integer> strings);
    }

}
