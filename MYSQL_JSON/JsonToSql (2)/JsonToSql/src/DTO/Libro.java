/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noja
 */
public class Libro {
    
    private String titulo;
    private int totalPaginas;
    private int precio;
    private List <Genero> listageneros=new ArrayList<>();
    private List <Autor> listaAutores=new ArrayList<>();

    public Libro() {
    }
    

    public Libro(String titulo, int totalPaginas, int precio) {
        this.titulo = titulo;
        this.totalPaginas = totalPaginas;
        this.precio = precio;
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

    public List<Genero> getListageneros() {
        return listageneros;
    }

    public void setListageneros(List<Genero> listageneros) {
        this.listageneros = listageneros;
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(List<Autor> listaAutores) {
        this.listaAutores = listaAutores;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", totalPaginas=" + totalPaginas + ", precio=" + precio + ", listageneros=" + listageneros + ", listaAutores=" + listaAutores + '}';
    }
    
    public void addGenero(Genero genero){
        this.listageneros.add(genero);
    }   
    
    public void addAutor(Autor autor){
        this.listaAutores.add(autor);
    }
    
            
    
}
