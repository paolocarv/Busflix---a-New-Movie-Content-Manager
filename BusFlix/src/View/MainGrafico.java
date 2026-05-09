/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

import Controller.Gestore;
import java.io.IOException;

/**
 *
 * @author marcianof
 */
public class MainGrafico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Gestore g = new Gestore();
        System.out.println(g.LeggiCSV());
        System.out.println("CERCA PER CATEGORIA");
        System.out.println( g.cercaPerCategoria("Crime"));
        
    }
    
}
