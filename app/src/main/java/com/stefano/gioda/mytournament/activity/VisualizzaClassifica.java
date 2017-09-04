package com.stefano.gioda.mytournament.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

public class VisualizzaClassifica extends AppCompatActivity {
    private TorneoItaliana torneo;
    private Data holder;
    private RecyclerView recycler;
    private ClassificaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_classifica);

        holder = Data.getInstance();

        torneo = holder.getTorneiItaliana().get(getIntent().getIntExtra("INDICE",0));

        adapter = new ClassificaAdapter(torneo);

        recycler = (RecyclerView) findViewById(R.id.classifica_recycler);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
