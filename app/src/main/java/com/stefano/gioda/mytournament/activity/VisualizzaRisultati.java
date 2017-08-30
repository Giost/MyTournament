package com.stefano.gioda.mytournament.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.TorneoEliminazione;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

public class VisualizzaRisultati extends AppCompatActivity {
    private boolean eliminazione;
    private TorneoItaliana torneoIt;
    private TorneoEliminazione torneoEl;
    private Data holder;
    private RecyclerView recycler;
    private RisultatiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_risultati);

        int indice = getIntent().getIntExtra("INDICE",0);
        eliminazione = getIntent().getBooleanExtra("ELIMINAZIONE",false);

        System.out.println("indice:"+indice+"eliminazione:"+eliminazione);
        holder = Data.getInstance();

        if (eliminazione)
        {
            torneoEl = holder.getTorneiEliminazione().get(indice);
            adapter = new RisultatiAdapter(torneoEl);
        }
        else
        {
            torneoIt = holder.getTorneiItaliana().get(indice);
            adapter = new RisultatiAdapter(torneoIt);
        }

        recycler = (RecyclerView) findViewById(R.id.risultati_recycler);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
