/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaze;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface con los métodos del ejercicio.
 * 
 * @author Pablo Herrero
 */
public interface InterfaceBiblioteca {

    public ResultSet retornarLibrosConQuery(String query) throws ClassNotFoundException, SQLException;

    public ResultSet retornarLibros(Boolean ordenarPorNombre) throws ClassNotFoundException, SQLException;

    public void insertarLibro(ArrayList<String> datosLibro) throws ClassNotFoundException, SQLException;

    public void eliminarLibro(String isbnOTitulo, Boolean isIsbn) throws ClassNotFoundException, SQLException;

    public ResultSet retornarLibrosPorAutor(String autor) throws ClassNotFoundException, SQLException;

    public ResultSet retornarNombresSociosYTitulosPrestados(String codigoSocio, Boolean ordenarPorTitulo) throws ClassNotFoundException, SQLException;

    public ResultSet retornarNombresSociosPorLibroPrestado(String tituloOIsbn, boolean isIsbn) throws ClassNotFoundException, SQLException;

    public void mostrarResultSet(ResultSet libros) throws ClassNotFoundException, SQLException;
}
