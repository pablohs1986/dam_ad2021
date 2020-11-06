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
 *
 * @author Pablo Herrero
 */
public class DistribucionLetras {
    public void calcularDistribucionLetrasTexto(File archivoTexto) throws Exception {
        HashMap<Character, Integer> distribucionLetras = new HashMap<Character, Integer>();
        
        if(archivoTexto.getName().endsWith(".txt")) {
            System.out.println("\nMostrando la distribución de letras para el archivo " + archivoTexto.getName() + ":");
            FileInputStream fis = new FileInputStream(archivoTexto);
            
            int caracterLeido;
            
            while((caracterLeido = fis.read()) != -1) {
                switch((char) caracterLeido) {
                    case 'a':
                        distribucionLetras.merge('a', 1, Integer::sum);
                        break;
                    case 'á':
                        distribucionLetras.merge('a', 1, Integer::sum);
                        break;
                    case 'A':
                        distribucionLetras.merge('a', 1, Integer::sum);
                        break;
                    case 'Á':
                        distribucionLetras.merge('a', 1, Integer::sum);
                        break;
                    case 'b':
                        distribucionLetras.merge('b', 1, Integer::sum);
                        break;
                    case 'B':
                        distribucionLetras.merge('b', 1, Integer::sum);
                        break;
                    case 'c':
                        distribucionLetras.merge('c', 1, Integer::sum);
                        break;
                    case 'C':
                        distribucionLetras.merge('c', 1, Integer::sum);
                        break;
                    case 'd':
                        distribucionLetras.merge('d', 1, Integer::sum);
                        break;
                    case 'D':
                        distribucionLetras.merge('d', 1, Integer::sum);
                        break;
                    case 'e':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        break;
                    case 'é':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        break;
                    case 'E':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        break;
                    case 'É':
                        distribucionLetras.merge('e', 1, Integer::sum);
                        break;
                    case 'f':
                        distribucionLetras.merge('f', 1, Integer::sum);
                        break;
                    case 'F':
                        distribucionLetras.merge('f', 1, Integer::sum);
                        break;
                    case 'g':
                        distribucionLetras.merge('g', 1, Integer::sum);
                        break;
                    case 'G':
                        distribucionLetras.merge('g', 1, Integer::sum);
                        break;
                    case 'h':
                        distribucionLetras.merge('h', 1, Integer::sum);
                        break;
                    case 'H':
                        distribucionLetras.merge('h', 1, Integer::sum);
                        break;
                    case 'i':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        break;
                    case 'í':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        break;
                    case 'I':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        break;
                    case 'Í':
                        distribucionLetras.merge('i', 1, Integer::sum);
                        break;
                    case 'j':
                        distribucionLetras.merge('j', 1, Integer::sum);
                        break;
                    case 'J':
                        distribucionLetras.merge('j', 1, Integer::sum);
                        break;
                    case 'k':
                        distribucionLetras.merge('k', 1, Integer::sum);
                        break;
                    case 'K':
                        distribucionLetras.merge('k', 1, Integer::sum);
                        break;
                    case 'l':
                        distribucionLetras.merge('l', 1, Integer::sum);
                        break;
                    case 'L':
                        distribucionLetras.merge('l', 1, Integer::sum);
                        break;
                    case 'm':
                        distribucionLetras.merge('m', 1, Integer::sum);
                        break;
                    case 'M':
                        distribucionLetras.merge('m', 1, Integer::sum);
                        break;
                    case 'n':
                        distribucionLetras.merge('n', 1, Integer::sum);
                        break;
                    case 'N':
                        distribucionLetras.merge('n', 1, Integer::sum);
                        break;
                    case 'o':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        break;
                    case 'ó':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        break;
                    case 'O':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        break;
                    case 'Ó':
                        distribucionLetras.merge('o', 1, Integer::sum);
                        break;
                    case 'p':
                        distribucionLetras.merge('p', 1, Integer::sum);
                        break;
                    case 'P':
                        distribucionLetras.merge('p', 1, Integer::sum);
                        break;
                    case 'q':
                        distribucionLetras.merge('q', 1, Integer::sum);
                        break;
                    case 'Q':
                        distribucionLetras.merge('q', 1, Integer::sum);
                        break;
                    case 'r':
                        distribucionLetras.merge('r', 1, Integer::sum);
                        break;
                    case 'R':
                        distribucionLetras.merge('r', 1, Integer::sum);
                        break;
                    case 's':
                        distribucionLetras.merge('s', 1, Integer::sum);
                        break;
                    case 'S':
                        distribucionLetras.merge('s', 1, Integer::sum);
                        break;
                    case 't':
                        distribucionLetras.merge('t', 1, Integer::sum);
                        break;
                    case 'T':
                        distribucionLetras.merge('t', 1, Integer::sum);
                        break;
                    case 'u':
                        distribucionLetras.merge('u', 1, Integer::sum);
                        break;
                    case 'U':
                        distribucionLetras.merge('u', 1, Integer::sum);
                        break;
                    case 'v':
                        distribucionLetras.merge('v', 1, Integer::sum);
                        break;
                    case 'V':
                        distribucionLetras.merge('v', 1, Integer::sum);
                        break;
                    case 'w':
                        distribucionLetras.merge('w', 1, Integer::sum);
                        break;
                    case 'W':
                        distribucionLetras.merge('w', 1, Integer::sum);
                        break;
                    case 'x':
                        distribucionLetras.merge('x', 1, Integer::sum);
                        break;
                    case 'X':
                        distribucionLetras.merge('x', 1, Integer::sum);
                        break;
                    case 'y':
                        distribucionLetras.merge('y', 1, Integer::sum);
                        break;
                    case 'Y':
                        distribucionLetras.merge('y', 1, Integer::sum);
                        break;
                    case 'z':
                        distribucionLetras.merge('z', 1, Integer::sum);
                        break;
                    case 'Z':
                        distribucionLetras.merge('z', 1, Integer::sum);
                        break;
                }
            }
            
            fis.close();
            System.out.println(distribucionLetras);
            
        }else {
            throw new Exception("El archivo indicado no es un documento de texto. Operación cancelada.");
        }

    }
}
