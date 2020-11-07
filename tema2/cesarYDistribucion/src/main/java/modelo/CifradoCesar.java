/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Herrero
 */
public class CifradoCesar {

    public void cifrarArchivosEnDirectorioConCesar(File directorio, int clave) {
        int contadorFicherosCifrados = 0;

        try {
            File[] listadoArchivos = listarFicherosTXT(directorio);
            for (File archivoTexto : listadoArchivos) {
                cifrarArchivoConCesar(archivoTexto, contadorFicherosCifrados, 5);
                contadorFicherosCifrados++;
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        
        System.out.println("\n" + contadorFicherosCifrados + " ficheros cifrados.");
    }

    public void descifrarArchivosEnDirectorioConCesar(File directorio, int clave) throws Exception {
        int contadorFicherosDescifrados = 0;

        try {
            File[] listadoArchivos = listarFicherosTXT(directorio);
            for (File archivoTexto : listadoArchivos) {
                if(archivoTexto.getName().startsWith("archivoCifrado")) { 
                    descifrarArchivoConCesar(archivoTexto, contadorFicherosDescifrados, clave);
                    contadorFicherosDescifrados++;
                }else {
                    throw new Exception("El archivo " + "\"" + archivoTexto.getName() + "\"" + "no está cifrado con Cesar. Archivo ignorado.");
                }
             
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        System.out.println("\n" + contadorFicherosDescifrados + " ficheros descifrados.");
    }

    public void cifrarArchivoConCesar(File archivoTexto, int numeroArchivo, int desplazamiento) {
        File archivoCifrado = new File("textos" + File.separator + "archivoCifrado" + numeroArchivo + ".txt");

        try {
            FileInputStream fis = new FileInputStream(archivoTexto);
            FileOutputStream fos = new FileOutputStream(archivoCifrado, true);

            System.out.println("\n" + "Cifrando el archivo " + "\"" + archivoTexto.getName() + "\"" + "...");

            int caracterLeido;
            int caracterCifrado;

            while ((caracterLeido = fis.read()) != -1) {
                caracterCifrado = caracterLeido + desplazamiento;
                fos.write(caracterCifrado);
                fos.flush();
            }

            System.out.println("\"" + archivoTexto.getName() + "\"" + " cifrado. Guardado como " + "\"" + archivoCifrado.getName() + "\"");

            fis.close();
            fos.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descifrarArchivoConCesar(File archivoTexto, int numeroArchivo, int desplazamiento) {
        File archivoDescifrado = new File("textos" + File.separator + "archivoDescifrado" + numeroArchivo + ".txt");

        try {
            FileInputStream fis = new FileInputStream(archivoTexto);
            FileOutputStream fos = new FileOutputStream(archivoDescifrado, true);

            System.out.println("\n" + "Descifrando el archivo " + "\"" + archivoTexto.getName() + "\"" + "...");

            int caracterLeido;
            int caracterCifrado;

            while ((caracterLeido = fis.read()) != -1) {
                caracterCifrado = caracterLeido - desplazamiento;
                fos.write(caracterCifrado);
                fos.flush();
            }

            System.out.println("\"" + archivoTexto.getName() + "\"" + " descifrado. Guardado como " + "\"" + archivoDescifrado.getName() + "\"");

            fis.close();
            fos.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static File[] listarFicherosTXT(File directorio) throws Exception {
        File[] listadoArchivos = null;
        if (directorio.isDirectory()) {
            listadoArchivos = CifradoCesar.filtroArchivosTexto(directorio);
        } else {
            throw new Exception("La ruta indicada no es un directorio. Operaci\u00f3n cancelada");
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
