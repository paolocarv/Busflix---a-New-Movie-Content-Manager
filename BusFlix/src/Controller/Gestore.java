package Controller;

import Model.Movie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    
    public void salvaCSV() {

    String filename = "catalogo.csv";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

        // intestazione
        bw.write("codice,nome,durata,episodi,protagonista,categoria,data");
        bw.newLine();

        // scrittura film
        for (Movie m : gestionefilm) {

            bw.write(
                    m.getCodice() + "," +
                    m.getNome() + "," +
                    m.getDurata() + "," +
                    m.getEpisodi() + "," +
                    m.getProtagonista() + "," +
                    m.getCategoria() + "," +
                    m.getDataUscita()
            );

            bw.newLine();
        }

        System.out.println("CSV aggiornato correttamente!");

    } catch (IOException e) {

        System.out.println("Errore salvataggio CSV");
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
    
    public void aggiungiFilm(Movie nuovoFilm) {

        gestionefilm.add(nuovoFilm);
        salvaCSV();
        System.out.println("Film aggiunto!");
    }
    
    public ArrayList<Movie> eliminaPerCodice(String codice) {

        for (int i = 0; i < gestionefilm.size(); i++) {

            if (gestionefilm.get(i).getCodice().equalsIgnoreCase(codice)) {

                gestionefilm.remove(i);
                salvaCSV();
                break;
            }
        }

        return gestionefilm;
    }
}