/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.List;
import javax.json.JsonObject;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Pablo Herrero
 */
public interface Interface {
    
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException;

    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException;

    public JsonObject crearDireccion(String calle, int numero, int piso, String escalera, int cp, String ciudad);
    
    public JsonObject crearCliente(String nombre, String apellido1, String apellido2, List<JsonObject> direcciones, int telefono);
}
