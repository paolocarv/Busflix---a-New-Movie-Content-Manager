/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Film;
import java.util.ArrayList;


/**
 *
 * @author carvellip
 */
public class Gestore {
    ArrayList <Film> gestionefilm = new ArrayList();
    
    public Film cercaPerCodice(String codice){
        for(Film f : gestionefilm){
            if(f.getCodice().equals(codice)){
                return f;
            }
        }
        return null;
    }
    
    public ArrayList<Film> cercaPerCategoria(String categoria){
        ArrayList<Film> gestcategoria = new ArrayList();
        for(Film f : gestcategoria){
            if(f.getCategoria().equals(categoria)){
                gestcategoria.add(f);
            }
        }
        return gestcategoria;
    }
    
    public ArrayList<Film> cercaPerProtagonista(String protagonista){
        ArrayList<Film> gestprotagonista = new ArrayList();
        for(Film f : gestprotagonista){
            if(f.getProtagonista().equals(protagonista)){
                gestprotagonista.add(f);
            }
        }
        return gestprotagonista;
    }
   
}
