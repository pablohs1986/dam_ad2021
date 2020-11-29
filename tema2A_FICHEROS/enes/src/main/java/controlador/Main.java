/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import modelo.Lector;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File archivo = new File("enes2.txt");
        try {
            Lector.leerEnes(archivo);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
}
