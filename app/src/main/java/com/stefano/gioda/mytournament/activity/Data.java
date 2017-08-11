package com.stefano.gioda.mytournament.activity;

import android.content.Context;
import android.content.res.Resources;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Squadra;
import com.stefano.gioda.mytournament.classi.TorneoEliminazione;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
        salva();
    }
}
