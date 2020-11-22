package modelo;

import java.io.*;
import java.util.*;

public class Ejercicio5 {
    public HashMap<String, HashMap<String, Integer>> generarMapPalabrasTxtDirectorio(String rutaDirectorio) throws Exception {
        File directorio = new File(rutaDirectorio);
        File[] arrayArchivos;
        FileReader fr;
        BufferedReader br;
        String aux;
        String[] arrayPalabras;
        HashMap<String, HashMap<String, Integer>> mapaFrecuenciaPalabrasFicheros = new HashMap<>();

        if(directorio.isDirectory()) {
            arrayArchivos = filtroArchivosTxt(directorio);

            for (File archivo: arrayArchivos) {
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                HashMap<String, Integer> mapaPalabras = new HashMap<>();

                while((aux=br.readLine()) != null) {
                    arrayPalabras = aux.split(" ");
                    for (String palabra: arrayPalabras) {
                        mapaPalabras.merge(palabra, 1, Integer::sum);
                    }
                }
                System.out.println("Calculando frecuencia de palabras para el archivo " + archivo.getName());
                mapaFrecuenciaPalabrasFicheros.put((archivo.getName()), mapaPalabras);
            }
        } else{
            throw new Exception("La ruta pasada no es un directorio. Operación cancelada.");
        }
        return mapaFrecuenciaPalabrasFicheros;
    }

    public File[] filtroArchivosTxt(File directorio) {
        File[] listadoArchivosTxt = directorio.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });

        if(listadoArchivosTxt.length == 0) {
            System.out.println("En el directorio " + directorio.getName() + " no hay archivos de texto.");
        } else {
            System.out.println("Archivos de texto encontrados.");
        }
        return listadoArchivosTxt;
    }


    public HashMap<String, Integer> generarMapPalabras(File archivo) throws IOException {
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        HashMap<String, Integer> mapaPalabras = new HashMap<String, Integer>();
        String s;
        String[] arrayPalabras = null;

        while ((s = br.readLine()) != null) {
            arrayPalabras = s.split(" ");
            for (String palabra : arrayPalabras) {
                mapaPalabras.merge(palabra, 1, Integer::sum);
            }
        }
        return mapaPalabras;
    }

    public void ponerLineasDelReves(File archivo, File archivoAEscribir) throws IOException {
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(archivoAEscribir);
        BufferedWriter bw = new BufferedWriter(fw);
        String s;

        while ((s = br.readLine()) != null) {
            StringBuilder bd = new StringBuilder(s);
            bw.write((bd.reverse()).toString());
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        System.out.println("Creado archivo " + archivoAEscribir.getName() + " con las líneas del Quijote escritas al revés.");
    }

    public int contarLetras(File archivo) throws IOException {
        int numeroLetras = 0;
        String[] arrayPalabras = null;
        String s;
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        while ((s = br.readLine()) != null) {
            arrayPalabras = s.split(" ");
            for (String palabra : arrayPalabras) {
                for (int i = 0; i < palabra.length(); i++) {
                    if ((palabra.charAt(i) != ',') && (palabra.charAt(i) != ';') && (palabra.charAt(i) != ':')
                            && (palabra.charAt(i) != ';') && (palabra.charAt(i) != '?') && (palabra.charAt(i) != '¿')
                            && (palabra.charAt(i) != '@') && (palabra.charAt(i) != '!') && (palabra.charAt(i) != '¡')
                            && (palabra.charAt(i) != '-') && (palabra.charAt(i) != '_')) {
                        numeroLetras++;
                    }
                }
            }
        }
        br.close();
        return numeroLetras;
    }

    public int contarPalabra(File archivo, String palabraAContar) throws IOException {
        int contadorPalabra = 0;
        String[] arrayPalabras = null;
        String s;
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        while ((s = br.readLine()) != null) {
            arrayPalabras = s.split(" ");
            for (String palabra : arrayPalabras) {
                if (palabra.contains(palabraAContar)) {
                    contadorPalabra++;
                }
            }
        }
        br.close();
        return contadorPalabra;
    }

    public int contarLineas(File archivo) throws IOException {
        int contadorLineas = 0;
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        while (br.readLine() != null) {
            contadorLineas++;
        }
        br.close();
        return contadorLineas;
    }

    public File buscarQuijoteEnDisco(String rutaDirectorio) throws Exception {
        File directorio = new File(rutaDirectorio);
        File quijote = null;

        if (directorio.isDirectory()) {
            File[] arrayFicheros = directorio.listFiles();
            for (File fichero : arrayFicheros) {
                if (fichero.getName().equals("el_quijote.txt")) {
                    System.out.println("Quijote encontrado!");
                    quijote = fichero;
                }
            }
        } else if (!directorio.isDirectory()) {
            throw new Exception("La ruta no es un directorio. Operación cancelada.");
        }
        return quijote;
    }


}
