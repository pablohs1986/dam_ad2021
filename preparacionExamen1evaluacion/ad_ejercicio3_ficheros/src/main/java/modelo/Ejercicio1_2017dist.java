/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Pablo Herrero
 */
public class Ejercicio1_2017dist {
    
    public void copiarLineasMalFormadas(ArrayList<File> listadoArchivos) throws FileNotFoundException, IOException {
        File archivoDestino = new File("frasesMalFormadas.txt");
        for (File archivo : listadoArchivos) {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(archivoDestino);
            BufferedWriter bw = new BufferedWriter(fw);
            String linea;
            
            while ((linea = br.readLine()) != null) {
                if ((comprobarSiLineaTieneTodosLosDatos(linea)==false) || (comprobarNifBienFormado(linea)==false) || (comprobarTelefonoBienFormado(linea)==false)) {
                    bw.write(linea);
                    bw.newLine();
                } 
            }
            bw.close();
            br.close();
        }
    }
    
    private boolean comprobarSiLineaTieneTodosLosDatos(String linea) {
        boolean flag = true;
        StringTokenizer tokens = new StringTokenizer(linea, ",");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (token.equals("")) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }
    
    private boolean comprobarNifBienFormado(String linea) {
        boolean flag = true;
        StringTokenizer tokens = new StringTokenizer(linea, ",");
        while (tokens.hasMoreTokens()) {
            String nombre = tokens.nextToken();
            String apellido = tokens.nextToken();
            String nif = tokens.nextToken();
            String telefono = tokens.nextToken();
            
            for (int i = 0; i < 9; i++) {
                int numeroDigitos = Character.digit(nif.charAt(i), 8);
                int numeroLetras = 0;
                
                if (Character.isLetter(nif.charAt(i))) {
                    numeroLetras++;
                }
                if (numeroDigitos != 8 || numeroLetras != 2 || numeroLetras != 1) {
                    flag = false;
                }
            }
        }
        return flag;
    }
    
    private boolean comprobarTelefonoBienFormado(String linea) {
        boolean flag = true;
        StringTokenizer tokens = new StringTokenizer(linea, ",");
        while (tokens.hasMoreTokens()) {
            String nombre = tokens.nextToken();
            String apellido = tokens.nextToken();
            String nif = tokens.nextToken();
            String telefono = tokens.nextToken();
            
            for (int i = 0; i < 9; i++) {
                int numeroDigitos = Character.digit(telefono.charAt(i), 9);
                if (numeroDigitos != 9) {
                    flag = false;
                }
            }
            if (telefono.startsWith("6") || telefono.startsWith("9")) {
                flag = false;
            }
        }
        return flag;
    }
    
    public ArrayList<File> listarArchivosTxtCsvQueEmpiezanPorVocal(String rutaDirectorio) throws Exception {
        File directorio = new File(rutaDirectorio);
        ArrayList<File> listadoArchivos = new ArrayList<>();
        
        if (directorio.isDirectory()) {
            File[] arrayArchivos = directorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".csv") || name.endsWith(".txt");
                }
            });
            
            String[] vocales = {"a", "e", "i", "o", "u"};
            for (File archivo : arrayArchivos) {
                for (String vocal : vocales) {
                    if (archivo.getName().startsWith(vocal, 0)) {
                        listadoArchivos.add(archivo);
                    }
                }
            }
        } else {
            throw new Exception("Error, la ruta indicada no es un directorio. Operación cancelada.");
        }
        return listadoArchivos;
    }
}
