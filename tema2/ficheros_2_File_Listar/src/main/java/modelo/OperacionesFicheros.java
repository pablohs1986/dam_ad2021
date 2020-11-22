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
public class OperacionesFicheros implements FilenameFilter{
    public void listarContenidosDirectorio(String ruta) {
        File directorio = new File(ruta);
        
        if (directorio.exists()) {  
            File[] listadoArchivos = directorio.listFiles(); // Listo contenido si el directorio existe

            for (File listadoArchivo : listadoArchivos) { 
                
                if(listadoArchivo.isDirectory()) {
                    System.out.println(listadoArchivo.getPath());
                    listarContenidosDirectorio(listadoArchivo.getPath()); // Recursivo: si es un directorio, lanzo el método
                    System.out.println("______________________________________________________");
                }
                
                if(listadoArchivo.isFile()) {
                    System.out.println("--------- " + listadoArchivo.getName());
                }
            }
        }
    }
    
    public void listarFicherosAvi(String ruta) {
        File directorio = new File(ruta);
        
        if(directorio.exists()) {
            File[] listadoArchivos = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".avi");
                }
            });
            
            System.out.println("Mostrando ficheros en el directorio " + directorio.getPath() + ":");
            
            for (File listadoArchivo : listadoArchivos) {
                System.out.println(listadoArchivo.getName());
            }
        }
    }

    @Override
    public boolean accept(File dir, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
