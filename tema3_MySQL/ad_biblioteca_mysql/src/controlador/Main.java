/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MetodosBiblioteca;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodosBiblioteca metodos = new MetodosBiblioteca();
        try {
            // APARTADO 1
            // Retorna todos los libros
            ResultSet libros = metodos.retornarLibros(true);
            
            // Devolver un ResultSet con los datos de los libros y visualizarlos en otro método
//            metodos.mostrarResultSet(libros);
            
            // APARTADO 2
            // Insertar un libro
            ArrayList<String> libro1 = new ArrayList<>();
            ArrayList<String> libro2 = new ArrayList<>();
            
            libro1.add("000000035");
            libro1.add("Docker para todos");
            libro1.add("JL");
            libro1.add("900");
            libro1.add("2020-10-10");
            libro1.add("1");
            libro1.add("2021-03-10");
            
            libro2.add("000000024");
            libro2.add("Jaibernate para todos");
            libro2.add("JL");
            libro2.add("600");
            libro2.add("2020-10-10");
            libro2.add("0");
            libro2.add("2021-03-10");

            metodos.insertarLibro(libro1);
            metodos.insertarLibro(libro2);
            // APARTADO 3
            // Eliminar un libro por id o por título.
//            metodos.eliminarLibro("000000005", true);
//            metodos.eliminarLibro("PSP para todos", false);

            // APARTADO 4
            // Seleccionar todos los libros de un autor usando un PreparedStatement
//            ResultSet librosPorAutor = metodos.retornarLibrosPorAutor("Robert Martin");
//            metodos.mostrarResultSet(librosPorAutor);
            
            // APARTADO 5
            // Retorna todos los nombres de los socios seguidos los títulos prestados.  (Devolver un ResultSet y visualizarlo en otro método),  también los puede devolver ordenados por titulo si el usuario lo desea.
//            ResultSet sociosYTitulosPrestados = metodos.retornarNombresSociosYTitulosPrestados("1", true);
//            metodos.mostrarResultSet(sociosYTitulosPrestados);

            // APARTADO 6
            // Retorna todos los nombres de los socios que tienen un determinado libro prestado, por título o ISBN (usar PreparedStatement). 
//            ResultSet nombresSociosPorLibro = metodos.retornarNombresSociosPorLibroPrestado("000000004", true);
//            metodos.mostrarResultSet(nombresSociosPorLibro);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
