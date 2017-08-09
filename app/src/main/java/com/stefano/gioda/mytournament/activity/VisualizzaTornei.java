package com.stefano.gioda.mytournament.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.stefano.gioda.mytournament.R;

public class VisualizzaTornei extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_tornei);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
