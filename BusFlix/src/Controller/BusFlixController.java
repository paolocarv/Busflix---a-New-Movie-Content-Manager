/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author matteo
 */
import Model.CSVManager;
import Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class BusFlixController {

    private CSVManager csvManager;
    private String utenteLoggato;

    public BusFlixController() {
        csvManager = new CSVManager();
    }

    // ================= LOGIN =================
    public boolean login(String username, String password) {

        boolean accesso
                = csvManager.controllaLogin(username, password);

        if (accesso) {
            utenteLoggato = username;
        }

        return accesso;
    }

    public String getUtenteLoggato() {
        return utenteLoggato;
    }

    // ================= CATALOGO GENERALE =================
    public List<Movie> getCatalogo() {
        return csvManager.leggiFilm();
    }

    // ================= CATALOGO UTENTE =================
    public List<Movie> getCatalogoUtente() {

        List<Movie> risultato = new ArrayList<>();

        if (utenteLoggato == null) {
            return risultato;
        }

        List<String> codici
                = csvManager.leggiCodiciUtente(utenteLoggato);

        List<Movie> catalogo
                = csvManager.leggiFilm(); // FIX IMPORTANTE

        for (Movie f : catalogo) {

            if (codici.contains(f.getCodice())) {
                risultato.add(f);
            }
        }

        return risultato;
    }

    // ================= AGGIUNGI FILM =================
    public boolean aggiungiFilm(String codiceFilm) {

        if (utenteLoggato == null) {
            return false;
        }

        // evita duplicati
        List<Movie> giaPresenti = getCatalogoUtente();

        for (Movie f : giaPresenti) {
            if (f.getCodice().equals(codiceFilm)) {
                return false;
            }
        }

        csvManager.salvaPreferito(utenteLoggato, codiceFilm);
        return true;
    }

    // ================= RICERCHE =================
    public List<Movie> cercaPerNome(List<Movie> catalogo, String nome) {

        List<Movie> risultato = new ArrayList<>();

        for (Movie f : catalogo) {

            if (f.getNome().toLowerCase()
                    .contains(nome.toLowerCase())) {

                risultato.add(f);
            }
        }

        return risultato;
    }

    public List<Movie> cercaPerCategoria(List<Movie> catalogo, String cat) {

        List<Movie> risultato = new ArrayList<>();

        for (Movie f : catalogo) {

            if (f.getCategoria().equalsIgnoreCase(cat)) {

                risultato.add(f);
            }
        }

        return risultato;
    }

    public List<Movie> cercaPerProtagonista(List<Movie> catalogo, String p) {

        List<Movie> risultato = new ArrayList<>();

        for (Movie f : catalogo) {

            if (f.getProtagonista().toLowerCase()
                    .contains(p.toLowerCase())) {

                risultato.add(f);
            }
        }

        return risultato;
    }

    public List<Movie> cercaPerDurata(List<Movie> catalogo, int max) {

        List<Movie> risultato = new ArrayList<>();

        for (Movie f : catalogo) {

            if (f.getDurata() <= max) {

                risultato.add(f);
            }
        }

        return risultato;
    }
}
