/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

import Controller.Gestore;
import Model.Movie;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author marcianof
 */
public class MainGrafico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Gestore g = new Gestore();

        // legge il file
        g.leggiCSV();

        // STAMPA TUTTI I FILM
        System.out.println("CATALOGO:");

        for (Movie m : g.getGestionefilm()) {
            System.out.println(m);
        }

        // CERCA PER CODICE
        System.out.println("\nRICERCA PER CODICE:");

        Movie trovato = g.cercaPerCodice("F002");

        if (trovato != null) {
            System.out.println(trovato);
        } else {
            System.out.println("Film non trovato");
        }

        // CERCA PER CATEGORIA
        System.out.println("\nFILM DELLA CATEGORIA Crime:");

        ArrayList<Movie> azione = g.cercaPerCategoria("Crime");

        for (Movie m : azione) {
            System.out.println(m);
        }
        
        //AGGIUNTA NUOVO FILM
        Movie nuovoFilm = new Movie(
                "F0202",
                "Pippo",
                169.0,
                1,
                "Matthew McConaughey",
                "Fantascienza",
                "2014"
        );

        g.aggiungiFilm(nuovoFilm);  
        g.eliminaPerCodice("F001");
        
        System.out.println("LISTA AGGIORNATA FILM:\n");

        for (Movie m : g.getGestionefilm()) {

            System.out.println(m);
        }

        
    }
    
}
