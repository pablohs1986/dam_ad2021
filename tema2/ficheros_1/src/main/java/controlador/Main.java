/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
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
        
        try {
            List<File> listadoFicheros = prueba.listarFicheros("/Users/pherrero/Downloads/", true, false);
            MetodosAuxiliares.mostrarListado(listadoFicheros);
        } catch (Excepciones.carpetaVacia ex) {
            System.out.println(ex.getMessage());
        } catch (Excepciones.noEsUnDirectorioNoSePuedeListar ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
