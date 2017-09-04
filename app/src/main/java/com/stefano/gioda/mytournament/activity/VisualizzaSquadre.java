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

        squadre = new ArrayList<>(holder.getSquadre());

        fields = (LinearLayout) findViewById(R.id.squadre_fields);
        empty = (LinearLayout) findViewById(R.id.squadre_empty);
        recycler = (RecyclerView) findViewById(R.id.squadre_recycler);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add_squadra);

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
    protected void onResume() {
        super.onResume();
        adapter.newDataSet(new ArrayList<>(holder.getSquadre()));

        if (holder.getSquadre().isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
            fields.setVisibility(View.GONE);
        }
        else
        {
            empty.setVisibility(View.GONE);
            fields.setVisibility(View.VISIBLE);
        }
    }
}
