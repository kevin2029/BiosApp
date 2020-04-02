package com.example.filmapp.TMDBCommands.opvragen;


import android.os.AsyncTask;

import com.example.filmapp.Domain.ExtraInfo;
import com.example.filmapp.Domain.FLijstInfo;
import com.example.filmapp.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TmdbKrijgLijst extends AsyncTask <String, String, ArrayList<ExtraInfo>> {
    private final String TAG = TmdbKrijgLijst.class.getSimpleName();
    private Listner ListListner;

    public TmdbKrijgLijst(Listner listListner) {
        this.ListListner = listListner;
    }


    protected ArrayList<ExtraInfo> doInBackground(String... strings) {
        ArrayList<ExtraInfo> temp = new ArrayList<>();

        try {
            String JsonAntwoord = NetworkUtils.doSendRequestToAPI("https://api.themoviedb.org/3/account/" + NetworkUtils.Ac_ID + "/lists?api_key=" + NetworkUtils.API_Key + "&session_id=" + NetworkUtils.Session_ID + "&page=1","GET");
            JSONObject JsonAntwoordLijsten = new JSONObject(JsonAntwoord);

            JSONArray FilmLijsten = JsonAntwoordLijsten.getJSONArray("results");


            for (int i = 0; i <FilmLijsten.length(); i++){
                JSONObject HuidigeLijstJSON = FilmLijsten.getJSONObject(i);
                int iLijstId = HuidigeLijstJSON.getInt("id");
                String ILijstNaam = HuidigeLijstJSON.getString("name");
                String IlijstOmschrijving = HuidigeLijstJSON.getString("description");

                FLijstInfo FLijstInfo = new FLijstInfo("" + iLijstId, ILijstNaam, IlijstOmschrijving);
                temp.add(FLijstInfo);
            }
        }catch (Exception E){}

        return temp;
    }

    @Override
    protected void onPostExecute(ArrayList<ExtraInfo> LijstX) {
        super.onPostExecute(LijstX);
        ListListner.processResult(LijstX);
    }

    public interface Listner {
        void processResult(ArrayList<ExtraInfo> movieLists);
    }
}