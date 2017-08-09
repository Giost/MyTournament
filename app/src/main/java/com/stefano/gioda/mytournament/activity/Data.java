package com.stefano.gioda.mytournament.activity;

import com.stefano.gioda.mytournament.classi.Squadra;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

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
    //private ArrayList<TorneoEliminazione> torneiEliminazione;

    public Data()
    {
        //DESERIALIZZAZIONE
    }

    public ArrayList<Squadra> getSquadre()
    {
        return squadreRegistrate;
    }

    public ArrayList<TorneoItaliana> getTorneiItaliana()
    {
        return torneiItaliana;
    }

    private static final Data holder = new Data();

    public static Data getInstance()
    {
        return holder;
    }
}
