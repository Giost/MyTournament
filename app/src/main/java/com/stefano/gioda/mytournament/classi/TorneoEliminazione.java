package com.stefano.gioda.mytournament.classi;

import java.util.ArrayList;

/**
 * Created by mint on 8/9/17.
 */

public class TorneoEliminazione implements Torneo
{
    private String nome;
    private ArrayList<Squadra> squadre;
    //private CalendarioEliminazione calendario;

    @Override
    public String getNome() {
        return null;
    }

    @Override
    public ArrayList<Squadra> getSquadre() {
        return null;
    }

    @Override
    public ArrayList<Giocatore> getGiocatori() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCalendario() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCalendario(int index) {
        return null;
    }

    @Override
    public ArrayList<String> getRisultati() {
        return null;
    }

    @Override
    public ArrayList<String> getRisultati(int index) {
        return null;
    }

    @Override
    public void updateRisultato(int index1, int index2, int goal1, int goal2) {

    }
}
