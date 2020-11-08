/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
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
            File[] listadoArchivosTexto = listarArchivosTextoEnDirectorio(directorio);
            for (File archivoTexto : listadoArchivosTexto) {
                cifrarArchivoConCesar(archivoTexto, contadorFicherosCifrados, clave);
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
            File[] listadoArchivos = listarArchivosTextoEnDirectorio(directorio);
            for (File archivoTexto : listadoArchivos) {
                if (archivoTexto.getName().startsWith("archivoCifrado")) {
                    descifrarArchivoConCesar(archivoTexto, contadorFicherosDescifrados, clave);
                    contadorFicherosDescifrados++;
                } else {
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
            InputStreamReader reader = new InputStreamReader(new FileInputStream(archivoTexto));
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(archivoCifrado));
            
            int caracterLeido = 0;
            int caracterCifrado = 0;
            
            while (caracterLeido != -1) {
                caracterLeido = reader.read();
                
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
                    caracterCifrado = 78 + desplazamiento;
                    writer.write(caracterCifrado);
                } else if (caracterLeido == 241) {
                    caracterCifrado = 110 + desplazamiento;
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
//        try {
//            FileInputStream fis = new FileInputStream(archivoTexto);
////            FileOutputStream fos = new FileOutputStream(archivoCifrado, true);
//
//            System.out.println("\n" + "Cifrando el archivo " + "\"" + archivoTexto.getName() + "\"" + "...");
//
//            int codigoAsciiLeido = 0;
//            int codigoAsciiCifrado = 0;
//            char caracterLeido;
//
//            while (codigoAsciiLeido != -1) {
//                codigoAsciiLeido = fis.read();
//                System.out.println("ascii leido " + codigoAsciiLeido);
//                
//                caracterLeido = (char) codigoAsciiLeido;
//                System.out.println("caracter leido " + caracterLeido);
//
//
////                fos.write(caracterCifrado);
////                fos.flush();
//            }
//
//            System.out.println("\"" + archivoTexto.getName() + "\"" + " cifrado. Guardado como " + "\"" + archivoCifrado.getName() + "\"");
//
//            fis.close();
////            fos.close();
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void cifrarArchivoConCesar(File archivoTexto, int numeroArchivo, int desplazamiento) {
//        File archivoCifrado = new File("textos" + File.separator + "archivoCifrado" + numeroArchivo + ".txt");
//
//        try {
//            FileInputStream fis = new FileInputStream(archivoTexto);
//            FileOutputStream fos = new FileOutputStream(archivoCifrado, true);
//
//            System.out.println("\n" + "Cifrando el archivo " + "\"" + archivoTexto.getName() + "\"" + "...");
//
//            int caracterLeido;
//            int caracterCifrado;
//
//            while ((caracterLeido = fis.read()) != -1) {
//                caracterCifrado = caracterLeido + desplazamiento;
//                fos.write(caracterCifrado);
//                fos.flush();
//            }
//
//            System.out.println("\"" + archivoTexto.getName() + "\"" + " cifrado. Guardado como " + "\"" + archivoCifrado.getName() + "\"");
//
//            fis.close();
//            fos.close();
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(CifradoCesar.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

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

}
