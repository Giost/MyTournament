package com.stefano.gioda.mytournament.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.util.ArrayList;

public class VisualizzaSquadre extends AppCompatActivity {

    private ArrayList<Squadra> squadre;
    private LinearLayout fields;
    private LinearLayout empty;
    private RecyclerView recycler;
    private FloatingActionButton fabAdd;
    private SquadraAdapter adapter;
    private Data holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_squadre);

        holder = Data.getInstance();

        squadre = holder.getSquadre();

        fields = (LinearLayout) findViewById(R.id.squadre_fields);
        empty = (LinearLayout) findViewById(R.id.squadre_empty);
        recycler = (RecyclerView) findViewById(R.id.squadre_recycler);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add_squadra);

        if (squadre.isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
            fields.setVisibility(View.GONE);
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent addSquadra = new Intent(getApplicationContext(),CreaSquadra.class);
                startActivity(addSquadra);
            }
        });

        adapter = new SquadraAdapter(squadre);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_ricerca, menu);

        MenuItem searchItem = menu.findItem(R.id.action_ricerca_semplice);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                simpleSearch(newText);
                return true;
            }
        });

        return true;
    }

    private void simpleSearch(String query) {
        /*
        ArrayList<Offerta> matchingElement = new ArrayList<>();

        if (!TextUtils.isEmpty(query)) {
            //query = query.toUpperCase();

            for (Offerta offerta : mSearchList) {

                String dataOfferta = "";
                if (offerta.getDataOfferta() != null && !TextUtils.isEmpty(offerta.getDataOfferta())) {
                    dataOfferta = offerta.getDataOfferta();
                }

                String versione = String.valueOf(offerta.getVersione());

                String presentata = String.valueOf(offerta.getAccettata());

                if (dataOfferta.contains(query) || versione.contains(query) || presentata.contains(query)) {
                    matchingElement.add(offerta);
                }
            }
            updateList(matchingElement);
        } else
            updateList(mSearchList);*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!squadre.equals(holder.getSquadre()))
        {
            squadre.clear();
            squadre.addAll(holder.getSquadre());
            recycler.scrollToPosition(0);
            adapter.notifyDataSetChanged();
        }
    }
}
