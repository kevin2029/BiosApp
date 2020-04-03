package com.example.filmapp.Gui.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.filmapp.Domain.ExtraInfo;
import com.example.filmapp.Domain.Film;
import com.example.filmapp.Gui.Adapter.FilmAdapter;
import com.example.filmapp.R;
import com.example.filmapp.TMDBCommands.opvragen.TmdbKrijgLijst;
import com.example.filmapp.TMDBCommands.toevoegen.TmdbVoegFilmToeAanLijst;
import com.example.filmapp.utils.GetListIdTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FilmDetailActivity extends AppCompatActivity implements View.OnClickListener, GetListIdTask.FilmListListener, TmdbKrijgLijst.Listner {
    public static final String TAG = FilmDetailActivity.class.getSimpleName();
    private TextView mTitle;
    private ImageView mImage;
    private TextView mDiscription;
    private TextView mReleaseDate;
    private TextView mReview;
    private Film filmExtra;
    private List<Integer> ids;
    private Button mAddto;
    private ListView mLijsten;

    private Dialog PopUpExtra;
    private ArrayList<String> mAddtoList;
    private ArrayList<ExtraInfo> mAddToExtraInfo;
    private TextView mPopupClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        mTitle = findViewById(R.id.film_detail_title);
        mImage = findViewById(R.id.film_detail_image);
        mDiscription = findViewById(R.id.film_detail_discription);
        mReleaseDate = findViewById(R.id.film_detail_releasedate);
        mReview = findViewById(R.id.film_detail_review);
        mAddto = findViewById(R.id.film_detail_addtolist);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(FilmAdapter.FILM))
                filmExtra = (Film) intent.getSerializableExtra(FilmAdapter.FILM);

            mTitle.setText(filmExtra.getmTitle());
            mDiscription.setText(filmExtra.getmDiscription());
            mReleaseDate.setText(filmExtra.getmReleaseDate());
            mReview.setText("Rating :" + " " + filmExtra.getmRating() + "/" + "10");
            Picasso.get().load(filmExtra.getmPicture()).into(mImage);
            mReview = findViewById(R.id.film_detail_review);
            final Button mRateButton = (Button) findViewById(R.id.film_detail_ratebutton);
            final RatingBar mRatingBar = (RatingBar) findViewById(R.id.film_detail_ratebar);
            final TextView mNewRating = (TextView) findViewById(R.id.film_detail_ratetext);
            final TextView mOldRating = (TextView) findViewById(R.id.film_detail_newrate);

            mRateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mOldRating.setText(filmExtra.getmVoteAverage() + " " + filmExtra.getmVoteCount());
                    mNewRating.setText("Jouw rating is: " + mRatingBar.getRating());
                    int ratingGiven = mRatingBar.getNumStars() * 2;
                    double nonAverage = filmExtra.getmVoteAverage() * filmExtra.getmVoteCount();
                    filmExtra.setmVoteCount();                                                      //+1 rating
                    double newRating = (nonAverage + ratingGiven) / filmExtra.getmVoteCount();
                    filmExtra.setMVoteAverage(newRating);
                    mNewRating.setText(filmExtra.getmVoteCount() + " " + filmExtra.getmVoteAverage());
                    mRateButton.setClickable(false);

                }

            });

            mAddto.setOnClickListener(this);

            PopUpExtra = new Dialog(this);
            mAddtoList = new ArrayList<>();


        }
    }

    @Override
    public void processResultInts(List<Integer> strings) {
        ids = strings;
        System.out.println(strings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share_button) {
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


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void showpopup(View v) {
        PopUpExtra.setContentView(R.layout.voeg_film_toe_popup);
        mLijsten = PopUpExtra.findViewById(R.id.Kies_Lijst);
        mPopupClose = (TextView) PopUpExtra.findViewById(R.id.tv_close_popup);

        TmdbKrijgLijst krijglijst = new TmdbKrijgLijst(this);
        krijglijst.execute();

        mLijsten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TmdbVoegFilmToeAanLijst addTo = new TmdbVoegFilmToeAanLijst();
                ExtraInfo extraInfo = mAddToExtraInfo.get(position);
                addTo.execute(extraInfo.getmLijstId(), Integer.toString(filmExtra.getmId()));
            }
        });

        mPopupClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpExtra.dismiss();
            }
        });

        PopUpExtra.show();
    }

    public ArrayAdapter addItemsToListPopupListview(ArrayList<ExtraInfo> listItems) {
        for (ExtraInfo name : listItems) {
            mAddtoList.add(name.getmLijstnaam());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.film_detail_toevoegen_aan_lijst, mAddtoList);
        return arrayAdapter;
    }

    @Override
    public void onClick(View v) {
        showpopup(v);
    }

    @Override
    public void processResult(ArrayList<ExtraInfo> extraInfos) {
        mAddToExtraInfo = new ArrayList<>();
        mAddToExtraInfo = extraInfos;
        mLijsten.setAdapter(addItemsToListPopupListview(mAddToExtraInfo));
    }
}
