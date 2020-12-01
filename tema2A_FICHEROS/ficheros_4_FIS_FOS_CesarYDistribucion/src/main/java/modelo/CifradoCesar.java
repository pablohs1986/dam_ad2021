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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Clase con un método que cifra los archivos de texto que se encuentran en el
 * directorio que se le pasa como parámetro.
 *
 * @version 1.3, 09/11/2020
 * @author Pablo Herrero
 */
public class CifradoCesar {

    /**
     * Cifra cada uno de los archivos de texto que se encuentran en el
     * directorio que recibe, guardándolos con un nuevo nombre.
     *
     * @param directorio Directorio sobre el que realizar la acción.
     * @param clave Valor numérico que indica la clave de desplazamiento del
     * cifrado cesar.
     */
    public void cifrarArchivosEnDirectorioConCesar(File directorio, int clave) {
        int contadorFicherosCifrados = 0;
        
        System.out.println("\nAccediendo al directorio " + "\"" + directorio.getName() + "\":");
        try {
            File[] listadoArchivosTexto = listarArchivosTextoEnDirectorio(directorio);
            for (File archivoTexto : listadoArchivosTexto) {                            // Itero sobre cada archivo de texto del directorio, cifrándolos.
                cifrarArchivoConCesar(archivoTexto, contadorFicherosCifrados, clave);
                contadorFicherosCifrados++;
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        System.out.println(contadorFicherosCifrados + " ficheros cifrados en el directorio \"" + directorio.getName() + "\".");
    }

    /**
     * Descifra cada uno de los archivos de texto cifrados que se encuentran en
     * el directorio que recibe, guardándolos con un nuevo nombre.
     *
     * @param directorio Directorio sobre el que realizar la acción.
     * @param clave Valor numérico que indica la clave de desplazamiento del
     * cifrado cesar.
     * @throws java.lang.Exception Si se detecta que el archivo sobre el que
     * itera no es un archivo cifrado.
     */
    public void descifrarArchivosEnDirectorioConCesar(File directorio, int clave) throws Exception {
        int contadorFicherosDescifrados = 0;
        
        System.out.println("\nAccediendo al directorio " + "\"" + directorio.getName() + "\":");
        try {
            File[] listadoArchivos = listarArchivosTextoEnDirectorio(directorio);
            for (File archivoTexto : listadoArchivos) {                             // Itero sobre cada archivo de texto del directorio, cifrándolos.
                if (comprobarSiEsUnArchivoCifrado(archivoTexto)) {
                    descifrarArchivoConCesar(archivoTexto, contadorFicherosDescifrados, clave);
                    contadorFicherosDescifrados++;
                } else {
                    throw new Exception("El archivo " + "\"" + archivoTexto.getName() + "\"" + "no está cifrado con Cesar. Archivo ignorado.");
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        System.out.println(contadorFicherosDescifrados + " ficheros descifrados en el directorio \"" + directorio.getName() + "\".");
    }

    /**
     * Cifra el archivo de texto que recibe.
     *
     * @param archivoTexto Archivo de texto sobre el que se realiza la acción.
     * @param numeroArchivo Id identificacivo para el archivo.
     * @param desplazamiento Valor numérico que indica la clave de
     * desplazamiento del cifrado cesar
     */
    public void cifrarArchivoConCesar(File archivoTexto, int numeroArchivo, int desplazamiento) {
        File archivoCifrado = new File("textos" + File.separator + "archivoCifrado" + numeroArchivo + ".txt");

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(archivoTexto));
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(archivoCifrado));
            int caracterLeido = 0;
            int caracterCifrado = 0;

            System.out.println("\t" + "Cifrando el archivo " + "\"" + archivoTexto.getName() + "\"" + "...");
            while (caracterLeido != -1) {                               // Itero sobre el archivo, comparando el caracter leido con su valor ASCII, 
                caracterLeido = reader.read();                          // aplicándole la clave de cifrado y guardándolo en un nuevo archivo.
                // Abecedario mayúsculas
                if (caracterLeido >= 65 && caracterLeido <= 90) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    if (caracterCifrado >= 90) {
                        caracterCifrado = caracterCifrado - 25;
                        writer.write(caracterCifrado);
                        writer.flush();
                    } else {
                        writer.write(caracterCifrado);
                        writer.flush();
                    }
                // Abecedario minúsculas
                } else if (caracterLeido >= 97 && caracterLeido <= 122) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    if (caracterCifrado >= 122) {
                        caracterCifrado = caracterCifrado - 25;
                        writer.write(caracterCifrado);
                        writer.flush();
                    } else {
                        writer.write(caracterCifrado);
                        writer.flush();
                    }
                // Ñ
                } else if (caracterLeido == 209) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    writer.write(caracterCifrado);
                } else if (caracterLeido == 241) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                // Acentos minúsculas
                } else if (caracterLeido == 225) {
                    caracterCifrado = 97 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 233) {
                    caracterCifrado = 101 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 237) {
                    caracterCifrado = 105 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 243) {
                    caracterCifrado = 111 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 250) {
                    caracterCifrado = 117 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                // Acentos mayúsculas
                } else if (caracterLeido == 193) {
                    caracterCifrado = 65 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 201) {
                    caracterCifrado = 69 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 205) {
                    caracterCifrado = 73 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 211) {
                    caracterCifrado = 79 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 218) {
                    caracterCifrado = 85 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                // Resto caracteres
                } else {
                    caracterCifrado = caracterLeido;
                    writer.write(caracterCifrado);
                    writer.flush();
                }
            }
            System.out.println("\t\"" + archivoTexto.getName() + "\"" + " cifrado. Guardado como " + "\"" + archivoCifrado.getName() + "\"");
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    /**
     * Descifra el archivo de texto que recibe.
     *
     * @param archivoTexto Archivo de texto sobre el que se realiza la acción.
     * @param numeroArchivo Id identificacivo para el archivo.
     * @param desplazamiento Valor numérico que indica la clave de
     * desplazamiento del cifrado cesar
     */
    public void descifrarArchivoConCesar(File archivoTexto, int numeroArchivo, int desplazamiento) {
        File archivoDescifrado = new File("textos" + File.separator + "archivoDescifrado" + numeroArchivo + ".txt");

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(archivoTexto));
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(archivoDescifrado));
            int caracterLeido = 0;
            int caracterCifrado = 0;

            System.out.println("\t" + "Descifrando el archivo " + "\"" + archivoTexto.getName() + "\"" + "...");

            while (caracterLeido != -1) {                               // Itero sobre el archivo, comparando el caracter leido con su valor ASCII, 
                caracterLeido = reader.read();                          // aplicándole la clave de cifrado y guardándolo en un nuevo archivo.

                // Abecedario mayúsculas
                if (caracterLeido >= 65 && caracterLeido <= 90) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    if (caracterCifrado >= 90) {
                        caracterCifrado = caracterCifrado - 25;
                        writer.write(caracterCifrado);
                        writer.flush();
                    } else {
                        writer.write(caracterCifrado);
                        writer.flush();
                    }
                // Abecedario minúsculas
                } else if (caracterLeido >= 97 && caracterLeido <= 122) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    if (caracterCifrado >= 122) {
                        caracterCifrado = caracterCifrado - 25;
                        writer.write(caracterCifrado);
                        writer.flush();
                    } else {
                        writer.write(caracterCifrado);
                        writer.flush();
                    }
                // Ñ
                } else if (caracterLeido == 209) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    writer.write(caracterCifrado);
                } else if (caracterLeido == 241) {
                    caracterCifrado = caracterLeido + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                // Acentos minúsculas
                } else if (caracterLeido == 225) {
                    caracterCifrado = 97 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 233) {
                    caracterCifrado = 101 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 237) {
                    caracterCifrado = 105 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 243) {
                    caracterCifrado = 111 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 250) {
                    caracterCifrado = 117 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                // Acentos mayúsculas
                } else if (caracterLeido == 193) {
                    caracterCifrado = 65 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 201) {
                    caracterCifrado = 69 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 205) {
                    caracterCifrado = 73 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 211) {
                    caracterCifrado = 79 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                } else if (caracterLeido == 218) {
                    caracterCifrado = 85 + desplazamiento;
                    writer.write(caracterCifrado);
                    writer.flush();
                // Resto caracteres
                } else {
                    caracterCifrado = caracterLeido;
                    writer.write(caracterCifrado);
                    writer.flush();
                }
            }
            System.out.println("\t\"" + archivoTexto.getName() + "\"" + " descifrado. Guardado como " + "\"" + archivoDescifrado.getName() + "\"");
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    /**
     * Lista los archivos de texto que se encuentran en el directorio que se le
     * pasa.
     *
     * @param directorio Directorio sobre el que se realiza la acción.
     * @return Array de archivos de texto.
     * @throws java.lang.Exception Si no se encuentran archivos de texto en el
     * directorio indicado.
     */
    public static File[] listarArchivosTextoEnDirectorio(File directorio) throws Exception {
        File[] listadoArchivosTexto = null;
        
        if (directorio.isDirectory()) {
            listadoArchivosTexto = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".txt");
                }
            });

            if (listadoArchivosTexto.length == 0) {
                throw new Exception("En el directorio " + directorio.getName() + " no hay archivos de texto.");
            }
        } else {
            throw new Exception("La ruta indicada no es un directorio. Operaci\u00f3n cancelada");
        }
        return listadoArchivosTexto;
    }

    /**
     * Comprueba si el archivo que se le pasa es un archivo cifrado.
     *
     * @param archivo Directorio sobre el que se realiza la acción.
     * @return True si el archivo es un archivo cifrado.
     */
    public boolean comprobarSiEsUnArchivoCifrado(File archivo) {
        return archivo.getName().startsWith("archivoCifrado");
    }
}
