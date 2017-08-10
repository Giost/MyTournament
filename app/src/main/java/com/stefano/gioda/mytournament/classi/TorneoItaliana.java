package com.stefano.gioda.mytournament.classi;

import java.util.ArrayList;

/**
 * Created by Stefano Gioda on 8/9/17.
 *
 * Classe che rappresenta un torneo all'italiana
 */
public class TorneoItaliana implements Torneo
{
    private String nome;
    private ArrayList<Squadra> squadre;
    private Classifica classifica;
    private CalendarioGirone calendario;

    public TorneoItaliana(String nome, ArrayList<Squadra> squadre)
    {
        this.nome=nome;
        this.squadre=new ArrayList<Squadra>(squadre);
        classifica = new Classifica(squadre.size());
        calendario = new CalendarioGirone(squadre.size());
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
    public void updateRisultato(int index1, int index2, int goal1, int goal2)
    {
        calendario.updateRisultato(index1,index2,goal1,goal2);
        classifica.updateRisultato(index1,index2,goal1,goal2);
    }
    
    public ArrayList<Integer> getPunti()
    {
    	return classifica.getPunti();
    }
    
    public ArrayList<Integer> getGoalFatti()
    {
    	return classifica.getGoalFatti();
    }
    
    public ArrayList<Integer> getGoalSubiti()
    {
    	return classifica.getGoalSubiti();
    }
    
    public ArrayList<Integer> getDifferenzaReti()
    {
    	return classifica.getDifferenzaReti();
    }
}
