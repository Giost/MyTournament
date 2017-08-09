package com.stefano.gioda.mytournament.activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.util.ArrayList;

public class VisualizzaSquadre extends AppCompatActivity {

    private ArrayList<Squadra> squadre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_squadre);

        squadre = (ArrayList<Squadra>) getIntent().getSerializableExtra("Squadre");
        System.out.println(squadre);
        System.out.println(squadre.get(0).getNome());
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
