package com.example.filmapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.filmapp.TMDBCommands.toevoegen.TmdbMaakLijstAan;


import java.util.List;

public class CreateListActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Title;
    private Button AddList;
    private EditText Beschrijving;
    private RadioGroup groep;
    private RadioButton nl;
    private RadioButton en;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_list);
        Title = findViewById(R.id.give_name_list);
        Beschrijving = findViewById(R.id.beschrijving_lijst);
        AddList = findViewById(R.id.button_add_list);
        groep = findViewById(R.id.Knop_groep);
        nl = findViewById(R.id.knop_nl);
        en = findViewById(R.id.knop_en);

        AddList.setOnClickListener(this);

    }


    @Override
    public void onClick(View v){
        TmdbMaakLijstAan Task = new TmdbMaakLijstAan();
        String LijstTaal = "en";
        String Lijsttitel= Title.getText().toString();
        String LijstBeschrijving = Beschrijving.getText().toString();


        Task.execute(Lijsttitel, LijstBeschrijving, LijstTaal);



    }




}


