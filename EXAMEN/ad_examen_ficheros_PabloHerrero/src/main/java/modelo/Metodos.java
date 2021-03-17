/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.excepciones.ArchivoNoEncontrado;

/**
 *
 * @author Pablo Herrero
 */
public class Metodos {

    /**
     * Genera un HashMap donde se almacena el la provincia (key) y el total de
     * casos para dicha provincia (value).
     *
     * @param nombreFichero Nombre del fichero csv donde están almacenados los
     * datos.
     * @return HashMap<String, Integer> Estructura de datos con los valores
     * indicados.
     * @throws ArchivoNoEncontrado Lanza esta excepción si no existe un archivo
     * con el nombre que se pasa como parámetro.
     */
    public static HashMap<String, Integer> generarMapaTotalCasosPorProvincia(String nombreFichero) throws ArchivoNoEncontrado, FileNotFoundException, IOException {
        HashMap<String, Integer> mapaTotalCasosProvincia = new HashMap<>();     // Estructura a retornar
        File archivoCsv = new File(nombreFichero);
        FileReader fr = new FileReader(archivoCsv);
        BufferedReader br = new BufferedReader(fr);
        String aux = br.readLine();

        ArrayList<String> listadoDatos = new ArrayList<String>();   // En esta lista almaceno todo el CSV

        if (!archivoCsv.exists()) {
            throw new ArchivoNoEncontrado("El archivo indicado no existe.");
        } else {
            while ((aux = br.readLine()) != null) {
                listadoDatos.add(aux);
            }

            for (String datosDia : listadoDatos) {
                String[] arrayCampos = datosDia.split(",");     // Para cada dato (linea), hago un array con el dato de cada campo.
                String provincia = arrayCampos[0];  // Asigno a la variable provincia el campo que me interesa, situado en el índice 0
                int numeroCasos = Integer.parseInt(arrayCampos[2]);     // Asigno a la variable provincia el campo que me interesa, situado en el índice 2
                mapaTotalCasosProvincia.merge(provincia, numeroCasos, Integer::sum);    // Introduzco key (Provincia) y value (número de casos). Uso merge para que incremente el número de casos.
            }
        }
        return mapaTotalCasosProvincia;
    }
}
