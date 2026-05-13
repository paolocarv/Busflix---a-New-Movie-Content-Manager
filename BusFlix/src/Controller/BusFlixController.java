package Controller;

import Model.CSVManager;
import Model.Movie;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class BusFlixController {

    private CSVManager csvManager;
    private String utenteLoggato;
    private File fileCorrente;

    // 🔥 LISTA INTERNA (FONDAMENTALE)
    private List<Movie> catalogo = new ArrayList<>();

    public BusFlixController() {
        csvManager = new CSVManager();
    }

    // ================= LOGIN =================
    public boolean login(String username, String password) {

        boolean accesso = csvManager.controllaLogin(username, password);

        if (accesso) {
            utenteLoggato = username;
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

            // 🔥 SALVIAMO LA LISTA
            catalogo = csvManager.caricaDaFile(fileCorrente);
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

    // ================= CATALOGO =================

    public List<Movie> getCatalogo() {
        return catalogo;
    }

    public List<Movie> getCatalogoUtente() {

        List<Movie> risultato = new ArrayList<>();

        if (utenteLoggato == null) {
            return risultato;
        }

        List<String> codici =
                csvManager.leggiCodiciUtente(utenteLoggato);

        for (Movie f : catalogo) {

            if (codici.contains(f.getCodice())) {
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

        List<Movie> giaPresenti = getCatalogoUtente();

        for (Movie f : giaPresenti) {
            if (f.getCodice().equals(codiceFilm)) {
                return false;
            }
        }

        csvManager.salvaPreferito(utenteLoggato, codiceFilm);

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

            csvManager.rimuoviPreferito(
                    utenteLoggato,
                    codiceFilm
            );

            return true;
        }

        return false;
    }

    // ================= MODIFICA =================

    public void modificaFilm(Movie film,
                             String nuovoNome,
                             String nuovaCategoria,
                             String nuovoProtagonista,
                             double nuovaDurata) {

        film.setNome(nuovoNome);
        film.setCategoria(nuovaCategoria);
        film.setProtagonista(nuovoProtagonista);
        film.setDurata(nuovaDurata);
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
    }
}