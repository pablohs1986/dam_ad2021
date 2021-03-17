/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Interfaces;

import javax.json.JsonArray;

/**
 *
 * @author Noja
 */
public interface Json_IO_Intrfz {
    
    public JsonArray leerJsonArchivo();
    
    public void escribirJson(JsonArray arrayLibros);
}
