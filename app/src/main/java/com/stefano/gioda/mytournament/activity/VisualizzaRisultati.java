package com.stefano.gioda.mytournament.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

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
    private int indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_risultati);

        indice = getIntent().getIntExtra("INDICE",0);
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

    @Override
    protected void onResume() {
        super.onResume();

        if (eliminazione)
        {
            torneoEl = holder.getTorneiEliminazione().get(indice);
            adapter.newDataSet(torneoEl);
        }
        else
        {
            torneoIt = holder.getTorneiItaliana().get(indice);
            adapter.newDataSet(torneoIt);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (!eliminazione)
        {
            getMenuInflater().inflate(R.menu.menu_classifica, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent i = new Intent(this, VisualizzaClassifica.class);
        i.putExtra("INDICE", holder.getTorneiItaliana().indexOf(torneoIt));
        startActivity(i);
        return true;
    }
}
