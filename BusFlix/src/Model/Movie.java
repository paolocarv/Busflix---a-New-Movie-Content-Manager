/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author marcianof
 */
public class Movie {

    private String codice;
    private String nome;
    private double durata;
    private int episodi;
    private String protagonista;
    private String categoria;
    private String data;

    public Movie(String codice,
            String nome,
            double durata,
            int episodi,
            String protagonista,
            String categoria,
            String data) {

        this.codice = codice;
        this.nome = nome;
        this.durata = durata;
        this.episodi = episodi;
        this.protagonista = protagonista;
        this.categoria = categoria;
        this.data = data;
    }

    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public double getDurata() {
        return durata;
    }

    public int getEpisodi() {
        return episodi;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {

        return codice + " - "
                + nome + " - "
                + categoria + " - "
                + protagonista;
    }
}
