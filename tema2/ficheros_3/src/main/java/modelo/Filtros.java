/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pablo Herrero
 */

// TODO Falta Javadoc y repasar punto V

public class Filtros {
    public static void filtroImagenes(String ruta) {
        File directorio = new File(ruta);
        
        if(directorio.isDirectory()) {
            File[] listadoArchivos = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jpg") || name.endsWith(".gif") || name.endsWith(".tiff");
                }
            });
            
            if(listadoArchivos.length == 0) {
                System.out.println("En el directorio " + directorio.getName() + " no hay archivos de imagen.");
            } else {
                System.out.println("Mostrando archivos de imagen en " + directorio.getName() );
                for (File listadoArchivo : listadoArchivos) {
                    System.out.println(listadoArchivo.getName());
                }
            }
        }
    }
    
    public static void filtroVideo(String ruta) {
        File directorio = new File(ruta);

        if (directorio.isDirectory()) {
            File[] listadoArchivos = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".avi") || name.endsWith(".mp4") || name.endsWith(".mkv");
                }
            });

            if (listadoArchivos.length == 0) {
                System.out.println("En el directorio " + directorio.getName() + " no hay archivos de vídeo.");
            } else {
                System.out.println("Mostrando archivos de vídeo en " + directorio.getName());
                for (File listadoArchivo : listadoArchivos) {
                    System.out.println(listadoArchivo.getName());
                }
            }
        }
    }
    
    public static void filtroDirectorios(String ruta) {
        File directorio = new File(ruta);
        
        if(directorio.isDirectory()) {
            File[] listadoArchivos = directorio.listFiles(new FilenameFilter(){
                @Override
                public boolean accept(File dir, String name) { // dir es el directorio, no el archivo que se lee, por eso hay que crear el archivo leido
                    File ficheroLeido = new File(dir.getAbsolutePath()+"//"+name); // -> Revisar
                    return ficheroLeido.isDirectory();
                }
            });
            
            if (listadoArchivos.length == 0) {
                System.out.println("En el directorio " + directorio.getName() + " no hay subdirectorios.");
            } else {
                System.out.println("Mostrando subdirectorios en " + directorio.getName() + ":");
                for (File listadoArchivo : listadoArchivos) {
                    System.out.println(listadoArchivo.getName());
                }
            }
        }
    }
    
    public static void filtroFicherosTamanoMinimo(String ruta, int tamano) {
        File directorio = new File(ruta);

        if (directorio.isDirectory()) {
            File[] listadoArchivos = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    File ficheroLeido = new File(dir.getAbsolutePath()+"//"+name);
                    return ficheroLeido.length() >= tamano;
                }
            });

            if (listadoArchivos.length == 0) {
                System.out.println("En el directorio " + directorio.getName() + " no hay archivos de vídeo.");
            } else {
                System.out.println("Mostrando archivos de vídeo en " + directorio.getName());
                for (File listadoArchivo : listadoArchivos) {
                    System.out.println(listadoArchivo.getName());
                }
            }
        }
    }
    
    public static void filtroFicherosModificadosUltimas24h(String ruta) {
        File directorio = new File(ruta);
        long unDia = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    
        if (directorio.isDirectory()) {
            File[] listadoArchivos = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    File ficheroLeido = new File(dir.getAbsolutePath() + "//" + name);
                    return ficheroLeido.lastModified() <= unDia;
                }
            });

            if (listadoArchivos.length == 0) {
                System.out.println("En el directorio " + directorio.getName() + " no hay archivos modificados en las últimas 24h.");
            } else {
                System.out.println("Mostrando archivos de modificados en las últimas 24h:");
                for (File listadoArchivo : listadoArchivos) {
                    System.out.println(listadoArchivo.getName());
                }
            }
        }
    }
}
