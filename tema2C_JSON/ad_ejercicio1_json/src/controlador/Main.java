/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Metodos;
import static modelo.Metodos.generarFicheroJson;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Apartado A
            generarFicheroJson();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // Apartado B
            int totalLibros = Metodos.contarLibros();
            System.out.println("Total libros: " + totalLibros);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // Apartado C
            Metodos.mostrarTitulosLibros();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // Apartado D
            Metodos.mostrarNombreAutor1DelLibro2();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // Apartado E
            int valorLibros = Metodos.calcularValorLibrosStock();
            System.out.println("Valor libros en stock: " + valorLibros);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
