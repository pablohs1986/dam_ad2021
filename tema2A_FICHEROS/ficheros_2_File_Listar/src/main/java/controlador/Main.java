/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.*;

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
        
//        prueba.listarContenidosDirectorio("/Users/pherrero/Downloads/carpetaVacia/");
        prueba.listarFicherosAvi("/Users/pherrero/Downloads/carpetaVacia/");
        
    }
    
}
