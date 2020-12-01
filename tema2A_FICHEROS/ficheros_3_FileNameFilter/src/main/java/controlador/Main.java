/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Filtros;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta = "/Users/pherrero/Downloads/carpetaVacia/";
        
//        Filtro.filtroImagenes(ruta);
//        Filtro.filtroVideo(ruta);
//        Filtros.filtroDirectorios(ruta);
//        Filtros.filtroFicherosTamanoMinimo(ruta, 4188006 );
        Filtros.filtroFicherosModificadosUltimas24h(ruta);
    }
    
}
