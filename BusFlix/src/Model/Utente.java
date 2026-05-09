/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author marcianof
 */
public class Utente {
    String nome, cognome, nome_utente , password;

    public Utente(String nome, String cognome, String nome_utente, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.nome_utente = nome_utente;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome_utente() {
        return nome_utente;
    }

    public String getPassword() {
        return password;
    }
    
}
