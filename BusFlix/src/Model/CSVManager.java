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

public class CSVManager {

    public boolean controllaLogin(String username,
            String password) {

        try {

            BufferedReader br
                    = new BufferedReader(
                            new FileReader("utenti.csv"));

            String riga;

            br.readLine();

            while ((riga = br.readLine()) != null) {

                String[] dati = riga.split(",");

                String usernameCSV = dati[2];
                String passwordCSV = dati[3];

                if (username.equals(usernameCSV)
                        && password.equals(passwordCSV)) {

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

    public List<Movie> leggiFilm() {

        List<Movie> listaFilm = new ArrayList<>();

        try {

            BufferedReader br
                    = new BufferedReader(
                            new FileReader("catalogo.csv"));

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

            BufferedReader br
                    = new BufferedReader(new FileReader("referenza.csv"));

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

        BufferedWriter bw =
                new BufferedWriter(
                        new FileWriter("referenza.csv", true));

        bw.write(username + "," + codiceFilm);
        bw.newLine();

        bw.close();

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}
