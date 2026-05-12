/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

import Controller.BusFlixController;
import Model.CSVManager;
import Model.Movie;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CSVManager csv = new CSVManager();
        BusFlixController controller = new BusFlixController();

        // ======================
        // LOGIN
        // ======================
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        controller.login(username, password);

        // se vuoi bloccare se login fallisce (opzionale)
        // puoi migliorarlo dopo
        // ======================
        // CARICO CATALOGO
        // ======================
        List<Movie> catalogo = csv.leggiFilm();

        // ======================
        // MENU
        // ======================
        int scelta;

        do {
            System.out.println("\n=== BUSFLIX MENU ===");
            System.out.println("1 - Mostra catalogo generale");
            System.out.println("2 - Cerca per nome");
            System.out.println("3 - Cerca per categoria");
            System.out.println("4 - Cerca per protagonista");
            System.out.println("5 - Cerca per durata max");
            System.out.println("6 - Catalogo utente");
            System.out.println("7 - Aggiungi film al catalogo utente");
            System.out.println("0 - Esci");

            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine(); // pulizia buffer

            switch (scelta) {

                case 1:
                    controller.mostraCatalogo(catalogo);
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    controller.cercaPerNome(catalogo, nome);
                    break;

                case 3:
                    System.out.print("Categoria: ");
                    String cat = scanner.nextLine();
                    controller.cercaPerCategoria(catalogo, cat);
                    break;

                case 4:
                    System.out.print("Protagonista: ");
                    String p = scanner.nextLine();
                    controller.cercaPerProtagonista(catalogo, p);
                    break;

                case 5:
                    System.out.print("Durata max: ");
                    int max = scanner.nextInt();
                    controller.cercaPerDurata(catalogo, max);
                    break;

                case 6:
                    
                    controller.mostraCatalogoUtente();
                    break;

                case 7:
                    System.out.print("Codice film: ");
                    String codice = scanner.nextLine();
                    controller.aggiungiFilm(codice);
                    break;

                case 0:
                    System.out.println("Uscita...");
                    break;

                default:
                    System.out.println("Scelta non valida");
            }

        } while (scelta != 0);
    }
}
