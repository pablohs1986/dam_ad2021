/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.*;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OperacionesFicheros prueba = new OperacionesFicheros();
        
        // Apartado A
//        try {
//            List<File> listadoFicheros = prueba.listarFicheros("/Users/pherrero/Downloads/", true, false);
//            MetodosAuxiliares.mostrarListado(listadoFicheros);
//        } catch (Excepciones.carpetaVacia ex) {
//            System.out.println(ex.getMessage());
//        } catch (Excepciones.noEsUnDirectorioNoSePuedeListar ex) {
//            System.out.println(ex.getMessage());
//        }
        
        // Apartado B
        int numeroDirectoriosCreados;
        File ruta = new File("/Users/pherrero/Downloads/");
        ArrayList<String> listadoDirectoriosACrear = new ArrayList<>();
        listadoDirectoriosACrear.add("directorioPrueba1");
        listadoDirectoriosACrear.add("directorioPrueba6");
        listadoDirectoriosACrear.add("directorioPrueba2");
        
        try {
            numeroDirectoriosCreados = (prueba.crearDirectorios(ruta, listadoDirectoriosACrear));
            System.out.println(numeroDirectoriosCreados + " directorios creados.");
        } catch (Excepciones.laRutaIndicadaNoExiste ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Excepciones.elDirectorioYaExiste ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
