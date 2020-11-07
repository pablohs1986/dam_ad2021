/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

/**
 * Clase con un método para calcular la distribución de las letras en un texto.
 *
 * @version 1.0, 06/11/2020
 * @author Pablo Herrero
 */
public class DistribucionLetras {

    /**
     * Calcula la distribución de las letras del archivo de texto que recibe
     * y cuenta el total de letras del mismo.
     *
     * @param archivoTexto Archivo de texto sobre el que realizar la acción.
     * @return Devuelve un HashMap con la distribución de letras del texto.
     * @throws java.lang.Exception si el archivo no es un archivo de texto.
     */
    public HashMap<Character, Integer> calcularDistribucionLetrasTexto(File archivoTexto) throws Exception {
        HashMap<Character, Integer> distribucionLetras = new HashMap<>();
        int totalLetras = 0;

        if (archivoTexto.getName().endsWith(".txt")) {                      // Compruebo si el archivo es un .txt. De ser así, prosigo, si no, lanzo excepción.
            FileInputStream fis = new FileInputStream(archivoTexto);        
            int caracterLeido;

            while ((caracterLeido = fis.read()) != -1) {                    // Recorro el stream, usando un switch para clasificar el caracter leido y asociarle una acción.
                switch ((char) caracterLeido) {
                    case 'a':
                        distribucionLetras.merge('a', 1, Integer::sum);     // En cada caso, añado al HashMap la Key (nombre de la letra) y el Value (conteo para esa letra).
                        totalLetras++;                                      // Uso el metodo .merge() porque me permite asignar un valor mínimo a la key si no existe, e ir incrementando su valor.
                        break;
                    case 'á':
                        distribucionLetras.merge('a', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'A':
                        distribucionLetras.merge('a', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'Á':
                        distribucionLetras.merge('a', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'b':
                        distribucionLetras.merge('b', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'B':
                        distribucionLetras.merge('b', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'c':
                        distribucionLetras.merge('c', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'C':
                        distribucionLetras.merge('c', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'd':
                        distribucionLetras.merge('d', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'D':
                        distribucionLetras.merge('d', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'e':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'é':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'E':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'É':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'f':
                        distribucionLetras.merge('f', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'F':
                        distribucionLetras.merge('f', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'g':
                        distribucionLetras.merge('g', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'G':
                        distribucionLetras.merge('g', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'h':
                        distribucionLetras.merge('h', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'H':
                        distribucionLetras.merge('h', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'i':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'í':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'I':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'Í':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'j':
                        distribucionLetras.merge('j', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'J':
                        distribucionLetras.merge('j', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'k':
                        distribucionLetras.merge('k', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'K':
                        distribucionLetras.merge('k', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'l':
                        distribucionLetras.merge('l', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'L':
                        distribucionLetras.merge('l', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'm':
                        distribucionLetras.merge('m', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'M':
                        distribucionLetras.merge('m', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'n':
                        distribucionLetras.merge('n', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'N':
                        distribucionLetras.merge('n', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'o':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'ó':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'O':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'Ó':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'p':
                        distribucionLetras.merge('p', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'P':
                        distribucionLetras.merge('p', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'q':
                        distribucionLetras.merge('q', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'Q':
                        distribucionLetras.merge('q', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'r':
                        distribucionLetras.merge('r', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'R':
                        distribucionLetras.merge('r', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 's':
                        distribucionLetras.merge('s', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'S':
                        distribucionLetras.merge('s', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 't':
                        distribucionLetras.merge('t', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'T':
                        distribucionLetras.merge('t', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'u':
                        distribucionLetras.merge('u', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'U':
                        distribucionLetras.merge('u', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'v':
                        distribucionLetras.merge('v', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'V':
                        distribucionLetras.merge('v', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'w':
                        distribucionLetras.merge('w', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'W':
                        distribucionLetras.merge('w', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'x':
                        distribucionLetras.merge('x', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'X':
                        distribucionLetras.merge('x', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'y':
                        distribucionLetras.merge('y', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'Y':
                        distribucionLetras.merge('y', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'z':
                        distribucionLetras.merge('z', 1, Integer::sum);
                        totalLetras++;
                        break;
                    case 'Z':
                        distribucionLetras.merge('z', 1, Integer::sum);
                        totalLetras++;
                        break;
                }
            }

            fis.close();
            System.out.println("\nCalculando la distribución de letras para el archivo " + "\"" + archivoTexto.getName() + "\"" + " con un total de " + totalLetras + " letras.");

        } else {
            throw new Exception("El archivo " + archivoTexto.getName() + " no es un documento de texto. Operación cancelada.");
        }
        return distribucionLetras;
    }
}
