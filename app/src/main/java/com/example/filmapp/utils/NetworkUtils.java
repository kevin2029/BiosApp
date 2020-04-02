package com.example.filmapp.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkUtils {

    public static final String TAG = NetworkUtils.class.getSimpleName();
    public static final String URL_LIST = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1ODU2NjIxMjIsInN1YiI6IjVlODMzZTUzMzM0NGM2MDAxNTJiYWFkOCIsImp0aSI6IjE5NDY1MDEiLCJhdWQiOiI5MDFiMjYwYzNiMWY5ZWUwYjkxNTgwYWRmY2RiMWM4MyIsInNjb3BlcyI6WyJhcGlfcmVhZCIsImFwaV93cml0ZSJdLCJ2ZXJzaW9uIjoxfQ.mkY5vk23tNeWcunFQkG3rqZf-tnW08j3t57OBRiyohU";
    public static final String Ac_ID = "5e833e533344c600152baad8";
    public static final String Session_ID = "7d916ff1653ba62da5409c3cd1ab9eebd5c3ea5f";
    public static final String API_Key = "901b260c3b1f9ee0b91580adfcdb1c83";

    public static String doSendRequestToAPI(String urlApiString, String BodyItem) {

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
            httpConnection.setRequestProperty("content-type", "application/json;charset=utf-8");
            httpConnection.setRequestProperty("authorization", "Bearer " + URL_LIST);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.setDoOutput(true);
            if (BodyItem != null) {
                try {
                    OutputStream os = httpConnection.getOutputStream();
                    byte[] input = BodyItem.getBytes("utf-8");
                    os.write(input, 0, input.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            httpConnection.connect();

            responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == 201) {
                // De verbinding is gelukt. Lees hier de data.
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.i(TAG, "response = " + response);
            } else {
                // Verbinding lukte, maar de server geeft een foutcode
                Log.e(TAG, "Fout in responsCode: code = " + responseCode);
                System.out.println(getStringFromInputStream(httpConnection.getErrorStream()));;
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

    public static String doSendPostRequest(String apiUrl, String requestBody) {
        String response = "";
        try {
            URL url = new URL(apiUrl);
            URLConnection urlConnection = url.openConnection();

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setDoOutput(true);
            httpConnection.connect();

            try {
                DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
                byte[] input = requestBody.getBytes("utf-8");
                wr.write(input);
            } catch (Exception f) {
                System.out.println("Error writing OutputStream");
            }

            InputStream inputStream = httpConnection.getInputStream();
            response = getStringFromInputStream(inputStream);
        }
        catch (Exception e) {
            Log.i(TAG,"Error: " + e.getMessage());
        }

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