/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.util.ArrayList;

/**
 *
 * @author Pablo Herrero
 */
public class Libro {
    
    private String titulo;
    private int totalPaginas;
    private int precio;
    private ArrayList<Autor> listaAutores;
    private ArrayList<Genero> listaGeneros;

    public Libro(String titulo, int totalPaginas, int precio, ArrayList<Autor> autores, ArrayList<Genero> generos) {
        this.titulo = titulo;
        this.totalPaginas = totalPaginas;
        this.precio = precio;
        this.listaAutores = autores;
        this.listaGeneros = generos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArrayList<Autor> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(ArrayList<Autor> listaAutores) {
        this.listaAutores = listaAutores;
    }

    public ArrayList<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(ArrayList<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", totalPaginas=" + totalPaginas + ", precio=" + precio + ", listaAutores=" + listaAutores + ", listaGeneros=" + listaGeneros + '}';
    }

}
