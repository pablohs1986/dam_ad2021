/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Pablo Herrero
 */
public class MetodosAuxiliares {
    
    public static File[] listarFicherosTXT(File directorio) throws Exception {
        File[] listadoArchivos = null;
        
        if (directorio.isDirectory()) {
            listadoArchivos = MetodosAuxiliares.filtroArchivosTexto(directorio);
        }else {
            throw new Exception("La ruta indicada no es un directorio. Operación cancelada");
        }
        return listadoArchivos;
    }
    
    public static File[] filtroArchivosTexto(File directorio) {
        File[] listadoArchivos = null;

        if (directorio.isDirectory()) {
            listadoArchivos = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".txt");
                }
            });

            if (listadoArchivos.length == 0) {
                System.out.println("En el directorio " + directorio.getName() + " no hay archivos de texto.");
            } else {
                return listadoArchivos;
            }
        }
        return listadoArchivos;
    }
}
