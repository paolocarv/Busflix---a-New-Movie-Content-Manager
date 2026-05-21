/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author matteo
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class CSVManager {

    public boolean controllaLogin(String username,
            String password) {

        try {

            BufferedReader br = new BufferedReader(new FileReader("utenti.csv"));

            String riga;

            br.readLine();

            while ((riga = br.readLine()) != null) {

                String[] dati = riga.split(",");

                String usernameCSV = dati[2];
                String passwordCSV = dati[3];

                if (username.equals(usernameCSV) && password.equals(passwordCSV)) {
                    br.close();
                    return true;
                }
            }

            br.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    

    public void salvaSuFile(File file, List<Movie> listaFilm) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            //intestazione CSV
            bw.write("codice,nome,durata,episodi,protagonista,categoria,data");
            bw.newLine();

            for (Movie film : listaFilm) {

                bw.write(
                        film.getCodice() + ","
                        + film.getNome() + ","
                        + film.getDurata() + ","
                        + film.getEpisodi() + ","
                        + film.getProtagonista() + ","
                        + film.getCategoria() + ","
                        + film.getData()
                );

                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public List<Movie> caricaDaFile(File file) {

    List<Movie> listaFilm = new ArrayList<>();

    try {

        BufferedReader br = new BufferedReader(new FileReader(file));

        String riga;

        br.readLine();

        while ((riga = br.readLine()) != null) {

            String[] dati = riga.split(",");

            Movie film = new Movie(
                    dati[0],
                    dati[1],
                    Double.parseDouble(dati[2]),
                    Integer.parseInt(dati[3]),
                    dati[4],
                    dati[5],
                    dati[6]
            );

            listaFilm.add(film);
        }

        br.close();

    } catch (Exception e) {

        e.printStackTrace();
    }

    return listaFilm;
}
    
    public void rimuoviPreferito(String username,String codiceFilm) {
        
    try {

        List<String> righe = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("referenza.csv"));

        String riga;

        while ((riga = br.readLine()) != null) {

            String[] dati = riga.split(",");

            boolean daEliminare = dati[0].equals(username) && dati[1].equals(codiceFilm);

            if (!daEliminare) {
                righe.add(riga);
            }
        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter("referenza.csv"));

        for (String s : righe) {
            bw.write(s);
            bw.newLine();
        }

        bw.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void aggiornaFilm(List<Movie> listaFilm,Movie filmModificato) {

        for (Movie film : listaFilm) {

            if (film.getCodice().equals(filmModificato.getCodice())) {

                film.setNome(filmModificato.getNome());
                film.setCategoria(filmModificato.getCategoria());
                film.setDurata(filmModificato.getDurata());
                film.setProtagonista(filmModificato.getProtagonista());
            }
        }
}

    public List<Movie> leggiFilm() {

        List<Movie> listaFilm = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader("catalogo.csv"));

            String riga;

            br.readLine();

            while ((riga = br.readLine()) != null) {

                String[] dati = riga.split(",");

                String codice = dati[0];
                String nome = dati[1];
                double durata = Double.parseDouble(dati[2]);
                int episodi = Integer.parseInt(dati[3]);
                String protagonista = dati[4];
                String categoria = dati[5];
                String data = dati[6];

                Movie film = new Movie(
                        codice,
                        nome,
                        durata,
                        episodi,
                        protagonista,
                        categoria,
                        data
                );

                listaFilm.add(film);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaFilm;
    }

    public List<String> leggiCodiciUtente(String username) {

        List<String> codici = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("referenza.csv"));
            
            String riga;
            br.readLine();

            while ((riga = br.readLine()) != null) {

                String[] d = riga.split(",");

                if (d[0].equals(username)) {
                    codici.add(d[1]);
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return codici;
    }

    public void salvaPreferito(String username, String codiceFilm) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("referenza.csv", true));

            bw.write(username + "," + codiceFilm);
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
