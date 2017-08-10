package com.stefano.gioda.mytournament.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Giocatore;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        FrameLayout visualTornei = (FrameLayout) findViewById(R.id.visualizza_tornei);
        FrameLayout creaTorneo = (FrameLayout) findViewById(R.id.crea_torneo);
        FrameLayout visualSquadre = (FrameLayout) findViewById(R.id.visualizza_squadre);

        visualTornei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Intent visualTornei = new Intent(getApplicationContext(),VisualizzaTornei.class);
                    startActivity(visualTornei);
                }
            }
        );

        visualSquadre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent visualSquadre = new Intent(getApplicationContext(),VisualizzaSquadre.class);
                startActivity(visualSquadre);
            }
          }
        );
    }
}
