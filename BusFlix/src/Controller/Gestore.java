/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Movie;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author carvellip
 */
public class Gestore {
    ArrayList <Movie> gestionefilm = new ArrayList();
    
    public ArrayList<Movie> LeggiCSV() throws IOException {
        String filename = "catalogo.csv";
 
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;

            br.readLine(); // salta intestazione

            while ((line = br.readLine()) != null) {
                
                System.out.println(line);
                String[] info = line.split(",");

                // controllo numero campi
                if (info.length >= 7) {

                    Movie m = new Movie(
                            info[0], // codice
                            info[1], // nome
                            Double.parseDouble(info[2]), // durata_minuti
                            Integer.parseInt(info[3]), // episodi
                            info[4], // protagonista
                            info[5], // categoria
                            info[6] // data_uscita
                    );
                    gestionefilm.add(m);
                }
            }
        }

        return gestionefilm;
    }
            
    public Movie cercaPerCodice(String codice){
        for(Movie f : gestionefilm){
            if(f.getCodice().equals(codice)){
                return f;
            }
        }
        return null;
    }
    
    public ArrayList<Movie> cercaPerCategoria(String categoria){
        ArrayList<Movie> gestcategoria = new ArrayList();
        for(Movie f : gestcategoria){
            if(f.getCategoria().equals(categoria)){
                gestcategoria.add(f);
            }
        }
        return gestcategoria;
    }
    
    public ArrayList<Movie> cercaPerProtagonista(String protagonista){
        ArrayList<Movie> gestprotagonista = new ArrayList();
        for(Movie f : gestprotagonista){
            if(f.getProtagonista().equals(protagonista)){
                gestprotagonista.add(f);
            }
        }
        return gestprotagonista;
    }
   
    
}
