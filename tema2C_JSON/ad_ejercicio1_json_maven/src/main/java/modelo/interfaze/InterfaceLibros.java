/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaze;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.JsonArray;
import javax.json.JsonObject;
import modelo.dto.Autor;
import modelo.dto.Genero;
import modelo.dto.Libro;

/**
 *
 * @author Pablo Herrero
 */
public interface InterfaceLibros {

    public JsonArray generarJsonArrayLibros(ArrayList<Libro> listaLibros);

    public JsonObject generarJsonObjectLibro(Libro libro);

    public JsonArray generarJsonArrayGeneros(ArrayList<Genero> generos);

    public JsonObject generarJsonObjectGenero(Genero genero);

    public JsonArray generarJsonArrayAutores(ArrayList<Autor> listaAutores);

    public JsonObject generarJsonObjectAutor(Autor autor);

    public void escribirArrayJsonEnFichero(JsonArray arrayJson, String nombreArchivoSalida) throws IOException;

    public JsonArray leerArchivoJson(String nombreArchivo) throws FileNotFoundException;

    public int contarLibros(JsonArray arrayLibros);

    public ArrayList<String> leerTitulosLibros(JsonArray arrayLibros);

    public String leerNombreAutor1DelLibro2(JsonArray arrayLibros);

    public int calcularValorLibrosStock(JsonArray arrayLibros);
}
