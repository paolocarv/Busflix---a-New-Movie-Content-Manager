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
        JMenuBar menuBar = new JMenuBar();

// ================= FILE =================
JMenu fileMenu = new JMenu("File");

JMenuItem apriItem = new JMenuItem("Apri");
JMenuItem salvaItem = new JMenuItem("Salva");
JMenuItem salvaConNomeItem =
        new JMenuItem("Salva con Nome");
JMenuItem esciItem = new JMenuItem("Esci");

fileMenu.add(apriItem);
fileMenu.add(salvaItem);
fileMenu.add(salvaConNomeItem);
fileMenu.addSeparator();
fileMenu.add(esciItem);

// ================= MODIFICA =================
JMenu modificaMenu = new JMenu("Modifica");

JMenuItem inserisciItem =
        new JMenuItem("Inserisci");

JMenuItem visualizzaItem =
        new JMenuItem("Visualizza Lista");

JMenuItem eliminaItem =
        new JMenuItem("Elimina Film");

modificaMenu.add(inserisciItem);
modificaMenu.add(visualizzaItem);
modificaMenu.add(eliminaItem);

// ================= INFO =================
JMenu infoMenu = new JMenu("Info");

JMenuItem aboutItem =
        new JMenuItem("About");

JMenuItem creditsItem =
        new JMenuItem("Credits");

infoMenu.add(aboutItem);
infoMenu.add(creditsItem);

// ================= AGGIUNTA MENU =================
menuBar.add(fileMenu);
menuBar.add(modificaMenu);
menuBar.add(infoMenu);

frame.setJMenuBar(menuBar);

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
    
    esciItem.addActionListener(e -> {

    System.exit(0);
});
    
    aboutItem.addActionListener(e -> {

    JOptionPane.showMessageDialog(
            frame,
            "BusFlix v1.0\nGestione Film MVC"
    );
});
    
    creditsItem.addActionListener(e -> {

    JOptionPane.showMessageDialog(
            frame,
            "Creato da Matteo"
    );
});
    
    apriItem.addActionListener(e -> {

    controller.apriFile();

    output.setText("File aperto!");
});
    
    salvaItem.addActionListener(e -> {

    controller.salvaFile();

    output.setText("File salvato!");
});
    
    salvaConNomeItem.addActionListener(e -> {

    controller.salvaConNome();

    output.setText("File salvato!");
});
    
    visualizzaItem.addActionListener(e -> {

    List<Movie> lista = controller.getCatalogo();

    output.setText("");

    for(Movie f : lista) {

        output.append(f + "\n");
    }
});
    
    inserisciItem.addActionListener(e -> {

    JDialog dialog =
            new JDialog(frame,
                    "Inserisci Film",
                    true);

    dialog.setSize(300,300);
    dialog.setLayout(new GridLayout(6,2));

    JTextField codice = new JTextField();
    JTextField nome = new JTextField();
    JTextField categoria = new JTextField();
    JTextField protagonista = new JTextField();

    JButton salva = new JButton("Salva");

    dialog.add(new JLabel("Codice"));
    dialog.add(codice);

    dialog.add(new JLabel("Nome"));
    dialog.add(nome);

    dialog.add(new JLabel("Categoria"));
    dialog.add(categoria);

    dialog.add(new JLabel("Protagonista"));
    dialog.add(protagonista);

    dialog.add(salva);

    salva.addActionListener(ev -> {

        output.append(
                "\nNuovo film inserito: "
                + nome.getText());

        dialog.dispose();
    });

    dialog.setVisible(true);
});
    
    eliminaItem.addActionListener(e -> {

    String codice =
            JOptionPane.showInputDialog(
                    "Codice film da eliminare:");

    int risposta =
            JOptionPane.showConfirmDialog(
                    frame,
                    "Vuoi eliminare il film?",
                    "Conferma",
                    JOptionPane.YES_NO_OPTION
            );

    if(risposta == JOptionPane.YES_OPTION) {

        boolean ok =
                controller.eliminaFilm(codice);

        if(ok) {

            output.setText(
                    "Film eliminato");
        }
    }
});



        frame.setVisible(true);
    }
}
