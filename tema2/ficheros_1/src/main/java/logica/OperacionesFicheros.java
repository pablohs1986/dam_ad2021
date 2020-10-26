/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Clase con diversos métodos para manejar ficheros.
 * @version 1.1, 25/10/2020
 * @author Pablo Herrero
 */
public class OperacionesFicheros {
    /**
     * Apartado A:
     * Lista los ficheros de un directorio, tomando una ruta dada o el root del
     * OS, permitiendo ordenar el resultado alfabéticamente y/o mostrar sólo
     * los directorios.
     * @param ruta La ruta del directorio.
     * @param ordenadosPorNombre True si se desea ordenar por nombre.
     * @param soloDirectorios True si se desea mostrar sólo los directorios.
     */
    public List<File> listarFicheros(String ruta, boolean ordenadosPorNombre, boolean soloDirectorios) throws Excepciones.carpetaVacia, Excepciones.noEsUnDirectorioNoSePuedeListar {
        File[] root = File.listRoots();
        File[] arrayFicheros;
        List<File> listaFicheros = new ArrayList<>();
        File directorio;
        
        // I. Si la ruta está vacía, se asigna el root dependiendo del OS.
        if (ruta.isEmpty()) {
            for (int i = 0; i < root.length; i++) {
                ruta = root[i].getPath();
                System.out.println("Unidad " + i + ": " + root[i]);
            }
        }
        
        directorio = new File(ruta); // Asigno al directorio la ruta
        
        // IV. Compruebo si es un directorio, si no, lanzo excepción
        if (!directorio.isDirectory()) {    
            throw new Excepciones.noEsUnDirectorioNoSePuedeListar();
        } else {
            arrayFicheros = directorio.listFiles(); // Almaceno temporalmente en un array.
            
            // IV. Compruebo si el directorio está vacío, de ser así, lanzo excepción.
            if (arrayFicheros.length == 0) {    
                throw new Excepciones.carpetaVacia();
            } else {
                // II & III. Se implementa ordenadosPorNombre y soloDirectorios.
                if (!ordenadosPorNombre && !soloDirectorios) {
                    listaFicheros = Arrays.asList(arrayFicheros);
                }

                if (ordenadosPorNombre && !soloDirectorios) {
                    listaFicheros = Arrays.asList(arrayFicheros);
                    Collections.sort(listaFicheros);
                }

                if (soloDirectorios && !ordenadosPorNombre) {
                    FileFilter filtrarDirectorios = new FileFilter() {
                        @Override
                        public boolean accept(File dir) {
                            return dir.isDirectory();
                        }
                    };
                    arrayFicheros = directorio.listFiles(filtrarDirectorios);
                    listaFicheros = Arrays.asList(arrayFicheros);
                }

                if (ordenadosPorNombre && soloDirectorios) {
                    FileFilter filtrarDirectorios = new FileFilter() {
                        @Override
                        public boolean accept(File dir) {
                            return dir.isDirectory();
                        }
                    };
                    arrayFicheros = directorio.listFiles(filtrarDirectorios);
                    listaFicheros = Arrays.asList(arrayFicheros);
                    Collections.sort(listaFicheros);
                }
            }
        }
        return listaFicheros;
    }
    
    public int crearDirectorios(File rutaOrigen, ArrayList<String> listaDirectorios) throws Excepciones.laRutaIndicadaNoExiste,Excepciones.elDirectorioYaExiste {
        int contadorDirectoriosCreados = 0;
        File directorioACrear;
        String rutaDirectorio;
        
        if (rutaOrigen.exists() == false) {
            throw new Excepciones.laRutaIndicadaNoExiste();
        } else {
            for (String listaDirectorio : listaDirectorios) {
                rutaDirectorio = rutaOrigen.getPath() + "/" + listaDirectorio; // Concateno la rutaOrigen y el nombre del directorio
                System.out.println(rutaDirectorio);
                directorioACrear = new File(rutaDirectorio);
                if(!directorioACrear.exists()){
                    directorioACrear.mkdir(); // Uso mkdir() porque no quiero que cree los padres
                    System.out.println("Directorio " + rutaDirectorio + " creado.");
                    contadorDirectoriosCreados++;
                } else if(directorioACrear.exists()) {
                    throw new Excepciones.elDirectorioYaExiste(); // TODO -> Me para el programa al lanzar la excepción. Revisar
                }
            }
        }
        return contadorDirectoriosCreados;
    }
}
