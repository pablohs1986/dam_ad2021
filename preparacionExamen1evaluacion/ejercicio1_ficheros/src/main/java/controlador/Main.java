package controlador;

import modelo.Ejercicios;

import java.io.File;
import java.io.IOException;

/**
 * @author Pablo herrero
 */
public class Main {
    public static void main(String[] args) {
        Ejercicios ejercicio = new Ejercicios();
        File archivo;
        String directorio = "/Users/pherrero/Documents/Repositorios/dam_ad2021/preparacionExamen1evaluacion/ejercicio1_ficheros/";

        // Ejejercicio 1
        String nombreArchivo = "prueba.txt";

        try {
            archivo = ejercicio.buscarArchivoEnDirectorio(nombreArchivo, directorio);
            System.out.println("Archivo encontrado: " + archivo.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ejercicio 2
        try {
            File archivoConFraseReemplazada = ejercicio.buscarCadenaEnArchivoYReemplazar(directorio+nombreArchivo, "acceso a datos", "JAVA");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ejercicio 3
        String archivoDatos = "datos.csv";
        try {
            ejercicio.dividirDatosEnArchivosPorPaises(directorio+archivoDatos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
