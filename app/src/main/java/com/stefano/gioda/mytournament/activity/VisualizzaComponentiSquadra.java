package com.stefano.gioda.mytournament.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Giocatore;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.util.ArrayList;

public class VisualizzaComponentiSquadra extends AppCompatActivity {

    private Squadra squadra;

    private TextView nomeSquadra;
    private ArrayList<TextView> nomeGiocatori;
    private ArrayList<TextView> cognomeGiocatori;
    private ArrayList<TextView> dataNascitaGiocatori;
    private ArrayList<TextView> telefonoGiocatori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_componenti_squadra);

        squadra = (Squadra) getIntent().getSerializableExtra("Squadra");

        nomeGiocatori = new ArrayList<>();
        cognomeGiocatori = new ArrayList<>();
        dataNascitaGiocatori = new ArrayList<>();
        telefonoGiocatori = new ArrayList<>();

        nomeSquadra = (TextView) findViewById(R.id.visualizza_componenti_nome_squadra);

        nomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nome1));
        cognomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_cognome1));
        dataNascitaGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nascita1));
        telefonoGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_telefono1));

        nomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nome2));
        cognomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_cognome2));
        dataNascitaGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nascita2));
        telefonoGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_telefono2));

        nomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nome3));
        cognomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_cognome3));
        dataNascitaGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nascita3));
        telefonoGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_telefono3));

        nomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nome4));
        cognomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_cognome4));
        dataNascitaGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nascita4));
        telefonoGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_telefono4));

        nomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nome5));
        cognomeGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_cognome5));
        dataNascitaGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_nascita5));
        telefonoGiocatori.add((TextView) findViewById(R.id.visualizza_componenti_telefono5));

        nomeSquadra.setText(squadra.getNome());

        ArrayList<Giocatore> giocatori = squadra.getGiocatori();

        for (int i=0;i<giocatori.size();i++)
        {
            nomeGiocatori.get(i).setText(giocatori.get(i).getNome());
            cognomeGiocatori.get(i).setText(giocatori.get(i).getCognome());
            dataNascitaGiocatori.get(i).setText(giocatori.get(i).getDataNascita());
            telefonoGiocatori.get(i).setText(giocatori.get(i).getTelefono());
        }
    }
}
