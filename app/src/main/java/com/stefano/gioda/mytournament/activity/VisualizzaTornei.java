package com.stefano.gioda.mytournament.activity;

import android.content.Intent;
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
import java.util.Collections;
import java.util.Comparator;

public class VisualizzaTornei extends AppCompatActivity {

    private ArrayList<Torneo> tornei;
    private LinearLayout fields;
    private LinearLayout empty;
    private RecyclerView recycler;
    private FloatingActionButton fabAdd;
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
        Collections.sort(tornei,new Comparator<Torneo>() {
            @Override
            public int compare(Torneo s1, Torneo s2) {
                return s1.getNome().compareToIgnoreCase(s2.getNome());
            }
        });

        fields = (LinearLayout) findViewById(R.id.torneo_fields);
        empty = (LinearLayout) findViewById(R.id.torneo_empty);
        recycler = (RecyclerView) findViewById(R.id.tornei_recycler);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add_torneo);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTorneo = new Intent(view.getContext(), CreaTorneo.class);
                view.getContext().startActivity(addTorneo);
            }
        });

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
    protected void onResume() {
        super.onResume();
        ArrayList<Torneo> newList = new ArrayList<>();
        newList.addAll(holder.getTorneiItaliana());
        newList.addAll(holder.getTorneiEliminazione());
        Collections.sort(newList,new Comparator<Torneo>() {
            @Override
            public int compare(Torneo s1, Torneo s2) {
                return s1.getNome().compareToIgnoreCase(s2.getNome());
            }
        });
        adapter.newDataSet(newList);
    }
}
