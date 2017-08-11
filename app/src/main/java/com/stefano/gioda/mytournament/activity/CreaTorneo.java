package com.stefano.gioda.mytournament.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.util.ArrayList;

public class CreaTorneo extends AppCompatActivity {

    private ArrayList<Squadra> squadreRegistrate;
    private Data holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_torneo);

        holder = Data.getInstance();

        squadreRegistrate = holder.getSquadre();

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        LinearLayout layoutSpinner = (LinearLayout) inflater.inflate(R.layout.spinner_inflater,null,false);
        LinearLayout layoutSpinner2 = (LinearLayout) inflater.inflate(R.layout.spinner_inflater,null,false);

        ((Spinner)layoutSpinner.findViewById(R.id.spinner)).setAdapter(new SquadraSpinnerAdapter(this,R.layout.spinner_row,squadreRegistrate));

        LinearLayout scroll = (LinearLayout) findViewById(R.id.layout_spinner);
        scroll.addView(layoutSpinner);
        scroll.addView(layoutSpinner2);
    }
}
