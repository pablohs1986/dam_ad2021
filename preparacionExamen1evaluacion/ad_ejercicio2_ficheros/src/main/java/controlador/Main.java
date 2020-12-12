/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Ejercicios;
import modelo.FileConColor;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ejercicios ejercicio = new Ejercicios();
        
        // Ejercicio 1
//        ArrayList<FileConColor> listadoArchivosOrdenadosEstrellasYColores = new ArrayList<>();
//        listadoArchivosOrdenadosEstrellasYColores = ejercicio.ordenarFicherosPorEstrellasYColores("./archivosAOrdenar/");
//        for (FileConColor archivo : listadoArchivosOrdenadosEstrellasYColores) {
//            System.out.println(archivo);
//            System.out.println("\tEstrellas: " + archivo.getEstrellas() + "\tColor: " + archivo.getColor());
//        }

        //Ejercicio 2
//        try {
//            ejercicio.crackearArchivo("./bytes.dat");
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

        // Ejercicio 3
//        try {
//            ejercicio.dividirArchivoEnNPartes("./java.png", 2);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        // Ejercicio 4
//        try {
//            ejercicio.unirPartesDeFichero("./java.png", 2);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
