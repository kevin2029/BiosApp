package com.example.filmapp;


import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapp.Domain.Film;
import com.squareup.picasso.Picasso;


import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder>{
    public static final String FILM = "film";
    private static String TAG = FilmAdapter.class.getSimpleName();
    private List<Film> films;

    public FilmAdapter(List<Film> films) {
        Log.i(TAG, "In Constructor: FilmAdapter");
        this.films = films;
    }

    @NonNull
    @Override
    public FilmAdapter.FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "In onCreateViewHolder");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View FilmItem = inflater.inflate(R.layout.film_item_vertical, parent, false);
        FilmAdapter.FilmViewHolder viewHolder = new FilmViewHolder(FilmItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Log.i(TAG, "In onBindViewHolder");
        Film film = films.get(position);
        holder.title.setText(film.getmTitle());
        holder.releaseDate.setText(film.getmReleaseDate());
        Picasso.get().load(film.getmPicture()).into(holder.image);

    }

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    public List<Film> getFilms() {
        return films;
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "In getItemCount");
        return films == null ? 0 : films.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView releaseDate;
        private ImageView image;



        public FilmViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.film_item_title);
            image = itemView.findViewById(R.id.film_item_imageview);
            releaseDate = itemView.findViewById(R.id.film_item_releaseDate);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, "In onclick van FilmViewHolder, positie: " + getAdapterPosition());
            int adapterPosition = getAdapterPosition();
            Film film = films.get(adapterPosition);

            Intent intent = new Intent(view.getContext(),FilmDetailActivity.class);
            intent.putExtra("film",film);
            view.getContext().startActivity(intent);

        }
    }
}
