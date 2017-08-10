package com.stefano.gioda.mytournament.classi;

import java.io.Serializable;

/**
 * Created by Stefano Gioda on 8/8/17.
 *
 * Classe che rappresenta un giocatore appartenente ad una squadra del torneo
 */

public class Giocatore implements Serializable
{
    private String nome;
    private String cognome;
    private String dataNascita;
    private String telefono;

    /**
     * Costruttore
     * @param nome valido, !=''
     * @param cognome valido, !=''
     * @param dataNascita valida, nel formato 'dd/mm/YYYY' e !=''
     * @param telefono valido, !=''
     */
    public Giocatore(String nome,String cognome,String dataNascita,String telefono)
    {
        setNome(nome);
        setCognome(cognome);
        setDataNascita(dataNascita);
        setTelefono(telefono);
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    private void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    private void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getTelefono() {
        return telefono;
    }

    private void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
