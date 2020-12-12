package modelo;

import java.io.*;
import java.util.StringTokenizer;

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

    public File buscarCadenaEnArchivoYReemplazar(String rutaArchivo, String cadenaABuscar, String nuevaCadena) throws IOException {
        File archivo = new File(rutaArchivo);
        File archivoConCadenaReemplazada = new File(rutaArchivo.replace(".txt", "_cadenaReemplazada.txt"));
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoConCadenaReemplazada));
        String linea;

        while ((linea = br.readLine()) != null) {
            if (linea.contains(cadenaABuscar)) {
                linea = linea.replace(cadenaABuscar, nuevaCadena);
            }
            bw.write(linea);
            bw.newLine();
        }
        bw.close();
        br.close();
        return archivoConCadenaReemplazada;
    }

    public void dividirDatosEnArchivosPorPaises(String rutaArchivo) throws FileNotFoundException, IOException {
        File archivoOriginal = new File(rutaArchivo);
        FileReader fr = new FileReader(archivoOriginal);
        BufferedReader br = new BufferedReader(fr);
        String nombreArchivoPais;
        BufferedWriter bw = null;
        String linea = "";

        while ((linea = br.readLine()) != null) {
            StringTokenizer tokens = new StringTokenizer(linea, ",");
            while (tokens.hasMoreTokens()) {
                String pais = tokens.nextToken();
                for (int i = 0; i < 3; i++) {
                    tokens.nextToken();
                }
                nombreArchivoPais = detectarPais(pais);
                bw = new BufferedWriter(new FileWriter(nombreArchivoPais, true));
                bw.write(linea);
                bw.newLine();
                bw.flush();
            }
        }
        bw.close();
        br.close();
    }

    public String detectarPais(String pais) {
        String nombreArchivoPais = null;
        switch (pais) {
            case "ES":
                nombreArchivoPais = "es.csv";
                break;
            case "FR":
                nombreArchivoPais = "fr.csv";
                break;
            case "EN":
                nombreArchivoPais = "en.csv";
                break;
        }
        return nombreArchivoPais;
    }
    
    public File modificarPalabrasProhibidas(String rutaArchivoPalabrasProhibidas, String rutaArchivoACorregir) throws FileNotFoundException, IOException {
        File archivoPalabrasProhibidas = new File(rutaArchivoPalabrasProhibidas);
        File archivoACorregir = new File(rutaArchivoACorregir);
        File archivoCorregido = new File(rutaArchivoACorregir.replace(".txt", "_palabrasProhibidasModificadas.txt"));
        
        BufferedReader lectorPalabrasProhibidas = new BufferedReader(new FileReader(archivoPalabrasProhibidas));
        BufferedReader lectorArchivoACorregir = new BufferedReader(new FileReader(archivoACorregir));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCorregido, true));
        
        String lineaEnPalabrasProhibidas = "";
        String lineaEnArchivoACorregir = "";
        
        while((lineaEnArchivoACorregir = lectorArchivoACorregir.readLine()) != null) {
            while((lineaEnPalabrasProhibidas = lectorPalabrasProhibidas.readLine()) != null) {
                String[] arrayPalabras = lineaEnPalabrasProhibidas.split(" ");
                String palabraProhibida = arrayPalabras[0];
                String palabraCorrecta = arrayPalabras[1];
                lineaEnArchivoACorregir = reemplazarPalabraProhibidaEnLinea(palabraProhibida, palabraCorrecta, lineaEnArchivoACorregir);
            }
            bw.write(lineaEnArchivoACorregir);
            bw.newLine();
            lineaEnPalabrasProhibidas = "";
            lectorPalabrasProhibidas = new BufferedReader(new FileReader(archivoPalabrasProhibidas));
        }
        lectorPalabrasProhibidas.close();
        lectorArchivoACorregir.close();
        bw.close();
        return archivoCorregido;
    }
    
    public String reemplazarPalabraProhibidaEnLinea(String palabraProhibida, String palabraCorrecta, String linea){
        if(linea.contains(palabraProhibida)) {
            linea = linea.replace(palabraProhibida, palabraCorrecta);
        }
        return linea;
    }
}
