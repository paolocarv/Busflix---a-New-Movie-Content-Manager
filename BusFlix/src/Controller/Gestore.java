package Controller;

import Model.Movie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gestore {

    private ArrayList<Movie> gestionefilm = new ArrayList<>();

    // LETTURA CSV
    public void leggiCSV() {

        String filename = "catalogo.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            br.readLine(); // salta intestazione

            while ((line = br.readLine()) != null) {

                String[] info = line.split(",");

                if (info.length == 7) {

                    Movie m = new Movie(
                            info[0].trim(),
                            info[1].trim(),
                            Double.parseDouble(info[2].trim()),
                            Integer.parseInt(info[3].trim()),
                            info[4].trim(),
                            info[5].trim(),
                            info[6].trim()
                    );

                    gestionefilm.add(m);
                }
            }

            System.out.println("File letto correttamente!");

        } catch (IOException e) {

            System.out.println("Errore nella lettura del file: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Errore conversione numeri nel CSV");

        }
    }

    // CERCA PER CODICE
    public Movie cercaPerCodice(String codice) {

        for (Movie m : gestionefilm) {

            if (m.getCodice().equalsIgnoreCase(codice)) {
                return m;
            }
        }

        return null;
    }

    // CERCA PER CATEGORIA
    public ArrayList<Movie> cercaPerCategoria(String categoria) {

        ArrayList<Movie> risultati = new ArrayList<>();

        for (Movie m : gestionefilm) {

            if (m.getCategoria().equalsIgnoreCase(categoria)) {
                risultati.add(m);
            }
        }

        return risultati;
    }

    // GETTER LISTA COMPLETA
    public ArrayList<Movie> getGestionefilm() {
        return gestionefilm;
    }
    
   

    
    public class LettoreCSV {

    public List<String> leggiCodiciFilm(String percorsoFile, String nomeUtente) {
        List<String> codiciFilm = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(percorsoFile))) {

            String riga;

            while ((riga = br.readLine()) != null) {

                // separa i campi del CSV
                String[] campi = riga.split(",");

                // controllo sicurezza
                if (campi.length >= 2) {

                    String utente = campi[0].trim();
                    String codiceFilm = campi[1].trim();

                    // confronto nome utente
                    if (utente.equals(nomeUtente)) {
                        codiciFilm.add(codiceFilm);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return codiciFilm;
    }
    
    }
}