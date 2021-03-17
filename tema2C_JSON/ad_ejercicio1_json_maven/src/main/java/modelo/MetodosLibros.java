/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.interfaze.InterfaceLibros;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import modelo.dto.Autor;
import modelo.dto.Genero;
import modelo.dto.Libro;

/**
 *
 * @author Pablo Herrero
 */
public class MetodosLibros implements InterfaceLibros {

    /**
     * Genera un JsonArray a partir de la lista de Libro que recibe.
     *
     * @param listaLibros
     * @return JsonArray de Libro con la información de los libros.
     */
    @Override
    public JsonArray generarJsonArrayLibros(ArrayList<Libro> listaLibros) {
        JsonArrayBuilder constructorArray = Json.createArrayBuilder();
        JsonArray jsonArrayLibros;

        for (int i = 0; i < listaLibros.size(); i++) {
            constructorArray.add(this.generarJsonObjectLibro(listaLibros.get(i)));
        }

        jsonArrayLibros = constructorArray.build();
        return jsonArrayLibros;
    }

    /**
     * Genera un JsonObject a partir del Libro que recibe.
     *
     * @param libro
     * @return JsonObject de Libro con la información del libro.
     */
    @Override
    public JsonObject generarJsonObjectLibro(Libro libro) {

        JsonObject jsonObjectLibro = Json.createObjectBuilder()
                .add("titulo", libro.getTitulo())
                .add("totalPaginas", libro.getTotalPaginas())
                .add("precio", libro.getPrecio())
                .add("autores", generarJsonArrayAutores(libro.getListaAutores()))
                .add("generos", generarJsonArrayGeneros(libro.getListaGeneros()))
                .build();
        return jsonObjectLibro;
    }

    /**
     * Genera un JsonArray a partir de la lista de Genero que recibe.
     *
     * @param generos
     * @return JsonArray de Genero con la información de los géneros.
     */
    @Override
    public JsonArray generarJsonArrayGeneros(ArrayList<Genero> generos) {
        JsonArrayBuilder constructorArray = Json.createArrayBuilder();
        JsonArray arrayGeneros;

        for (int i = 0; i < generos.size(); i++) {
            constructorArray.add(this.generarJsonObjectGenero(generos.get(i)));
        }

        arrayGeneros = constructorArray.build();
        return arrayGeneros;
    }

    /**
     * Genera un JsonObject a partir del Genero que recibe
     *
     * @param genero
     * @return JsonObject de Genero con la información del género.
     */
    @Override
    public JsonObject generarJsonObjectGenero(Genero genero) {
        JsonObject jsonObjectGenero = Json.createObjectBuilder()
                .add("genero", genero.getGenero())
                .build();
        return jsonObjectGenero;
    }

    /**
     * Genera un JsonArray a partir de la lista de Autor que recibe.
     *
     * @param listaAutores
     * @return JsonArray de Autor con la información de los autores.
     */
    @Override
    public JsonArray generarJsonArrayAutores(ArrayList<Autor> listaAutores) {
        JsonArrayBuilder constructorArray = Json.createArrayBuilder();
        JsonArray arrayAutores;

        for (int i = 0; i < listaAutores.size(); i++) {
            constructorArray.add(this.generarJsonObjectAutor(listaAutores.get(i))); // Coge un autor de la lista, genera el jsonObject y lo añade al array
        }

        arrayAutores = constructorArray.build();
        return arrayAutores;
    }

    /**
     * Genera un JsonObject a partir del Autor que recibe.
     *
     * @param autor
     * @return JsonObject Autor con la información del autor.
     */
    @Override
    public JsonObject generarJsonObjectAutor(Autor autor) {
        JsonObject jsonObjectAutor = Json.createObjectBuilder()
                .add("nombre", autor.getNombre())
                .add("apellido", autor.getApellido())
                .build();
        return jsonObjectAutor;
    }

    /**
     * Escribe en un archivo con el nombre que se le pasa como parámetro el
     * JsonArray que recibe.
     *
     * @param arrayJson
     * @param nombreArchivoSalida
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void escribirArrayJsonEnFichero(JsonArray arrayJson, String nombreArchivoSalida) throws IOException {
        FileWriter ficheroSalida = new FileWriter(nombreArchivoSalida);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJson);
        ficheroSalida.flush();
        ficheroSalida.close();
    }

    /**
     * Lee el archivo json que recibe y devuelve un JsonArray.
     *
     * @param nombreArchivo
     * @return JSonArray con la información del archivo json.
     * @throws java.io.FileNotFoundException
     */
    @Override
    public JsonArray leerArchivoJson(String nombreArchivo) throws FileNotFoundException {
        FileReader fr = new FileReader(nombreArchivo);
        JsonReader jr = Json.createReader(fr);
        JsonArray jsonArrayLibros = jr.readArray();
        return jsonArrayLibros;
    }

    /**
     * Calcula el número total de libros y devuelve su valor.
     *
     * @param arrayLibros
     * @return Número total de libros.
     */
    @Override
    public int contarLibros(JsonArray arrayLibros) {
        return arrayLibros.size();
    }

    /**
     * Lee el valor de los títulos de los libros y los retorna en una lista.
     *
     * @param arrayLibros
     * @return Lista con el valor de los títulos de los libros.
     */
    @Override
    public ArrayList<String> leerTitulosLibros(JsonArray arrayLibros) {
        ArrayList<String> listaTitulosLibros = new ArrayList<>();

        for (int i = 0; i < arrayLibros.size(); i++) {
            String titulo = arrayLibros.getJsonObject(i).getString("titulo");
            listaTitulosLibros.add(titulo);
        }
        return listaTitulosLibros;
    }

    /**
     * Lee el valor del nombre del autor 1 del libro 2 y retorna su valor.
     *
     * @param arrayLibros
     * @return Valor del nombre del autor 1 del libro 2.
     */
    @Override
    public String leerNombreAutor1DelLibro2(JsonArray arrayLibros) {
        String nombreAutor1Libro2 = null;

        for (int i = 0; i < arrayLibros.size(); i++) {
            if (i == 1) {
                JsonArray arrayAutoresLibro2 = arrayLibros.getJsonObject(i).getJsonArray("autores");
                for (int j = 0; j < arrayAutoresLibro2.size(); j++) {
                    if (j == 0) {
                        nombreAutor1Libro2 = arrayAutoresLibro2.getJsonObject(j).getString("nombre");
                    }
                }
            }
        }
        return nombreAutor1Libro2;
    }

    /**
     * Calcula el valor de los libros en stock del fichero libros.json y lo
     * retorna
     *
     * @param arrayLibros
     * @return Valor de los libros
     */
    @Override
    public int calcularValorLibrosStock(JsonArray arrayLibros) {
        int valorLibros = 0;

        for (int i = 0; i < arrayLibros.size(); i++) {
            JsonNumber precioLibro = arrayLibros.getJsonObject(i).getJsonNumber("precio");
            valorLibros += precioLibro.intValue();
        }
        return valorLibros;
    }
}
