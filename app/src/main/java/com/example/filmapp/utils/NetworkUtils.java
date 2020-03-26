package com.example.filmapp.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkUtils {

    public static final String TAG = NetworkUtils.class.getSimpleName();

    public static String doSendRequestToAPI(String urlApiString) {

        InputStream inputStream = null;
        int responseCode = -1;

        // Het resultaat dat we gaan retourneren
        String response = "";

        // Maak verbinding met de urlApiString en haal het response op
        try {
            URL url = new URL(urlApiString);
            URLConnection urlConnection = url.openConnection();

            // Als het niet gelukt is om een URL connection op te zetten moeten we stoppen
            if (!(urlConnection instanceof HttpURLConnection)) {
                Log.e(TAG, "Niet gelukt om een URL connectie te maken!");
                return null;
            }

            // Initiëer de connectie en maak verbinding
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // De verbinding is gelukt. Lees hier de data.
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.i(TAG, "response = " + response);
            } else {
                // Verbinding lukte, maar de server geeft een foutcode
                Log.e(TAG, "Fout in responsCode: code = " + responseCode);
            }
        } catch (MalformedURLException e) {
            // De URL was niet correct geformuleerd.
            Log.e(TAG, "MalformedURLEx " + e.getMessage());
            return null;
        } catch (IOException e) {
            // Het lukte niet om verbinding te maken.
            Log.e(TAG, "IOException " + e.getMessage());
            return null;
        }
        // Hier hebben we een resultaat
        return response;
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Het lukte niet om verbinding te maken.
                    Log.e(TAG, "doInBackground IOException " + e.getMessage());
                }
            }
        }
        return sb.toString();
    }
}