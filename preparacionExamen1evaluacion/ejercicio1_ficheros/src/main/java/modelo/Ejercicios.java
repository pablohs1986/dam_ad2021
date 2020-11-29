package modelo;

import java.io.*;

/**
 * @author Pablo herrero
 */
public class Ejercicios {
    public File buscarArchivoEnDirectorio(final String archivoABuscar, String directorioBusqueda) throws Exception {
        File directorio = new File(directorioBusqueda);
        File archivoBuscado = null;

        if (directorio.isDirectory()) {
            File[] listadoArchivos = directorio.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.contentEquals(archivoABuscar);
                }
            });

            if (listadoArchivos.length == 0) {
                throw new Exception("El archivo " + archivoABuscar + " no se ha encontrado en el directorio " + directorio.getName() + ". Operación cancelada.");
            } else {
                for (File archivo : listadoArchivos) {
                    if (archivo.getName().equals(archivoABuscar)) {
                        archivoBuscado = archivo;
                    }
                }
            }
        } else {
            throw new Exception("La ruta no pertenece a un directorio. Operación cancelada.");
        }
        return archivoBuscado;
    }

    public File buscarCadenaEnArchivoYReemplazar(String rutaArchivo, String fraseABuscar, String nuevaFrase) throws IOException {
        File archivo = new File(rutaArchivo);
        File archivoConFraseReemplazada = new File(rutaArchivo.replace(".txt", "fraseReemplazada.txt"));
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(archivoConFraseReemplazada);
        BufferedWriter bw = new BufferedWriter(fw);
        String linea;

        while((linea=br.readLine()) != null) {
            if (linea.contains(fraseABuscar)) {
                linea = linea.replace(fraseABuscar, nuevaFrase);
            }
            fw.write(linea);
        }
        fw.close();
        br.close();

        return archivoConFraseReemplazada;
    }

    public void dividirDatosEnArchivosPorPaises(String rutaArchivo) throws IOException {
        File archivoOriginal = new File(rutaArchivo);
        FileReader fr = new FileReader(archivoOriginal);
        BufferedReader br = new BufferedReader(fr);

        String nombreArchivoPais;
        BufferedWriter bw = null;

        String aux = "";

        while((aux=br.readLine())!=null) {
            String pais = aux.substring(0,2);
            switch (pais) {
                case "ES":
                    nombreArchivoPais = "es.csv";
                    bw = new BufferedWriter(new FileWriter(nombreArchivoPais, true));
                    bw.write(aux);
                    bw.newLine();
                    bw.flush();
                    break;
                case "FR":
                    nombreArchivoPais = "fr.csv";
                    bw = new BufferedWriter(new FileWriter(nombreArchivoPais, true));
                    bw.write(aux);
                    bw.newLine();
                    bw.flush();
                    break;
                case "EN":
                    nombreArchivoPais = "en.csv";
                    bw = new BufferedWriter(new FileWriter(nombreArchivoPais, true));
                    bw.write(aux);
                    bw.newLine();
                    bw.flush();
                    break;
            }
        }
        bw.close();
        br.close();
    }

}
