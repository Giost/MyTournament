package com.stefano.gioda.mytournament.classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Stefano Gioda on 8/9/17.
 *
 * Classe che rappresenta il calendario di un torneo a eliminazione
 */
public class CalendarioEliminazione implements Calendario, Serializable
{
    private int numeroSquadre;
    private ArrayList<ArrayList<Integer>> partite;
    private ArrayList<ArrayList<Integer>> risultati;

    /**
     * Costruttore
     * @param numeroSquadre, deve essere una potenza di 2 e >=2
     */
    public CalendarioEliminazione(int numeroSquadre)
    {
        this.numeroSquadre=numeroSquadre;
        this.partite = new ArrayList<ArrayList<Integer>>();
        this.risultati = new ArrayList<ArrayList<Integer>>();
        sorteggio(numeroSquadre);
        
        for (int i=partite.size();i<numeroSquadre-1;i++)
        {
        	partite.add(new ArrayList<Integer>(Arrays.asList(-1,-1)));
        }

        for (int i = 0; i<partite.size(); i++)
        {
            risultati.add(new ArrayList<Integer>(Arrays.asList(-1,-1)));
        }
    }

    /**
     * Crea gli incontri tra le squadre casualmente
     * @param numeroSquadre, deve essere una potenza di 2 e >=2
     */
    private void sorteggio(int numeroSquadre)
    {
        ArrayList<Integer> copySquadre = new ArrayList<Integer>();
        int random;
        int aux;
        int numSquadMezzo=numeroSquadre/2;
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
    	ArrayList<String> risultatiString = new ArrayList<String>();
        for (ArrayList<Integer> risultato : risultati)
        {
            risultatiString.add(risultato.get(0)+" - "+risultato.get(1));
        }
        return risultatiString;
    }

    @Override
    public ArrayList<String> getRisultati(int index) {
    	ArrayList<String> risultatiString = new ArrayList<String>();
    	int indiceInizio=0;
        int durata=numeroSquadre/2;

        for (int i=0,m=numeroSquadre/2;i<index;i++,m=m/2)
        {
            indiceInizio+=m;
            durata=durata/2;
        }

        for (int i=0,j=indiceInizio;i<durata;i++,j++)
        {
            risultatiString.add(risultati.get(j).get(0)+" - "+risultati.get(j).get(1));
        }
        return risultatiString;
    }
    
    /**
     * Aggiorna il calendario e il risultato della partita, gli indici passati
     * devono corrispondere a 2 indici nella stessa riga del calendario.
     * La partita non può finire in pareggio
     * @param index1 indice della squadra in casa valido, >=0 e <numeroSquadre
     * @param index2 indice della squadra fuori casa valido, >=0 e <numeroSquadre
     * @param goal1 goal della squadra in casa valido, >=0
     * @param goal2 goal della squadra fuori casa valido, >=0
     */
    @Override
    public void updateRisultato(int index1, int index2, int goal1, int goal2) 
    {
    	int vincitore;
        int indiceInizio=0;
        int durata=numeroSquadre/2;
        ArrayList<Integer> row;
    	int indice = -1;
    	
        for (int i=0;i<partite.size() && indice==-1;i++)
        {
            if (partite.get(i).get(0)==index1 && partite.get(i).get(1)==index2)
            {
                risultati.remove(i);
                risultati.add(i,new ArrayList<Integer>(Arrays.asList(goal1,goal2)));
                
                indice=i;
            }
        }
        
        for (int i=0,m=numeroSquadre/2;i<=trovaNumeroFase(indice);i++,m=m/2)
        {
            indiceInizio+=m;
            durata=durata/2;
        }
        
        if (durata>0)
        {
	        //Avanzamento squadra vincitrice se non è la finale     
	        if (goal1>goal2)
	        {
	        	vincitore=index1;
	        }
	        else
	        {
	        	vincitore=index2;
	        }
	        
	        indiceInizio+=(indice/2)%durata;
	        row=partite.remove(indiceInizio);
	        
	        if (indice%2==0)
	        {
	        	row.remove(0);
	        	row.add(0,vincitore);
	        }
	        else
	        {
	        	row.remove(1);
	        	row.add(1,vincitore);
	        }
	        
	        partite.add(indiceInizio,row);
        }
    }
    
    /**
     * Restituisce il numero di fasi
     * @return numero fasi
     */
    public int getNumeroFasi()
    {
        return (int) (Math.log(numeroSquadre)/Math.log(2.0));
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
    
    private int trovaNumeroFase(int indice)
    {
    	int fase=-1;
    	
    	for (int i=0,m=numeroSquadre/2;i<getNumeroFasi() && fase==-1;i++,m+=m/2)
        {
    		if (indice<m)
    		{
    			fase=i;
    		}
        }
    	return fase;
    }
}
