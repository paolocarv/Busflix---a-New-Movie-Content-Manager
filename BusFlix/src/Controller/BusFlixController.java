package Controller;

import Model.CSVManager;
import Model.Movie;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BusFlixController {

    private CSVManager csvManager;
    private String utenteLoggato;
    private File fileCorrente;

    private List<Movie> catalogo = new ArrayList<>();

    // 🔥 CACHE PREFERITI (FIX FONDAMENTALE)
    private List<String> preferitiCache = new ArrayList<>();

    public BusFlixController() {
        csvManager = new CSVManager();
    }

    // ================= LOGIN =================
    public boolean login(String username, String password) {

        boolean accesso = csvManager.controllaLogin(username, password);

        if (accesso) {
            utenteLoggato = username;
            refreshPreferiti(); // 🔥 carica subito preferiti
        }

        return accesso;
    }

    public String getUtenteLoggato() {
        return utenteLoggato;
    }

    // ================= FILE =================
    public void apriFile() {

        JFileChooser chooser = new JFileChooser();

        int scelta = chooser.showOpenDialog(null);

        if (scelta == JFileChooser.APPROVE_OPTION) {

            fileCorrente = chooser.getSelectedFile();
            caricaCatalogo();
        }
    }

    public void salvaFile() {

        if (fileCorrente == null) {
            salvaConNome();
            return;
        }

        csvManager.salvaSuFile(fileCorrente, catalogo);
    }

    public void salvaConNome() {

        JFileChooser chooser = new JFileChooser();

        int scelta = chooser.showSaveDialog(null);

        if (scelta == JFileChooser.APPROVE_OPTION) {

            fileCorrente = chooser.getSelectedFile();
            csvManager.salvaSuFile(fileCorrente, catalogo);
        }
    }

    // ================= LOAD =================
    public void caricaCatalogo() {

        if (fileCorrente != null) {
            catalogo = csvManager.caricaDaFile(fileCorrente);
        }
    }

    // ================= CATALOGO =================
    public List<Movie> getCatalogo() {
        return catalogo;
    }

    // ================= UTENTE FILM =================
    public List<Movie> getCatalogoUtente() {

        List<Movie> risultato = new ArrayList<>();

        if (utenteLoggato == null) {
            return risultato;
        }

        // 🔥 SEMPRE FRESH DA CACHE (non dal file ogni volta)
        for (Movie f : catalogo) {
            if (preferitiCache.contains(f.getCodice())) {
                risultato.add(f);
            }
        }

        return risultato;
    }

    // ================= AGGIUNGI =================
    public boolean aggiungiFilm(String codiceFilm) {

        if (utenteLoggato == null) {
            return false;
        }

        if (preferitiCache.contains(codiceFilm)) {
            return false;
        }

        csvManager.salvaPreferito(utenteLoggato, codiceFilm);

        // 🔥 FIX: aggiorna subito memoria
        preferitiCache.add(codiceFilm);

        return true;
    }

    // ================= ELIMINA =================
    public boolean eliminaFilm(String codiceFilm) {

        if (utenteLoggato == null) {
            return false;
        }

        int risposta = JOptionPane.showConfirmDialog(
                null,
                "Vuoi eliminare il film?",
                "Conferma eliminazione",
                JOptionPane.YES_NO_OPTION
        );

        if (risposta == JOptionPane.YES_OPTION) {

            csvManager.rimuoviPreferito(utenteLoggato, codiceFilm);

            // 🔥 FIX: aggiorna cache subito
            preferitiCache.remove(codiceFilm);

            return true;
        }

        return false;
    }

    // ================= REFRESH =================
    private void refreshPreferiti() {

        if (utenteLoggato != null) {
            preferitiCache = csvManager.leggiCodiciUtente(utenteLoggato);
        }
    }

    // ================= RICERCHE =================
    public List<Movie> cercaPerNome(List<Movie> catalogo, String nome) {

        List<Movie> risultato = new ArrayList<>();

        for (Movie f : catalogo) {
            if (f.getNome().toLowerCase().contains(nome.toLowerCase())) {
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
            if (f.getProtagonista().toLowerCase().contains(p.toLowerCase())) {
                risultato.add(f);
            }
        }

        return risultato;
    }

    public List<Movie> cercaPerDurata(List<Movie> catalogo, double max) {

        List<Movie> risultato = new ArrayList<>();

        for (Movie f : catalogo) {
            if (f.getDurata() <= max) {
                risultato.add(f);
            }
        }

        return risultato;
    }

    public List<Movie> cercaCompleta(List<Movie> catalogo, String testo) {

        List<Movie> risultato = new ArrayList<>();

        for (Movie f : catalogo) {
            if (f.getNome().toLowerCase().contains(testo.toLowerCase())
                    || f.getProtagonista().toLowerCase().contains(testo.toLowerCase())
                    || f.getCategoria().toLowerCase().contains(testo.toLowerCase())) {
                risultato.add(f);
            }
        }

        return risultato;
    }

    // ================= LOGOUT =================
    public void logout() {
        utenteLoggato = null;
        preferitiCache.clear();
    }
}