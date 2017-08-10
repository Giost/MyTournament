package com.stefano.gioda.mytournament.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Giocatore;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class CreaSquadra extends AppCompatActivity {

    private EditText nomeSquadra;

    private ArrayList<EditText> nomeGiocatori;
    private ArrayList<EditText> cognomeGiocatori;
    private ArrayList<EditText> dataNascitaGiocatori;
    private ArrayList<EditText> telefonoGiocatori;
    private ArrayList<DatePickerDialog> datePickerDialog;
    private AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_squadra);

        nomeGiocatori = new ArrayList<>();
        cognomeGiocatori = new ArrayList<>();
        dataNascitaGiocatori = new ArrayList<>();
        telefonoGiocatori = new ArrayList<>();
        dialogBuilder = new AlertDialog.Builder(this);

        nomeSquadra = (EditText) findViewById(R.id.nome_squadra);

        nomeGiocatori.add((EditText) findViewById(R.id.nome_giocatore1));
        cognomeGiocatori.add((EditText) findViewById(R.id.cognome_giocatore1));
        dataNascitaGiocatori.add((EditText) findViewById(R.id.nascita_giocatore1));
        telefonoGiocatori.add((EditText) findViewById(R.id.telefono_giocatore1));

        nomeGiocatori.add((EditText) findViewById(R.id.nome_giocatore2));
        cognomeGiocatori.add((EditText) findViewById(R.id.cognome_giocatore2));
        dataNascitaGiocatori.add((EditText) findViewById(R.id.nascita_giocatore2));
        telefonoGiocatori.add((EditText) findViewById(R.id.telefono_giocatore2));

        nomeGiocatori.add((EditText) findViewById(R.id.nome_giocatore3));
        cognomeGiocatori.add((EditText) findViewById(R.id.cognome_giocatore3));
        dataNascitaGiocatori.add((EditText) findViewById(R.id.nascita_giocatore3));
        telefonoGiocatori.add((EditText) findViewById(R.id.telefono_giocatore3));

        nomeGiocatori.add((EditText) findViewById(R.id.nome_giocatore4));
        cognomeGiocatori.add((EditText) findViewById(R.id.cognome_giocatore4));
        dataNascitaGiocatori.add((EditText) findViewById(R.id.nascita_giocatore4));
        telefonoGiocatori.add((EditText) findViewById(R.id.telefono_giocatore4));

        nomeGiocatori.add((EditText) findViewById(R.id.nome_giocatore5));
        cognomeGiocatori.add((EditText) findViewById(R.id.cognome_giocatore5));
        dataNascitaGiocatori.add((EditText) findViewById(R.id.nascita_giocatore5));
        telefonoGiocatori.add((EditText) findViewById(R.id.telefono_giocatore5));

        Button creaNuovo = (Button) findViewById(R.id.crea_nuova_squadra);

        creaNuovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifica();
            }
        });

        Button annulla = (Button) findViewById(R.id.annulla_nuova_squadra);

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new ArrayList<>();
        for (int i=0;i<dataNascitaGiocatori.size();i++)
        {
            final int index = i;
            datePickerDialog.add(new DatePickerDialog(this, new android.app.DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    dataNascitaGiocatori.get(index).setText(dateFormatter.format(newDate.getTime()));
                }

            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)));

            dataNascitaGiocatori.get(index).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePickerDialog.get(index).show();
                }
            });
            dataNascitaGiocatori.get(index).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v,boolean isFocused) {
                    if (isFocused) {
                        datePickerDialog.get(index).show();
                    }
                }
            });
        }
    }

    private void modifica()
    {
        boolean mancante=false;
        ArrayList<Giocatore> giocatori;

        if(nomeSquadra.getText().toString().isEmpty())
        {
            nomeSquadra.setError("Campo necessario");
            mancante=true;
        }

        for (int i=0;i<nomeGiocatori.size();i++)
        {
            if (nomeGiocatori.get(i).getText().toString().isEmpty())
            {
                nomeGiocatori.get(i).setError("Campo necessario");
                mancante=true;
            }
        }

        for (int i=0;i<cognomeGiocatori.size();i++)
        {
            if (cognomeGiocatori.get(i).getText().toString().isEmpty())
            {
                cognomeGiocatori.get(i).setError("Campo necessario");
                mancante=true;
            }
        }

        for (int i=0;i<dataNascitaGiocatori.size();i++)
        {
            if (dataNascitaGiocatori.get(i).getText().toString().isEmpty())
            {
                dataNascitaGiocatori.get(i).setError("Campo necessario");
                mancante=true;
            }
        }

        for (int i=0;i<telefonoGiocatori.size();i++)
        {
            if (telefonoGiocatori.get(i).getText().toString().isEmpty())
            {
                telefonoGiocatori.get(i).setError("Campo necessario");
                mancante=true;
            }
        }

        if (!mancante)
        {
            giocatori = new ArrayList<>();

            for (int i=0;i<5;i++)
            {
                giocatori.add(new Giocatore(nomeGiocatori.get(i).getText().toString(),cognomeGiocatori.get(i).getText().toString(),dataNascitaGiocatori.get(i).getText().toString(),telefonoGiocatori.get(i).getText().toString()));
            }
            Data.getInstance().addSquadra(new Squadra(nomeSquadra.getText().toString(),giocatori));

            dialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog dialog = dialogBuilder.create();
            dialog.setTitle("Successo!");
            dialog.setMessage("Operazione avvenuto con successo");
            dialog.show();

        }
    }
}
