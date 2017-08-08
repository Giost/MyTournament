package com.stefano.gioda.mytournament.classi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stefano Gioda on 8/8/17.
 *
 * Classe che rappresenta una squadra appartenente ad un torneo
 */

public class Squadra implements Serializable
{
    private String nome;
    private ArrayList<Giocatore> giocatori;

    public Squadra(String nome,ArrayList<Giocatore> giocatori)
    {
        setNome(nome);
        setGiocatori(giocatori);
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    private void setGiocatori(ArrayList<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }
}
