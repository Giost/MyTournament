package com.stefano.gioda.mytournament.classi;

import java.util.ArrayList;

/**
 * Created by Stefano Gioda on 8/8/17.
 *
 * Classe che rappresenta la classifica di un torneo
 */

public class Classifica
{
    private ArrayList<Integer> punti;
    private ArrayList<Integer> goalFatti;
    private ArrayList<Integer> goalSubiti;

    public Classifica(int numeroSquadre)
    {
        punti = new ArrayList<Integer>();
        goalFatti = new ArrayList<Integer>();
        goalSubiti = new ArrayList<Integer>();

        for (int i = 0; i<numeroSquadre; i++)
        {
            punti.add(0);
            goalFatti.add(0);
            goalSubiti.add(0);
        }
    }


    public ArrayList<Integer> getPunti() {
        return new ArrayList<>(punti);
    }

    public ArrayList<Integer> getGoalFatti() {
        return new ArrayList<>(goalFatti);
    }

    public ArrayList<Integer> getGoalSubiti() {
        return new ArrayList<>(goalSubiti);
    }

    /**
     * Calcola e ritorna la differenza reti di ogni squadra
     * @return differenza reti di ogni squadra
     */
    public ArrayList<Integer> getDifferenzaReti()
    {
        ArrayList<Integer> differenzaReti = new ArrayList<Integer>();

        for (int i=0;i<goalFatti.size();i++)
        {
            differenzaReti.add(goalFatti.get(i)-goalSubiti.get(i));
        }
        return differenzaReti;
    }

    /**
     * Aggiorna la classifica con il risultato passato
     * @param index1 indice della lista di squadre del torneo, >=0 e <numeroSquadre
     * @param index2 indice della lista di squadre del torneo, >=0 e <numeroSquadre
     * @param goal1 goal fatti dalla prima squadra, >=0
     * @param goal2 goal fatti dalla seconda squadra, >=0
     */
    public void updateRisultato(int index1,int index2,int goal1,int goal2)
    {
        if (goal1==goal2)
        {
            punti.add(index1,punti.remove(index1)+1);
            punti.add(index2,punti.remove(index2)+1);
        }
        else if (goal1>goal2)
        {
            punti.add(index1,punti.remove(index1)+3);
        }
        else
        {
            punti.add(index2,punti.remove(index2)+3);
        }
        goalFatti.add(index1,goalFatti.remove(index1)+goal1);
        goalFatti.add(index2,goalFatti.remove(index2)+goal2);

        goalSubiti.add(index1,goalSubiti.remove(index1)+goal2);
        goalSubiti.add(index2,goalSubiti.remove(index2)+goal1);
    }
}
