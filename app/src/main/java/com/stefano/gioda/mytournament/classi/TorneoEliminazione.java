package com.stefano.gioda.mytournament.classi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stefano Gioda on 8/9/17.
 *
 * Classe che rappresenta un torneo a eliminazione diretta
 */
public class TorneoEliminazione implements Torneo, Serializable
{
    private String nome;
    private ArrayList<Squadra> squadre;
    private CalendarioEliminazione calendario;
    
    public TorneoEliminazione(String nome,ArrayList<Squadra> squadre)
    {
    	this.nome=nome;
        this.squadre=new ArrayList<Squadra>(squadre);
        calendario = new CalendarioEliminazione(squadre.size());
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public ArrayList<Squadra> getSquadre() {
        return squadre;
    }

    @Override
    public ArrayList<Giocatore> getGiocatori() {
    	ArrayList<Giocatore> giocatori = new ArrayList<>();
        for (Squadra squadra : squadre)
        {
            giocatori.addAll(squadra.getGiocatori());
        }
        return giocatori;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCalendario() {
    	return calendario.getCalendario();
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCalendario(int index) {
    	return calendario.getCalendario(index);
    }

    @Override
    public ArrayList<String> getRisultati() {
    	return calendario.getRisultati();
    }

    @Override
    public ArrayList<String> getRisultati(int index) {
    	return calendario.getRisultati(index);
    }

    @Override
    public void updateRisultato(int index1, int index2, int goal1, int goal2) {
    	calendario.updateRisultato(index1, index2, goal1, goal2);
    }

    @Override
    public boolean isFinito()
    {
        boolean finito = true;

        for (String risultati : calendario.getRisultati())
        {
            if (risultati.equals("-1 - -1"))
            {
                finito=false;
                break;
            }
        }

        return finito;
    }

    @Override
    public int getNumeroFasi() {
        return calendario.getNumeroFasi();
    }
}
