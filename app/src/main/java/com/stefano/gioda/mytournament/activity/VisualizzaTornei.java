package com.stefano.gioda.mytournament.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.stefano.gioda.mytournament.classi.Torneo;

import java.util.ArrayList;

public class VisualizzaTornei extends AppCompatActivity {

    private ArrayList<Torneo> tornei;
    private LinearLayout fields;
    private LinearLayout empty;
    private RecyclerView recycler;
    private TorneoAdapter adapter;
    private Data holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_tornei);

        holder = Data.getInstance();

        tornei = new ArrayList<>();
        tornei.addAll(holder.getTorneiItaliana());
        tornei.addAll(holder.getTorneiEliminazione());

        fields = (LinearLayout) findViewById(R.id.torneo_fields);
        empty = (LinearLayout) findViewById(R.id.torneo_empty);
        recycler = (RecyclerView) findViewById(R.id.tornei_recycler);

        if (tornei.isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
            fields.setVisibility(View.GONE);
        }

        adapter = new TorneoAdapter(tornei);

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
        ArrayList<Torneo> newList = new ArrayList<>();
        newList.addAll(holder.getTorneiItaliana());
        newList.addAll(holder.getTorneiEliminazione());
        if (!tornei.equals(newList))
        {
            tornei = newList;
            recycler.scrollToPosition(0);
            adapter.notifyDataSetChanged();
        }
    }
}
