package com.stefano.gioda.mytournament.classi;

import java.util.ArrayList;

/**
 * Created by Stefano Gioda on 8/9/17.
 *
 * Interfaccia di un torneo
 */
public interface Torneo
{
    public String getNome();
    public ArrayList<Squadra> getSquadre();
    public ArrayList<Giocatore> getGiocatori();
    public ArrayList<ArrayList<Integer>> getCalendario();
    public ArrayList<ArrayList<Integer>> getCalendario(int index);
    public ArrayList<String> getRisultati();
    public ArrayList<String> getRisultati(int index);
    public void updateRisultato(int index1,int index2,int goal1,int goal2);
    public boolean isFinito();
    public int getNumeroFasi();
}
