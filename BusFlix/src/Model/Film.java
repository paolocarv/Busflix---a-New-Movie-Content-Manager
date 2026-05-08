/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author marcianof
 */
public class Film {
    String codice;
    String nome;
    Double durata;
    int episodi;
    String protagonista;
    String categoria;
    String dataUscita;

    public Film(String codice, String nome, Double durata, int episodi, String protagonista, String categoria, String dataUscita) {
        this.codice = codice;
        this.nome = nome;
        this.durata = durata;
        this.episodi = episodi;
        this.protagonista = protagonista;
        this.categoria = categoria;
        this.dataUscita = dataUscita;
    }

    @Override
    public String toString() {
        return "Film{" + "codice=" + codice + ", nome=" + nome + ", durata=" + durata + ", episodi=" + episodi + ", protagonista=" + protagonista + ", categoria=" + categoria + ", dataUscita=" + dataUscita + '}';
    }
    
    
}

