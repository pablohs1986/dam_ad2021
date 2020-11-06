/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CifradoCesar;
import modelo.DistribucionLetras;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Apartado Cifrado Cesar
//        File directorio = new File("textos");
//        CifradoCesar cesar = new CifradoCesar();
        
//        cesar.cifrarArchivosEnDirectorioConCesar(directorio, 5);
//        try {
//            cesar.descifrarArchivosEnDirectorioConCesar(directorio, 5);
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        // Apartado Distribución Letras
        File archivoTextoCastellano = new File("textos" + File.separator + "lazarilloDeTormes_CASTELLANO.txt");
        File archivoTextoIngles= new File("textos" + File.separator + "lazarilloDeTormes_INGLES.txt");
        File prueba = new File("textos/prueba.txt");
        DistribucionLetras distribucion = new DistribucionLetras();
        
        try {
            distribucion.calcularDistribucionLetrasTexto(archivoTextoCastellano);
        } catch (Exception ex) {
            ex.getMessage();
        }
        
        try {
            distribucion.calcularDistribucionLetrasTexto(archivoTextoIngles);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
