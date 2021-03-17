/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import DTO.*;
import Modelo.connect.ConexionMySQL;
import java.util.ArrayList;

/**
 *
 * @author Noja
 */
public class OperacionesMySql {

    ConexionMySQL conex;
int z;
int j;
    public OperacionesMySql() {
        this.conex = new ConexionMySQL();
        z = 0;
        j=0;
    }

    public String insertarLibro(ArrayList<Libro> listaLibros) {
        for (int i = 0; i < listaLibros.size(); i++) {
            String titulo = listaLibros.get(i).getTitulo();
            int numeroPaginas = listaLibros.get(i).getTotalPaginas();
            int precio = listaLibros.get(i).getPrecio();
            String query = "insert into Libro values (" + i + ",'" + titulo + "'," + precio + "," + numeroPaginas + ");";
            conex.hacerInsert(query);
            ArrayList<Autor> listaAutores = (ArrayList<Autor>) listaLibros.get(i).getListaAutores();
            this.insertarAutor(listaAutores, i);
            ArrayList<Genero> listaGeneros = (ArrayList<Genero>) listaLibros.get(i).getListageneros();
            this.insertarGeneros(listaGeneros, i);

        }
        return "La inserción ha sido correcta!!!!!!!!!!!!";
    }

    public void insertarGeneros(ArrayList<Genero> listaGeneros, int fk_libro) {
       
        for (Genero genero : listaGeneros) {
            String query = "insert into Generos values (" + z + ",'" + genero.getGenero() + "'," + fk_libro + ");";
            conex.hacerInsert(query);
            z++;
        }
    }

    public void insertarAutor(ArrayList<Autor> listaAutores, int fk_libro) {        
        for (Autor autor : listaAutores) {
            String query = "insert into Autor values (" + j + ",'" + autor.getNombre() + "','" + autor.getApellido() + "'," + fk_libro + ");";
            conex.hacerInsert(query);
            j++;
        }
    }

}
