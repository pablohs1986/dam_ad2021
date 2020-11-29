package controlador;

import modelo.Ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pablo herrero
 */
public class Main {
    public static void main(String[] args) {
        Ejercicios ejercicio = new Ejercicios();
        File archivo;
        String directorio = "./";

        // Ejejercicio 1
        try {
            File archivo1 = ejercicio.buscarArchivoEnDirectorio("texto.txt", "./");
            System.out.println("Archivo encontrado: " + archivo1.getName() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ejercicio 2
        try {
            File archivo2 = ejercicio.buscarCadenaEnArchivoYReemplazar("texto.txt", "acceso a datos", "JAVA");
            System.out.println("Cadena reemplazada, generado nuevo archivo " + archivo2.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ejercicio 3
        try {
            ejercicio.dividirDatosEnArchivosPorPaises("datos.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            // Ejercicio 4
            File archivo3 = ejercicio.modificarPalabrasProhibidas("palabrasProhibidas.txt", "textoACorregir.txt");
            System.out.println("Palabras prohibidas modificadas, generado nuevo archivo " + archivo3.getName() + "\n");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
