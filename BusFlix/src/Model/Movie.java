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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDurata(double durata) {
        this.durata = durata;
    }

    public void setEpisodi(int episodi) {
        this.episodi = episodi;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Movie{" + "codice=" + codice + ", nome=" + nome + ", durata=" + durata + ", episodi=" + episodi + ", protagonista=" + protagonista + ", categoria=" + categoria + ", data=" + data + '}';
    }
    
    

}