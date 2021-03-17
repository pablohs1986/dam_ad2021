/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Interfaces.Json_IO_Intrfz;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author Noja
 */
public class Json_IO implements Json_IO_Intrfz{
    
    /**
     * Método que lee un archivo Json y devuelve un JsonArray con el contenido
     * 
     * @return JsonArray
     */
    
    @Override
    public JsonArray leerJsonArchivo(){
        FileReader entrada = null;
        JsonArray objectPrincipal = null;
        try {
            entrada = new FileReader("libros.json");
            JsonReader jsonReader = Json.createReader(entrada);
            objectPrincipal = jsonReader.readArray();           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Json_IO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                entrada.close();
            } catch (IOException ex) {
                Logger.getLogger(Json_IO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objectPrincipal;
    }
    
    
    /**
     * Método que recibe un JsonArray de libros y lo escribe en un archivo de salida
     * 
     * @param arrayLibros 
     */
    @Override
    public void escribirJson(JsonArray arrayLibros)
    {
       FileWriter ficheroSalida = null;
        try {
            ficheroSalida = new FileWriter("SalidaJson.json");
            JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
            jsonWriter.writeArray(arrayLibros); 
            ficheroSalida.flush();
           
        } catch (IOException ex) {
            Logger.getLogger(Json_IO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroSalida.close();
            } catch (IOException ex) {
                Logger.getLogger(Json_IO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
