package com.stefano.gioda.mytournament;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.stefano.gioda.mytournament.classi.Giocatore;
import com.stefano.gioda.mytournament.classi.Squadra;
import com.stefano.gioda.mytournament.classi.TorneoEliminazione;

public class TestTorneoEliminazione {
	private TorneoEliminazione torneoTest;

	@Test
	public void testTorneoEliminazione() {
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
		ArrayList<Integer> semifinale = calendario.get(0);
		
		// Controllo che tutti le squadre siano diverse
		
		for (int i=0;i<semifinale.size();i++)
		{
			for (int j=0;j<semifinale.size();j++)
			{
				if (j!=i)
				{
					assertNotEquals(semifinale.get(i),semifinale.get(j));
				}
			}
		}
		
		assertEquals(calendario.size(),3); //numero giornata girone andata
	}

	@Test
	public void testGetCalendarioInt() {
		inizializza();
		ArrayList<ArrayList<Integer>> calendarioFinale = torneoTest.getCalendario(1);
		
		assertEquals(1,calendarioFinale.size());
		
		for (int i=0;i<calendarioFinale.size();i++)
		{
			assertEquals(-1,(int)calendarioFinale.get(i).get(0));
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
		
		assertEquals(3,risultati.size());
	}

	@Test
	public void testUpdateRisultatoGetRisultatiInt() {
		inizializza();
		ArrayList<ArrayList<Integer>> calendarioSemifinale = torneoTest.getCalendario(0);
		ArrayList<ArrayList<Integer>> calendarioFinale;
		ArrayList<String> risultati;
		
		torneoTest.updateRisultato(calendarioSemifinale.get(0).get(0), calendarioSemifinale.get(0).get(1), 0, 2);
		torneoTest.updateRisultato(calendarioSemifinale.get(1).get(0), calendarioSemifinale.get(1).get(1), 3, 1);
		
		risultati = torneoTest.getRisultati(0);
		
		assertEquals("0 - 2",risultati.get(0));
		assertEquals("3 - 1",risultati.get(1));
		
		calendarioFinale = torneoTest.getCalendario(1);
		
		assertEquals(calendarioSemifinale.get(0).get(1),calendarioFinale.get(0).get(0));
		assertEquals(calendarioSemifinale.get(1).get(0),calendarioFinale.get(0).get(1));
		
		torneoTest.updateRisultato(calendarioFinale.get(0).get(0), calendarioFinale.get(0).get(1), 3, 2);
		risultati = torneoTest.getRisultati(1);
		
		assertEquals("3 - 2",risultati.get(0));
		
		
	}
	
	@Test
	public void testUpdateRisultato8Squadre() {
		inizializza8Squadre();
		ArrayList<ArrayList<Integer>> calendarioQuarti = torneoTest.getCalendario(0);
		ArrayList<ArrayList<Integer>> calendarioSemifinale;
		ArrayList<ArrayList<Integer>> calendarioFinale;
		ArrayList<String> risultati;
		
		torneoTest.updateRisultato(calendarioQuarti.get(0).get(0), calendarioQuarti.get(0).get(1), 0, 2);
		torneoTest.updateRisultato(calendarioQuarti.get(1).get(0), calendarioQuarti.get(1).get(1), 3, 1);
		torneoTest.updateRisultato(calendarioQuarti.get(2).get(0), calendarioQuarti.get(2).get(1), 0, 1);
		torneoTest.updateRisultato(calendarioQuarti.get(3).get(0), calendarioQuarti.get(3).get(1), 10, 0);
		
		risultati = torneoTest.getRisultati(0);
		
		assertEquals("0 - 2",risultati.get(0));
		assertEquals("3 - 1",risultati.get(1));
		assertEquals("0 - 1",risultati.get(2));
		assertEquals("10 - 0",risultati.get(3));
		
		calendarioSemifinale = torneoTest.getCalendario(1);
		
		assertEquals(calendarioQuarti.get(0).get(1),calendarioSemifinale.get(0).get(0));
		assertEquals(calendarioQuarti.get(1).get(0),calendarioSemifinale.get(0).get(1));
		assertEquals(calendarioQuarti.get(2).get(1),calendarioSemifinale.get(1).get(0));
		assertEquals(calendarioQuarti.get(3).get(0),calendarioSemifinale.get(1).get(1));
		
		torneoTest.updateRisultato(calendarioSemifinale.get(0).get(0), calendarioSemifinale.get(0).get(1), 3, 2);
		torneoTest.updateRisultato(calendarioSemifinale.get(1).get(0), calendarioSemifinale.get(1).get(1), 1, 4);

		risultati = torneoTest.getRisultati(1);
		
		assertEquals("3 - 2",risultati.get(0));
		assertEquals("1 - 4",risultati.get(1));
		
		calendarioFinale = torneoTest.getCalendario(2);
		
		assertEquals(calendarioSemifinale.get(0).get(0),calendarioFinale.get(0).get(0));
		assertEquals(calendarioSemifinale.get(1).get(1),calendarioFinale.get(0).get(1));
		
		torneoTest.updateRisultato(calendarioFinale.get(0).get(0), calendarioFinale.get(0).get(1), 2, 3);
		risultati = torneoTest.getRisultati(2);
		
		assertEquals("2 - 3",risultati.get(0));
	}
	
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
		
		torneoTest = new TorneoEliminazione("Calcetto tra amici", squadre);
	}
	
	private void inizializza8Squadre()
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
		
		giocatori.add(new Giocatore("Francesco","Vello","11/05/1956","1"));
		giocatori.add(new Giocatore("Luca","Iopolo","03/11/1999","1"));
		giocatori.add(new Giocatore("Marco","Trego","14/07/1976","1"));
		giocatori.add(new Giocatore("Giovanni","Vituni","25/02/1989","1"));
		giocatori.add(new Giocatore("Raffaele","Ateilo","30/10/1997","1"));
		
		squadre.add(new Squadra("Rats",giocatori));
		
		giocatori.clear();
		
		giocatori.add(new Giocatore("Luca","Vello","30/10/1997","1"));
		giocatori.add(new Giocatore("Marco","Iopolo","11/05/1956","1"));
		giocatori.add(new Giocatore("Giovanni","Trego","03/11/1999","1"));
		giocatori.add(new Giocatore("Raffaele","Vituni","14/07/1976","1"));
		giocatori.add(new Giocatore("Francesco","Ateilo","25/02/1989","1"));
		
		squadre.add(new Squadra("Bellaisola",giocatori));
		
		giocatori.clear();
		
		giocatori.add(new Giocatore("Marco","Vello","25/02/1989","1"));
		giocatori.add(new Giocatore("Giovanni","Iopolo","30/10/1997","1"));
		giocatori.add(new Giocatore("Raffaele","Trego","11/05/1956","1"));
		giocatori.add(new Giocatore("Francesco","Vituni","03/11/1999","1"));
		giocatori.add(new Giocatore("Luca","Ateilo","14/07/1976","1"));
		
		squadre.add(new Squadra("Giuventus",giocatori));
		
		giocatori.clear();
		
		giocatori.add(new Giocatore("Giovanni","Vello","14/07/1976","1"));
		giocatori.add(new Giocatore("Raffaele","Iopolo","25/02/1989","1"));
		giocatori.add(new Giocatore("Francesco","Trego","30/10/1997","1"));
		giocatori.add(new Giocatore("Luca","Vituni","11/05/1956","1"));
		giocatori.add(new Giocatore("Marco","Ateilo","03/11/1999","1"));
		
		squadre.add(new Squadra("La viola",giocatori));
		
		torneoTest = new TorneoEliminazione("Eliminazione con 8 squadre", squadre);
	}
}
