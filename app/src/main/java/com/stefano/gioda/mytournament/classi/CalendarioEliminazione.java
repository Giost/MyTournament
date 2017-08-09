package com.stefano.gioda.mytournament.classi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Stefano Gioda on 8/9/17.
 *
 * Classe che rappresenta il calendario di un torneo a eliminazione
 */
public class CalendarioEliminazione implements Calendario
{
    private int numeroSquadre;
    private ArrayList<ArrayList<Integer>> partite;
    private ArrayList<ArrayList<Integer>> risultati;

    /**
     * Costruttore
     * @param numeroSquadre, deve essere pari e >=2
     */
    public CalendarioEliminazione(int numeroSquadre)
    {
        this.numeroSquadre=numeroSquadre;
        this.partite = new ArrayList<ArrayList<Integer>>();
        this.risultati = new ArrayList<ArrayList<Integer>>();
        sorteggio(numeroSquadre);

        for (int i = 0; i<numeroSquadre; i++)
        {
            risultati.add(new ArrayList<Integer>(Arrays.asList(-1,-1)));
        }
    }

    /**
     * Crea gli incontri tra le squadre casualmente
     * @param numeroSquadre, deve essere pari e >=2
     */
    private void sorteggio(int numeroSquadre)
    {
        ArrayList<Integer> copySquadre = new ArrayList<Integer>();
        int random;
        int aux;
        int numSquadMezzo=copySquadre.size()/2;
        Random generator = new Random();

        for (int i=0;i<numeroSquadre;i++)
        {
            copySquadre.add(i);
        }

        for (int i=0;i<numSquadMezzo;i++)
        {
            random = generator.nextInt(copySquadre.size());
            aux=copySquadre.remove(random);

            random = generator.nextInt(copySquadre.size());
            partite.add(new ArrayList<>(Arrays.asList(aux,copySquadre.remove(random))));
        }
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCalendario() {
        return clone(partite);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCalendario(int index) {
        ArrayList<ArrayList<Integer>> giornata = new ArrayList<ArrayList<Integer>>();
        int indiceInizio=0;
        int durata=numeroSquadre/2;

        for (int i=0,m=numeroSquadre/2;i<index;i++,m=m/2)
        {
            indiceInizio+=m;
            durata=durata/2;
        }

        for (int i=0,j=indiceInizio;i<durata;i++,j++)
        {
            giornata.add(new ArrayList<>(partite.get(j)));
        }
        return giornata;
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

    private ArrayList<ArrayList<Integer>> clone (ArrayList<ArrayList<Integer>> list)
    {
        ArrayList<ArrayList<Integer>> copyList = new ArrayList<ArrayList<Integer>>();

        for (int i=0;i<list.size();i++)
        {
            copyList.add(new ArrayList<Integer>(list.get(i)));
        }
        return copyList;
    }
}
