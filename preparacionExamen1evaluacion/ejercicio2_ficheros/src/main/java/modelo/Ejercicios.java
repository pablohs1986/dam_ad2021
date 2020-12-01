/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Pablo Herrero
 */
public class Ejercicios {

    // Ejercicio 1
    public ArrayList<FileConColor> ordenarFicherosPorEstrellasYColores(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        FileConColor archivoColor;
        ArrayList<FileConColor> listadoArchivos = new ArrayList<>();

        if (directorio.isDirectory()) {
            File[] arrayArchivos = directorio.listFiles();
            for (File archivo : arrayArchivos) {
                archivoColor = asignarColoresYEstrellasAArchivo(archivo);
                listadoArchivos.add(archivoColor);
            }
        }
        ordenarPorEstrellas(listadoArchivos);
        ordenarPorColor(listadoArchivos);
        return listadoArchivos;
    }

    private void ordenarPorEstrellas(ArrayList<FileConColor> listadoArchivos) {
        Collections.sort(listadoArchivos, new Comparator<FileConColor>() {
            @Override
            public int compare(FileConColor o1, FileConColor o2) {
                return o1.getEstrellas() - o2.getEstrellas();
            }
        });
    }

    private void ordenarPorColor(ArrayList<FileConColor> listadoArchivos) {
        Collections.sort(listadoArchivos, new Comparator<FileConColor>() {
            @Override
            public int compare(FileConColor o1, FileConColor o2) {
                return (int) (o1.getColor() - o2.getColor());
            }
        });
    }

    public FileConColor asignarColoresYEstrellasAArchivo(File archivo) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Valora el archivo " + archivo.getName() + ":");
        System.out.println("\tColor (RGBY - Fomato RRR.GGG.BBB):");
        String colorCadena = sc.nextLine();
        colorCadena = colorCadena.replace(".", "");
        int colorNumero = Integer.parseInt(colorCadena);
        System.out.println("\tEstrellas (de 0 a 5):");
        int estrellas = sc.nextInt();

        FileConColor archivoColor = new FileConColor(colorNumero, estrellas, archivo.getName());
        return archivoColor;
    }

    // Ejercicio 2 [INCOMPLETO, NO ME DIO TIEMPO]
//    public void crackearArchivo(String rutaArchivo) throws FileNotFoundException, IOException {
//        File archivo = new File(rutaArchivo);
//        File archivoCrackeado = new File(rutaArchivo.replace(".dat", "_crackeado.dat"));
//        FileInputStream fis = new FileInputStream(archivo);
//        FileOutputStream fos = new FileOutputStream(archivoCrackeado);
//        
//        byte[] cadenaASustituir = new byte[] {65,66,67};
//        byte[] cadenaSustituta = new byte[] {66,66,66};
//        byte dato;
//        
//        while((dato = (byte) fis.read()) != -1) {
//            System.out.println("Byte original" + dato);
//            for (int i = 0; i < cadenaASustituir.length; i++) {
//                if (cadenaASustituir[i] == dato) {
//                    for (int j = 0; j < cadenaSustituta.length; j++) {
//                        dato = cadenaSustituta[j];
//                    }
//                }
//
//            }
//            System.out.println("Nuevo byte" + dato);
//            fos.write(dato);
//        }
//        fis.close();
//        fos.close();
//    }
    // Ejercicio 3
    public void dividirArchivoEnNPartes(String rutaArchivo, int numeroPartes) throws FileNotFoundException, IOException {
        File archivoOriginal = new File(rutaArchivo);
        FileInputStream fis = new FileInputStream(archivoOriginal);
        long tamanoArchivo = archivoOriginal.length();

        for (int i = 0; i < numeroPartes; i++) {
            byte[] arrayBytes = fis.readNBytes((int) (tamanoArchivo / numeroPartes));
            File ficheroParte = new File(rutaArchivo + String.valueOf(i));
            FileOutputStream fos = new FileOutputStream(ficheroParte);
            fos.write(arrayBytes);
            fos.close();
        }
    }

    // Ejercicio 4
    public void unirPartesDeFichero(String rutaArchivoOriginal, int numeroPartes) throws FileNotFoundException, IOException {
        File archivoDestino = new File(rutaArchivoOriginal.replace(".png", "_partesUnidas.png"));
        FileOutputStream fos = new FileOutputStream(archivoDestino);
        int dato;

        for (int parte = 0; parte < numeroPartes; parte++) {
            File archivoEntrada = new File(rutaArchivoOriginal + String.valueOf(parte));
            FileInputStream fis = new FileInputStream(archivoEntrada);
            while ((dato = fis.read()) != -1) {
                fos.write(dato);
            }
            fis.close();
        }
        fos.close();
    }
}
