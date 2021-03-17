/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonObject;
import modelo.MetodosLibros;
import modelo.dto.Autor;
import modelo.dto.Genero;
import modelo.dto.Libro;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            MetodosLibros metodos = new MetodosLibros();
            JsonArray arrayPrincipalLibros = metodos.leerArchivoJson("libros.json");
            
            /* APARTADO A */
            // Genero datos usando clases DTO
            ArrayList<Libro> listaLibros = new ArrayList<>();
            
            // Libro 1
            ArrayList<Autor> listaAutoresLibro1 = new ArrayList<>();
            ArrayList<Genero> listaGenerosLibro1 = new ArrayList<>();
            
            Autor autor1libro1 = new Autor("Javier", "Pérez");
            Autor autor2libro1 = new Autor("María", "Rodriguez");
            
            listaAutoresLibro1.add(autor1libro1);
            listaAutoresLibro1.add(autor2libro1);
            
            Genero genero1libro1 = new Genero("novela");
            Genero genero2libro1 = new Genero("ficción");
            
            listaGenerosLibro1.add(genero1libro1);
            listaGenerosLibro1.add(genero2libro1);
            
            Libro libro1 = new Libro("Sueños IA", 210, 10, listaAutoresLibro1, listaGenerosLibro1);
            
            // Libro 2
            ArrayList<Autor> listaAutoresLibro2 = new ArrayList<>();
            ArrayList<Genero> listaGenerosLibro2 = new ArrayList<>();
            
            Autor autor1libro2 = new Autor("Ana", "Cota");
            Autor autor2libro2 = new Autor("Mar", "Fernández");
            
            listaAutoresLibro2.add(autor1libro1);
            listaAutoresLibro2.add(autor2libro1);
            
            Genero genero1libro2 = new Genero("informática");
            Genero genero2libro2 = new Genero("JSON");
            
            listaGenerosLibro2.add(genero1libro1);
            listaGenerosLibro2.add(genero2libro1);
            
            Libro libro2 = new Libro("JSON para todos", 310, 20, listaAutoresLibro1, listaGenerosLibro1);
            
            // Añado ambos libros a la lista
            listaLibros.add(libro1);
            listaLibros.add(libro2);
            
            // Creo arrayJson
            JsonArray arrayJsonLibros = metodos.generarJsonArrayLibros(listaLibros);
            
            // Almaceno en disco
            try {
                metodos.escribirArrayJsonEnFichero(arrayJsonLibros, "librosSALIDA.json");
                
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* APARTADO B */
            int totalLibros = metodos.contarLibros(arrayPrincipalLibros);
            System.out.println("Total libros: " + totalLibros);
            
            /* APARTADO C */
            ArrayList<String> listaTitulosLibros = metodos.leerTitulosLibros(arrayPrincipalLibros);
            System.out.println("Titulos:");
            for (String titulo : listaTitulosLibros) {
                System.out.println("\t" + titulo);
            }
            
            /* APARTADO D */
            String nombreAutor1Libro2 = metodos.leerNombreAutor1DelLibro2(arrayPrincipalLibros);
            System.out.println("Nombre del autor 1 del libro 2: " + nombreAutor1Libro2);
            
            /* APARTADO E */
            int valorLibros = metodos.calcularValorLibrosStock(arrayJsonLibros);
            System.out.println("Valor de todos los libros: " + valorLibros);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
