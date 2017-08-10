package com.stefano.gioda.mytournament.classi;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * Created by Stefano Gioda on 8/9/17.
 *
 * Classe che rappresenta il calendario di un torneo all'italiana
 */
public class CalendarioGirone implements Calendario
{
    private int numeroSquadre;
    private ArrayList<ArrayList<Integer>> partite;
    private ArrayList<ArrayList<Integer>> risultati;

    /**
     * Costruttore
     * @param numeroSquadre, deve essere pari e >=2
     */
    public CalendarioGirone(int numeroSquadre)
    {
        this.numeroSquadre=numeroSquadre;
        this.partite = new ArrayList<ArrayList<Integer>>();
        this.risultati = new ArrayList<ArrayList<Integer>>();
        sorteggio(numeroSquadre);

        for (int i = 0; i<getNumeroGiornate()*(numeroSquadre/2); i++)
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
        ArrayList<Integer> gruppoA = new ArrayList<Integer>();
        ArrayList<Integer> gruppoB = new ArrayList<Integer>();

        Random generator = new Random();
        int random;
        int numSquadMezzo;

        //##### Divisione squadre in due gruppi casuali #####
        for (int i=0;i<numeroSquadre;i++)
        {
            gruppoA.add(i);
        }
        numSquadMezzo=gruppoA.size()/2;
        for (int i=0;i<numSquadMezzo;i++)
        {
            //random = ThreadLocalRandom.current().nextInt(0, gruppoA.size());
            random = generator.nextInt(gruppoA.size());
            gruppoB.add(gruppoA.get(random));
            gruppoA.remove(random);
        }

        //##### Scontri #####
        for (int i=0;i<numeroSquadre-1;i++)
        {
            if (i%2==0)
            {
                for (int j=0;j<gruppoA.size();j++)
                {
                    partite.add(new ArrayList<>(Arrays.asList(gruppoA.get(j),gruppoB.get(j))));
                }
            }
            else
            {
                for (int j=0;j<gruppoA.size();j++)
                {
                    partite.add(new ArrayList<>(Arrays.asList(gruppoB.get(j),gruppoA.get(j))));
                }
            }
            gruppoA.add(1, gruppoB.remove(0));
            gruppoB.add(gruppoA.remove(gruppoA.size()-1));
        }
    }

    /**
     * Restituisce il calendario con tutte le partite del girone d'andata.
     * Il calendario Ã¨ un'array, in ogni riga sono presenti gli indici delle due squadre
     */
    @Override
    public ArrayList<ArrayList<Integer>> getCalendario() {
        return clone(partite);
    }

    /**
     * Restituisce il calendario con tutte le partite della giornata,
     * Il calendario Ã¨ un'array, in ogni riga sono presenti gli indici delle due squadre
     * @param index, numero della giornata (la prima giornata ha index=0), >=0 e <getNumeroGiornate()
     */
    @Override
    public ArrayList<ArrayList<Integer>> getCalendario(int index) {
        ArrayList<ArrayList<Integer>> giornata = new ArrayList<ArrayList<Integer>>();
        boolean ritorno = (index<numeroSquadre-1 ? false : true);
        index = index%(numeroSquadre-1);

        for (int i=0,j=(numeroSquadre/2)*index;i<numeroSquadre/2;i++,j++)
        {
            if (ritorno)
            {
                giornata.add(new ArrayList<Integer>(Arrays.asList(partite.get(j).get(1),partite.get(j).get(0))));
            }
            else
            {
                giornata.add(new ArrayList<>(partite.get(j)));
            }
        }
        return giornata;
    }

    /**
     * Restituisce tutti i risultati sotto forma di: "goalSquadraInCasa - goalSquadraFuoriCasa"
     * @return tutti i risultati delle partite
     */
    @Override
    public ArrayList<String> getRisultati() {
        ArrayList<String> risultatiString = new ArrayList<String>();
        for (ArrayList<Integer> risultato : risultati)
        {
            risultatiString.add(risultato.get(0)+" - "+risultato.get(1));
        }
        return risultatiString;
    }

    /**
     * Restituisce tutti i risultati della giornata sotto forma di: "goalSquadraInCasa - goalSquadraFuoriCasa"
     * @param index, numero della giornata (la prima giornata ha index=0), >=0 e <getNumeroGiornate()
     * @return tutti i risultati delle partite
     */
    @Override
    public ArrayList<String> getRisultati(int index) {
        ArrayList<String> risultatiString = new ArrayList<String>();

        for (int i=0,j=(numeroSquadre/2)*index;i<numeroSquadre/2;i++,j++)
        {
            risultatiString.add(risultati.get(j).get(0)+" - "+risultati.get(j).get(1));
        }
        return risultatiString;
    }

    @Override
    public void updateRisultato(int index1, int index2, int goal1, int goal2)
    {
        boolean trovato=false;
        //Ricerca nel girone d'andata
        for (int i=0;i<partite.size() && !trovato;i++)
        {
            if (partite.get(i).get(0)==index1 && partite.get(i).get(1)==index2)
            {
                risultati.remove(i);
                risultati.add(i,new ArrayList<Integer>(Arrays.asList(goal1,goal2)));
                trovato=true;
            }
        }
        //Ricerca nel girone di ritorno
        for (int i=0;i<partite.size() && !trovato;i++)
        {
            if (partite.get(i).get(1)==index1 && partite.get(i).get(0)==index2)
            {
                risultati.remove(i+(numeroSquadre/2)*(numeroSquadre-1));
                risultati.add(i+(numeroSquadre/2)*(numeroSquadre-1),new ArrayList<Integer>(Arrays.asList(goal2,goal1)));
                trovato=true;
            }
        }
    }

    /**
     * Restituisce il numero di giornate
     * @return numero giornate
     */
    public int getNumeroGiornate()
    {
        return (numeroSquadre-1)*2;
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
