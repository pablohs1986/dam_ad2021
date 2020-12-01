/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Ejercicio1_2017dist;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejercicio 1
        Ejercicio1_2017dist ejercicio1 = new Ejercicio1_2017dist();
        ArrayList<File> listadoArchivos = new ArrayList<>();
        
        try {
            listadoArchivos = ejercicio1.listarArchivosTxtCsvQueEmpiezanPorVocal("./");
            ejercicio1.copiarLineasMalFormadas(listadoArchivos);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
