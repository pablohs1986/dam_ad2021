/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author Pablo Herrero
 */
public class Metodos {

    /**
     * Genera un archivo .json con los json creados
     *
     * @throws java.io.IOException
     */
    public static void generarFicheroJson() throws IOException {
        // Libro 1    
        JsonObject libroJson1 = Json.createObjectBuilder()
                .add("titulo", "Sueños IA")
                .add("totalPaginas", 210)
                .add("precio", 10)
                .add("autores", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("nombre", "Javier")
                                .add("apellido", "Pérez"))
                        .add(Json.createObjectBuilder()
                                .add("nombre", "María")
                                .add("apellido", "Rodriguez")))
                .add("generos", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("genero", "novela"))
                        .add(Json.createObjectBuilder()
                                .add("genero", "ficción")))
                .build();

        // Libro 2   
        JsonObject libroJson2 = Json.createObjectBuilder()
                .add("titulo", "JSON para todos")
                .add("totalPaginas", 310)
                .add("precio", 20)
                .add("autores", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("nombre", "Ana")
                                .add("apellido", "Cota"))
                        .add(Json.createObjectBuilder()
                                .add("nombre", "Mar")
                                .add("apellido", "Fernández")))
                .add("generos", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("genero", "informática"))
                        .add(Json.createObjectBuilder()
                                .add("genero", "JSON")))
                .build();

        // Array con ambos libros
        JsonArray arrayJson = Json.createArrayBuilder().add(libroJson1).add(libroJson2).build();

        // Salida
        FileWriter ficheroSalida = new FileWriter("salidaLibros.json");
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJson);
        ficheroSalida.flush();
        ficheroSalida.close();
    }

    /**
    * Cuenta el número de libros del fichero libros.json.
    *
    * @return Total de libros
    * @throws java.io.FileNotFoundException
    */
    public static int contarLibros() throws FileNotFoundException {
        FileReader fr = new FileReader("libros.json");
        JsonReader jr = Json.createReader(fr);
        JsonArray arrayLibros = jr.readArray();

        return arrayLibros.size();
    }

    /**
    * Muestra el título de los libros del fichero libros.json
    *
    * @throws java.io.FileNotFoundException
    */
    public static void mostrarTitulosLibros() throws FileNotFoundException {
        FileReader fr = new FileReader("libros.json");
        JsonReader jr = Json.createReader(fr);
        JsonArray arrayLibros = jr.readArray();

        System.out.println("Títulos:");
        for (int i = 0; i < arrayLibros.size(); i++) {
            String titulo = arrayLibros.getJsonObject(i).getString("titulo");
            System.out.println("\t " + titulo);
        }
    }

    /**
    * Muestra el nombre del autor 1 del libro 2 del archivo libros.json
    *
    * @throws java.io.FileNotFoundException
    */
    public static void mostrarNombreAutor1DelLibro2() throws FileNotFoundException {
        FileReader fr = new FileReader("libros.json");
        JsonReader jr = Json.createReader(fr);
        JsonArray arrayLibros = jr.readArray();

        for (int i = 0; i < arrayLibros.size(); i++) {
            if (i == 1) {
                JsonArray arrayAutoresLibro2 = arrayLibros.getJsonObject(i).getJsonArray("autores");
                for (int j = 0; j < arrayAutoresLibro2.size(); j++) {
                    if (j == 0) {
                        String nombre = arrayAutoresLibro2.getJsonObject(j).getString("nombre");
                        System.out.println("Nombre del autor 1 del libro 2: " + nombre);
                    }
                }
            }
        }
    }

    /**
    * Calcula el valor de los libros en stock del fichero libros.json
    *
    * @return Valor de los libros
    * @throws java.io.FileNotFoundException
    */
    public static int calcularValorLibrosStock() throws FileNotFoundException {
        FileReader fr = new FileReader("libros.json");
        JsonReader jr = Json.createReader(fr);
        JsonArray arrayLibros = jr.readArray();
        int valorLibros = 0;

        for (int i = 0; i < arrayLibros.size(); i++) {
            JsonNumber precioLibro = arrayLibros.getJsonObject(i).getJsonNumber("precio");
            valorLibros += precioLibro.intValue();
        }

        return valorLibros;
    }
}
