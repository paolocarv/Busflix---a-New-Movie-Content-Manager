/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author matteo
 */
import Controller.BusFlixController;
import Model.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainGrafico {

    private static BusFlixController controller = new BusFlixController();

    public static void main(String[] args) {

        JFrame frame = new JFrame("BusFlix");
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // ================= LOGIN =================
        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);
        JButton loginBtn = new JButton("Login");

        frame.add(new JLabel("Username"));
        frame.add(userField);

        frame.add(new JLabel("Password"));
        frame.add(passField);

        frame.add(loginBtn);

        // ================= OUTPUT =================
        JTextArea output = new JTextArea(18, 50);
        output.setEditable(false);
        frame.add(new JScrollPane(output));

        // ================= BOTTONI =================
        JButton catalogoBtn = new JButton("Catalogo generale");
        JButton mieiFilmBtn = new JButton("Miei film");
        JButton aggiungiBtn = new JButton("Aggiungi film");
        JButton cercaNomeBtn = new JButton("Cerca Nome");
        JButton cercaCatBtn = new JButton("Cerca Categoria");
        JButton cercaProtBtn = new JButton("Cerca Protagonista");
        JButton cercaDurBtn = new JButton("Cerca Durata");

        JTextField codiceField = new JTextField(6);

        frame.add(catalogoBtn);
        frame.add(mieiFilmBtn);

        frame.add(new JLabel("Codice film:"));
        frame.add(codiceField);
        frame.add(aggiungiBtn);
        frame.add(cercaNomeBtn);
        frame.add(cercaCatBtn);
        frame.add(cercaProtBtn);
        frame.add(cercaDurBtn);

        // ================= LOGIN =================
        loginBtn.addActionListener(e -> {

            String user = userField.getText();
            String pass = new String(passField.getPassword());

            boolean ok = controller.login(user, pass);

            if (ok) {
                output.setText("Login riuscito!\nBenvenuto " + user);
            } else {
                output.setText("Login fallito");
            }
        });

        // ================= CATALOGO GENERALE =================
        catalogoBtn.addActionListener(e -> {

            List<Movie> lista = controller.getCatalogo();

            StringBuilder sb = new StringBuilder();

            for (Movie f : lista) {
                sb.append(f).append("\n");
            }

            output.setText(sb.toString());
        });

        // ================= CATALOGO UTENTE =================
        mieiFilmBtn.addActionListener(e -> {

            List<Movie> lista = controller.getCatalogoUtente();

            StringBuilder sb = new StringBuilder();

            if (lista.isEmpty()) {
                sb.append("Nessun film nel tuo catalogo");
            } else {
                for (Movie f : lista) {
                    sb.append(f).append("\n");
                }
            }

            output.setText(sb.toString());
        });

        // ================= AGGIUNGI FILM =================
        aggiungiBtn.addActionListener(e -> {

            String codice = codiceField.getText();

            boolean ok = controller.aggiungiFilm(codice);

            if (ok) {
                output.setText("Film aggiunto ai preferiti!");
            } else {
                output.setText("Errore o film già presente");
            }
        });
          cercaNomeBtn.addActionListener (e  
        -> {

    String input = JOptionPane.showInputDialog("Nome:");

        List<Movie> lista
                = controller.cercaPerNome(controller.getCatalogo(), input);

        output.setText("");

        for (Movie f : lista) {
            output.append(f + "\n");
        }
    }

    );


    cercaCatBtn.addActionListener (e  
        -> {

    String input = JOptionPane.showInputDialog("Categoria:");

        List<Movie> lista
                = controller.cercaPerCategoria(controller.getCatalogo(), input);

        output.setText("");

        for (Movie f : lista) {
            output.append(f + "\n");
        }
    }

    );


    cercaProtBtn.addActionListener (e  
        -> {

    String input = JOptionPane.showInputDialog("Protagonista:");

        List<Movie> lista
                = controller.cercaPerProtagonista(controller.getCatalogo(), input);

        output.setText("");

        for (Movie f : lista) {
            output.append(f + "\n");
        }
    }

    );


    cercaDurBtn.addActionListener (e  
        -> {

    String input = JOptionPane.showInputDialog("Durata max:");

        int max = Integer.parseInt(input);

        List<Movie> lista
                = controller.cercaPerDurata(controller.getCatalogo(), max);

        output.setText("");

        for (Movie f : lista) {
            output.append(f + "\n");
        }
    }

);



        frame.setVisible(true);
    }
}
