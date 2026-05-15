package View;

import Controller.BusFlixController;
import Model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainGrafico {

    private static BusFlixController controller
            = new BusFlixController();

    public static void main(String[] args) {

        JFrame frame = new JFrame("BUSFLIX");

        frame.setSize(1400, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        Color bg = new Color(18, 18, 18);
        Color panel = new Color(30, 30, 30);
        Color red = new Color(229, 9, 20);

        frame.getContentPane().setBackground(bg);

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBounds(0, 0, 1400, 80);
        header.setBackground(Color.BLACK);
        header.setLayout(null);

        JLabel titolo = new JLabel("BUSFLIX");
        titolo.setForeground(red);
        titolo.setFont(new Font("Arial", Font.BOLD, 40));
        titolo.setBounds(560, 15, 400, 50);

        header.add(titolo);
        frame.add(header);

        // ================= MENU =================
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem apriItem = new JMenuItem("Apri");
        JMenuItem salvaItem = new JMenuItem("Salva");
        JMenuItem salvaConNomeItem = new JMenuItem("Salva con Nome");
        JMenuItem esciItem = new JMenuItem("Esci");

        fileMenu.add(apriItem);
        fileMenu.add(salvaItem);
        fileMenu.add(salvaConNomeItem);
        fileMenu.addSeparator();
        fileMenu.add(esciItem);

        JMenu modificaMenu = new JMenu("Modifica");
        JMenuItem inserisciItem = new JMenuItem("Inserisci");
        JMenuItem visualizzaItem = new JMenuItem("Visualizza Lista");
        JMenuItem eliminaItem = new JMenuItem("Elimina Film");

        modificaMenu.add(inserisciItem);
        modificaMenu.add(visualizzaItem);
        modificaMenu.add(eliminaItem);

        JMenu infoMenu = new JMenu("Info");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem creditsItem = new JMenuItem("Credits");

        infoMenu.add(aboutItem);
        infoMenu.add(creditsItem);

        menuBar.add(fileMenu);
        menuBar.add(modificaMenu);
        menuBar.add(infoMenu);

        frame.setJMenuBar(menuBar);

        // ================= SIDEBAR =================
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 80, 250, 770);
        sidebar.setBackground(panel);
        sidebar.setLayout(null);

        frame.add(sidebar);

        JButton catalogoBtn = creaBottone("Catalogo", 25);
        JButton mieiFilmBtn = creaBottone("Miei Film", 85);
        JButton aggiungiBtn = creaBottone("Aggiungi", 145);
        JButton cercaNomeBtn = creaBottone("Cerca Nome", 205);
        JButton cercaCatBtn = creaBottone("Cerca Categoria", 265);
        JButton cercaProtBtn = creaBottone("Cerca Attore", 325);
        JButton cercaDurBtn = creaBottone("Cerca Durata", 385);

        sidebar.add(catalogoBtn);
        sidebar.add(mieiFilmBtn);
        sidebar.add(aggiungiBtn);
        sidebar.add(cercaNomeBtn);
        sidebar.add(cercaCatBtn);
        sidebar.add(cercaProtBtn);
        sidebar.add(cercaDurBtn);

        // ================= TABELLA =================
        String[] colonne = {
                "Codice",
                "Nome",
                "Categoria",
                "Protagonista",
                "Durata"
        };

        DefaultTableModel model = new DefaultTableModel(colonne, 0);
        JTable table = new JTable(model);

        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 15));

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(red);
        table.getTableHeader().setForeground(Color.WHITE);

        table.setBackground(new Color(25, 25, 25));
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.DARK_GRAY);

        table.setSelectionBackground(red);
        table.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(280, 110, 1080, 570);

        frame.add(scroll);

        // ================= LOGIN =================
        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(300, 720, 100, 30);

        JTextField userField = new JTextField();
        userField.setBounds(390, 720, 150, 30);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(560, 720, 100, 30);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(650, 720, 150, 30);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(830, 720, 120, 30);
        loginBtn.setBackground(red);
        loginBtn.setForeground(Color.WHITE);

        JLabel codiceLabel = new JLabel("Codice Film");
        codiceLabel.setForeground(Color.WHITE);
        codiceLabel.setBounds(980, 720, 100, 30);

        JTextField codiceField = new JTextField();
        codiceField.setBounds(1080, 720, 100, 30);

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginBtn);
        frame.add(codiceLabel);
        frame.add(codiceField);

        // ================= LOAD INIZIALE =================
        controller.apriFile();

        // ================= LOGIN =================
        loginBtn.addActionListener(e -> {

            String user = userField.getText();
            String pass = new String(passField.getPassword());

            boolean ok = controller.login(user, pass);

            if (ok) {
                JOptionPane.showMessageDialog(frame,
                        "LOGIN RIUSCITO\nBenvenuto " + user);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "LOGIN FALLITO");
            }
        });

        // ================= CATALOGO =================
        catalogoBtn.addActionListener(e -> {
            aggiornaTabella(model, controller.getCatalogo());
        });

        // ================= MIEI FILM =================
        mieiFilmBtn.addActionListener(e -> {
            aggiornaTabella(model, controller.getCatalogoUtente());
        });

        // ================= AGGIUNGI =================
        aggiungiBtn.addActionListener(e -> {

            String codice = codiceField.getText().trim();

            boolean ok = controller.aggiungiFilm(codice);

            if (ok) {
                JOptionPane.showMessageDialog(frame,
                        "Film aggiunto!");

                // 🔥 aggiorna subito lista corrente
                aggiornaTabella(model, controller.getCatalogoUtente());

            } else {
                JOptionPane.showMessageDialog(frame,
                        "Film già presente");
            }
        });

        // ================= RICERCHE =================
        cercaNomeBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Nome:");
            aggiornaTabella(model,
                    controller.cercaPerNome(controller.getCatalogo(), input));
        });

        cercaCatBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Categoria:");
            aggiornaTabella(model,
                    controller.cercaPerCategoria(controller.getCatalogo(), input));
        });

        cercaProtBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Protagonista:");
            aggiornaTabella(model,
                    controller.cercaPerProtagonista(controller.getCatalogo(), input));
        });

        cercaDurBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Durata max:");
            int max = Integer.parseInt(input);
            aggiornaTabella(model,
                    controller.cercaPerDurata(controller.getCatalogo(), max));
        });

        // ================= FILE =================
        apriItem.addActionListener(e -> {
            controller.apriFile();
            aggiornaTabella(model, controller.getCatalogo());
            JOptionPane.showMessageDialog(frame, "FILE APERTO");
        });

        salvaItem.addActionListener(e -> {
            controller.salvaFile();
            JOptionPane.showMessageDialog(frame, "FILE SALVATO");
        });

        salvaConNomeItem.addActionListener(e -> {
            controller.salvaConNome();
            JOptionPane.showMessageDialog(frame, "FILE SALVATO");
        });

        // ================= VISUALIZZA =================
        visualizzaItem.addActionListener(e -> {
            aggiornaTabella(model, controller.getCatalogo());
        });

        // ================= INSERISCI =================
        inserisciItem.addActionListener(e -> {

            JDialog dialog = new JDialog(frame, "Inserisci Film", true);
            dialog.setSize(350, 350);
            dialog.setLayout(null);

            JTextField codice = new JTextField();
            codice.setBounds(120, 30, 150, 30);

            JTextField nome = new JTextField();
            nome.setBounds(120, 80, 150, 30);

            JTextField categoria = new JTextField();
            categoria.setBounds(120, 130, 150, 30);

            JTextField protagonista = new JTextField();
            protagonista.setBounds(120, 180, 150, 30);

            JButton salva = new JButton("Salva");
            salva.setBounds(120, 240, 100, 35);

            dialog.add(new JLabel("Codice")).setBounds(30, 30, 80, 30);
            dialog.add(codice);

            dialog.add(new JLabel("Nome")).setBounds(30, 80, 80, 30);
            dialog.add(nome);

            dialog.add(new JLabel("Categoria")).setBounds(30, 130, 80, 30);
            dialog.add(categoria);

            dialog.add(new JLabel("Protagonista")).setBounds(30, 180, 100, 30);
            dialog.add(protagonista);

            dialog.add(salva);

            salva.addActionListener(ev -> {
                JOptionPane.showMessageDialog(frame,
                        "Nuovo film inserito: " + nome.getText());
                dialog.dispose();
            });

            dialog.setVisible(true);
        });

        // ================= ELIMINA =================
        eliminaItem.addActionListener(e -> {

            String codice = JOptionPane.showInputDialog("Codice film:");

            if (codice == null || codice.isEmpty()) return;

            int risposta = JOptionPane.showConfirmDialog(
                    frame,
                    "Eliminare il film?",
                    "Conferma",
                    JOptionPane.YES_NO_OPTION
            );

            if (risposta == JOptionPane.YES_OPTION) {

                boolean ok = controller.eliminaFilm(codice);

                if (ok) {
                    aggiornaTabella(model, controller.getCatalogoUtente());

                    JOptionPane.showMessageDialog(frame,
                            "FILM ELIMINATO");
                }
            }
        });

        // ================= ABOUT =================
        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "BusFlix v1.0"));

        creditsItem.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Creato da Matteo"));

        esciItem.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // ================= UPDATE =================
    private static void aggiornaTabella(DefaultTableModel model, List<Movie> lista) {

        model.setRowCount(0);

        for (Movie f : lista) {
            model.addRow(new Object[]{
                    f.getCodice(),
                    f.getNome(),
                    f.getCategoria(),
                    f.getProtagonista(),
                    f.getDurata() + " min"
            });
        }
    }

    // ================= BOTTONI =================
    private static JButton creaBottone(String testo, int y) {

        JButton btn = new JButton(testo);

        btn.setBounds(20, y, 200, 45);
        btn.setBackground(new Color(229, 9, 20));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 15));

        return btn;
    }
}