package controlador;

import modelo.Ejercicio5;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Ejercicio5 ejercicio = new Ejercicio5();
        File quijote = null;
        File quijoteReves = new File("quijoteReves.txt");
        int numeroLineasQuijote = 0;
        int numeroPalabras = 0;
        int numeroLetras = 0;
        HashMap<String, Integer> mapaPalabras = new HashMap<String, Integer>();
        HashMap<String, HashMap<String, Integer>> mapaFrecuenciaPalabrasFicheros  = new HashMap<String, HashMap<String, Integer>>();

        // Apartado A
        try {
            quijote = ejercicio.buscarQuijoteEnDisco("/Users/pherrero/Documents/Repositorios/dam_ad2021/tema2/ficheros_5_FR_BR_FW_BW");
        } catch (Exception e) {
            e.getMessage();
        }

        // Apartado B
        try {
            numeroLineasQuijote = ejercicio.contarLineas(quijote);
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("El quijote tiene " + numeroLineasQuijote + " líneas.");

        // Apartado C
        try {
            numeroPalabras = ejercicio.contarPalabra(quijote, "Quijote");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("La palabra Quijote se repite " + numeroPalabras + " veces");

        // Apartado D
        try {
            numeroLetras = ejercicio.contarLetras(quijote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("El quijote tiene " + numeroLetras + " letras.");

        // Apartado E
        try {
            ejercicio.ponerLineasDelReves(quijote, quijoteReves);
        } catch (IOException e) {
            e.getMessage();
        }

        // Apartado F
        try {
            mapaPalabras = ejercicio.generarMapPalabras(quijote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mapaPalabras);

        // Apartado G
        try {
            mapaFrecuenciaPalabrasFicheros = ejercicio.generarMapPalabrasTxtDirectorio("/Users/pherrero/Documents/Repositorios/dam_ad2021/tema2/ficheros_5_FR_BR_FW_BW");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, HashMap<String, Integer>> elementoMapa:  mapaFrecuenciaPalabrasFicheros.entrySet()) {
            System.out.println(elementoMapa.getKey());
            System.out.println(elementoMapa.getValue());
        }

    }
}
