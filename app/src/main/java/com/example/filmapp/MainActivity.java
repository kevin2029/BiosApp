package com.example.filmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.filmapp.Domain.Film;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FilmList.FilmListListener{
    private FilmAdapter mFilmAdapter;
    private RecyclerView mRandomFilmssRecyclerView;

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String URL_FILM_INFORMATIE = "https://api.themoviedb.org/3/movie/top_rated?api_key=0b76c837ee95664733e2cbe4a92c6b5d&language=en-US&page=1";
    public static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FilmList filmList = new FilmList(this);

        /* checks for previous saved Lists, then picks it or makes a new List */
        if (savedInstanceState != null && savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)
                && savedInstanceState.getSerializable(LIFECYCLE_CALLBACKS_TEXT_KEY) instanceof List) {
            mFilmAdapter = new FilmAdapter((List) savedInstanceState.getSerializable(LIFECYCLE_CALLBACKS_TEXT_KEY));
        } else {
            mFilmAdapter = new FilmAdapter(new ArrayList<Film>());
            filmList.execute(URL_FILM_INFORMATIE);
        }

        /* sets the data for the xml atribute */
        mRandomFilmssRecyclerView = findViewById(R.id.randomFilmsList);
        mRandomFilmssRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRandomFilmssRecyclerView.setAdapter(mFilmAdapter);
    }

    @Override
    public void processResult(List<Film> films) {
        mFilmAdapter.setFilms(films);
    }

    //saved de staus waar de pagina zich in bevind
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "In onSaveInstanceState");
        outState.putSerializable(LIFECYCLE_CALLBACKS_TEXT_KEY, (ArrayList) mFilmAdapter.getFilms());
    }

    //vouwt de menu uit voor sharing
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.visulizer_menu, menu);
        return true;
    }

    //de opties als je er op klikt
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent startSettingActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
