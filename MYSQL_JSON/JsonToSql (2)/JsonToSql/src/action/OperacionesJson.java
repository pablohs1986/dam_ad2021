/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import DTO.Autor;
import DTO.Genero;
import DTO.Libro;
import Modelo.Json_IO;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Noja
 */
public class OperacionesJson {

    Json_IO entrada;

    public OperacionesJson() {
        this.entrada = new Json_IO();
    }

    public ArrayList<Libro> getListaLibros() {
        ArrayList<Libro> listaLibros = new ArrayList<>();
        JsonArray arrayJson = entrada.leerJsonArchivo();
        for (int i = 0; i < arrayJson.size(); i++) {
            JsonObject libroJson = arrayJson.getJsonObject(i);
            Libro libroDTO = this.jsonToLibroDto(libroJson);
            listaLibros.add(libroDTO);
        }
        return listaLibros;
    }

    public Libro jsonToLibroDto(JsonObject libroJson) {
        Libro libroDTO = new Libro();
        int totalpaginas = libroJson.getInt("totalPaginas");
        String titulo = libroJson.getString("titulo");
        int precio = libroJson.getInt("precio");
        JsonArray arrayAutores = libroJson.getJsonArray("autores");
        libroDTO.setListaAutores(this.getAutores(arrayAutores));
        JsonArray arrayGeneros = libroJson.getJsonArray("generos");
        libroDTO.setListageneros(this.getGeneros(arrayGeneros));
        libroDTO.setPrecio(precio);
        libroDTO.setTitulo(titulo);
        libroDTO.setTotalPaginas(totalpaginas);
        return libroDTO;

    }

    public ArrayList<Autor> getAutores(JsonArray arrayAutores) {
        ArrayList<Autor> listaAutores=new ArrayList<>();
        Autor autorDTO = new Autor();
        for (int j = 0; j < arrayAutores.size(); j++) {
            JsonObject autorJson = arrayAutores.getJsonObject(j);
            String nombre = autorJson.getString("nombre");
            String apellido = autorJson.getString("apellido");
            autorDTO = new Autor(nombre, apellido);
            listaAutores.add(autorDTO);
        }
        return listaAutores;
    }

    public ArrayList<Genero> getGeneros(JsonArray arrayGeneros) {
        ArrayList<Genero> listaGeneros=new ArrayList<>();
        Genero generoDTO = new Genero();
        for (int j = 0; j < arrayGeneros.size(); j++) {
            JsonObject generoJson = arrayGeneros.getJsonObject(j);
            String genero = generoJson.getString("genero");
            generoDTO = new Genero(genero);
            listaGeneros.add(generoDTO);

        }
        return listaGeneros;
    }

}
