package com.example.filmapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.filmapp.TMDBCommands.opvragen.TmdbKrijgLijst;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener, TmdbKrijgLijst.Listner {
    private static final String TAG = ListActivity.class.getSimpleName();

    private LinearLayout mRView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lijsten_pagina);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRView = findViewById(R.id.lijsten_view);


    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void processResult(ArrayList<ExtraInfo> movieLists) {

    }


}