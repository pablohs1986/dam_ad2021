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
                if ((char) caracterLeido == 'a'|| (char) caracterLeido == 'á' || (char) caracterLeido == 'A' || (char) caracterLeido == 'Á') {
                    distribucionLetras.merge('a', 1, Integer::sum);
                }else if ((char) caracterLeido == 'b'|| (char) caracterLeido == 'B') {
                    distribucionLetras.merge('b', 1, Integer::sum);
                }else if ((char) caracterLeido == 'c'|| (char) caracterLeido == 'C') {
                    distribucionLetras.merge('c', 1, Integer::sum);
                }else if ((char) caracterLeido == 'd'|| (char) caracterLeido == 'D') {
                    distribucionLetras.merge('d', 1, Integer::sum);
                }else if ((char) caracterLeido == 'e'|| (char) caracterLeido == 'E' || (char) caracterLeido == 'é' || (char) caracterLeido == 'É') {
                    distribucionLetras.merge('e', 1, Integer::sum);
                }else if ((char) caracterLeido == 'f'|| (char) caracterLeido == 'F') {
                    distribucionLetras.merge('f', 1, Integer::sum);
                }else if ((char) caracterLeido == 'g'|| (char) caracterLeido == 'G') {
                    distribucionLetras.merge('g', 1, Integer::sum);
                }else if ((char) caracterLeido == 'h'|| (char) caracterLeido == 'H') {
                    distribucionLetras.merge('h', 1, Integer::sum);
                }else if ((char) caracterLeido == 'i'|| (char) caracterLeido == 'I'|| (char) caracterLeido == 'í'|| (char) caracterLeido == 'Í') {
                    distribucionLetras.merge('i', 1, Integer::sum);
                }else if ((char) caracterLeido == 'j'|| (char) caracterLeido == 'J') {
                    distribucionLetras.merge('j', 1, Integer::sum);
                }else if ((char) caracterLeido == 'k'|| (char) caracterLeido == 'K') {
                    distribucionLetras.merge('k', 1, Integer::sum);
                }else if ((char) caracterLeido == 'l'|| (char) caracterLeido == 'L') {
                    distribucionLetras.merge('l', 1, Integer::sum);
                }else if ((char) caracterLeido == 'm'|| (char) caracterLeido == 'M') {
                    distribucionLetras.merge('m', 1, Integer::sum);
                }else if ((char) caracterLeido == 'n'|| (char) caracterLeido == 'N') {
                    distribucionLetras.merge('n', 1, Integer::sum);
                }else if ((char) caracterLeido == 'o'|| (char) caracterLeido == 'O'|| (char) caracterLeido == 'ó'|| (char) caracterLeido == 'Ó') {
                    distribucionLetras.merge('o', 1, Integer::sum);
                }else if ((char) caracterLeido == 'p'|| (char) caracterLeido == 'P') {
                    distribucionLetras.merge('p', 1, Integer::sum);
                }else if ((char) caracterLeido == 'q'|| (char) caracterLeido == 'Q') {
                    distribucionLetras.merge('q', 1, Integer::sum);
                }else if ((char) caracterLeido == 'r'|| (char) caracterLeido == 'R') {
                    distribucionLetras.merge('r', 1, Integer::sum);
                }else if ((char) caracterLeido == 's'|| (char) caracterLeido == 'S') {
                    distribucionLetras.merge('s', 1, Integer::sum);
                }else if ((char) caracterLeido == 't'|| (char) caracterLeido == 'T') {
                    distribucionLetras.merge('t', 1, Integer::sum);
                }else if ((char) caracterLeido == 'u'|| (char) caracterLeido == 'U'|| (char) caracterLeido == 'ú'|| (char) caracterLeido == 'Ú')  {
                    distribucionLetras.merge('u', 1, Integer::sum);
                }else if ((char) caracterLeido == 'v'|| (char) caracterLeido == 'V') {
                    distribucionLetras.merge('v', 1, Integer::sum);
                }else if ((char) caracterLeido == 'w'|| (char) caracterLeido == 'W') {
                    distribucionLetras.merge('w', 1, Integer::sum);
                }else if ((char) caracterLeido == 'x'|| (char) caracterLeido == 'X') {
                    distribucionLetras.merge('x', 1, Integer::sum);
                }else if ((char) caracterLeido == 'y'|| (char) caracterLeido == 'Y') {
                    distribucionLetras.merge('y', 1, Integer::sum);
                }else if ((char) caracterLeido == 'z'|| (char) caracterLeido == 'Z') {
                    distribucionLetras.merge('z', 1, Integer::sum);
                }
            }
            
            fis.close();
            System.out.println(distribucionLetras);
            
        }else {
            throw new Exception("El archivo indicado no es un documento de texto. Operación cancelada.");
        }

    }
}
