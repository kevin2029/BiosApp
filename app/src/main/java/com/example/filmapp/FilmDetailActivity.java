package com.example.filmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.filmapp.Domain.Film;
import com.squareup.picasso.Picasso;


public class FilmDetailActivity extends AppCompatActivity{
    private TextView mTitle;
    private TextView mGenre;
    private ImageView mImage;
    private TextView mDiscription;
    private TextView mReleaseDate;
    private TextView mRuntime;
    private TextView mReview;
    private Film filmExtra;
    private Button mShareButton;

    public static final String TAG = FilmDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        mTitle = findViewById(R.id.film_detail_title);
        mGenre = findViewById(R.id.film_detail_genre);
        mImage = findViewById(R.id.film_item_imageview);
        mDiscription = findViewById(R.id.film_detail_discription);
        mReleaseDate = findViewById(R.id.film_detail_releasedate);
        mRuntime = findViewById(R.id.film_detail_runtime);
        mReview = findViewById(R.id.film_detail_review);
        mShareButton = findViewById(R.id.shareButton);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(FilmAdapter.FILM)) {
                filmExtra = (Film) intent.getSerializableExtra(FilmAdapter.FILM);

                mTitle.setText(filmExtra.getmTitle());
                mDiscription.setText(filmExtra.getmDiscription());
                mReleaseDate.setText(filmExtra.getmReleaseDate());
                //Picasso.get().load(filmExtra.getmPicture()).into(mImage);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.share_button ){
                Log.i(TAG, "In onOptionsItemSelected door: " + item.toString());

                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "body";
                String shareSubject = filmExtra.toString();

                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareSubject);
                startActivity(Intent.createChooser(myIntent, "Share"));
        }
        return super.onOptionsItemSelected(item);
    }
}
