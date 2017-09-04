package com.stefano.gioda.mytournament;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.stefano.gioda.mytournament.classi.*;

public class TestTorneoItaliana {
	
	private TorneoItaliana torneoTest;

	@Test
	public void testTorneoItaliana() {
		inizializza();
		assertEquals(torneoTest.getNome(),"Calcetto tra amici");
	}

	@Test
	public void testGetNome() {
		inizializza();
		assertEquals(torneoTest.getNome(),"Calcetto tra amici");
	}

	@Test
	public void testGetSquadre() {
		inizializza();
		assertEquals(torneoTest.getSquadre().size(),4);
		assertEquals(torneoTest.getSquadre().get(3).getGiocatori().size(),5);
		assertEquals(torneoTest.getSquadre().get(0).getNome(),"The best");
	}

	@Test
	public void testGetGiocatori() {
		inizializza();
		assertEquals(torneoTest.getGiocatori().size(),20);
		assertEquals(torneoTest.getGiocatori().get(19).getDataNascita(),"03/11/1999");
		assertEquals(torneoTest.getGiocatori().get(0).getCognome(),"Vello");
	}

	@Test
	public void testGetCalendario() {
		inizializza();
		ArrayList<ArrayList<Integer>> calendario = torneoTest.getCalendario();
		ArrayList<Integer> avversari = new ArrayList<>();
		
		for (ArrayList<Integer> row : calendario)
		{
			if (row.get(0)==3)
			{
				avversari.add(row.get(1));
			}
			else if (row.get(1)==3)
			{
				avversari.add(row.get(0));
			}
		}
		assertEquals(avversari.size(),3);
		
		// Controllo che tutti gli avversari siano diversi
		boolean ripetuto=false;
		
		for (int i=0;i<avversari.size() && !ripetuto;i++)
		{
			for (int j=0;j<avversari.size() && !ripetuto;j++)
			{
				if (j!=i)
				{
					if (avversari.get(i)==avversari.get(j))
					{
						ripetuto=true;
					}
				}
			}
		}
		assertFalse(ripetuto);
		assertEquals(calendario.size()/2,3); //numero giornata girone andata
	}

	@Test
	public void testGetCalendarioInt() {
		inizializza();
		ArrayList<ArrayList<Integer>> calendarioAndata = torneoTest.getCalendario(0);
		ArrayList<ArrayList<Integer>> calendarioRitorno = torneoTest.getCalendario(3);
		
		assertEquals(2,calendarioAndata.size());
		assertEquals(2,calendarioRitorno.size());
		
		for (int i=0;i<calendarioAndata.size();i++)
		{
			assertEquals(calendarioAndata.get(i).get(0),calendarioRitorno.get(i).get(1));
			assertEquals(calendarioAndata.get(i).get(1),calendarioRitorno.get(i).get(0));
		}
	}

	@Test
	public void testGetRisultati() {
		inizializza();
		ArrayList<String> risultati = torneoTest.getRisultati();
		
		for (String ris : risultati)
		{
			assertEquals(ris,"-1 - -1");
		}
		
		assertEquals(12,risultati.size());
	}

	@Test
	public void testUpdateRisultatoGetRisultatiIntClassifica() {
		inizializza();
		ArrayList<ArrayList<Integer>> calendarioAndata = torneoTest.getCalendario(1);
		ArrayList<String> risultati;
		
		assertEquals(2,calendarioAndata.size());
		
		torneoTest.updateRisultato(calendarioAndata.get(0).get(0), calendarioAndata.get(0).get(1), 0, 2);
		risultati = torneoTest.getRisultati(1);
		
		assertEquals("0 - 2",risultati.get(0));
		
		assertEquals(3,(int)torneoTest.getPunti().get(calendarioAndata.get(0).get(1)));
		assertEquals(0,(int)torneoTest.getPunti().get(calendarioAndata.get(0).get(0)));
		
		assertEquals(2,(int)torneoTest.getGoalFatti().get(calendarioAndata.get(0).get(1)));
		assertEquals(0,(int)torneoTest.getGoalFatti().get(calendarioAndata.get(0).get(0)));
		
		assertEquals(0,(int)torneoTest.getGoalSubiti().get(calendarioAndata.get(0).get(1)));
		assertEquals(2,(int)torneoTest.getGoalSubiti().get(calendarioAndata.get(0).get(0)));
		
		assertEquals(2,(int)torneoTest.getDifferenzaReti().get(calendarioAndata.get(0).get(1)));
		assertEquals(-2,(int)torneoTest.getDifferenzaReti().get(calendarioAndata.get(0).get(0)));
	}
	
	/*@Test
	public void testData()
	{
		inizializza();
		Data holder = Data.getInstance();
		
		System.out.println(holder.getSquadre().size());
		System.out.println(holder.getSquadre().get(0).getNome());
		
	}*/
	
	private void inizializza()
	{
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		ArrayList<Squadra> squadre = new ArrayList<>();
		
		giocatori.add(new Giocatore("Francesco","Vello","11/05/1956","1"));
		giocatori.add(new Giocatore("Luca","Iopolo","03/11/1999","1"));
		giocatori.add(new Giocatore("Marco","Trego","14/07/1976","1"));
		giocatori.add(new Giocatore("Giovanni","Vituni","25/02/1989","1"));
		giocatori.add(new Giocatore("Raffaele","Ateilo","30/10/1997","1"));
		
		squadre.add(new Squadra("The best",giocatori));
		
		giocatori.clear();
		assertEquals(squadre.get(0).getGiocatori().size(),5);
		
		giocatori.add(new Giocatore("Luca","Vello","30/10/1997","1"));
		giocatori.add(new Giocatore("Marco","Iopolo","11/05/1956","1"));
		giocatori.add(new Giocatore("Giovanni","Trego","03/11/1999","1"));
		giocatori.add(new Giocatore("Raffaele","Vituni","14/07/1976","1"));
		giocatori.add(new Giocatore("Francesco","Ateilo","25/02/1989","1"));
		
		squadre.add(new Squadra("Siamo noi",giocatori));
		
		giocatori.clear();
		
		giocatori.add(new Giocatore("Marco","Vello","25/02/1989","1"));
		giocatori.add(new Giocatore("Giovanni","Iopolo","30/10/1997","1"));
		giocatori.add(new Giocatore("Raffaele","Trego","11/05/1956","1"));
		giocatori.add(new Giocatore("Francesco","Vituni","03/11/1999","1"));
		giocatori.add(new Giocatore("Luca","Ateilo","14/07/1976","1"));
		
		squadre.add(new Squadra("LOL",giocatori));
		
		giocatori.clear();
		
		giocatori.add(new Giocatore("Giovanni","Vello","14/07/1976","1"));
		giocatori.add(new Giocatore("Raffaele","Iopolo","25/02/1989","1"));
		giocatori.add(new Giocatore("Francesco","Trego","30/10/1997","1"));
		giocatori.add(new Giocatore("Luca","Vituni","11/05/1956","1"));
		giocatori.add(new Giocatore("Marco","Ateilo","03/11/1999","1"));
		
		squadre.add(new Squadra("WOW",giocatori));
		
		torneoTest = new TorneoItaliana("Calcetto tra amici", squadre);
	}
}
