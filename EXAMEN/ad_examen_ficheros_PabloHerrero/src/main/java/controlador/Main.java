/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Metodos;
import modelo.excepciones.ArchivoNoEncontrado;

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
            HashMap<String, Integer> mapaTotalCasosProvincia = Metodos.generarMapaTotalCasosPorProvincia("datos_provincias.csv");
            System.out.println(mapaTotalCasosProvincia);
        } catch (ArchivoNoEncontrado ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
