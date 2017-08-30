package com.stefano.gioda.mytournament.activity;

import android.content.Context;

import com.stefano.gioda.mytournament.classi.Squadra;
import com.stefano.gioda.mytournament.classi.TorneoEliminazione;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Stefano Gioda on 8/9/17.
 *
 * Classe che gestice i dati dell'applicazione, compreso la scrittura e la lettura su file
 */
public class Data
{
    private ArrayList<Squadra> squadreRegistrate;
    private ArrayList<TorneoItaliana> torneiItaliana;
    private ArrayList<TorneoEliminazione> torneiEliminazione;

    public Data()
    {
        deserializzazione();
    }

    private void deserializzazione()
    {
        try
        {
            FileInputStream inputStream = App.getContext().openFileInput("data.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            squadreRegistrate = (ArrayList<Squadra>) objectInputStream.readObject();
            torneiItaliana = (ArrayList<TorneoItaliana>) objectInputStream.readObject();
            torneiEliminazione = (ArrayList<TorneoEliminazione>) objectInputStream.readObject();

            objectInputStream.close();
            inputStream.close();
        }
        catch (Exception ex)
        {
            squadreRegistrate = new ArrayList<>();
            torneiItaliana = new ArrayList<>();
            torneiEliminazione = new ArrayList<>();
        }
    }

    public void salva()
    {
        try
        {
            FileOutputStream outputStream = App.getContext().openFileOutput("data.ser", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(squadreRegistrate);
            objectOutputStream.writeObject(torneiItaliana);
            objectOutputStream.writeObject(torneiEliminazione);

            objectOutputStream.flush();
            objectOutputStream.close();
            outputStream.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public ArrayList<Squadra> getSquadre()
    {
        return squadreRegistrate;
    }

    public ArrayList<TorneoItaliana> getTorneiItaliana()
    {
        return torneiItaliana;
    }

    public ArrayList<TorneoEliminazione> getTorneiEliminazione()
    {
        return torneiEliminazione;
    }

    private static final Data holder = new Data();

    public static Data getInstance()
    {
        return holder;
    }

    public void addSquadra (Squadra squadra)
    {
        squadreRegistrate.add(squadra);

        Collections.sort(squadreRegistrate,new Comparator<Squadra>() {
            @Override
            public int compare(Squadra s1, Squadra s2) {
                return s1.getNome().compareToIgnoreCase(s2.getNome());
            }
        });

        salva();
    }

    public void addTorneoItaliana (TorneoItaliana torneo)
    {
        torneiItaliana.add(torneo);

        Collections.sort(torneiItaliana,new Comparator<TorneoItaliana>() {
            @Override
            public int compare(TorneoItaliana s1, TorneoItaliana s2) {
                return s1.getNome().compareToIgnoreCase(s2.getNome());
            }
        });

        salva();
    }

    public void addTorneoEliminazione (TorneoEliminazione torneo)
    {
        torneiEliminazione.add(torneo);

        Collections.sort(torneiEliminazione,new Comparator<TorneoEliminazione>() {
            @Override
            public int compare(TorneoEliminazione s1, TorneoEliminazione s2) {
                return s1.getNome().compareToIgnoreCase(s2.getNome());
            }
        });

        salva();
    }
}
