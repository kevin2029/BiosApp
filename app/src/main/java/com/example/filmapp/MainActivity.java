package com.example.filmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.filmapp.Domain.Film;
import com.example.filmapp.Gui.Activity.CreateListActivity;
import com.example.filmapp.Gui.Adapter.FilmAdapter;
import com.example.filmapp.Settings.SettingsActivity;
import com.example.filmapp.utils.FilmList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FilmList.FilmListListener {
    private FilmAdapter mFilmAdapter;
    private RecyclerView mRandomFilmssRecyclerView;

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String URL_FILM_INFORMATIE = "https://api.themoviedb.org/3/movie/top_rated?api_key=901b260c3b1f9ee0b91580adfcdb1c83&language=en-US&page=1";
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

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mFilmAdapter.getFilter().filter(newText);
                return false;
            }
        });
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

        if (id == R.id.go_to_list){
            Intent gotolistcreate = new Intent(this, CreateListActivity.class );
          startActivity(gotolistcreate);
          return true;

        }

        if (id == R.id.go_to_list_with_lists ){
            Intent gotolists = new Intent(this, ListActivity.class);
            startActivity(gotolists);
        }

        return super.onOptionsItemSelected(item);
    }
}
