/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.interfaze.InterfaceBiblioteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que implementa los métodos del interface InterfaceBiblioteca
 * junto a métodos auxiliares.
 * 
 * @author Pablo Herrero
 */
public class MetodosBiblioteca implements InterfaceBiblioteca {
    
    /**
     * Método que retorna un ResultSet con todos los libros pasando un String
     * con la query.
     *
     * @param query
     * @return ResultSet
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public ResultSet retornarLibrosConQuery(String query) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        Statement statement = conexion.createStatement();
        ResultSet libros = statement.executeQuery(query);
        return libros;
    }
    
    /**
     * Método que retorna un ResultSet con todos los libros, permitiendo
     * ordenar el resultado.
     *
     * @param ordenarPorNombre
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public ResultSet retornarLibros(Boolean ordenarPorNombre) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        Statement statement = conexion.createStatement();
        Statement statementOrdenados = conexion.createStatement();
        
        ResultSet libros = statement.executeQuery("SELECT * FROM libro");
        ResultSet librosOrdenadosPorNombre = statementOrdenados.executeQuery("SELECT * FROM libro ORDER BY titulo ASC");
        return ordenarPorNombre ? librosOrdenadosPorNombre : libros;
    }
    
    /**
     * Método que permite insertar un libro pasando parámetros.
     *
     * @param isbn
     * @param titulo
     * @param autor
     * @param numeroPaginas
     * @param fechaPublicacion
     * @param prestadoSiNo
     * @param fechaDevoluion
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void insertarLibroConParametros(String isbn, String titulo, String autor, int numeroPaginas, String fechaPublicacion, Boolean prestadoSiNo, String fechaDevoluion) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        Statement statement = conexion.createStatement();
        
        statement.executeUpdate(
                "INSERT into libro (isbn, titulo, autor, numeroPaginas, fechaPublicacion, prestadoSiNo, fechaDevolucion)" 
                + " VALUES ('"+isbn+"', '"+titulo+"', '"+autor+"', "+numeroPaginas+", '"+fechaPublicacion+"', "+prestadoSiNo+", '"+fechaDevoluion+"')");
        statement.close();
    }
    /**
     * Método que permite insertar un libro a partir de una lista que recibe.
     *
     * @param datosLibro
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public void insertarLibro(ArrayList<String> datosLibro) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        Statement statement = conexion.createStatement();
        
        statement.executeUpdate(
                "INSERT into libro (isbn, titulo, autor, numeroPaginas, fechaPublicacion, prestadoSiNo, fechaDevolucion)" 
                + " VALUES ('"+datosLibro.get(0)+"', '"+datosLibro.get(1)+"', '"+datosLibro.get(2)+"', "
                        + ""+Integer.valueOf(datosLibro.get(3))+", '"+datosLibro.get(4)+"', "+datosLibro.get(5)+
                        ", '"+datosLibro.get(6)+"')");
        statement.close();
    }
    
    /**
     * Método que permite eliminar un libro introduciendo su isbn
     * o su título.
     *
     * @param isbnOTitulo 
     * @param isIsbn 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public void eliminarLibro(String isbnOTitulo, Boolean isIsbn) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        Statement statement = conexion.createStatement();
        
        if(isIsbn) {
            statement.executeUpdate(
                    "DELETE from libro WHERE isbn = '" + isbnOTitulo + "'");
        } else {
            statement.executeUpdate(
                    "DELETE from libro WHERE titulo = '" + isbnOTitulo + "'");
        }
        statement.close();
    }
    
    /**
     * Método que retorna los libros de un determinado autor.
     *
     * @param autor
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public ResultSet retornarLibrosPorAutor(String autor) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        PreparedStatement psLibrosPorAutor = conexion.prepareCall("SELECT * FROM libro WHERE autor =  ?");
        psLibrosPorAutor.setString(1, autor);
        ResultSet librosPorAutor = psLibrosPorAutor.executeQuery();
        return librosPorAutor;
    }
    
    /**
     * Método que retorna los nombres de los socios junto a los títulos
     * de los libros prestados asociados al mismo.
     *
     * @param codigoSocio
     * @param ordenarPorTitulo
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public ResultSet retornarNombresSociosYTitulosPrestados(String codigoSocio, Boolean ordenarPorTitulo) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        Statement statement = conexion.createStatement();
        Statement statementOrdenados = conexion.createStatement();
        
        ResultSet nombresSociosYTitulos = statement.executeQuery(
                "SELECT socio.nombreSocio, libro.titulo FROM socio JOIN prestamo "
                        + "ON socio.codigoSocio = prestamo.codigoSocio JOIN libro "
                        + "ON prestamo.libro_isbn = libro.isbn WHERE socio.codigoSocio = " + codigoSocio);
        ResultSet nombresSociosYTitulosOrdenados = statementOrdenados.executeQuery(
                "SELECT socio.nombreSocio, libro.titulo FROM socio JOIN prestamo "
                        + "ON socio.codigoSocio = prestamo.codigoSocio JOIN libro "
                        + "ON prestamo.libro_isbn = libro.isbn WHERE socio.codigoSocio = " 
                        + codigoSocio + " ORDER BY libro.titulo");
        return ordenarPorTitulo ? nombresSociosYTitulosOrdenados : nombresSociosYTitulos;
    }
    
    /**
     * Método que retorna los nombres de los socios que tienen un determinado
     * libro en préstamo.
     *
     * @param tituloOIsbn
     * @param isIsbn
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public ResultSet retornarNombresSociosPorLibroPrestado(String tituloOIsbn, boolean isIsbn) throws ClassNotFoundException, SQLException {
        Connection conexion = this.conexion();
        String tipoConsulta = isIsbn ? "isbn" : "titulo";  
        
        PreparedStatement psNombresSociosPorLibro = conexion.prepareCall(
                "SELECT socio.nombreSocio FROM socio JOIN prestamo "
                        + "ON socio.codigoSocio = prestamo.codigoSocio JOIN libro "
                        + "ON prestamo.libro_"+tipoConsulta+" = libro."+tipoConsulta+" WHERE libro."+tipoConsulta+" = ?");
        psNombresSociosPorLibro.setString(1, tituloOIsbn);
        
        ResultSet nombresSociosPorLibroIsbn = psNombresSociosPorLibro.executeQuery();
        return nombresSociosPorLibroIsbn;
    }
    
    /**
     * Método que muestra el ResultSet que recibe.
     *
     * @param resultSet
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public void mostrarResultSet(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int numeroColumnas = rsmd.getColumnCount();
        
        while(resultSet.next()) {
            for (int i = 1; i <= numeroColumnas; i++) {
                String valorColumna = resultSet.getString(i);
                System.out.print(valorColumna + " | " );
            }
            System.out.println("");
        }
    }
        
    /**
     * Método que realiza la conexión a la BBDD MySql.
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    private Connection conexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String cadenaConexion = "jdbc:mysql://localhost:3306/biblioteca";
        String user = "root";
        String pass = "9874";
        Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
        return conexion;
    }
}
