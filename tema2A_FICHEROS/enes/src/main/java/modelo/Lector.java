/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author pablo
 */
public class Lector {
    public static void leerEnes(File archivo) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(archivo);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        
        int valorLeido = isr.read();
        char caracter = 0;

        while (valorLeido != -1) {
            System.out.println(valorLeido);
            System.out.println((char) valorLeido);
            valorLeido = isr.read();
        }
    }
}
